package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static base.CommonUtilities.staticWait;

public class PaymentPage{
    public WebDriver driver;
    private final By cardHolderName = By.xpath("//input[@id='cardHolderName']");
    private final By cardTypeVisa = By.xpath("//input[@id='cardTypeVISA']");
    private final By cardTypeMastercard = By.xpath("//input[@id='cardTypeMastercard']");
    private final By cardNumber = By.xpath("//input[@id='cardNumber']");
    private final By cvv = By.xpath("//input[@id='cardCVV']");
    private final By cardExpiryMonth = By.xpath("//select[@id='cardExpiryMonth']");
    private final By cardExpiryYear = By.xpath("//input[@id='cardExpiryYear']");
    private final By nextBtnPayment = By.xpath("//button[text()='Next']");
    private final By cardHolderLabel = By.xpath("//label[text()='Card Holder Name']");
    public PaymentPage(WebDriver driver)
    {
        this.driver =driver;

    }

    public void setCardHolderName(String cName)
    {
        driver.findElement(cardHolderName).sendKeys(cName);
    }
    public void setCardType(String cType)
    {
        if(cType.equals("VISA"))
        {
            driver.findElement(cardTypeVisa).click();
        }
        else if(cType.equals("Mastercard")){
            driver.findElement(cardTypeMastercard).click();
        }
    }
    public void setCardNumber(String cNumber)
    {
        driver.findElement(cardNumber).sendKeys(cNumber);
    }
    public void setCvv(String cvvNum)
    {
        driver.findElement(cvv).sendKeys(cvvNum);
    }
    public void setCardExpiryMonth(String eMonth)
    {
       Select select = new Select(driver.findElement(cardExpiryMonth));
       select.selectByVisibleText(eMonth);

    }
    public void setCardExpiryYear(String eYear)
    {
        driver.findElement(cardExpiryYear).sendKeys(eYear);
    }
    public void setNextBtnPayment()
    {
        driver.findElement(nextBtnPayment).click();
    }

    public PaymentPage userPayment(String cName, String cType, String cNumber, String cvvNum,
                                   String eMonth, String eYear)
    {
        setCardHolderName(cName);
        staticWait(2);
        setCardType(cType);
        staticWait(2);
        setCardNumber(cNumber);
        staticWait(2);
        setCvv(cvvNum);
        staticWait(2);
        setCardExpiryMonth(eMonth);
        staticWait(2);
        setCardExpiryYear(eYear);
        staticWait(2);
        setNextBtnPayment();
        staticWait(10);
        return new PaymentPage(driver);

    }

    public boolean getCardHolderLabel(){
        staticWait(3);
        return driver.findElement(cardHolderLabel).isDisplayed();}

}
