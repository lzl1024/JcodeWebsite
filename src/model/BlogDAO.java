package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Blog;


public class BlogDAO extends GenericDAO<Blog> {
	
	public BlogDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Blog.class, tableName, pool);
	}

	public Blog[] getBlogs(String email) throws RollbackException {
		Blog[] blogs = match(MatchArg.equals("email",email));
		Arrays.sort(blogs); 
		return blogs;
	}
	
	public void create(Blog newBlog) throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(newBlog);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id, String owner) throws RollbackException {
		try {
			Transaction.begin();
    		Blog p = read(id);

    		if (p == null) {
				throw new RollbackException("Blog does not exist: id="+id);
    		}

    		if (!owner.equals(p.getUser())) {
				throw new RollbackException("Blog not owned by "+owner);
    		}

			delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}
