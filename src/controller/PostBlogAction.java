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

import model.BlogDAO;
import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;
import databeans.Comment;
import databeans.User;
import formbeans.PostBlogForm;

public class PostBlogAction extends Action {
	private FormBeanFactory<PostBlogForm> formBeanFactory = FormBeanFactory.getInstance(PostBlogForm.class);
	
	private BlogDAO	blogDAO;
	private UserDAO userDAO;

	public PostBlogAction(Model model) {
		blogDAO = model.getBlogDAO();
		userDAO = model.getUserDAO();
	}

	public String getName() { return "blog.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
	                
	        PostBlogForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        Blog[] hotBlog = blogDAO.match();
			Blog b = new Blog();
			Arrays.sort(hotBlog, b.hb);
			if (hotBlog.length > 10)
				hotBlog = Arrays.copyOf(hotBlog, 10);
			
			request.setAttribute("hotblog", hotBlog);
	        
	        if (!form.isPresent()) {
	            return "blog.jsp";
	        }
	       
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) return "blog.jsp";

			Blog blog = new Blog();  
			
			SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
			Date curDate = new Date(System.currentTimeMillis()); 
			blog.setDate(formatter.format(curDate));
			blog.setContent((fixBadChars(form.getContent())).getBytes("Unicode"));
			blog.setTitle(fixBadChars(form.getTitle()));
			blog.setUser(user.getUserName());
			blog.setEmail(user.getEmail());
			blog.setCommentNum(0);
			blogDAO.create(blog);
			
			User u = userDAO.read(blog.getEmail());
			Blog[] archives = blogDAO.getBlogs(u.getEmail());
  
    		request.setAttribute("blogOwner",u); 
    		request.setAttribute("archives",archives); 

			request.setAttribute("errors", errors);
			request.setAttribute("commentlist", new Comment[0]);
			request.setAttribute("blog", blog);
	        return "viewblog.jsp";
	 	} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "blog.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "blog.jsp";
		} catch (UnsupportedEncodingException e) {
			errors.add(e.getMessage());
			return "blog.jsp";
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