package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class Profile {
	private String email	 	= null;
	private String realName 	= null;
	private byte[] bytes       	= null;
	private String occupation 	= null;
	private String interest		= null;
	private String introduction = null;
	private String status		= null;
	private String pictype		= null;


	public String  getRealName() 	   { return realName; 		}
	public byte[]  getBytes()          { return bytes;          }
	public String  getEmail()		   { return email; 			}		
	public String  getOccupation()     { return occupation;		}
	public String  getInterest()	   { return interest;		}
	public String  getIntroduction()   { return introduction;   }
	public String  getStatus()		   { return status;			}
	public String  getPictype()		   { return pictype;		}

	public void setRealName(String x)  		 { realName = x; 	}
	public void setBytes(byte[] s)        	 { bytes = s;		}
	public void setOccupation(String s)      { occupation = s;  }	
	public void setInterest(String s)        { interest = s;    }
	public void setEmail(String s)			 { email = s;		}
	public void setStatus(String s)			 { status = s;		}
	public void setPictype(String s)		 { pictype = s;		}
	public void setIntroduction(String s)	 { introduction = s;}

	public String toString() {
		return "Profile("+getEmail()+")";
	}
	

}
