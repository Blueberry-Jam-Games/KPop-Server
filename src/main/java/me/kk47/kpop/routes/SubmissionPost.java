package me.kk47.kpop.routes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import me.kk47.kpop.CommonParamaters;
import me.kk47.kpop.puzzles.PuzzleManager;
import spark.Request;
import spark.Response;
import spark.Route;

public class SubmissionPost implements Route {

	public SubmissionPost() {

	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		System.out.println("Post called");
		int puzzleID = PuzzleManager.getNextPuzzleID();
		savePuzzleImage(getBufferedImage(request, "im1"), puzzleID, true);
		savePuzzleImage(getBufferedImage(request, "im2"), puzzleID, false);
		response.redirect("/");
		PuzzleManager.registerValidPuzzle(puzzleID);
		return null;
	}

	private File getValidSaveFile(String name) {
		try {
			String path = CommonParamaters.STATIC_FOLDER + "/puzzles/" + name + ".png";
			File check = new File(path);

			if(!check.exists()) {
				check.mkdirs();
				check.createNewFile();
			}

			return check;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void savePuzzleImage(BufferedImage bIn, int puzzleNumber, boolean aSide) {
		String name = puzzleNumber + (aSide ? "a" : "b");
		BufferedImage formatted = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		Graphics g = formatted.getGraphics();
		g.clearRect(0, 0, 512, 512);
		g.drawImage(bIn, 0, 0, 512, 512, null);
		g.dispose();
		try {
			ImageIO.write(formatted, "PNG", getValidSaveFile(name));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Issue occured in file writing process");
		}
	}

	private BufferedImage getBufferedImage(Request request, String file) {
		try {
			request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));

			// "file" is the key of the form data with the file itself being the value
			Part filePart = request.raw().getPart(file);

			// The name of the file user uploaded
			String uploadedFileName = filePart.getSubmittedFileName();
			InputStream stream = filePart.getInputStream();
			// Write stream to file under storage folder
			System.out.println("File name " + uploadedFileName);
			BufferedImage bi = ImageIO.read(stream);
			//System.out.println("Image data " + bi);
			ImageIO.write(bi, "PNG", new File("src/main/resources/images/" + uploadedFileName));
			return bi;

		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return null;
		}
	}

}
