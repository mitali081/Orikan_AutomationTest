package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.CommonUtilities.staticWait;

public class CompletePage{
    public WebDriver driver;
    public CompletePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public boolean getSuccessRegistrationMsg(String email)
    {
        staticWait(3);
        return driver.findElement(By.xpath("//p[contains(text(),'Your user account '"+email+"' has been successfully registered')]")).isDisplayed();
    }
}
