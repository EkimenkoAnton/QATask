package daoPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.Random;

abstract class Page {

    protected WebDriver driver;
    private static final String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private SecureRandom rnd = new SecureRandom();
    private static final int attemptCount=3;

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    protected String getEmail(){
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < 8; i++ )
            sb.append( Alphabet.charAt( rnd.nextInt(Alphabet.length()) ) );
        return sb.append("wpt@wriketask.qaa").toString();
    }

    protected WebElement getElementWithCustomWayting(By by) {
        int i=attemptCount;
        while (i>0) {
            try {
                return driver.findElement(by);
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    private WebElement getVisibleElement(By locator){
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isElementIsVisible(WebElement element){
        return element != null && element.isDisplayed() && element.getSize().getHeight() > 0 && element.getSize().getWidth() > 0;
    }

    protected boolean isElementIsVisible(By locator){
        return isElementIsVisible(getVisibleElement(locator));
    }

    protected boolean tryClick(WebElement element){
        int i=3;
        while (i>0) {
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element)).click();
                return true;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    protected boolean tryClick(By locator){
        int i=attemptCount;
        while (i>0) {
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(locator)).click();
                return true;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    protected boolean tryFill(By locator, String keys){
        int i=attemptCount;
        while (i>0) {
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(keys);
                return true;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    protected boolean switchToFrame(By locator){
        WebElement buttons =
                new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.switchTo().frame(buttons);
        return true;
    }

    protected boolean switchToDefaultContent(){
        driver.switchTo().defaultContent();
        return true;
    }

    protected String getTextFilld(By locator){
        int i=attemptCount;
        while (i>0) {
            try {
                return getVisibleElement(locator).getText();
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    protected String getAttribute(By locator,String attribute){
        if (null == attribute || attribute.isEmpty()) return null;
        int i=attemptCount;
        while (i>0) {
            try {
                return getVisibleElement(locator).getAttribute(attribute);
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    public By getRandomElemetFromArray(By... locators){
        if(null == locators ) return null;
        if (0 == locators.length) return locators[0];
        return locators[new Random().nextInt(locators.length)];
    }

    public String createString(Object... objs){
        if (null == objs) return "";
        if (objs.length==1) return (null != objs[0]) ? objs[0].toString() : "";
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj:objs) {
            if(null != obj) stringBuilder.append(obj.toString());
        }
        return stringBuilder.toString();
    }

}
