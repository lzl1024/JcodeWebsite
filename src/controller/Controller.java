package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.ProblemDAO;
import model.ProfileDAO;
import model.UserDAO;

import databeans.Problem;
import databeans.Profile;
import databeans.User;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public void init() throws ServletException {
    	
    
        Model model = new Model(getServletConfig());   
        UserDAO userDAO = model.getUserDAO();
        ProblemDAO problemDAO = model.getProblemDAO();
        ProfileDAO profileDAO = model.getProfileDAO();
        
        Action.add(new LoginAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new ManageAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new OnlineJudgeAction(model));
        Action.add(new PostBlogAction(model));
        Action.add(new ViewBlogAction(model));
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
        Action.add(new PostProblemAction(model));
        Action.add(new AllProblemAction(model));
        Action.add(new EditProblemAction(model));
        Action.add(new ManageProblemAction(model));
        Action.add(new DeleteProblemAction(model));
        Action.add(new CommentProblemAction(model));
        Action.add(new MyStatisticAction(model));
        Action.add(new TopCoderAction(model));
        Action.add(new SearchProblemAction(model));
        
        // add an admin
        try {
			if(userDAO.read("admin@admin") == null) {
				adduser(userDAO, "admin", "1", "admin@admin", "admin");			
			
				//Create the profile bean
				Profile profile = setProfileDefault("admin@admin", "admin");
				profileDAO.create(profile);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
        
        // add a demo problem
        try {
			if(problemDAO.match() == null || problemDAO.match().length == 0) {
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
				Date curDate = new Date(System.currentTimeMillis()); 
				
				Problem problem = new Problem();
				problem.setDate(formatter.format(curDate));
				problem.setContent((fixBadChars("This is a demo problem, please write a problem printing \"Hello World\"")).getBytes("Unicode"));
				problem.setTitle(fixBadChars("Demo problem"));
				problem.setCommentNum(0);
				problem.setStartCode(("public class Source {\r\n  public void hello() {\r\n    //TO DO\r\n  }\r\n}").getBytes("Unicode"));
				problem.setTestCode(("public class Test {\r\n  public static void main(String[] args) {\r\n   Source s = new Source();\r\n    s.hello();\r\n  }\r\n}").getBytes("Unicode"));
				problem.setReferRes(("Hello World").getBytes("Unicode"));
				problemDAO.create(problem);

			}
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
      
    }
    
    void adduser(UserDAO userDAO, String uname, String ps, String email, String group) {
       	 User user = new User();
   	     user.setUserName(uname);
   	     user.setPassword(ps);
   	     user.setEmail(email);
   	     user.setUserGroup(group);
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

        if(action.equals("registerPage.do")) {
        	//direct to register jsp
        	return "register.jsp";
        }
        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);
        if (action.equals("register.do") || action.equals("login.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
         
        
        if (user == null && !action.equals("manage.do") && !action.equals("view.do") 
        	&& !action.equals("allblog.do") && !action.equals("viewprofile.do") && !action.equals("image.do")) {
        	// If the user hasn't logged in, direct him to the login page
	      //  session.setAttribute("last", action);
			return "login.jsp";
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
    
    private String fixBadChars(String s) {
		if (s == null || s.length() == 0) return s;
		
		Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                		   break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }
        
        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}