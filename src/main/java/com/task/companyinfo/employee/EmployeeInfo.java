package com.task.companyinfo.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {
	
	@Id
	private int employeeID;
	private String employeeName;
	private String employeeType;
	private String email;
	private String password;

	

	
	
}
