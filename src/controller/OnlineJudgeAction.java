package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbeans.OnlineJudgeForm;


public class OnlineJudgeAction extends Action {
	private FormBeanFactory<OnlineJudgeForm> formBeanFactory = FormBeanFactory.getInstance(OnlineJudgeForm.class);
	
	private String   OJPath;

	public OnlineJudgeAction(Model model) {
    	OJPath = model.getOJPath();
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
	        String code = form.getCode();
	        Console con = Console.getInstance();
	  
	        
	        String result = con.compileRun(code, OJPath);
	        HttpSession session = request.getSession();
	        session.setAttribute("code", code);
	        session.setAttribute("result", result);

	        return "oj.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
