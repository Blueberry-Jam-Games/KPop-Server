package me.kk47.kpop.routes;

import me.kk47.kpop.puzzles.PuzzleManager;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostResponse implements Route {

	public PostResponse() {
		
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		int id = Integer.parseInt(request.queryParamOrDefault("id", "0"));
		boolean same = false;
		if(request.queryParams("same") == "s") {
			same = true;
		}
		PuzzleManager.recordAnswer(id, same);
		return null;
	}

}
