package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CommentForm extends FormBean {
	private String content;
	
	public String getContent()  { return content; }
	
	public void setContent(String s)  { content = s;  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (content == null || content.length() == 0) {
			errors.add("Content should not be empty");
		}
				
		return errors;
	}
}