package com.mycomp.user.service.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Varsha T
 *
 */
@Entity
@Table(name = "userdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column
	private long mobileNo;
	@Column
	private String gender;
	@Column
	private Date birthdate;

	
}
