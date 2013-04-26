package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.ProfileDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Profile;
import databeans.User;
import formbeans.UploadPhotoForm;

public class UploadPhotoAction extends Action {
	private FormBeanFactory<UploadPhotoForm> formBeanFactory = FormBeanFactory.getInstance(UploadPhotoForm.class);

	private ProfileDAO profileDAO;
	
	public UploadPhotoAction(Model model) {
		profileDAO = model.getProfileDAO();
	}

	public String getName() { return "uploadPhoto.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
        	Profile profile = profileDAO.read(user.getEmail());
			request.setAttribute("profile", profile);

			UploadPhotoForm form= formBeanFactory.create(request);

			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	request.setAttribute("form", form);

	        	System.out.println("Upload photot: fail");

	        	return "changePhoto.jsp";
	        }
	        
	        
	        FileProperty fileProp = form.getFile();
	        if (fileProp!= null && fileProp.getFileName().length()!= 0 && fileProp.getBytes().length!= 0) {
	        	String type = fileProp.getContentType();
	        	
	        	if(!type.startsWith("image/")) {
	        		//error type
	        		errors.add("Error Type. Please select image file.");
	        		return "error.jsp";
	        	}
	        	profile.setBytes(fileProp.getBytes());
	        	profile.setPictype(fileProp.getContentType());
	        }
			
	        System.out.println("Upload photo: success");
			profileDAO.update(profile);

	        return "changePhoto.jsp";
	 	} catch (RollbackException e) {

	 		System.out.println("Upload photo: fail: rollback excpetion");

			errors.add(e.getMessage());
			return "changePhoto.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
        	System.out.println("Upload photo: fail: formbeanexception" + e.getMessage());

			return "changePhoto.jsp";
		}
    }
    
}
