package me.kk47.kpop.routes;

import spark.Request;
import spark.Response;
import spark.Route;

public class Homepage implements Route {

	public Homepage() {
		
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		return "<h1>Hello World</h1> \n\r <a href=\"submission\">Submission Page</a>";
	}

}
