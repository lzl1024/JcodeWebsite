package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import model.Model;
import model.ProfileDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Profile;
import databeans.User;
import formbeans.UploadPhotoForm;

public class ChangePhotoAction extends Action {

	private ProfileDAO profileDAO;
	
	public ChangePhotoAction(Model model) {
		profileDAO = model.getProfileDAO();
	}

	public String getName() { return "changePhoto.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			User user = (User) request.getSession(false).getAttribute("user");
        	Profile profile = profileDAO.read(user.getEmail());
			request.setAttribute("profile", profile);
			
			System.out.println("changephoto: change: " + request.getParameter("change"));
			System.out.println("changephoto: x1: " + request.getParameter("x1"));
			System.out.println("changephoto: y1: " + request.getParameter("y1"));

			if (request.getParameter("change") != null) {
				int x1 = Integer.parseInt(request.getParameter("x1"));
				int y1 = Integer.parseInt(request.getParameter("y1"));
				int x2 = Integer.parseInt(request.getParameter("x2"));
				int y2 = Integer.parseInt(request.getParameter("y2"));
				int w = Integer.parseInt(request.getParameter("w"));
				int h = Integer.parseInt(request.getParameter("h"));
				System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " "
						+ w + " " + " " + h);

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				BufferedImage image;
				try {
					image = ImageIO.read(new ByteArrayInputStream(profile.getBytes()));
					// Get the sub image
					BufferedImage newImage = image.getSubimage(x1, y1, w, h);
					//ImageIO.write(newImage, "jpg", new File("/Users/maoqian/Desktop/haha1111111.jpg"));
					System.out.println("profile type: " + profile.getPictype());
					
					String type = profile.getPictype();
		        	String newType = null;
		        	if(type.startsWith("image/")) {
		        		newType = type.substring(type.indexOf('/') + 1);
		        	} else {
		        		//error type
		        		errors.add("Error Type");
		        		return "error.jsp";
		        	}
					ImageIO.write(newImage, newType, out);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				profile.setBytes(out.toByteArray());
				System.out.println("changePhotot: out: "+ out.toString());
				System.out.println("changePhotot: outarray: "+ Arrays.toString(out.toByteArray()));

				try {
		   			profileDAO.update(profile);
		   		} catch (RollbackException e) {
		   			e.printStackTrace();
		   		}

				request.setAttribute("profile", profile);
				return "viewprofile.jsp";
			} else
				return "changePhoto.jsp";
	 	} catch (RollbackException e) {

	 		System.out.println("Upload photot: fail: rollback excpetion");

			errors.add(e.getMessage());
			return "error.jsp";
	 	}
    }
    
}
