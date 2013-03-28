package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Comment;


public class CommentDAO extends GenericDAO<Comment> {
	
	public CommentDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Comment.class, tableName, pool);
	}

	public Comment[] getComments(int blogid) throws RollbackException {
		Comment[] comments = match(MatchArg.equals("blogid",blogid));
		Arrays.sort(comments); 
		return comments;
	}
	
	public void create(Comment newComment) throws RollbackException {
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
    		Comment p = read(id);

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
