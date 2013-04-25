package databeans;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Problem implements Comparable<Problem> {

	private int    id			= -1;
	private String title		= null;
	private byte[] content		= null;
	private String date 		= null;
	private int commentNum		= 0;
	private byte[] startCode	= null;
	private byte[] testCode		= null;
	private byte[] referRes		= null;
	private int	   accept		= 0;
	private int    deny			= 0;
	
	public Problem(){
		accept = 0;
		deny = 0;
	}
	
	public class HotProblem implements Comparator<Problem> {
		@Override
		public int compare(Problem o1, Problem o2) {
			if (o1.accept + o1.deny > o2.accept + o1.deny) return -1;
			if (o1.accept + o1.deny < o2.accept + o1.deny) return 1;
			return 0;
		}
		
	}
	
	public int compareTo(Problem other) {
		// Order by date
		if (date == null && other.date != null) return -1;
		if (date != null && other.date == null) return 1;
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
	/*
	public boolean equals(Object obj) {
		if (obj instanceof Blog) {
			Problem other = (Problem) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	*/
	
    public String getTitle() 	   { return title; 	 }
    public byte[] getContent() 	   { return content; }
    public int    getId()          { return id;      }
    public String getDate()  	   { return date;    }
    public int    getCommentNum()  { return commentNum;}
    public byte[] getStartCode()   { return startCode; }
    public byte[] getTestCode()    { return testCode; }
    public byte[] getReferRes()    { return referRes; }
    public int	  getAccept()	   { return accept;	  }
    public int 	  getDeny()		   { return deny;	  }

    
    public String getReadableCon() throws UnsupportedEncodingException { 
    	String str = new String(content, "Unicode");
    	return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }
    
    public String getShortDes()	throws UnsupportedEncodingException { 
    	String ret = getReadableCon();
    	if (ret.length() > 250) {
    		return ret.substring(0,250);
    	}
    	
    	return ret; 
    }
    
    public String getReadableStartCode() throws UnsupportedEncodingException { 
    	return new String(startCode, "Unicode");
    }
    
    public String getReadableTestCode() throws UnsupportedEncodingException { 
    	return new String(testCode, "Unicode");
    }
    
    public String getReadableReferRes() throws UnsupportedEncodingException { 
    	return new String(referRes, "Unicode");
    }
    
    public void setTitle(String s)  	  { title = s;  	}
    public void setContent(byte[] s)  	  { content = s;  	}
    public void setId(int x)              { id = x;         }
    public void setStartCode(byte[] s)	  { startCode = s;  }
    public void setTestCode(byte[] s)     { testCode = s;   }
    public void setReferRes(byte[] s)     { referRes = s;   }
    public void setDate(String d)	 	  { date = d;       } 
    public void setCommentNum(int s)	  { commentNum = s;	}
    public void setAccept(int s)		  { accept = s;		}
    public void setDeny(int s)			  { deny  = s;		}
    
    public String toString() {
    	return "Blog("+id+")";
    }
}