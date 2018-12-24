import daoPages.HomePage;
import daoPages.SignUpPage;
import io.qameta.allure.Feature;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Home on 20.12.2018.
 */
public class TestWrike {

    private static final String siteUrl = "https://www.wrike.com/";
    private static final String webdriverName = "webdriver.chrome.driver";
    private static final String webdriverPath = "C:\\Users\\Home\\IdeaProjects\\chromedriver.exe";
    //private static final String webdriverPath = "/home/anton/IdeaProjects/Allure/chromedriver";

    private static final String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static SecureRandom rnd = new SecureRandom();


    private WebDriver driver;

    @Before
    public void setup(){
        if (null == webdriverPath || !(new File(webdriverPath).exists()))
            return;
        System.setProperty(webdriverName,webdriverPath);
        driver = new ChromeDriver();
        if (null == driver) return;
        driver.manage().window().maximize();
        driver.get(siteUrl);
    }

    /**
     * Test case scenario:
     * 1. Open url: wrike.com;
     * 2. Click "Get started for free" button near "Login" button;
     * 3. Fill in the email field with random generated value of email with mask “<random_text>+wpt@wriketask.qaa” (e.g. “abcdef+wpt@wriketask.qaa”);
     * 4. Click on "Create my Wrike account" button + check with assertion that you are moved to the next page;
     * 5. Fill in the Q&A section at the left part of page (like random generated answers) + check with assertion that your answers are submitted;
     * 5. Click on "Resend email" + check it with assertion;
     * 6. Check that section "Follow us" at the site footer contains the "Twitter" button that leads to the correct url and has the correct icon.
     */
    @Feature(value = "checkSingUp")
    @Test
    public void testCase(){
        String text="sent you an activation email";
        String twitterUrl="https://twitter.com/wrike";
        String expectedImagePath="/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v1#twitter";

        LogUtil.log("Open url :"+siteUrl);

        HomePage homePage = new HomePage(driver);

        LogUtil.log("Click \"Get started for free\" button near \"Login\" button;");
        LogUtil.log("Fill email field");
        LogUtil.log("Click on \"Create my Wrike account\" button");
        SignUpPage signUpPage = homePage.singnIn();
        signUpPage.closePopUpFrame();
        String customSingUpTitle = signUpPage.getCustomTitle();
        LogUtil.log("Check with assertion that moved to the next page");
        System.out.println("customSingUpTitle".toUpperCase()+customSingUpTitle);
        Assert.assertTrue(null != customSingUpTitle && customSingUpTitle.contains(text));

        LogUtil.log("Fill in the Q&A section at the left part of page");
        signUpPage.fillAndConfirmQuestionBlock();
        String sumitText = signUpPage.getTexConfirm();
        LogUtil.log("Check with assertion that your answers are submitted");
        Assert.assertEquals("Thanks for helping us out!",sumitText);

        LogUtil.log("Click on \"Resend email\" + check it with assertion;");
        signUpPage.clickResendButton();
        Assert.assertTrue(signUpPage.getStatusResend());

        LogUtil.log("Check that section \"Follow us\" at the site footer contains the \"Twitter\" button that leads to the correct url and has the correct icon.");
        Assert.assertTrue(signUpPage.checkTwitterLink(twitterUrl)
                && signUpPage.isVisibleTwitterImage()
                && signUpPage.isValidSourceTwitterImage(expectedImagePath));

    }


    @After
    public void quiet(){
        driver.close();
    }

}
