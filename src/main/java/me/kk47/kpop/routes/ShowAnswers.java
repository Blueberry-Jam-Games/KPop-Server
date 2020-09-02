package me.kk47.kpop.routes;

import me.kk47.kpop.puzzles.Puzzle;
import me.kk47.kpop.puzzles.PuzzleManager;
import spark.Request;
import spark.Response;
import spark.Route;

public class ShowAnswers implements Route {

	public ShowAnswers() {
		
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String answer = "<table>";
		for(Puzzle pzl : PuzzleManager.puzzles) {
			answer = answer + pzl.toTableRow();
		}
		answer = answer + "</table>";
		return answer;
	}

}
