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
import formbeans.EditProfileForm;

public class EditProfileAction extends Action {
	private FormBeanFactory<EditProfileForm> formBeanFactory = FormBeanFactory.getInstance(EditProfileForm.class);

	private ProfileDAO profileDAO;
	
	public EditProfileAction(Model model) {
		profileDAO = model.getProfileDAO();
	}

	public String getName() { return "editprofile.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
        	Profile profile = profileDAO.read(user.getEmail());
        	
			EditProfileForm form;
			
			if (request.getParameter("edit") == null)
				form= formBeanFactory.create(request);
			else {
				form = new EditProfileForm();
				form.setInterest(profile.getInterest());
				form.setIntroduction(profile.getIntroduction());
				form.setOccupation(profile.getOccupation());
				form.setStatus(profile.getStatus());
				
				request.setAttribute("form", form);
				return "editprofile.jsp";
			}
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	request.setAttribute("form", form);
	        	return "editprofile.jsp";
	        }
	        
	        
	        FileProperty fileProp = form.getFile();
	        if (fileProp!= null && fileProp.getFileName().length()!= 0 && fileProp.getBytes().length!= 0) {
	        	profile.setBytes(fileProp.getBytes());
	        	profile.setPictype(fileProp.getContentType());
	        }
			profile.setInterest(form.getInterest());			
			profile.setIntroduction(form.getIntroduction());
			profile.setOccupation(form.getOccupation());
			profile.setStatus(form.getStatus());
			
			profileDAO.update(profile);

			// Update photoList (there's now one more on the list)
			request.setAttribute("profile", profile);
	        return "viewprofile.jsp";
	 	} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "editprofile.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "editprofile.jsp";
		}
    }
    
}
