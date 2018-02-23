import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SmsSpamTest {

    private WebDriver driver;
    private SmsPage sms;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://sms.priv.pl/");
    }

    @Test(dataProvider = "numberProvider")
    public void sendSmsSpamTest(String number) throws IOException {
        sms = new SmsPage(driver);
        sms.enterNumber(number)
                .enterMessage()
                .enterSender()
                .sendSms()
                .returnToHomepage();
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @DataProvider(name = "numberProvider")
    public Object[][] getRandomNumber(){
        return new Object[][]{
                {"730529471"},
                {"730529471"},
                {"730529471"}
        };
    }
}
