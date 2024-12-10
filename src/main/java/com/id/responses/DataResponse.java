package com.id.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DataResponse extends BaseResponse {
	
	private Object data;
	
	public DataResponse(int code, String status, String message, Object data) {
		super(code, status, message);
		this.data = data;
		// TODO Auto-generated constructor stub
	}
	
}
