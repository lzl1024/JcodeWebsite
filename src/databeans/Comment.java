package databeans;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Comment implements Comparable<Comment> {

	private int    id		= -1;
	private byte[] content 	= null;
	private String user		= null;
	private int    blogid 	= -1;
	private String date		= null;
	
	public int compareTo(Comment other) {
		// Order first by owner, then by position
		if (date == null && other.date != null) return -1;
		if (date != null && other.date == null) return 1;
		Date d1,d2;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {    
			d1 = format.parse(date);  
			d2 = format.parse(other.date);
			if (d1.after(d2))
				return 1;
			else return -1;
		} catch (ParseException e) {    
			e.printStackTrace();    
		}  

		return -1;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Comment) {
			Comment other = (Comment) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public byte[] getContent() 	   { return content; }
    public int    getId()          { return id;      }
    public String getUser()		   { return user;	 }
    public int	  getBlogId()  	   { return blogid;   }
    public String getDate()  	   { return date;    }
    public String getReadableCon() throws UnsupportedEncodingException { 
    	String str = new String(content, "Unicode");
    	return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }
    
    public void setContent(byte[] s)  	  { content = s;  		}
    public void setId(int x)              { id = x;           	}
    public void setUser(String userName)  { user = userName; 	}
    public void setBlogId(int b)	 	  { blogid = b;      	} 
    public void setDate(String d)	 	  { date = d;       	} 
    
    public String toString() {
    	return "Comment("+id+")";
    }
}