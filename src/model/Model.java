package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private UserDAO  	userDAO;
	private String   	OJPath;
	private BlogDAO 	blogDAO;
	private CommentDAO 	commentDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			OJPath     = config.getInitParameter("OJPath");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  	= new UserDAO("user", pool);
			blogDAO  	= new BlogDAO("blog", pool);
			commentDAO 	= new CommentDAO("comment", pool);
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO  	getUserDAO()  	{ return userDAO; 		}
	public String   	getOJPath()   	{ return OJPath;   		}
	public BlogDAO   	getBlogDAO() 	{ return blogDAO;  		}
	public CommentDAO   getCommentDAO() { return commentDAO;  	}
}
