package com.id.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class WithTotalDataResponse extends BaseResponse {

	private int totalData;
	private Object data;
	
	public WithTotalDataResponse(int code, String status, String message, int totalData, Object data) {
		super(code, status, message);
		this.totalData = totalData;
		this.data = data;
	}
	
}
