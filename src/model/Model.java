package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private UserDAO  userDAO;
	private String   OJPath;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			OJPath     = config.getInitParameter("OJPath");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  = new UserDAO("user", pool);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO  getUserDAO()  { return userDAO;  }
	public String   getOJPath()   { return OJPath;   }
}
