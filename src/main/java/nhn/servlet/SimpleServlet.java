package nhn.servlet;

import nhn.entitiy.HttpRequest;
import nhn.entitiy.HttpResponse;

public interface SimpleServlet {
	public void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
