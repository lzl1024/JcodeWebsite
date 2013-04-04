package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.ProblemDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;
import databeans.Comment;
import databeans.Problem;

import formbeans.IdForm;
import formbeans.OnlineJudgeForm;


public class OnlineJudgeAction extends Action {
	private FormBeanFactory<OnlineJudgeForm> ojFactory = FormBeanFactory.getInstance(OnlineJudgeForm.class);
	private FormBeanFactory<IdForm> idFactory = FormBeanFactory.getInstance(IdForm.class);

	
	private String   OJPath;
	private ProblemDAO problemDAO;

	public OnlineJudgeAction(Model model) {
    	OJPath = model.getOJPath();
    	problemDAO = model.getProblemDAO();
	}

	public String getName() { return "oj.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
       
		try {
			OnlineJudgeForm ojForm = ojFactory.create(request);
			IdForm idForm = idFactory.create(request);

	        request.setAttribute("ojForm",ojForm);
	        request.setAttribute("idForm",idForm);
	        
	        String switcher = ojForm.getSwitcher();
	        // Any validation errors?
	        errors.addAll(ojForm.getValidationErrors());
	        
	        int idID = idForm.getIdAsInt();
	        int idOJ = ojForm.getProblemId();
	        Problem problem = null;
	        if(idID >= 0) {
	        	problem = problemDAO.read(idID);
	        	if (problem == null) {
	        		errors.add("No problem with id="+idID);
	        		return "error.jsp";
	        	}
	        } else if(idOJ >= 0) {
	        	problem = problemDAO.read(idOJ);
	        	if (problem == null) {
	        		errors.add("No problem with id="+idOJ);
	        		return "error.jsp";
	        	}
	        }
    		
	        if (errors.size() != 0) {
	            return "oj.jsp";
	        }
	        
	        request.setAttribute("problem",problem);
	        /*
	        Comment[] comments = commentDAO.getComments(p.getId());
			request.setAttribute("commentlist",comments);
    		*/
	        
	        //extract the content and comment
	        /*
	        Problem problem = problemDAO.read(ojForm.getProblemId());
	        if(problem == null) {
	        	errors.add("Error problem ID");
	        	request.setAttribute("errors", errors);
	        	return "error.jsp";
	        } else {
	        	request.setAttribute("problem", problem);
	        }
	        */
	        
	        //compile and run
	        String code = ojForm.getCode();
	        Console con = Console.getInstance();
	        
	
	        String result = null;
	
	        String act = request.getParameter("submit");
	        if(act != null) {
	        	if(act.equals("run")) {
	        		result = con.compileRun(code, OJPath);
	        	}
	        	else if(act.equals("verify")) {	
	        		//String testCode = "public class Test { public static void main(String[] args) {Source a = new Source(); System.out.println(a.len(\"test\"));}}";
	        		String testCode = problem.getReadableTestCode();
	        		String referRes = problem.getReadableReferRes();
	        		
	        		result = con.compileVerify(code, testCode, OJPath);
	        		//compare the result with the reference
        			System.out.println("result: " + result);
        			System.out.println("referRes: " + referRes);
        			//
	        		if(referRes.trim().equals(result.trim())) {
	        			result = "Accepted";
	        		} else {
	        			result = "Denied";

	        		}
	        	}
	        }
	        //seems the blewo attribute is not necessary
	        request.setAttribute("problem", problem);
	        request.setAttribute("code", code);
	        request.setAttribute("result", result);
	        request.setAttribute("switcher", switcher);

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (UnsupportedEncodingException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
        	return "error.jsp";
		}
		
        request.setAttribute("errors",errors);
        return "oj.jsp";

    }
}
