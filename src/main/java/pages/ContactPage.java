package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.CommonUtilities.staticWait;

public class ContactPage{

    public WebDriver driver;

    private final By firstName = By.xpath("//input[@id='firstName']");
    private final By middleName = By.xpath("//input[@id='middleName']");
    private final By lastName = By.xpath("//input[@id='lastName']");
    private final By preferredName = By.xpath("//input[@id='preferredFullName']");
    private final By addressFirstLine = By.xpath("//input[@id='addressLine1']");
    private final By postCode = By.xpath("//input[@id='postcode']");
    private final By city = By.xpath("//input[@id='city']");
    private final By state = By.xpath("//select[@id='state']");
    private final By nextBtnContact = By.xpath("//button[text()='Next']");
    private final By cardHolderLabel = By.xpath("//label[text()='Card Holder Name']");
    private final By firstNameLabel = By.xpath("//label[contains(text(),'First Name')]");
    public ContactPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void setFirstName(String fName)
    {
        driver.findElement(firstName).sendKeys(fName);
    }
    public void setLastName(String lName)
    {
        driver.findElement(lastName).sendKeys(lName);
    }
    public void setAddressFirstLine(String address1)
    {
        driver.findElement(addressFirstLine).sendKeys(address1);
    }
    public void setPostcode(String postcode)
    {
        driver.findElement(postCode).sendKeys(postcode);
    }

    public void setCity(String city1) {driver.findElement(city).sendKeys(city1);}
    public void setState(String state1)
    {
        driver.findElement(state).sendKeys(state1);
    }
    public void setNextBtnContact()
    {
        driver.findElement(nextBtnContact).click();
    }
    public ContactPage userContact(String fName, String lName, String address1, String postcode,
                                   String city1, String state1)
    {
        staticWait(3);
        setFirstName(fName);
        staticWait(2);
        setLastName(lName);
        staticWait(2);
        setAddressFirstLine(address1);
        staticWait(2);
        setPostcode(postcode);
        staticWait(2);
        setCity(city1);
        staticWait(2);
        setState(state1);
        staticWait(2);
        setNextBtnContact();
        staticWait(10);
        return new ContactPage(driver);
    }

    public String getPreferredName()
    {
        staticWait(3);
        return driver.findElement(preferredName).getText();
    }

    public boolean checkFirstNameLabel(){
        staticWait(3);
        return driver.findElement(firstNameLabel).isDisplayed();}

}
