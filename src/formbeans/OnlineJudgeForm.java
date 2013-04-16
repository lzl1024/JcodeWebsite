package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class OnlineJudgeForm extends FormBean {
	private int	   problemId = -1;
	private String code;
	
	public int	  getProblemId() { return problemId; }
	public String getCode()  { return code; }
	
	public void setProblemId(int d) { problemId = d; }
	public void setCode(String s)   { code = s;  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (code == null || code.length() == 0) {
			errors.add("Please Enter Code");
		}
		
		return errors;
	}
}