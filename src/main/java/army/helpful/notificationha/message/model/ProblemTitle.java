package army.helpful.notificationha.message.model;



import java.util.ArrayList;
import java.util.List;


public class ProblemTitle extends BasicModel {
      private List<ProblemContent> problemContents = new ArrayList<>();
      User user;
     Long currentThankAmount;

    public Long getCurrentThankAmount() {
        return currentThankAmount;
    }

    public void setCurrentThankAmount(Long currentThankAmount) {
        this.currentThankAmount = currentThankAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
