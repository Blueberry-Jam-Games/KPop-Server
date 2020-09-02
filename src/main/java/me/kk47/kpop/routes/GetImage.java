package me.kk47.kpop.routes;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import me.kk47.kpop.CommonParamaters;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetImage implements Route {

	public GetImage() {
		
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		int puzzle = Integer.parseInt(request.queryParamOrDefault("id", "0"));
		String side = request.queryParamOrDefault("side", "a");
		return rawImage(response, puzzle, side);
	}
	
	private Response rawImage(Response start, int index, String side) {
		start.raw().setContentType("image/png");

		File f = new File(CommonParamaters.STATIC_FOLDER + "/puzzles/" + index + side + ".png");
		try (OutputStream out = start.raw().getOutputStream()) {
			ImageIO.write(ImageIO.read(f), "PNG", out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return start;
	}

//	@Override
//	public Object handle(Request request, Response response) throws Exception {
//		response.raw().setContentType("image/png");
//
//		File f = new File("src/main/resources/images/test-image.png");
//		try (OutputStream out = response.raw().getOutputStream()) {
//			ImageIO.write(ImageIO.read(f), "jpg", out);
//		}
//
//		return response;
//	}
}
