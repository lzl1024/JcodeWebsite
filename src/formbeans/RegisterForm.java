package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String userName;
	private String password;
	private String confirm ;
	private String email;
	private String use;
	
	public String getUserName()  { return userName;  }
	public String getEmail()	 { return email;	 }
	public String getPassword()  { return password;  }
	public String getConfirm()   { return confirm;   }
	public String getUse()   	 { return use;   }
	
	public void setUse(String s)   	   { use = s;   }
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setEmail(String s)     { email     = s.trim();				    }
	public void setPassword(String s)  { password  = s.trim();                  }
	public void setConfirm(String s)   { confirm   = s.trim();                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();


		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}

		if (email == null || !email.matches(".+@.+")) {
			errors.add("Email is blank or not in correct format");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		
		return errors;
	}
}
