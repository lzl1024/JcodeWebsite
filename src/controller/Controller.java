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

import model.BlogDAO;
import model.Model;
import model.ProblemDAO;
import model.ProfileDAO;
import model.UserDAO;

import databeans.Blog;
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
        BlogDAO blogDAO = model.getBlogDAO();
        
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
        Action.add(new ViewProblemAction(model));
        Action.add(new UploadPhotoAction(model));
        Action.add(new ChangePhotoAction(model));
        
        // add an admin
        try {
			if(userDAO.read("admin@admin") == null) {
				adduser(userDAO, "admin", "1", "admin@admin", "admin");
				adduser(userDAO, "lzl", "1", "lzl@com", "user");
				adduser(userDAO, "qm", "1", "qm@com", "user");
			
				//Create the profile bean
				setProfileDefault(profileDAO, "admin@admin", "admin","jpg","/images/1.jpg");
				setProfileDefault(profileDAO, "lzl@com", "lzl","jpg","/images/lzl.jpg");
				setProfileDefault(profileDAO, "qm@com", "qm","jpg","/images/mao.jpg");	
				
				
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
        
        // add a demo problem
        try {
			if(problemDAO.match() == null || problemDAO.match().length == 0) {
				addProblem(problemDAO, "Demo problem", "This is a demo problem, please write a program printing \"Hello World\"", "public class Source {\r\n  public void hello() {\r\n    //TO DO\r\n  }\r\n}", 
						"public class Test {\r\n  public static void main(String[] args) {\r\n   Source s = new Source();\r\n    s.hello();\r\n  }\r\n}", "Hello World");
				
				addProblem(problemDAO, "Sum of all numbers from 1 to 1000", "This is a warm-up problem. Please write a problem calculating the sum of all numbers from 1 to 1000 (inclusive).", "public class Source {\r\n  public int sum() {\r\n    //TO DO\r\n  }\r\n}", 
						"public class Test {\r\n  public static void main(String[] args) {\r\n   Source s = new Source();\r\n    if(s.sum() == 500500) System.out.println(1);\r\n    \r\n  }\r\n}", "1");
				
				addProblem(problemDAO, "Balanced Binary Tree", "Given a binary tree, determine if it is height-balanced. \r\n\r\nFor this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.\r\n", 
						"public class Source {\r\n  public boolean isBalanced(TreeNode root) {\r\n    //TO DO\r\n  }\r\n\r\nclass TreeNode {\r\n  int val;\r\n  TreeNode left;\r\n  TreeNode right;\r\n  TreeNode(int x) { val = x; }\r\n}", 
						"public class Test {\r\n  public static void main(String[] args) {\r\n   TreeNode n1 = new TreeNode(1);\r\n    TreeNode n2 = new TreeNode(2);\r\n    TreeNode n3 = new TreeNode(3);\r\n    TreeNode n4 = new TreeNode(4);\r\n    TreeNode n5 = new TreeNode(5);\r\n    TreeNode root = n1;\r\n    " +
						"n1.left = n2;\r\n    n1.right = n3;\r\n    n3.right = n4;\r\n    n4.left = n5;\r\n    Source s = new Source();\r\n    if(s.isBalanced(root) == false) System.out.println(1);\r\n    \r\n  }\r\n}", "1");
				

			}
			
			if(blogDAO.match() == null || blogDAO.match().length == 0){
				//Create default blog
				User user = userDAO.read("admin@admin");
				addBlog(blogDAO, user, "Welcome to Jcode!", "Welcome to Jcode!\r\n\r\n This is programmer's home.");
			
				user = userDAO.read("lzl@com");
				addBlog(blogDAO, user, "Apple releases revised plans for its 'spaceship' headquarters",
						"As Apple waits for the city of Cupertino to give the final thumbs up for the design of its new spaceship-like headquarters, the company has been tinkering with the details.\r\n\r\n"+
"Cupertino published the software giant's project update of the headquarters on the city Web site on Wednesday. Included in the documents are the project description, site and landscaping plans, floor plans, renderings, and a bicycle plan.\r\n\r\n"+
"While a ton of information was submitted, it appears that the basic essentials haven't changed too much. The building still looks like a circular spaceship; in fact, many of the renderings in the new packet are the same as past iterations. It also has the same specifications: it will be 2.8 million square feet, sit up to 14,200 employees, and have a copious amount of trees.");
			
				addBlog(blogDAO, user, "iPhone should come to the big screen in the fall",
"Tim Cook likes to talk in religous tones about Apple's higher calling. Speaking at the Goldman Sachs Technology conference last month, Apple's CEO laid out his credo, noting that the only thing the company will never do is produce \"a crappy product.\"\r\n\r\n"+
"\"That's the only religion that we have,\" Cook said. \"We must do something bold, something ambitious, something great for the customers, and we sweat all of the details.\"\r\n\r\n" +
"In the meantime, customers and investors are waiting for Apple to do something bold and ambitious again, and Cook is calling out the competitors' products as wanting.\r\n\r\n" +
"When questioned Tuesday during the second-quarter earnings call about Apple's stance on offering an iPhone with a 5-inch screen, he offered this revealing glimpse into his thinking:");
				
				
				
				user = userDAO.read("qm@com");
				addBlog(blogDAO, user, "MacBook Pro declared 'best performing' Windows laptop",
"An Apple 13-inch MacBook Pro is the \"best performing\" Windows laptop? Yes, says a PC services company that has done \"frustration analytics\" on some of the best-selling PCs.\r\n\r\n"+
"The MacBook Pro won out over established PC makers like Dell, Acer, and Lenovo, according to Soluto, which was quick to explain its finding.");
				
				addBlog(blogDAO, user, "Auctioning Tim Cook: Winner gets coffee with Apple's CEO",
"Apple CEO Tim Cook is selling his time, and it's worth $50,000.\r\n\r\n"+
"Cook is auctioning off a coffee meeting with himself at Apple's headquarters in Cupertino, Calif., to raise money for the Robert F. Kennedy Center for Justice and Human Rights. Nine bids have already been submitted on charity auction site Charitybuzz, with the current highest at $10,500.\r\n\r\n"+
"Charitybuzz is calling the item a \"power meeting\" that lets a lucky winner \"nab one-on-one access to one of the most powerful figures in tech and business.\" The meeting can run between 30 minutes and one hour.");
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
      
    }
    
    void addProblem(ProblemDAO problemDAO, String title, String content, String startCode, String testCode, String referRes) throws UnsupportedEncodingException {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
		Date curDate = new Date(System.currentTimeMillis()); 
		
		Problem problem = new Problem();
		problem.setDate(formatter.format(curDate));
		problem.setContent((fixBadChars(content)).getBytes("Unicode"));
		problem.setTitle(fixBadChars(title));
		problem.setCommentNum(0);
		problem.setStartCode((startCode).getBytes("Unicode"));
		problem.setTestCode((testCode).getBytes("Unicode"));
		problem.setReferRes((referRes).getBytes("Unicode"));
		try {
			problemDAO.create(problem);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void addBlog(BlogDAO blogDAO, User user, String title, String content) {
		Blog blog = new Blog();  // id & position will be set when created
		
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
		Date curDate = new Date(System.currentTimeMillis()); 
		blog.setDate(formatter.format(curDate));
		try {
			blog.setContent(content.getBytes("Unicode"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		blog.setTitle(title);
		blog.setUser(user.getUserName());
		blog.setEmail(user.getEmail());
		blog.setCommentNum(0);
		try {
			blogDAO.create(blog);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
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
    
    private void setProfileDefault(ProfileDAO profileDAO, String email, String userName, String type, String pic) {
    	Profile p = new Profile();
    	BufferedImage image;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
		try { 
			image = ImageIO.read(getClass().getResourceAsStream(pic));    	
	    	ImageIO.write(image, type, out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		p.setBytes(out.toByteArray());
		p.setPictype(type);
		p.setEmail(email);
		p.setInterest("I just like code");
		p.setOccupation("No code no job");
		p.setRealName(userName);
		p.setStatus("Watching best demo!");
		p.setIntroduction("I am too lazy to intro myself");
		
		try {
   			profileDAO.create(p);
   		} catch (RollbackException e) {
   			e.printStackTrace();
   		}
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