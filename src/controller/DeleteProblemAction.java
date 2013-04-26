package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.ProblemDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.User;

public class DeleteProblemAction extends Action {
	
	private ProblemDAO	problemDAO;

	public DeleteProblemAction(Model model) {
		problemDAO = model.getProblemDAO();
	}

	public String getName() { return "deleteproblem.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			User user = (User) request.getSession(false).getAttribute("user");
			if (!user.getUserGroup().equals("admin")) {
				errors.add("You are not administrator!");
				return "error.jsp";
			}
			
			
			String strid = request.getParameter("problemid");
			int problemid = Integer.parseInt(strid);

			if (problemDAO.read(problemid) == null) {
				errors.add("No problem with id="+problemid);
    			return "error.jsp";
			}
			
	        problemDAO.delete(problemid);			
	        
			return "manageProblem.do";
	 	} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}catch (NumberFormatException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
    }
    
}