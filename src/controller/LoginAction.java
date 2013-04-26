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

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
    	System.out.println("Login: recevied ");
    	
        List<String> errors = new ArrayList<String>();
        String status = null;
        String msg = null;
       
        try {
	    	LoginForm form = formBeanFactory.create(request);
	    	System.out.println(form.getEmail());
	    	System.out.println(form.getPassword());

	        request.setAttribute("loginForm",form);
	
	        if (!form.isPresent()) {
	        	status = "fail";
	        	msg = "Information is incomplete.";
	        	request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            request.setAttribute("msg",msg);
	            return "ajax.jsp";
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	status = "fail";
	        	msg = "notValidate";
	        	request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            request.setAttribute("msg",msg);
	            
		        System.out.println("login status: " + msg);

	            return "ajax.jsp";
	        }

	        // Look up the user
	        User user = userDAO.read(form.getEmail());
	        
	        if (user == null) {
	        	status = "fail";
	        	msg = "noEmail";
	            errors.add("Email not found");
	            request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            request.setAttribute("msg",msg);
	            
		        System.out.println("login status: " + msg);

	            return "ajax.jsp";
	        }

	        // Check the password
	        if (!user.checkPassword(form.getPassword())) {
	        	status = "fail";
	        	msg = "incorrectPwd";
	            errors.add("Incorrect password");
	            request.setAttribute("errors",errors);
	            request.setAttribute("status",status);
	            request.setAttribute("msg",msg);
	            
		        System.out.println("login status: " + msg);

	            return "ajax.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        status = "success";
	        msg = "success";
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
            request.setAttribute("msg",msg);
	        return "ajax.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}