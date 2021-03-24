package journeysManagement;

public class ResponseObject {
	
	private int errorCode;
	private String errorMessage;
	private String response;
	
	public ResponseObject(int errorCode, String errorMessage) {
		
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
			this.response = "Error " + String.valueOf(errorCode)+ ": " + errorMessage;
		
	}
	
	public String getResponse() {
		return response;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

}
