package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Problem;


public class ProblemDAO extends GenericDAO<Problem> {
	
	public ProblemDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Problem.class, tableName, pool);
	}

	public Problem[] getProblems(int id) throws RollbackException {
		Problem[] problems = match(MatchArg.equals("id",id));
		//Arrays.sort(blogs); 
		return problems;
	}
	
	public void create(Problem newProblem) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(newProblem);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id) throws RollbackException {
		try {
			Transaction.begin();
			Problem p = read(id);

    		if (p == null) {
				throw new RollbackException("Problem does not exist: id="+id);
    		}

			delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
