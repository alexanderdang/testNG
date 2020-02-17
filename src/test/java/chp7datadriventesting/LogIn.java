package chp7datadriventesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogIn {

    //Class that has both test method and data provider

    private SoftAssert softassert = new SoftAssert();

    @Test(dataProvider = "logInData") //dataProvider = ("signin-provider") - can refer to @DataProvider assigned name instead
    public void logIn (String email, String password, boolean success) {

        System.out.println("Log In Credentials: " + "\n" +
                        " Email = " + email + "\n" +
                        " Password = " + password + "\n" +
                        " Successful Log In = " + success + "\n");

        //soft assert used to illustrate that each data set has it's own test result
        softassert.assertTrue( false, "Login Error");
        softassert.assertAll();
    }

    @DataProvider //(name = "signin-provider) - assigning @DataProvider a name is optional
    public Object [][] logInData() {

        Object [][] data = new Object [3][3];

        data [0][0] = "TestNG@Framework.com";
        data [0][1] = "TestNG1234";
        data [0][2] = true;

        data [1][0] = "Joe@Doe.com";
        data [1][1] = "DoeDoe34";
        data [1][2] = false;

        data [2][0] = "Test@Automation.com";
        data [2][1] = "TA1234";
        data [2][2] = true;

        return data;
    }
}
