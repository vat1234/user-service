package com.mycomp.user.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Varsha T
 *
 */
@Configuration
public class UserServiceProperties {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	@Value("${spring.datasource.username}")
	private String dataSourceUserName;
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	public String getDataSourceUrl() {
		return dataSourceUrl;
	}

	public void setDataSourceUrl(String dataSourceUrl) {
		this.dataSourceUrl = dataSourceUrl;
	}

	public String getDataSourceUserName() {
		return dataSourceUserName;
	}

	public void setDataSourceUserName(String dataSourceUserName) {
		this.dataSourceUserName = dataSourceUserName;
	}

	public String getDataSourcePassword() {
		return dataSourcePassword;
	}

	public void setDataSourcePassword(String dataSourcePassword) {
		this.dataSourcePassword = dataSourcePassword;
	}

}
