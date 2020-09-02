package me.kk47.kpop;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.stop;

import java.io.File;
import java.util.Scanner;

import me.kk47.kpop.routes.GetImage;
import me.kk47.kpop.routes.GetPuzzle;
import me.kk47.kpop.routes.Homepage;
import me.kk47.kpop.routes.PostResponse;
import me.kk47.kpop.routes.ShowAnswers;
import me.kk47.kpop.routes.SubmissionGet;
import me.kk47.kpop.routes.SubmissionPost;

public class LaunchRegister {

	public static void main(String[] args) {
		port(5000); // Needs to happen first
		
		// Set the static file location to be predictable
		File relativeFinder = new File("src/main/resources");
		System.out.println("File path found to be " + relativeFinder.getAbsolutePath());
		CommonParamaters.STATIC_FOLDER = relativeFinder.getAbsolutePath();
		staticFiles.externalLocation(CommonParamaters.STATIC_FOLDER);
		
		//Now Register Routes
		get("/", new Homepage());
		get("/submission", new SubmissionGet());
		post("/submission", new SubmissionPost());
		
		get("/api-puzzle", new GetPuzzle());
		get("/api-image", new GetImage());
		get("/api-response", new PostResponse());
		
		get("/answers", new ShowAnswers());
		
		System.out.println("Started");
		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);

		//  prompt for the user's name
		System.out.print("Press any button to quit");

		// get their input as a String
		scanner.next();
		stop();
		scanner.close();
		System.out.println("Shutdown");
	}
}
