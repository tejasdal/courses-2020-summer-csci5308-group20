package CSCI5308.GroupFormationTool.Question;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question implements IQuestion {
    public static final int NUMERIC = 1;
    public static final int MULTIPLE_CHOICE_CHOOSE_ONE = 2;
    public static final int MULTIPLE_CHOICE_CHOOSE_MANY = 3;
    public static final int FREE_TEXT = 4;

    private String questionTypeString;
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private int questionType;
    private List<QuestionOption> questionOptions = new ArrayList<>();
    private Date createdAt;
    private List<Answers> answers;
    
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
        this.answers = new ArrayList<Answers>();
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

    @Override
    public String getQuestionTypeString() {
        return questionTypeString;
    }

    @Override
    public void setQuestionTypeString(String questionTypeString) {
        this.questionTypeString = questionTypeString;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int getQuestionType() {
        return questionType;
    }

    @Override
    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Override
    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    @Override
    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
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

    public static Comparator<IQuestion> titleComparator = new Comparator<IQuestion>() {
        @Override
        public int compare(IQuestion question1, IQuestion question2) {
            String title1 = question1.getTitle();
            String title2 = question2.getTitle();

            return title1.compareTo(title2);
        }
    };

    public static Comparator<IQuestion> dateComparator = new Comparator<IQuestion>() {
        @Override
        public int compare(IQuestion question1, IQuestion question2) {
            Date createdAt1 = question1.getCreatedAt();
            Date createdAt2 = question2.getCreatedAt();
            return createdAt1.compareTo(createdAt2);
        }
    };

    @Override
    public List<Answers> getAnswers() {
		return answers;
	}
    
    @Override
    public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}
}

