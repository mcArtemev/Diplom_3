import POM.*;
import POM.Helpers.APICalls;
import POM.Helpers.Helpers;
import POM.Helpers.UserSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTests extends TestFixtures {
    ConstructorElements constructorElements = new ConstructorElements(driver);
    LoginFormElements loginFormElements = new LoginFormElements(driver);
    NavbarElements navbarElements = new NavbarElements(driver);
    CabinetElements cabinetElements = new CabinetElements(driver);
    RestorePasswordElements restorePasswordElements = new RestorePasswordElements(driver);
    RegistrationFormElements registrationFormElements = new RegistrationFormElements(driver);
    Helpers helpers = new Helpers();
    APICalls apiCalls = new APICalls();

    @Before
    public void testData() {
        name = "example";
        email = "example@example.com";
        password = "123456";

        apiCalls.createUser(new UserSerializer(name, email, password));
        isUserCreated = apiCalls.checkCreatedUser(new UserSerializer(email, password));
    }

    @Test
    public void enterInAccountFromConstructorPageEnterInAccountButton(){
        constructorElements.clickEnterInAccount();
        loginFormElements.completeLoginForm(email, password);
        loginFormElements.clickLoginButton();
        navbarElements.clickCabinetButton();
        helpers.waitElementDisplayed(driver, 5, cabinetElements.getCabinetData());
        Assert.assertEquals(cabinetElements.getUserName(), name);
        Assert.assertEquals(cabinetElements.getUserLogin(), email);
    }

    @Test
    public void enterInAccountFromNavbarPrivateCabinet(){
        navbarElements.clickCabinetButton();
        loginFormElements.completeLoginForm(email, password);
        loginFormElements.clickLoginButton();
        navbarElements.clickCabinetButton();
        helpers.waitElementDisplayed(driver, 5, cabinetElements.getCabinetData());
        Assert.assertEquals(cabinetElements.getUserName(), name);
        Assert.assertEquals(cabinetElements.getUserLogin(), email);
    }

    @Test
    public void enterInAccountFromRegistrationFormLinkSingIn(){
        navbarElements.clickCabinetButton();
        loginFormElements.clickSignUpLink();
        registrationFormElements.clickLoginLink();
        loginFormElements.completeLoginForm(email, password);
        loginFormElements.clickLoginButton();
        navbarElements.clickCabinetButton();
        helpers.waitElementDisplayed(driver, 5, cabinetElements.getCabinetData());
        Assert.assertEquals(cabinetElements.getUserName(), name);
        Assert.assertEquals(cabinetElements.getUserLogin(), email);
    }

    @Test
    public void enterInAccountFromRestorePasswordLinkSingIn(){
        navbarElements.clickCabinetButton();
        loginFormElements.clickRestorePasswordLink();
        restorePasswordElements.clickLoginLink();

        loginFormElements.completeLoginForm(email, password);
        loginFormElements.clickLoginButton();
        navbarElements.clickCabinetButton();
        helpers.waitElementDisplayed(driver, 5, cabinetElements.getCabinetData());
        Assert.assertEquals(cabinetElements.getUserName(), name);
        Assert.assertEquals(cabinetElements.getUserLogin(), email);
    }
}
