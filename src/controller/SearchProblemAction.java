package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ProblemDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Problem;

import formbeans.SearchForm;

public class SearchProblemAction extends Action{
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class);
	
	private ProblemDAO problemDAO;

	public SearchProblemAction(Model model) {
		problemDAO = model.getProblemDAO();
	}
	
	public String getName() { return "searchproblem.do"; }
	
	 public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
			try {
				SearchForm search = formBeanFactory.create(request);
				
	            // Set up user list for nav bar
				Problem[] problemlist;
				String[] strar = search.getKeyword().split(" ");
				if (strar == null || strar.length == 0)
				{
					problemlist = problemDAO.match();
				}else {			
					MatchArg match = MatchArg.containsIgnoreCase("title", strar[0]);
					for(int i = 1; i < strar.length; i++) {
						match = MatchArg.or(match, MatchArg.containsIgnoreCase("title", strar[i]));
					}
					problemlist = problemDAO.match(match);
					
				}
				
				String begin;
				if((begin = request.getParameter("begin")) == null) {
					request.setAttribute("begin",1);
				}else {
					request.setAttribute("begin", Integer.parseInt(begin));
				}
				
				request.setAttribute("problemlist",problemlist);
		        return "problemList.jsp";
	        } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        }
	    }
}