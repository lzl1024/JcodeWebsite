package formbeans;

import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class EditProfileForm extends FormBean {
	private String occupation     = "";
	private String interest    = "";
	private String introduction	  = "";
	private String status		="";
	private FileProperty file = null;
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public String       getOccupation()     { return occupation;     }
	public FileProperty getFile()           { return file;           }
	public String       getInterest()    	{ return interest;    	 }
	public String		getIntroduction()	{ return introduction;	 }
	public String 		getStatus()		 	{ return status;		 }

	public void setOccupation(String s)     { occupation    = s;     }
	public void setInterest(String s)    	{ interest 		= s; 	 }
	public void setFile(FileProperty file)  { this.file   = file;    }
	public void setStatus(String p)			{ status		= p;	 }
	public void setIntroduction(String s)   { introduction  = s;	 }
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (file == null) {
			errors.add("You must right file");
			return errors;
		}
		
		if (file.getFileName().length() != 0 && file.getBytes().length == 0) {
			errors.add("Zero length file");
		}
		
		if (occupation == null || occupation.length() == 0) {
			errors.add("Occupation should not be left blank");
		}
		
		if (occupation.length() > 255) {
			errors.add("Please edit occupation less than 255 characters!");
		}

		if (interest == null || interest.length() == 0) {
			errors.add("Interest should not be left blank");
		}
		
		if (interest.length() > 255) {
			errors.add("Please edit interest less than 255 characters!");
		}
		
		if (introduction == null || introduction.length() == 0) {
			errors.add("Introduction should not be left blank");
		}
		
		if (introduction.length() > 255) {
			errors.add("Please edit introduction less than 255 characters!");
		}
		
		if (status == null || status.length() == 0) {
			errors.add("Status should not be left blank");
		}
		
		if (status.length() > 255) {
			errors.add("Please edit status less than 255 characters!");
		}
		
		return errors;
	}
}
