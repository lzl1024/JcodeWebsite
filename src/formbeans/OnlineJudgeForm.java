package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class OnlineJudgeForm extends FormBean {
	private String code;
	private String result;
	
	public String getCode()  { return code; }
	public String getResult()  { return result; }
	
	public void setCode(String s) { code = s;  }
	public void setResult(String s) {	result = s;                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (code == null || code.length() == 0) {
			errors.add("Please Enter Code");
		}
		
		return errors;
	}
}