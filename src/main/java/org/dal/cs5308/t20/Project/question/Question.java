package org.dal.cs5308.t20.Project.question;

import java.sql.Date;
import java.util.ArrayList;
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
    private List<QuestionOption> questionOptions = new ArrayList<>();
    private Date createdAt;

    public Question() {
    }

    public Question(Long id, String title, String description, Long userId, int questionType, Date createdAt,
                    List<QuestionOption> questionOptions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
        this.createdAt = createdAt;
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

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
