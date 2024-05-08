package pageTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Listeners(base.ListenerHelper.class)
public class RegistrationPageTest extends BaseTest {

    public RegistrationPageTest() {
        key = "registrationpage";
    }

    //Testing Working Scenario: 2
    @Test(priority = 2, description = "Verify working scenario, Register a new user and is routed to Contact page, Success Message is shown to user",
            dataProvider = "getRegisterUserData")
    public void testPositiveRegistrationOfUser(HashMap<String,String> input)
    {

        registrationPage.loadRegistrationPage();
        registrationPage = registrationPage.registerUser(input.get("email1"),input.get("pswd1"),input.get("confirm1"));
        Boolean test2 = registrationPage.getRegistrationSuccessMessage();
        Assert.assertEquals(test2, true,"Success toast message is shown to user.");
        Boolean test1 = registrationPage.getFirstNameLabel();
        Assert.assertEquals(test1, true,"User is registered and moved to Contact Page.");

    }
    //Testing Working Scenario: 3
    @Test(priority = 3, description = "Verify working scenario, Register with existing user and Failure Message is shown to user",
            dataProvider = "getRegisterUserData")
    public void testRegistrationWithExistingUser(HashMap<String,String> input)
    {

        registrationPage.loadRegistrationPage();
        registrationPage = registrationPage.registerUser(input.get("email"),input.get("pswd"),input.get("confirm"));
        Boolean test3 = registrationPage.getRegistrationFailureMessage();
        Assert.assertEquals(test3, true,"Failure toast message is shown to user.");
    }
    //Testing Working Scenario: 1
    @Test(priority = 1,description = "Verify working scenario where if password and confirm password mismatch, error message is shown to user", dataProvider = "getRegisterUserData")
    public void testPasswordMismatchError(HashMap<String,String>input)
    {

        registrationPage.loadRegistrationPage();
        registrationPage = registrationPage.registerUser(input.get("email2"),input.get("pswd2"),input.get("confirm2"));
        Boolean test4 = registrationPage.getPasswordsMismatchWarning();
        Assert.assertEquals(test4, true);
    }


    @DataProvider
    public Object[][] getRegisterUserData() throws IOException
    {
        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/testData/registration_data.json");
        return new Object[][]
                {
                        {data.get(0)}
                };

    }

}
