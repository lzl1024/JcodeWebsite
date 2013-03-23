package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.User;
import formbeans.LoginForm;
import formbeans.OnlineJudgeForm;


public class OnlineJudgeAction extends Action {
	private FormBeanFactory<OnlineJudgeForm> formBeanFactory = FormBeanFactory.getInstance(OnlineJudgeForm.class);
	
	private UserDAO  userDAO;

	public OnlineJudgeAction(Model model) {
    	userDAO  = model.getUserDAO();
	}

	public String getName() { return "oj.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			OnlineJudgeForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "oj.jsp";
	        }
	        
	        //compile and run
	        HttpSession session = request.getSession();
	        
	        String code = form.getCode();
	        Console con = Console.getInstance();
	        //get real file path
	        ServletContext context = session.getServletContext();
	        String realContextPath = context.getRealPath("/OJ"); 
	        String result = con.compileRun(code, realContextPath);
	        
	        session.setAttribute("code", code);
	        session.setAttribute("result", result);

	        return "oj.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
