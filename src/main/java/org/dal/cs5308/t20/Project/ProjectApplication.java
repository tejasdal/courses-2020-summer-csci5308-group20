package org.dal.cs5308.t20.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		try {
			AppProperties.fetchProperties();
			Factory.getDbUtilInstance().initializeDb();
			SpringApplication.run(ProjectApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
