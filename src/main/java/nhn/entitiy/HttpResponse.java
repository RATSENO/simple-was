package nhn.entitiy;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nhn.constants.ErrorCode;
import nhn.servlet.SimpleServlet;
import nhn.util.SettingUtil;

public class HttpResponse {
	
	private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);
	private static final String ROOT_PACKAGE = "nhn";
	private static final String INDEX_PAGE = "index.html";
	
	private int errorCode;
	private String filePath;
	
	private OutputStream outputStream;
	private SimpleServlet servlet;
	
	public HttpResponse(HttpRequest httpRequest) throws IOException {
		this.outputStream = httpRequest.getRequest().getOutputStream();
		String requestUri = httpRequest.getRequestUri();
		if(requestUri.endsWith("/")) {
			this.filePath = this.INDEX_PAGE;
			return;
		}
		
		if(requestUri.replace("/", "").indexOf("service.") < 0) {
			requestUri = this.ROOT_PACKAGE + ".service." + requestUri;
		}
		this.filePath = requestUri;
		
		if(httpRequest.getRequestUri().indexOf("..") > - 1 || httpRequest.getRequestUri().indexOf(".exe") > - 1 ) {
			this.errorCode = ErrorCode.FORBIDDEN.getCode();
			return;
		}

		/*
		//html 파일
		String fileName = "";
		if(requestUri.endsWith("/")) {
			fileName = this.INDEX_PAGE;
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
			URL uri = Thread.currentThread().getContextClassLoader().getResource("index.html");
			File file = new File(uri.getPath(), fileName.substring(1, fileName.length()));
			if(file.canRead()) {
				 byte[] theData = Files.readAllBytes(file.toPath());
				 outputStream.write(theData);
				 outputStream.flush();
			}else {
				this.errorCode = ErrorCode.FORBIDDEN.getCode();
			}
		}
		*/
		return;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getFilePath() {
		return filePath;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public SimpleServlet getServlet() {
		int errorCode = getErrorCode();
		if(errorCode==0) {
			try {
				Class<?> findClass = Class.forName(getFilePath());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
		}
	}
	
	private SimpleServlet createErrorServle() {
		JSONArray hostJsonArray = SettingUtil.getHostJsonArray();
		
		String filePathTemp = "";
	}
}
