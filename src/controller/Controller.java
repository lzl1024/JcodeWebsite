package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.ProfileDAO;
import model.UserDAO;

import databeans.Profile;
import databeans.User;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public void init() throws ServletException {
    	
    
        Model model = new Model(getServletConfig());   
        UserDAO userDAO = model.getUserDAO();
        ProfileDAO profileDAO = model.getProfileDAO();
        
        Action.add(new LoginAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new ManageAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new OnlineJudgeAction(model));
        Action.add(new PostBlogAction(model));
        Action.add(new ViewAction(model));
        Action.add(new YourBlogAction(model));
        Action.add(new AllBlogAction(model));
        Action.add(new SearchAction(model));
        Action.add(new CommentAction(model));
        Action.add(new EditBlogAction(model));
        Action.add(new DeleteBlogAction(model));
        Action.add(new ChangePwdAction(model));
        Action.add(new ViewProfileAction(model));
        Action.add(new ImageAction(model));
        Action.add(new EditProfileAction(model));
        
        try {
			if(userDAO.read("admin@admin") == null) {
				adduser(userDAO, "admin", "1", "admin@admin");			
			}
			//Create the profile bean
	    	Profile profile = setProfileDefault("admin@admin", "admin");
	    	profileDAO.create(profile);
		} catch (RollbackException e) {
			e.printStackTrace();
		}
        
      
    }
    
    void adduser(UserDAO userDAO, String uname, String ps, String email) {
       	 User user = new User();
   	     user.setUserName(uname);
   	     user.setPassword(ps);
   	     user.setEmail(email);
   	     try {
   			userDAO.create(user);
   		} catch (RollbackException e) {
   			e.printStackTrace();
   		}
     }    
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);

        
        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);
        if (action.equals("register.do") || action.equals("login.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
         
        
        if (user == null && !action.equals("manage.do") && !action.equals("view.do") 
        	&& !action.equals("allblog.do") && !action.equals("viewprofile.do") && !action.equals("image.do")) {
        	// If the user hasn't logged in, direct him to the login page
	      //  session.setAttribute("last", action);
			return Action.perform("login.do",request);
        }
        
        
        /*
        if (user != null && action.equals("oj.do")) {
        	// If user logged in and click online judge system
        	return Action.perform("oj.do",request);
        }*/
        

      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	if (nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
    
    private Profile setProfileDefault(String email, String userName) {
    	Profile p = new Profile();
    	BufferedImage image;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/images/1.jpg"));    	
	    	ImageIO.write(image, "jpg", out);	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		p.setBytes(out.toByteArray());
		p.setPictype("jpg");
		p.setEmail(email);
		p.setInterest("I just like code");
		p.setOccupation("No code no job");
		p.setRealName(userName);
		p.setStatus("Watching best demo!");
		p.setIntroduction("I am too lazy to intro myself");
		
    	
    	return p;
    }
}