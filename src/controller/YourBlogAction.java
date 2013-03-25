package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.Blog;
import databeans.User;



public class YourBlogAction extends Action {

	private BlogDAO  blogDAO;

    public YourBlogAction(Model model) {
    	blogDAO = model.getBlogDAO();
	}

    public String getName() { return "yourblog.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
	    	
	       	if (user == null) {
    			errors.add("Invalid User! ");
    			return "error.jsp";
    		}
	       	
			Blog[] blogs = blogDAO.getBlogs(user.getEmail());
			request.setAttribute("bloglist",blogs);
			
	        return "list.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}