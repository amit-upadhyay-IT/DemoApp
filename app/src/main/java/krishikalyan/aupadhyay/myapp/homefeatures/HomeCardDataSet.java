package krishikalyan.aupadhyay.myapp.homefeatures;

/**
 * Created by aupadhyay on 4/1/17.
 */

public class HomeCardDataSet {

    private String titleMessage, hintMessage;
    private int bgColor;

    public HomeCardDataSet(String titleMessage, String hintMessage, int bgColor) {
        this.titleMessage = titleMessage;
        this.hintMessage = hintMessage;
        this.bgColor = bgColor;
    }

    public HomeCardDataSet() {
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public String getHintMessage() {
        return hintMessage;
    }

    public void setHintMessage(String hintMessage) {
        this.hintMessage = hintMessage;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}
