package daoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage extends Page {

    private final By framePopUp = By.cssSelector("iframe[src^='https']");
    private final By buttonclosePopUpFrame = By.cssSelector("div[jsname='I5FQ0b']");
    private final By titleCustom = By.cssSelector("p.h4.subtitle:nth-child(3)");

    private final By buttonVeryIntetested = By.xpath("//button[contains(text(),'Very interested')]");
    private final By buttonJustLooking = By.xpath("//button[contains(text(),'Just looking')]\n");

    private final By button1moreMembers = By.xpath("//button[contains(text(),'1–5')]");
    private final By button6moreMembers = By.xpath("//button[contains(text(),'6–15')]");
    private final By button16moreMembers = By.xpath("//button[contains(text(),'16–25')]");
    private final By button26moreMembers = By.xpath("//button[contains(text(),'26–50')]");
    private final By button50moreMembers = By.xpath("//button[contains(text(),'50+')]");

    private final By buttonYes = By.xpath("//button[contains(text(),'Yes')]");
    private final By buttonNo = By.xpath("//button[contains(text(),'No')]");

    private final By buttonOther = By.xpath("//label[@class='switch switch--text switch--fullwidth']//button[@type='button']");
    private final By fieldOther = By.xpath("//input[@placeholder='Your comment']");

    private final By buttonConfirm = By.cssSelector("div.survey button[type='submit']");
    private final By textConfirm = By.cssSelector("div.survey-success h3");

    private final By buttonResend = By.xpath("//button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']");
    private final By statusResend = By.xpath("//button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button wg-btn--loading']");

    private By twitterLink = By.cssSelector(".wg-footer__bottom-section a[href='https://twitter.com/wrike']");
    private final By svgTwitter = By.cssSelector(".wg-footer__bottom-section a[href='https://twitter.com/wrike'] svg");
    private final By svgTwitterSource = By.cssSelector(".wg-footer__bottom-section a[href='https://twitter.com/wrike'] svg use");

    private String expectedImagePath="/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v1#twitter";


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void closePopUpFrame(){
        switchToFrame(framePopUp);
        tryClick(buttonclosePopUpFrame);
        switchToDefaultContent();
    }

    public String getCustomTitle(){
        return getTextFilld(titleCustom);
    }

    private void fillQuestionBlock(){
        By element;
        element = getRandomElemetFromArray(buttonVeryIntetested,buttonJustLooking);
        tryClick(element);
        element = getRandomElemetFromArray(button1moreMembers,button6moreMembers,button16moreMembers,button26moreMembers,button50moreMembers);
        tryClick(element);
        element = getRandomElemetFromArray(buttonYes,buttonNo);
        tryClick(element);
    }

    private void confirmQuestionBlock(){
        tryClick(buttonConfirm);
    }

    public String getTexConfirm(){
        return getTextFilld(textConfirm);
    }

    public void fillAndConfirmQuestionBlock(){
        fillQuestionBlock();
        confirmQuestionBlock();
    }

    public void clickResendButton(){
        tryClick(buttonResend);
    }

    public Boolean getStatusResend(){
        WebElement element = getElementWithCustomWayting(statusResend);
        return (null!=element && element.isEnabled());

    }

    public Boolean checkTwitterLink(String link){
        if (link != null && !link.isEmpty())
            twitterLink = By.cssSelector(createString(".wg-footer__bottom-section a[href='"+link+"']"));
        WebElement element = getElementWithCustomWayting(twitterLink);
        return null != element;
    }

    public Boolean isVisibleTwitterImage(){
        return isElementIsVisible(svgTwitter);
    }

    public Boolean isValidSourceTwitterImage(String path){
        if (path != null && !path.isEmpty())
            expectedImagePath=path;
        String svgSource = getAttribute(svgTwitterSource, "xlink:href");
        return (null != svgSource && svgSource.equals(expectedImagePath));
    }

}
