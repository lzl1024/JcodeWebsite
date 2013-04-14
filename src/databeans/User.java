package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class User implements Comparable<User> {
	private String  userName = null;

	private String  hashedPassword = "*";
	private int     salt           = 0;
	private String  email		   = null;
	private String  userGroup	   = "user";
	private int 	accept		   = 0;
	private int     deny		   = 0;
	private int     score		   = 0;
	
	public User(){
		accept 	= 0;
		deny  	= 0;
		score 	= 0;
	}

	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	public int compareTo(User other) {
		// Order first by lastName and then by firstName
		if (score > other.score ) 
			return -1;
		else if (score < other.score)
			return 1;
		else return 0;
	
	}

	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			return userName.equals(other.userName);
		}
		return false;
	}

	public String  getHashedPassword() { return hashedPassword; }
	public String  getUserName()       { return userName;       }
	public int     getSalt()           { return salt;           }
	public String  getEmail()		   { return email; 			}	
	public String  getUserGroup()	   { return userGroup;		}
	public int 	   getAccept()		   { return accept;			}
	public int     getDeny()		   { return deny;		    }
	public int 	   getScore()		   { return score;			}

	public int     hashCode()          { return email.hashCode(); }

	public void setHashedPassword(String x)  { hashedPassword = x; }
	public void setPassword(String s)        { salt = newSalt(); hashedPassword = hash(s); }
	public void setSalt(int x)               { salt = x;           }
	public void setUserGroup(String s)		 { userGroup = s;	   }
	public void setUserName(String s)        { userName = s;       }
	public void setEmail(String s)			 { email = s;		   }
	public void setAccept(int s)			 { accept = s;		   }
	public void setDeny(int s)				 { deny = s;		   }
	public void setScore(int s)				 { score = s;		   }

	public String toString() {
		return "User("+getUserName()+")";
	}

	private String hash(String clearPassword) {
		if (salt == 0) return null;

		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}

}
