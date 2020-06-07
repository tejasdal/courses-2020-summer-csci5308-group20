package org.dal.cs5308.t20.Project.question;

public class QuestionAnswer {
<<<<<<< HEAD
	private Long id;
	private String answer;
	private int value;
	
	public QuestionAnswer(Long id, String answer, int value) {
		this.id = id;
=======
	private String answer;
	private int value;
	
	public QuestionAnswer(String answer, int value) {
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
		this.answer = answer;
		this.value = value;
	}

	public String getAnswer() {
		return answer;
	}

	public int getValue() {
		return value;
	}

<<<<<<< HEAD
	public Long getId() {
		return id;
	}
=======
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
}
