package formbeans;

import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class UploadPhotoForm extends FormBean {
	private FileProperty file = null;
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public FileProperty getFile()           { return file;           }


	public void setFile(FileProperty file)  { this.file   = file;    }
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (file == null) {
			errors.add("You must right file");
			return errors;
		}
		
		if (file.getFileName().length() != 0 && file.getBytes().length == 0) {
			errors.add("Zero length file");
		}
		
		return errors;
	}
}
