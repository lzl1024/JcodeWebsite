package controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PCommentDAO;
import model.ProblemDAO;
import model.StatisticDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.PComment;
import databeans.Problem;
import databeans.Statistic;
import databeans.User;

import formbeans.PostProblemForm;

public class EditProblemAction extends Action {
	private FormBeanFactory<PostProblemForm> formBeanFactory = FormBeanFactory.getInstance(PostProblemForm.class);
	

	private ProblemDAO  problemDAO;
	private PCommentDAO pcommentDAO;
	private StatisticDAO statisticDAO;
	
    public EditProblemAction(Model model) {
    	problemDAO  = model.getProblemDAO();
    	pcommentDAO = model.getPCommentDAO();
    	statisticDAO = model.getStatisticDAO();
	}

    public String getName() { return "editproblem.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			PostProblemForm form = formBeanFactory.create(request);
			User user = (User) request.getSession(false).getAttribute("user");
			if (!user.getEmail().equals("admin@admin")) {
				errors.add("You are not administrator!");
				return "error.jsp";
			}
			
			
			
    		int id = Integer.parseInt((String)request.getParameter("id"));
    		Problem p = problemDAO.read(id);
    		if (p == null) {
    			errors.add("No problem with id="+id);
    			return "error.jsp";
    		}
    		
    		PostProblemForm form2 = new PostProblemForm();
    		
			Problem[] hotProblem = problemDAO.match();
			Problem pt = new Problem();
			Arrays.sort(hotProblem, pt.hp);
			if (hotProblem.length > 10)
				hotProblem = Arrays.copyOf(hotProblem, 10);
			
			//System.out.println(hotProblem.length);
			request.setAttribute("hotproblem", hotProblem);
			

    		form2.setContent(p.getReadableCon().replaceAll("<br>", "\n"));
    		form2.setTitle(p.getTitle());
    		form2.setStartCode(p.getReadableStartCode());
    		form2.setTestCode(p.getReadableTestCode());
    		form2.setReferRes(p.getReadableReferRes());
	        // Any validation errors?
	        if (!form.isPresent()) {	        	
	    		request.setAttribute("postForm",form2);  
	    		request.setAttribute("edit", 1);
	    		request.setAttribute("id", id);
	            return "postProblem.jsp";
	        }
	         
	       
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	request.setAttribute("postForm",form2); 
	        	request.setAttribute("edit", 1);
	        	request.setAttribute("id", id);
	        	return "postProblem.jsp";
	        }
    		
    		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
			Date curDate = new Date(System.currentTimeMillis()); 
			Transaction.begin();
			p.setDate(formatter.format(curDate));
			p.setContent((fixBadChars(form.getContent())).getBytes("Unicode"));
			p.setStartCode((form.getStartCode()).getBytes("Unicode"));
			p.setTitle(fixBadChars(form.getTitle()));
			p.setTestCode((form.getTestCode()).getBytes("Unicode"));
			p.setReferRes((form.getReferRes()).getBytes("Unicode"));
			problemDAO.update(p);
			Transaction.commit();
			
			
    		Statistic[] stat = statisticDAO.match(MatchArg.equals("problemId", p.getId()));
			Arrays.sort(stat);
			if (stat.length > 10)
				stat = Arrays.copyOf(stat, 10);
    		request.setAttribute("stat",stat);
    		
    		request.setAttribute("problem",p); 
			PComment[] pcomments = pcommentDAO.getComments(p.getId());
			request.setAttribute("commentlist",pcomments);
			request.setAttribute("begin",1);
    		
            return "viewproblem.jsp";
		}catch (NumberFormatException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";  	
    	}catch (UnsupportedEncodingException e) {
    		errors.add(e.getMessage());
			return "error.jsp";
		}finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
    }
		
		private String fixBadChars(String s) {
			if (s == null || s.length() == 0) return s;
			
			Pattern p = Pattern.compile("[<>\"&]");
	        Matcher m = p.matcher(s);
	        StringBuffer b = null;
	        while (m.find()) {
	            if (b == null) b = new StringBuffer();
	            switch (s.charAt(m.start())) {
	                case '<':  m.appendReplacement(b,"&lt;");
	                           break;
	                case '>':  m.appendReplacement(b,"&gt;");
	                           break;
	                case '&':  m.appendReplacement(b,"&amp;");
	                		   break;
	                case '"':  m.appendReplacement(b,"&quot;");
	                           break;
	                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
	            }
	        }
	        
	        if (b == null) return s;
	        m.appendTail(b);
	        return b.toString();
	    }
}