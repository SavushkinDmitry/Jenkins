package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StepAvito {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] captureScreenshot(Screenshot screenshot) {
        //Создаём объект в котором будут записаны данные в массив байтов
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            //Записываем скриншот, указываем формат и куда будет записываться наш скриншот
            // (в начало текущего указателя потока)
            ImageIO.write(screenshot.getImage(), "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //С помощью toByteArray получаем данные
        return byteArrayOutputStream.toByteArray();
    }

    @Attachment(value = "Результат вывода в консоль")
    public String saveResult(String result) {
        return result;
    }

    @Step("Открывается ресурс авито")
    @Пусть("открыт ресурс авито")
    public void openResource() {
        driver.get("https://www.avito.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);

    }

    @ParameterType(".*")
    public ProductCategory categoryProduct(String name) {
        return ProductCategory.valueOf(name);
    }

    @Step("И в выпадающем списке категорий выбрана категория товаров")
    @И("в выпадающем списке категорий выбрана {categoryProduct}")
    public void selectCategory(ProductCategory categoryProduct) {
        Select sCategory = new Select(driver.findElement(By.cssSelector(categoryProduct.getId())));
        sCategory.selectByVisibleText(categoryProduct.getName());

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("И в поле поиска введено наименование товара")
    @И("в поле поиска введено значение {word}")
    public void searchProduct(String nameProduct) {
        WebElement inputSearchProduct = driver.findElement(By.xpath("//input[@data-marker='search-form/suggest']"));
        inputSearchProduct.sendKeys(nameProduct);

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("И активирован чекбокс только с фотографией")
    @И("активирован чекбокс только с фотографией")
    public void checkboxWithPhoto() {
        WebElement checkbox = driver.findElement(By.xpath("//input[@data-marker='search-form/with-images']"));
        if (!checkbox.isSelected()) {
            checkbox.sendKeys(Keys.SPACE);
        }

        Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Тогад нужно кликнуть по выпадающему списку региона")
    @Тогда("кликнуть по выпадающему списку региона")
    public void clickListRegion() {
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Тогда в поле регион нужно ввести название региона")
    @Тогда("в поле регион введено значение {word}")
    public void searchRegion(String nameRegion) {
        WebElement inputSearchRegion =  driver.findElement(By.xpath("//div/input[@data-marker='popup-location/region/input']"));
        inputSearchRegion.sendKeys(nameRegion);

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-marker='suggest(0)']"))).click();
    }

    @Step("И нужно нажать кнопку показать объявление")
    @И("нажата кнопка показать объявления")
    public void clickAds() {
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Тогда открылась страница результата по запросу")
    @Тогда("открылась страница результата по запросу {word}")
    public void checkVerifyPage(String namePage) {
        WebElement resultRequest = driver.findElement(By.xpath("//h1[@data-marker='page-title/text']"));
        Assert.assertEquals(resultRequest.getText(), "Объявления по запросу «" + namePage + "» во Владивостоке");

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @ParameterType(".*")
    public PriceCategory priceCategory(String name) {
        return PriceCategory.valueOf(name);
    }

    @Step("И в выпадающем списке сортировки по ценам выбрать категорию")
    @И("в выпадающем списке сортировка выбрано значение {priceCategory}")
    public void selectPriceCategory(PriceCategory priceCategory) {
        Select selectCPrice = new Select(driver.findElement(By.xpath(priceCategory.getId())));
        selectCPrice.selectByVisibleText(priceCategory.getName());

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("И в консоль вывести наименование и цену выбранных товаров")
    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void searchAndSelectProduct(int count) {
        By itemTitle = By.xpath(".//a[@data-marker='item-title']");
        By itemPrice = By.xpath(".//span[@data-marker='item-price']");

        List<WebElement> titleProduct = driver.findElements(itemTitle);
        List<WebElement> priceProduct = driver.findElements(itemPrice);

        for (int i = 0; i < count; i++) {
            String result = "Наименование товара: " + titleProduct.get(i).getText() +
                    "\n Цена товара: " + priceProduct.get(i).getText() + " (" + i + ").";

            System.out.println(saveResult(result));
        }

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

}
