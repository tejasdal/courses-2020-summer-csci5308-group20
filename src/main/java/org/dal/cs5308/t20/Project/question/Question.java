package org.dal.cs5308.t20.Project.question;

import java.util.List;

public class Question {
	public static final int NUMERIC = 1;
	public static final int MULTIPLE_CHOICE_CHOOSE_ONE = 2;
	public static final int MULTIPLE_CHOICE_CHOOSE_MANY = 3;
	public static final int FREE_TEXT = 4;
	
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private int questionType;
	private List<QuestionAnswer> presetAnswers;
	
	public Question(Long id, String title, String description, Long userId, int questionType, List<QuestionAnswer> presetAnswers) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.questionType = questionType;
		this.presetAnswers = presetAnswers;
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
	
	public List<QuestionAnswer> getPresetAnswers() {
		return presetAnswers;
	}
}
