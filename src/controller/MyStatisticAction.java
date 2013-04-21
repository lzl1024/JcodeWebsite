package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.StatisticDAO;

import org.genericdao.RollbackException;

import databeans.Statistic;
import databeans.User;



public class MyStatisticAction extends Action {

	private StatisticDAO  statisticDAO;

    public MyStatisticAction(Model model) {
    	statisticDAO = model.getStatisticDAO();
	}

    public String getName() { return "statistic.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
	    	
	       	if (user == null) {
    			errors.add("Invalid User! ");
    			return "error.jsp";
    		}
	       	
			Statistic[] stats = statisticDAO.getStats(user.getEmail());
			
			
			
			request.setAttribute("stats",stats);
			
	        return "mystatistic.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
