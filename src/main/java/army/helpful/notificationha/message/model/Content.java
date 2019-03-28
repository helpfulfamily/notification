package army.helpful.notificationha.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Content extends BasicModel {

    private String text;
     private Title title;


    private boolean firstContent;

    public Content() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
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

}
