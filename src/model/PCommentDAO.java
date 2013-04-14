package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.PComment;


public class PCommentDAO extends GenericDAO<PComment> {
	
	public PCommentDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(PComment.class, tableName, pool);
	}

	public PComment[] getComments(int problemid) throws RollbackException {
		PComment[] comments = match(MatchArg.equals("problemid",problemid));
		Arrays.sort(comments); 
		return comments;
	}
	
	public void create(PComment newComment) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(newComment);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id, String owner) throws RollbackException {
		try {
			Transaction.begin();
    		PComment p = read(id);

    		if (p == null) {
				throw new RollbackException("Comment does not exist: id="+id);
    		}

    		if (!owner.equals(p.getUser())) {
				throw new RollbackException("Comment not owned by "+owner);
    		}

			delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
