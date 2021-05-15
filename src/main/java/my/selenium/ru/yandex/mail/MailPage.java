package my.selenium.ru.yandex.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(@class, 'mail-ComposeButton')]")
    private WebElement writeButton;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public int countMailsBySubject(String subject) {
        List<WebElement> mails = driver.findElements(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span[text()='" + subject + "']"));
        return mails.size();
    }

    public SendMailForm createMail() {
        writeButton.click();
        return new SendMailForm(driver);
    }
}
