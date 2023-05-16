import POM.*;
import POM.Helpers.APICalls;
import POM.Helpers.UserSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RegistrationTests extends TestFixtures {
    private NavbarElements navbarElements = new NavbarElements(driver);
    private LoginFormElements loginFormElements = new LoginFormElements(driver);
    private RegistrationFormElements registrationFormElements= new RegistrationFormElements(driver);
    private APICalls apiCalls = new APICalls();
    public String error;
    public RegistrationTests(String name,String email,String password,String error) {
        this.name=name;
        this.email=email;
        this.password=password;
        this.error=error;
    }

    @Parameterized.Parameters(name = "Для данных: {0} {1} {2}, статус: {3}")
    public static Object[][] openTabExpectHeader(){
        return new Object[][] {
                {"example", "example@example.com", "123456", "ОК"},
                {"example", "example@example.com", "123", "Некорректный пароль"},
        };
    }

    @Test
    public void registerNewUserWithCorrectData() throws InterruptedException {
        navbarElements.clickCabinetButton();
        loginFormElements.clickSignUpLink();
        registrationFormElements.completeRegistrationForm(name, email, password);
        String errorMessage = registrationFormElements.clickSignUpButton();

        if (errorMessage != null) {
            Assert.assertEquals(errorMessage,"Некорректный пароль");
        } else {
            isUserCreated = apiCalls.checkCreatedUser(new UserSerializer(email, password));
            Assert.assertTrue(isUserCreated);
        }
    }
}
