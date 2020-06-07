package org.dal.cs5308.t20.Project.question;

public class QuestionAnswer {
	private Long id;
	private String answer;
	private int value;
	
	public QuestionAnswer(Long id, String answer, int value) {
		this.id = id;
		this.answer = answer;
		this.value = value;
	}

	public String getAnswer() {
		return answer;
	}

	public int getValue() {
		return value;
	}

	public Long getId() {
		return id;
	}
}
