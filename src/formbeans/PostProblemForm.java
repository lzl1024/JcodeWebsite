package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class PostProblemForm extends FormBean {
	private String title;
	private String content;
	private String startCode;
	private String testCode;
	private String referRes = "";	//referRes could be empty
	
	public String getTitle()		{ return title;     }
	public String getContent() 		{ return content;   }
	public String getStartCode()  	{ return startCode; }
	public String getTestCode()    	{ return testCode;  }
	public String getReferRes()		{ return referRes;  }
	
	public void setTitle(String s)		{ title = s;      }
	public void setContent(String s)	{ content = s;    }
	public void setStartCode(String s)  { startCode = s;  }
	public void setTestCode(String s)   { testCode = s;   }
	public void setReferRes(String s)   { referRes = s;   }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (title == null || title.length() == 0) {
			errors.add("Title should not be empty");
		}
		
		if (title.length() > 255) {
			errors.add("Plase make your title less than 255 characters!");
		}
		
		if (content == null || content.length() == 0) {
			errors.add("Content should not be empty");
		}
		
		if (startCode == null || startCode.length() == 0) {
			errors.add("Start code should not be empty");
		}
		
		if (testCode == null || testCode.length() == 0) {
			errors.add("Test code should not be empty");
		}
		
		return errors;
	}
}