package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PCommentDAO;
import model.ProblemDAO;
import model.StatisticDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.PComment;
import databeans.Problem;
import databeans.Statistic;
import databeans.User;

import formbeans.IdForm;
import formbeans.OnlineJudgeForm;


public class OnlineJudgeAction extends Action {
	private FormBeanFactory<OnlineJudgeForm> ojFactory = FormBeanFactory.getInstance(OnlineJudgeForm.class);
	private FormBeanFactory<IdForm> idFactory = FormBeanFactory.getInstance(IdForm.class);


	private String   OJPath;
	private ProblemDAO problemDAO;
	private PCommentDAO pcommentDAO;
	private StatisticDAO statisticDAO;
	private UserDAO		userDAO;

	public OnlineJudgeAction(Model model) {
    	OJPath = model.getOJPath();
    	problemDAO = model.getProblemDAO();
    	pcommentDAO = model.getPCommentDAO();
    	statisticDAO = model.getStatisticDAO();
    	userDAO	= model.getUserDAO();
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

	        // Any validation errors?
	        errors.addAll(ojForm.getValidationErrors());

	        int idID = idForm.getIdAsInt();
	        int idOJ = ojForm.getProblemId();
	        System.out.printf("%d, %d", idID, idOJ);
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
	        }else {
	        	problem =(Problem) request.getSession(false).getAttribute("problem");
	        	request.getSession(false).removeAttribute("problem");
	        }
    		
	        if(ojForm.getCode() == null || ojForm.getCode().length() == 0)
	        	ojForm.setCode(problem.getReadableStartCode());
	        //else
	        	//ojForm.setCode("public class Source {\r\n    public static void main(String[] args) {\r\n        //Please don't modify the class name\r\n    }\r\n}");

	        System.out.println("---------------");
	        System.out.println(ojForm.getCode());
	        System.out.println("---------------");


	        request.setAttribute("ojForm", ojForm);
	        request.setAttribute("problem",problem);


	        PComment[] comments = pcommentDAO.getComments(problem.getId());
			
			request.setAttribute("commentlist",comments);
			String begin;
			if((begin = request.getParameter("begin")) == null) {
				request.setAttribute("begin",1);
			}else {
				request.setAttribute("begin", Integer.parseInt(begin));
			}
	 

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
	        			problem.setAccept(problem.getAccept()+1);
	        		} else {
	        			result = "Denied";
	        			problem.setDeny(problem.getDeny()+1);			
	        		}
	        		problemDAO.update(problem);
	        		
	        		//update Statistic
	    	        User user = (User) request.getSession(false).getAttribute("user");
	    	        Statistic stat = statisticDAO.read(user.getEmail(), problem.getId());
	    	        if (stat == null){
	    	        	stat = new Statistic(user.getEmail(), problem.getId(),problem.getTitle());
	    	        	if(result.equals("Accepted")) {
	    	        		stat.setAccept(1);
	    	        		stat.setScore(100);
	    	        		user.setScore(user.getScore()+100);
	    	        		user.setAccept(user.getAccept()+1);
	    	        	}else {
	    	        		stat.setDeny(1);
	    	        		user.setDeny(user.getDeny()+1);
	    	        		
	    	        	}
	    	        	statisticDAO.create(stat);
	    	        	userDAO.update(user);
	    	        }else if(stat.getAccept() == 0) {
	    	        	int uscore = user.getScore()-stat.getScore();
	    	        	if(result.equals("Accepted")) {
	    		        	stat.setAccept(1);
	    		        	user.setAccept(user.getAccept()+1);
	    		        }else {
	    		        	stat.setDeny(stat.getDeny()+1);
	    		        	user.setDeny(user.getDeny()+1);
	    		        }
	    	        	int sc = ScoreComput(stat.getAccept(), stat.getDeny());
	    	        	stat.setScore(sc);
	    	        	statisticDAO.update(stat);
	    	        	user.setScore(uscore+sc);
	    	        	userDAO.update(user);
	    	        }
	        	}
	        }
	        //seems the below attribute is not necessary
	        request.setAttribute("problem", problem);
	        request.setAttribute("code", code);
	        request.setAttribute("msg", result);
	        request.setAttribute("status", "success");
	        System.out.println(result);

	        
	        if (errors.size() != 0) {
	            return "oj.jsp";
	        }

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
        
        System.out.println("Return OJ OK");
        return "ajax.jsp";

    }
		
	public int ScoreComput(int accept, int deny) {
		if (accept == 0 ) return 0;
			
		if (deny < 5) {
			return 100 - deny*10;
		}else return 50;
	}
}