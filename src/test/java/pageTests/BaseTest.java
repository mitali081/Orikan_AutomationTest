package pageTests;

import base.DriverSetup;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.*;

public class BaseTest extends DriverSetup {

    protected RegistrationPage registrationPage;
    protected ContactPage contactPage;
    protected PaymentPage paymentPage;
    protected TermsPage termsPage;
    protected CompletePage completePage;

    //Key for pageObject initialization
    protected String key;


    public BaseTest()
    {
        browserTypeKey = "chrome";
    }
    @BeforeTest(alwaysRun = true)
    public void setup()
    {
        setupDriver();
        if(key.equalsIgnoreCase("registrationpage"))
        {
            registrationPage = new RegistrationPage(driver);
        } else if(key.equalsIgnoreCase("contactpage"))
        {
            contactPage = new ContactPage(driver);
        } else if(key.equalsIgnoreCase("paymentpage"))
        {
            paymentPage = new PaymentPage(driver);
        } else if(key.equalsIgnoreCase("termspage"))
        {
            termsPage = new TermsPage(driver);
        } else if(key.equalsIgnoreCase("completepage"))
        {
            completePage = new CompletePage(driver);
        } else {
            System.out.println("Page not yet defined!!");
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown()
    {
        quitDriver();
    }
}
