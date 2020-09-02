package me.kk47.kpop.routes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.kk47.kpop.CommonParamaters;
import spark.Request;
import spark.Response;
import spark.Route;

public class SubmissionGet implements Route {

	String textResponse;
	
	public SubmissionGet() {
		try {
			textResponse = new String(Files.readAllBytes(Paths.get(CommonParamaters.STATIC_FOLDER + "/submission.html")));
		} catch (IOException e) {
			e.printStackTrace();
			textResponse = "No";
		}
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		return textResponse;
	}

}
