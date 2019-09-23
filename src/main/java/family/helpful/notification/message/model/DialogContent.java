package family.helpful.notification.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class DialogContent extends BasicModel {

    Long currentThankAmount;
    private String text;
    private User sender;
    private User receiver;

}
