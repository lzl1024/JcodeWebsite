package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.User;
import formbeans.LoginForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do
 */
public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        String status = null;
        

       
        try {
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("loginForm",form);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	status = "Information is incomplete.";
	        	request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            return "login.jsp";
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	status = "notValidate";
	        	request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            return "login.jsp";
	        }

	        // Look up the user
	        User user = userDAO.read(form.getEmail());
	        
	        if (user == null) {
	        	status = "noEmail";
	            errors.add("Email not found");
	            request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            return "login.jsp";
	        }

	        // Check the password
	        if (!user.checkPassword(form.getPassword())) {
	        	status = "incorrectPwd";
	            errors.add("Incorrect password");
	            request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        status = "success";
	        HttpSession session = request.getSession();
	        session.setAttribute("user",user);
	        
	      /*  String lastpage;
	        if ((lastpage = (String) session.getAttribute("last"))!= null) {
	        	session.removeAttribute("last");
	        	return lastpage;
	        }*/
	        	
	        System.out.println("login status: " + status);
	        request.setAttribute("errors",errors);
	        request.setAttribute("status",status);
	        return "login.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}