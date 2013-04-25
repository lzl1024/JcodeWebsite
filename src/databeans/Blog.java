package databeans;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Comparator;
import org.genericdao.PrimaryKey;


@PrimaryKey("id")
public class Blog implements Comparable<Blog> { 
	private int    id		= -1;
	private String title 	= null;
	private byte[] content 	= null;
	private String user		= null;
	private String email 	= null;
	private String date		= null;
	private int    commentNum = 0;
	
	public class HotBlog implements Comparator<Blog> {
		@Override
		public int compare(Blog o1, Blog o2) {
			if (o1.commentNum > o2.commentNum) return -1;
			if (o1.commentNum < o2.commentNum) return 1;
			return 0;
		}
		
	}
	
	public int compareTo(Blog other) {
		// Order first by owner, then by position
		if (user == null && other.user != null) return -1;
		if (user != null && other.user == null) return 1;
		Date d1,d2;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {    
			d1 = format.parse(date);  
			d2 = format.parse(other.date);
			if (d1.after(d2))
				return -1;
			else return 1;
		} catch (ParseException e) {    
			e.printStackTrace();    
		}  

		return -1;
	}
	
	
	public boolean equals(Object obj) {
		if (obj instanceof Blog) {
			Blog other = (Blog) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public String getTitle() 	   { return title; 	 }
    public byte[] getContent() 	   { return content; }
    public int    getId()          { return id;      }
    public String getUser()		   { return user;	 }
    public String getEmail()  	   { return email;   }
    public String getDate()  	   { return date;    }
    public int    getCommentNum()  { return commentNum;}
    public String getReadableCon() throws UnsupportedEncodingException { 
    	String str = new String(content, "Unicode");
    	return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }
    public String getShortDes()	throws UnsupportedEncodingException { 
    	String ret = getReadableCon();
    	int i = 0;
    	int k = -4;
    	while((k=ret.indexOf("<br>",k+4))>=0 && i < 3){
    		i++;
    	}
    	if (k > 0)
    		ret = ret.substring(0,k);
    	if (ret.length() > 250) {
    		return ret.substring(0,250);
    	}
    	
    	return ret; 
    }
    
    public void setTitle(String s)  	  { title = s;  	}
    public void setContent(byte[] s)  	  { content = s;  	}
    public void setId(int x)              { id = x;           }
    public void setUser(String userName)  { user = userName; }
    public void setEmail(String e)	 	  { email = e;        } 
    public void setDate(String d)	 	  { date = d;        } 
    public void setCommentNum(int s)	  { commentNum = s;	}
    
    public String toString() {
    	return "Blog("+id+")";
    }
}