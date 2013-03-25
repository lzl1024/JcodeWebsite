package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Blog implements Comparable<Blog> {

	private int    id          = -1;
	
	private String title = null;
	private String content = null;
	private String user       = null;
	private String email       = null;
	private String date		   = null;
	
	public int compareTo(Blog other) {
		// Order first by owner, then by position
		if (user == null && other.user != null) return -1;
		if (user != null && other.user == null) return 1;
		int c = user.compareTo(other.user);
		if (c != 0) return c;
		return id - other.id;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Blog) {
			Blog other = (Blog) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public String getTitle() 	   { return title; }
    public String getContent() 	   { return content; }
    public int    getId()          { return id;          }
    public String getUser()		   { return user;	}
    public String getEmail()  	   { return email;  }
    public String getDate()  	   { return date;  }
    
    public void setTitle(String s)  	  { title = s;  }
    public void setContent(String s)  	  { content = s;  }
    public void setId(int x)              { id = x;           }
    public void setUser(String userName)  { user = userName; }
    public void setEmail(String e)	 	  { email = e;        } 
    public void setDate(String d)	 	  { date = d;        } 
    
    public String toString() {
    	return "Blog("+id+")";
    }
}