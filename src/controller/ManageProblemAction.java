package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.ProblemDAO;

import org.genericdao.RollbackException;

import databeans.Problem;
import databeans.User;

public class ManageProblemAction extends Action {

	private ProblemDAO  problemDAO;

    public ManageProblemAction(Model model) {
    	problemDAO = model.getProblemDAO();
	}

    public String getName() { return "manageProblem.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
	    	
	       	if (user == null || !user.getUserGroup().equals("admin")) {
    			errors.add("Invalid User! ");
    			return "error.jsp";
    		}
	       	
			Problem[] problems = problemDAO.match();
			
			Arrays.sort(problems);
			String begin;
			if((begin = request.getParameter("begin")) == null) {
				request.setAttribute("begin",1);
			}else {
				int b = Integer.parseInt(begin);
				if (b <= 0) b = 1;
				request.setAttribute("begin", b);
			}
			
			Problem[] hotProblem = problemDAO.match();
			Problem p = new Problem();
			Arrays.sort(hotProblem, p.hp);
			if (hotProblem.length > 10)
				hotProblem = Arrays.copyOf(hotProblem, 10);
			
			//System.out.println(hotProblem.length);
			request.setAttribute("hotproblem", hotProblem);
			
			
			request.setAttribute("problemlist",problems);
			
	        return "manageProblem.jsp";
		}catch (NumberFormatException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}