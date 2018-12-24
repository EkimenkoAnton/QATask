package daoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Home on 21.12.2018.
 */
public class HomePage extends Page {

    private By signUpButton = By.cssSelector("div.r button");
    private By emailField = By.cssSelector("label.modal-form-trial__label input");
    private By confirmButton = By.cssSelector("label.modal-form-trial__label button");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void clickSingUpButton(){
        tryClick(signUpButton);
    }

    private void fillEmailFIeld(){
        tryFill(emailField,getEmail());
    }

    private void clickConfirmButton(){
        tryClick(confirmButton);
    }

    public SignUpPage singnIn(){
        clickSingUpButton();
        fillEmailFIeld();
        clickConfirmButton();
        return new SignUpPage(driver);
    }

}
