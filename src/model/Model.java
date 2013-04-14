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
	private ProfileDAO	profileDAO;
	private ProblemDAO  problemDAO;
	private PCommentDAO pcommentDAO;
	private StatisticDAO statisticDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			OJPath     = config.getInitParameter("OJPath");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  	= new UserDAO("user", pool);
			blogDAO  	= new BlogDAO("blog", pool);
			commentDAO 	= new CommentDAO("comment", pool);
			profileDAO	= new ProfileDAO("profile", pool);
			problemDAO  = new ProblemDAO("problem", pool);
			pcommentDAO = new PCommentDAO("pcomment", pool);
			statisticDAO= new StatisticDAO("statistic", pool);
			
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO  	getUserDAO()  	{ return userDAO; 		}
	public String   	getOJPath()   	{ return OJPath;   		}
	public BlogDAO   	getBlogDAO() 	{ return blogDAO;  		}
	public CommentDAO   getCommentDAO() { return commentDAO;  	}
	public ProfileDAO	getProfileDAO() { return profileDAO;	}
	public ProblemDAO	getProblemDAO() { return problemDAO;	}
	public PCommentDAO  getPCommentDAO(){ return pcommentDAO;  	}
	public StatisticDAO getStatisticDAO(){return statisticDAO;	}

}
