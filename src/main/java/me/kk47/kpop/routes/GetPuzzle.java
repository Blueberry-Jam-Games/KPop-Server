package me.kk47.kpop.routes;

import me.kk47.kpop.puzzles.PuzzleManager;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetPuzzle implements Route {

	public GetPuzzle() {
		
	}

	@Override
	public Object handle(Request request, Response response) {
		System.out.println("Get puzzle");
		return PuzzleManager.getNextPuzzle().toFormat();
	}

}
