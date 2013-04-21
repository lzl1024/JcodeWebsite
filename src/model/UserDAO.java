package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.User;


public class UserDAO extends GenericDAO<User> {
	
	public UserDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(User.class, tableName, pool);
	}

	public User[] getUsers() throws RollbackException {
		User[] users = match();
		Arrays.sort(users);  // We want them sorted by score
		return users;
	}
	
	public void setPassword(String userEmail, String password) throws RollbackException {
        try {
        	Transaction.begin();
			User dbUser = read(userEmail);
			
			if (dbUser == null) {
				throw new RollbackException("User "+userEmail+" no longer exists");
			}
			
			dbUser.setPassword(password);
			
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
