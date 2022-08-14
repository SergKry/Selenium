package ru.netology;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;


public class DebitCardApplicationTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {     // драйвер для хрома
        System.setProperty("webdriver.chorome.driver", "./driver/win/choromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
        // открытие хрома

    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
    }


    @AfterEach
    void rearDown() {
        driver.quit();   // закрытие хрома
        driver = null;    // обнуление данных
    }

    @Test
    void shouldTestValidData() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Николаев Александр");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+79870599950");
        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click();
        driver.findElement(By.cssSelector("[type = button]")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim(); //.trim обрезает лишние пробелы в начале и конце

        Assertions.assertEquals(expected, actual);
    }


}

