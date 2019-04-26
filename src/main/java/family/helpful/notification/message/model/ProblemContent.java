package family.helpful.notification.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProblemContent extends BasicModel  {

    private String text;


    @JsonIgnoreProperties("problemContents")

    private ProblemTitle problemTitle;


    @JsonIgnoreProperties("problemContents")
    private User user;

    private boolean firstContent;


    Long currentThankAmount;


    public ProblemContent() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProblemTitle getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(ProblemTitle problemTitle) {
        this.problemTitle = problemTitle;
    }

    public boolean isFirstContent() {
        return firstContent;
    }

    public void setFirstContent(boolean firstContent) {
        this.firstContent = firstContent;
    }


    public String toString(){
     return   this.getClass().getSimpleName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }
}
