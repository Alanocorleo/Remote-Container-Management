package response;

/**
 * ResponseObject class is used to get a response when executing a function. This is
 * useful when doing tests. It can also be used to display error messages either for 
 * user interface or for future programmers that will develop this software. In our
 * implementation, codes from 000 to 099 indicate successful operation, whereas 
 * codes from 100 to 999 indicate otherwise.
 */

public class ResponseObject {
	
	private int errorCode;
	private String errorMessage;
	
	/**
	 * This constructor creates an error code with an error message.
	 * @param errorCode
	 * @param errorMessage
	 */
	public ResponseObject(int errorCode, String errorMessage) {
		
			setErrorCode(errorCode);
			setErrorMessage(errorMessage);
			// this.response = "Error " + String.valueOf(errorCode)+ ": " + errorMessage;
		
	}
	
	/**
	 * This method returns the error code.
	 * @return error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * This method sets the error code.
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * This method returns the error message.
	 * @return error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * This method sets the error message.
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
