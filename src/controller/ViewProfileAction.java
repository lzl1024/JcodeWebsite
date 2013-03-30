package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import model.Model;
import model.ProfileDAO;

import databeans.Profile;

public class ViewProfileAction extends Action {

	private ProfileDAO  profileDAO;
	
    public ViewProfileAction(Model model) {
    	profileDAO  = model.getProfileDAO();
	}

    public String getName() { return "viewprofile.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        String email = request.getParameter("email");
        
        try {
			Profile profile = profileDAO.read(email);
			request.setAttribute("profile",profile);
			return "viewprofile.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
    		
    }
}
