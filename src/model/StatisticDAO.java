package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Statistic;


public class StatisticDAO extends GenericDAO<Statistic> {
	
	public StatisticDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Statistic.class, tableName, pool);
	}

	public Statistic[] getStats(String useremail) throws RollbackException {
		Statistic[] statistic = match(MatchArg.equals("userEmail",useremail));
		Arrays.sort(statistic); 
		return statistic;
	}
	
	
}

