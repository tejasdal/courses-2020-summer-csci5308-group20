package org.dal.cs5308.t20.Project.question;

import java.util.List;

public class Question {
	public static final int NUMERIC = 1;
	public static final int MULTIPLE_CHOICE_CHOOSE_ONE = 2;
	public static final int MULTIPLE_CHOICE_CHOOSE_MANY = 3;
	public static final int FREE_TEXT = 4;
<<<<<<< HEAD

=======
	
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private int questionType;
	private List<QuestionAnswer> presetAnswers;
<<<<<<< HEAD
	private Long createdAt;

	public Question(Long id, String title, String description, Long userId, int questionType, Long createdAt,
			List<QuestionAnswer> presetAnswers) {
=======
	
	public Question(Long id, String title, String description, Long userId, int questionType, List<QuestionAnswer> presetAnswers) {
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.questionType = questionType;
		this.presetAnswers = presetAnswers;
<<<<<<< HEAD
		this.createdAt = createdAt;
=======
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
	}

	public static int getNumeric() {
		return NUMERIC;
	}

	public static int getMultipleChoiceChooseOne() {
		return MULTIPLE_CHOICE_CHOOSE_ONE;
	}

	public static int getMultipleChoiceChooseMany() {
		return MULTIPLE_CHOICE_CHOOSE_MANY;
	}

	public static int getFreeText() {
		return FREE_TEXT;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getUserId() {
		return userId;
	}

	public int getQuestionType() {
		return questionType;
	}
<<<<<<< HEAD

	public List<QuestionAnswer> getPresetAnswers() {
		return presetAnswers;
	}
	
	public Long getCreatedAt() {
		return createdAt;
	}
=======
	
	public List<QuestionAnswer> getPresetAnswers() {
		return presetAnswers;
	}
>>>>>>> 7d279f7ba596c8299828ab8d59447c5cf5c32316
}
