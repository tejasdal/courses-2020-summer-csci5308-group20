package CSCI5308.GroupFormationTool.Question;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question {
    public static final int NUMERIC = 1;
    public static final int MULTIPLE_CHOICE_CHOOSE_ONE = 2;
    public static final int MULTIPLE_CHOICE_CHOOSE_MANY = 3;
    public static final int FREE_TEXT = 4;
    public static Comparator<Question> titleComparator = new Comparator<Question>() {
        @Override
        public int compare(Question question1, Question question2) {
            String title1 = question1.getTitle();
            String title2 = question2.getTitle();

            return title1.compareTo(title2);
        }
    };
    public static Comparator<Question> dateComparator = new Comparator<Question>() {
        @Override
        public int compare(Question question1, Question question2) {
            Date createdAt1 = question1.getCreatedAt();
            Date createdAt2 = question2.getCreatedAt();
            return createdAt1.compareTo(createdAt2);
        }
    };
    private String questionTypeString;
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private int questionType;
    private List<QuestionOption> questionOptions = new ArrayList<>();
    private Date createdAt;
    private List<Answers> answers = new ArrayList<>();

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

    public String getQuestionTypeString() {
        return questionTypeString;
    }

    public void setQuestionTypeString(String questionTypeString) {
        this.questionTypeString = questionTypeString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuestionTypeStringMapping(int questionType) {
        String question;
        switch (questionType) {
            case NUMERIC:
                question = "Numeric";
                break;
            case MULTIPLE_CHOICE_CHOOSE_MANY:
                question = "Multiple Choice, Choose Many";
                break;
            case MULTIPLE_CHOICE_CHOOSE_ONE:
                question = "Multiple Choice, Choose One";
                break;
            case FREE_TEXT:
                question = "Free Text";
                break;
            default:
                question = null;
        }
        return question;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

}

