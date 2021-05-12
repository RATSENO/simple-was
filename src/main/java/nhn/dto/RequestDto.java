package nhn.dto;

/**
 * 
 * nhn.dto ===>RequestDto.java.java
 *
 * @Class : RequestDto.java
 * @Since : 2021. 5. 12.
 * @Desc :
 */
public class RequestDto {

	private String requestUri;
	private String requestMethod;
	private String protocol;
	private String host;

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
