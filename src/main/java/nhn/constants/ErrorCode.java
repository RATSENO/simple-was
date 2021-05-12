package nhn.constants;

public enum ErrorCode {
	//��û�� �����ϳ�, ������ ���� �ź�
	FORBIDDEN(403),
	//����� ��û�� �������� ã�� �� ����.
	NOT_FOUND(404),
	//������ ���� ����
	SERVER_ERROR(500);
	
	private int code;

	private ErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
