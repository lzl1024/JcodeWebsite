package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.CommentDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;
import databeans.Comment;
import databeans.User;

import formbeans.IdForm;

public class ViewBlogAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private BlogDAO  blogDAO;
	private UserDAO	 userDAO;
	private CommentDAO commentDAO;
	
    public ViewBlogAction(Model model) {
    	blogDAO  = model.getBlogDAO();
    	userDAO  = model.getUserDAO();
    	commentDAO = model.getCommentDAO();
	}

    public String getName() { return "viewblog.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			IdForm form = formBeanFactory.create(request);
			
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
        
    		int id = form.getIdAsInt();
    		Blog p = blogDAO.read(id);
    		if (p == null) {
    			errors.add("No blog with id="+id);
    			return "error.jsp";
    		}
    		
    		User u = userDAO.read(p.getEmail());
			Blog[] archives = blogDAO.getBlogs(u.getEmail());

    		
    		request.setAttribute("blog",p);  
    		request.setAttribute("blogOwner",u); 
    		request.setAttribute("archives",archives);  


			Comment[] comments = commentDAO.getComments(p.getId());
			request.setAttribute("commentlist",comments);
			String begin;
			if((begin = request.getParameter("begin")) == null) {
				request.setAttribute("begin",1);
			}else {
				int b = Integer.parseInt(begin);
				if (b <= 0) b = 1;
				request.setAttribute("begin", b);
			}
    		
            return "viewblog.jsp";
		}catch (NumberFormatException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
