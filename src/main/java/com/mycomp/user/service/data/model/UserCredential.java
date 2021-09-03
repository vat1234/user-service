package com.mycomp.user.service.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "usercredential")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredential {
	@Id
	private String username;
	@Column
	private String password;

}
