package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;

import formbeans.IdForm;

public class ViewAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private BlogDAO  blogDAO;
	
    public ViewAction(Model model) {
    	blogDAO  = model.getBlogDAO();
	}

    public String getName() { return "view.do"; }

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
    		
    		request.setAttribute("blog",p);    		
    		
            return "view.jsp";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
