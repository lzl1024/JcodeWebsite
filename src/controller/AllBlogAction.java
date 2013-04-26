package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.BlogDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.Blog;

public class AllBlogAction extends Action {

	private BlogDAO  blogDAO;

    public AllBlogAction(Model model) {
    	blogDAO = model.getBlogDAO();
	}

    public String getName() { return "allblog.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

	       	
			Blog[] blogs = blogDAO.match();
			Arrays.sort(blogs);
			String begin;
			if((begin = request.getParameter("begin")) == null) {
				request.setAttribute("begin",1);
			}else{
				int b = Integer.parseInt(begin);
				if (b <= 0) b = 1;
				request.setAttribute("begin", b);
			}
			request.setAttribute("bloglist",blogs);
			
			Blog[] hotBlog = blogDAO.match();
			Blog b = new Blog();
			Arrays.sort(hotBlog, b.hb);
			if (hotBlog.length > 10)
				hotBlog = Arrays.copyOf(hotBlog, 10);
			
			request.setAttribute("hotblog", hotBlog);
			
	        return "blogList.jsp";
		}catch (NumberFormatException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}