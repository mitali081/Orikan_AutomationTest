package pages;

import base.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.CommonUtilities.getCustomProperty;
import static base.CommonUtilities.staticWait;
import static java.sql.DriverManager.getDriver;

public class RegistrationPage extends DriverSetup {
     public WebDriver driver;
    Wait<WebDriver> wait = new FluentWait<>(driver)
            .pollingEvery(Duration.ofMillis(100))
            .ignoring(NoSuchElementException.class)
            .withTimeout(Duration.ofSeconds(30));

    //locators
    private final By emailId = By.xpath("//input[@id='emailAddress']");
    private final By emailLabel = By.xpath("//label[text()='Email Address']");
    private final By password = By.xpath("//input[@id='password']");
    private final By confirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By nxtBtn = By.xpath("//button[contains(text(),'Next')]");
   // private final By userAlreadyExistsMsg = By.xpath("//div[contains(text(),'Email address is already registered')]");
   private final By firstNameLabel = By.xpath("//label[contains(text(),'First Name')]");
   private final By incorrectEmailWarning = By.xpath("//span[contains(text(),'Invalid email address')]");
    private final By incorrectPasswordWarning = By.xpath("//span[contains(text(),'Password is required')]");
    private final By incorrectConfirmPasswordWarning = By.xpath("//span[contains(text(),'Confirm Password is required')]");
    private final By passwordsMismatchWarning = By.xpath("//span[contains(text(),'Passwords do not mach')]");
    private final By registrationSuccessToastMessage = By.xpath("//div[contains(text(),'Email address is available for registration')]");
    private final By registrationFailureToastMessage = By.xpath("//div[contains(text(),'Email address is already registered')]");
    public RegistrationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loadRegistrationPage()
    {
        System.out.println(getCustomProperty("homePageUrl"));
        driver.get(getCustomProperty("homePageUrl"));
        staticWait(2);
    }

    public void setEmailId(String email)
    {
        driver.findElement(emailId).sendKeys(email);
    }
    public void setPassword(String pswd)
    {
        driver.findElement(password).sendKeys(pswd);
    }
    public void setConfirmPassword(String confirm)
    {
        driver.findElement(confirmPassword).sendKeys(confirm);
    }

    public void setNextBtn()
    {
        driver.findElement(nxtBtn).click();
    }

    public RegistrationPage registerUser(String email, String pswd, String confirm)
    {
        setEmailId(email);
        staticWait(2);
        setPassword(pswd);
        staticWait(2);
        setConfirmPassword(confirm);
        staticWait(2);
        setNextBtn();
        staticWait(5);
        return new RegistrationPage(driver);

    }

    public boolean getIncorrectEmailWarning()
    {
        staticWait(3);
        return driver.findElement(incorrectEmailWarning).isDisplayed();
    }
    public boolean getIncorrectPasswordWarning() {
        staticWait(3);
        return driver.findElement(incorrectPasswordWarning).isDisplayed();}
    public boolean getIncorrectConfirmPasswordWarning() {
        staticWait(3);
        return driver.findElement(incorrectConfirmPasswordWarning).isDisplayed();}
    public boolean getPasswordsMismatchWarning() {
        staticWait(3);
        return driver.findElement(passwordsMismatchWarning).isDisplayed();}
    public boolean getFirstNameLabel(){
        staticWait(3);
        return driver.findElement(firstNameLabel).isDisplayed();}
    public boolean getRegistrationSuccessMessage()
    {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(registrationSuccessToastMessage))).isDisplayed();
    }
    public Boolean getRegistrationFailureMessage()
    {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(registrationFailureToastMessage))).isDisplayed();
    }

}
