package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String userName;
	private String password;
	private String confirm ;
	private String email;

	
	public String getUserName()  { return userName;  }
	public String getEmail()	 { return email;	 }
	public String getPassword()  { return password;  }
	public String getConfirm()   { return confirm;   }
	
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setEmail(String s)     { email     = s.trim();				    }
	public void setPassword(String s)  { password  = s.trim();                  }
	public void setConfirm(String s)   { confirm   = s.trim();                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();


		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		
		if (userName != null && userName.length() > 255) {
			errors.add("Please edit userName less than 255 characters!");
		}

		if (email == null || !email.matches(".+@.+")) {
			errors.add("Email is blank or not in correct format");
		}
		
		if (email.length() > 255) {
			errors.add("Please edit email less than 255 characters!");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		
		if (password.length() >= 30) {
			errors.add("Plase make your password less than 30 characters!");
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
