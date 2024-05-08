package pageTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Listeners(base.ListenerHelper.class)

public class PaymentPageTest extends BaseTest {
    public PaymentPageTest() {
        key = "paymentpage";
    }

    //Issue 1: Single digits should not be allowed in Card Number, CVV
    @Test(priority = 5, description = "Verify that user should not be allowed to proceed if they enter single digit in the Card and CVV section",
            dataProvider = "getPaymentPageData")
    public void testPositiveRegistrationOfUser(HashMap<String,String> input)
    {

        driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
        driver.findElement(By.xpath("//input[@id='emailAddress']")).sendKeys("Test@test5");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test555");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Test555");
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
        staticWait(5);
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Test5");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Test5");
        driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys("Test555");
        driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("555555");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Queens");
        driver.findElement(By.xpath("//select[@id='state']")).sendKeys("Queensland");
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        staticWait(5);
        paymentPage.userPayment(input.get("cardHolderName2"),input.get("cardType2"),input.get("cardNumber2"),input.get("cvv2"),input.get("cardExpiryMonth2"),input.get("cardExpiryYear2"));
        Boolean test5 = paymentPage.getCardHolderLabel();
        Assert.assertEquals(test5, true,"User should not be allowed to proceed to terms page due to invalid cvv and card number.");
    }
    //Issue 2: Expiry year should not be less than current year
    @Test(priority = 6,description = "Verify user is not allowed to provide expiry year less than current year", dataProvider = "getPaymentPageData")
    public void testPasswordMismatchError(HashMap<String,String>input)
    {

        driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
        driver.findElement(By.xpath("//input[@id='emailAddress']")).sendKeys("Test@test6");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test666");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Test666");
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
        staticWait(5);
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Test6");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Test6");
        driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys("Test666");
        driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("666666");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Queens");
        driver.findElement(By.xpath("//select[@id='state']")).sendKeys("Queensland");
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        staticWait(5);
        paymentPage.userPayment(input.get("cardHolderName3"),input.get("cardType3"),input.get("cardNumber3"),input.get("cvv3"),input.get("cardExpiryMonth3"),input.get("cardExpiryYear3"));
        Boolean test6 = paymentPage.getCardHolderLabel();
        Assert.assertEquals(test6, true,"User should not be allowed to proceed to terms page due to invalid expiry year.");
    }

    @DataProvider
    public Object[][] getPaymentPageData() throws IOException
    {
        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/testData/payment_data.json");
        return new Object[][]
                {
                        {data.get(0)}
                };

    }
}
