import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class SmsPage {

    private WebDriver driver;
    private String line;

    @FindBy(id = "number")
    private WebElement numberField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(id = "sender")
    private WebElement senderField;

    @FindBy(id = "send")
    private WebElement sendBtn;

    public SmsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SmsPage enterNumber(String number){
        numberField.sendKeys(number);
        return this;
    }

    public SmsPage enterMessage() throws IOException {
        messageField.sendKeys(randomMessage());
        return this;
    }

    public SmsPage enterSender(){
        senderField.clear();
        senderField.sendKeys("Żarcik dnia");
        return this;
    }

    public SmsPage sendSms(){
        sendBtn.click();
        return this;
    }

    private String randomMessage() throws IOException {
        FileInputStream fs = new FileInputStream("message.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        ArrayList<String> array = new ArrayList<String>();
        while((line = br.readLine()) != null){
            array.add(line);
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(array.size());
        return array.get(randomIndex) + " hehe";
    }

    public SmsPage returnToHomepage() {
        driver.get("https://sms.priv.pl/");
        return this;
    }
}
