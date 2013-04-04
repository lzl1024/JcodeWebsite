package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.ProfileDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Profile;
import databeans.User;
import formbeans.RegisterForm;

/*
 * Processes the parameters from the form in register.jsp.
 * If successful:
 *   (1) creates a new User bean
 *   (2) sets the "user" session attribute to the new User bean
 */
public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

	private UserDAO userDAO;
	private ProfileDAO profileDAO;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
		profileDAO = model.getProfileDAO();
	}

	public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
			
	        if (form.getUse() == null || form.getUse().equals("1")) {
	            return "register.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        errors.addAll(primarykeyError(userDAO, form.getEmail(), form.getUserName()));
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	
	        // Create the user bean
	        User user = new User();
	        user.setUserName(form.getUserName());
	        user.setPassword(form.getPassword());
	        user.setEmail(form.getEmail());
	        user.setUserGroup("user");
        	userDAO.create(user);
        	
        	//Create the profile bean
        	Profile profile = setProfileDefault(form.getEmail(), form.getUserName());
        	profileDAO.create(profile);
        	       	
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	        
			return "manage.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
    
    private List<String> primarykeyError(UserDAO userDAO, String email, String username) {
    	List<String> errors = new ArrayList<String>();
    		
    	// if exist email
    	try {
    		// if exist username
    		if (userDAO.read(email) != null) {
    			errors.add("This Email has already been registered");
        		return errors;
        	}
    		
			User[] usergp = userDAO.match();
			
			for (User user: usergp) {
				if (user.getUserName().equals(username)){
					errors.add("This User Name has already been registered");
		    		return errors;
				}
			}
			
		} catch (RollbackException e) {
			e.printStackTrace();
		}
    	
    	return errors;
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
