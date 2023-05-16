import POM.ConstructorElements;
import POM.TestFixtures;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class ConstructorTests extends TestFixtures {
    static ConstructorElements constructorElements = new ConstructorElements(driver);
    private By ingredientTab;


    public ConstructorTests(By ingredientTab) {
        this.ingredientTab=ingredientTab;
    }

    @Parameterized.Parameters(name = "Для вкладки: {0}, ингредиенты: {1}")
    public static Object[][] openTabExpectHeader(){
        return new Object[][] {
                {constructorElements.getBunsTab()},
                {constructorElements.getFillingsTab()},
                {constructorElements.getSaucesTab()},

        };
    }
    @Test
    public void openIngredientTabCheckIngredientSection(){
        String classAttribute = driver.findElement(ingredientTab).getAttribute("class");
        // Так как по дефолту вкл "Булки" активна, но при попытке click() на нее падает ошибка
        // Поменял try catch на проверку наличия у класса current
        // Придется проверять клик по вкладке только по изменению класса вкладки
        if(!classAttribute.contains("current")) {
            constructorElements.clickTab(ingredientTab);
        }
        Assert.assertTrue(driver.findElement(ingredientTab).getAttribute("class").contains("current"));
    }
}
