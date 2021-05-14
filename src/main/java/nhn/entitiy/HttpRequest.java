package nhn.entitiy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest {

	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

	private String httpMethod;
	private String requestUri;
	private String protocol;
	private Boolean passed;
	private Long requestStartTime;

	private Socket request;

	public HttpRequest(Socket socket) throws IOException {
		this.request = socket;
		this.passed = false;

		BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
		Reader reader = new InputStreamReader(bufferedInputStream, "UTF-8");

		StringBuilder stringBuilder = new StringBuilder();
		while (reader.ready()) {
			int ch = reader.read();
			stringBuilder.append((char) ch);
		}
		String requestStr = stringBuilder.toString();
		if (requestStr.length() == 0) {
			return;
		}

		String[] requestInfoArr = requestStr.split("\\s+");
		this.httpMethod = requestInfoArr[0];
		this.requestUri = requestInfoArr[1];
		this.protocol = requestInfoArr[2];
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public Long getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Long requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public Socket getRequest() {
		return request;
	}

	public void setRequest(Socket request) {
		this.request = request;
	}
}
