package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TermsPage{
     WebDriver driver;

    public TermsPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    private final By termsLabel = By.xpath("//label[text()='Terms and Conditions']");
    private final By agreeCheckbox = By.xpath("//input[@id='agreedToTerms']");
    private final By submitButton = By.xpath("//button[text()='Submit']");

    public boolean getTermsLabel() {return driver.findElement(termsLabel).isDisplayed();}

    public TermsPage agreeAndSubmit()
    {
        if(driver.findElement(agreeCheckbox).isSelected())
        {
            Assert.assertTrue(driver.findElement(submitButton).isEnabled());
            driver.findElement(submitButton).click();
        }
        else {
            Assert.assertFalse(driver.findElement(submitButton).isEnabled());
            driver.findElement(agreeCheckbox).click();
            Assert.assertTrue(driver.findElement(submitButton).isEnabled());
            driver.findElement(submitButton).click();
        }
        return new TermsPage(driver);

    }
}
