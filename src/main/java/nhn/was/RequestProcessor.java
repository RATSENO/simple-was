package nhn.was;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nhn.entitiy.HttpRequest;

public class RequestProcessor implements Runnable{
	
	private static final Logger log = LoggerFactory.getLogger(RequestProcessor.class);
	
	private Socket request;

	public RequestProcessor(Socket request) {
		this.request = request;
	}

	@Override
	public void run() {
		try {
			HttpRequest httpRequest = new HttpRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
