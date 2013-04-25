package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;

import formbeans.SearchForm;

public class SearchAction extends Action{
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class);
	
	private BlogDAO blogDAO;

	public SearchAction(Model model) {
		blogDAO = model.getBlogDAO();
	}
	
	public String getName() { return "search.do"; }
	
	 public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
			try {
				SearchForm search = formBeanFactory.create(request);
				
	            // Set up user list for nav bar
				Blog[] bloglist;
				String[] strar = search.getKeyword().split(" ");
				if (strar == null || strar.length == 0)
				{
					bloglist = blogDAO.match();
				}else {			
					MatchArg match = MatchArg.containsIgnoreCase("title", strar[0]);
					for(int i = 1; i < strar.length; i++) {
						match = MatchArg.or(match, MatchArg.containsIgnoreCase("title", strar[i]));
					}
					bloglist = blogDAO.match(match);
					
				}
				String begin;
				if((begin = request.getParameter("begin")) == null) {
					request.setAttribute("begin",1);
				}else {
					int b = Integer.parseInt(begin);
					if (b <= 0) b = 1;
					request.setAttribute("begin", b);	
				}
				
		        Blog[] hotBlog = blogDAO.match();
				Blog b = new Blog();
				Arrays.sort(hotBlog, b.hb);
				if (hotBlog.length > 10)
					hotBlog = Arrays.copyOf(hotBlog, 10);
				
				request.setAttribute("hotblog", hotBlog);
				
				request.setAttribute("bloglist",bloglist);
		        return "blogList.jsp";
			}catch (NumberFormatException e) {
	    		errors.add(e.getMessage());
	    		return "error.jsp";
			} catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        }
	    }
}