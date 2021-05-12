package nhn.constants;

public enum ErrorCode {
	//요청은 정당하나, 서버가 응답 거부
	FORBIDDEN(403),
	//현재는 요청한 페이지를 찾을 수 없다.
	NOT_FOUND(404),
	//내부적 서버 오류
	SERVER_ERROR(500);
	
	private int code;

	private ErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
