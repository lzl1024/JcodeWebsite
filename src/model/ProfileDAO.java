package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;


import databeans.Profile;

public class ProfileDAO extends GenericDAO<Profile> {
	
	public ProfileDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Profile.class, tableName, pool);
	}

}
