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

    //Testing Working Scenario: 1
    @Test(priority = 2, description = "Verify working scenario, Register a new user and is routed to Contact page",
            dataProvider = "getRegisterUserData")
    public void testPositiveRegistrationOfUser(HashMap<String,String> input)
    {

        registrationPage.loadRegistrationPage();
        registrationPage = registrationPage.registerUser(input.get("email1"),input.get("pswd1"),input.get("confirm1"));
        Boolean test1 = registrationPage.getFirstNameLabel();
        Assert.assertEquals(test1, true,"User is registered and moved to Contact Page.");
    }
    //Testing Working Scenario: 2
    @Test(priority = 1,description = "Verify working scenario where if password and confirm password mismatch, error message is shown to user", dataProvider = "getRegisterUserData")
    public void testPasswordMismatchError(HashMap<String,String>input)
    {

        registrationPage.loadRegistrationPage();
        registrationPage = registrationPage.registerUser(input.get("email2"),input.get("pswd2"),input.get("confirm2"));
        Boolean test2 = registrationPage.getPasswordsUnmatchWarning();
        Assert.assertEquals(test2, true,"test2");
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
