package my.selenium.ru.yandex.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendMailForm {
    private WebDriver driver;

    //@FindBy(xpath = "//input[@id='passp-field-login']")
    //    private WebElement loginInput;

    @FindBy(xpath = "//div[contains(@class, 'MultipleAddressesDesktop-Field')]//div[@contenteditable='true']")
    private WebElement addressInput;

    @FindBy(xpath = "//input[contains(@class, 'ComposeSubject-TextField')]")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textBox;

    @FindBy(xpath = "//div[contains(@class, 'ComposeSendButton')]//button[@type='button']")
    private WebElement sendButton;


    public SendMailForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MailPage sendMail(String subject, String address, String text) {
        addressInput.sendKeys(address);
        subjectInput.sendKeys(subject);
        textBox.sendKeys(text);
        sendButton.click();

        return new MailPage(driver);
    }
}
