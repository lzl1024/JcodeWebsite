package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;

import databeans.User;


public class TopCoderAction extends Action {

	private UserDAO userDAO;

    public TopCoderAction(Model model) {
    	userDAO = model.getUserDAO();
	}

    public String getName() { return "topcoder.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
	       	
			User[] users = userDAO.getUsers();
			
			request.setAttribute("users",users);
			
	        return "topCoder.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
