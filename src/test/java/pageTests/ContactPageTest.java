package pageTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IConfigureCallBack;
import org.testng.annotations.*;
import pages.ContactPage;
import pages.RegistrationPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Listeners(base.ListenerHelper.class)
public class ContactPageTest extends BaseTest {
    public ContactPageTest() {
        key = "contactpage";
    }

    //Working Scenario
    @Test(priority = 3, description = "Verify user should not be taken to Payment page without filling any fields in Contact page")
    public void testNavToPayment_WithoutFillingContactDetails()
    {
        driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
        driver.findElement(By.xpath("//input[@id='emailAddress']")).sendKeys("Test@test");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test128");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Test128");
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
        staticWait(5);
        contactPage.setNextBtnContact();
        Boolean test3 = contactPage.checkFirstNameLabel();
        Assert.assertEquals(test3, true,"User should not taken to payment page as the contact details are not filled.");
    }
    //Working Scenario
    @Test(priority = 4,description = "Verify user should not be taken to payment page if even 1 mandatory field is not filled in Contact Page", dataProvider = "getContactPageData")
    public void testPasswordMismatchError(HashMap<String,String>input)
    {
//        driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
//        driver.findElement(By.xpath("//input[@id='emailAddress']")).sendKeys("Test@test");
//        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test128");
//        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Test128");
//        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
        contactPage.page.loadRegistrationPage();
        contactPage.page.registerUser("test@test","test","test");
        staticWait(5);
        contactPage.userContact(input.get("firstName1"),input.get("lastName1"),input.get("addressFirstLine1"),input.get("postcode1"),"",input.get("state1"));
        Boolean test4 = contactPage.checkFirstNameLabel();
        Assert.assertEquals(test4, true,"User should not taken to payment page as mandatory field 'city' is not filled.");
    }

    @DataProvider
    public Object[][] getContactPageData() throws IOException
    {
        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/testData/contact_data.json");
        return new Object[][]
                {
                        {data.get(0)}
                };

    }

}
