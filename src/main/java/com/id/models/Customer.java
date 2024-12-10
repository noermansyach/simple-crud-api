package com.id.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter // Membuat getter dan setter
@NoArgsConstructor // Membuat constructor tanpa parameter
@AllArgsConstructor // Membuat constructor dengan semua parameter
public class Customer {

	private String custId;
	private String name;
	private String gender;
	private String address;
	private String occupation;
	
}
