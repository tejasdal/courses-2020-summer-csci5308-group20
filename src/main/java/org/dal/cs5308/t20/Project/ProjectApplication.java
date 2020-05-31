package org.dal.cs5308.t20.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProjectApplication {

	public static void main(String[] args) {
		try {
			AppProperties.fetchProperties();
			Factory.getDbUtilInstance().initializeDb();
			Factory.getUserService().addAdminUser();
			SpringApplication.run(ProjectApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
