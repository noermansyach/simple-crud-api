package com.id.enums;

public enum ResponseEnum {
	
	retrieveSuccess("Success", "Data retrieved successfully.", 200),
	deleteSuccess("Success", "Data succuessfully deleted.", 200),
	noContent("Success", "No data.", 204),
	stillEmpty("Error", "Data is still empty.", 404),
	updateSuccess("Success", "Data", 200),
	addSuccess("Success", "Data created successfully.", 201),
	invalidRequest("Error", "Invalid data.", 400),
	unauthorized("Error", "Unauthorized.", 401),
	forbiddenAccess("Error", "Access forbidden.", 403),
	notFound("Error", "Data not found.", 404),
	dataDuplicate("Error", "Data already exist.", 409),
	invalidData("Error", "Invalid data request.", 422),
	technicalError("Error", "Internal server error.", 500);
	
	private String status;
	private String message;
	private int code;
	
	ResponseEnum(String status, String message, int code) {
		this.status = status;
		this.message = message;
		this.code = code;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
