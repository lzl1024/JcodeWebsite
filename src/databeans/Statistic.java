package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("userEmail,problemId")
public class Statistic implements Comparable<Statistic>{
	private String  userEmail = null;
	private int  problemId = -1;
	private String problemTitle = "";
	private int accept = 0;
	private int deny = 0;
	private int score = 0;
	
	public Statistic(){}
	
	public Statistic(String email, int pid, String pname) {
		this.userEmail = email;
		this.problemId = pid;
		this.problemTitle = pname;
		this.accept = 0;
		this.deny = 0;
		this.score = 0;		
	}
	
	public String  getUserEmail() 		{ return userEmail; 	}
	public int	   getProblemId()       { return problemId;     }
	public String  getProblemTitle()    { return problemTitle;  }
	public int     getAccept()  	    { return accept;  		}	
	public int     getDeny()		    { return deny;			}
	public int	   getScore()			{ return score;			}	

	public void setUserEmail(String x)  	{ userEmail = x; 	}
	public void setProblemId(int id)    	{ problemId = id;	}
	public void setProblemTitle(String x)   { problemTitle = x; }
	public void setAccept(int ac)		 	{ accept = ac;		}
	public void setDeny(int den)     	    { deny = den;       }
	public void setScore(int s)			 	{ score = s;        }

	@Override
	public int compareTo(Statistic st) {
		if (this.problemId < st.problemId) {
			return -1;
		}else if(this.problemId > st.problemId){
			return 1;
		}else {
			return 0;
		}
	}

}
