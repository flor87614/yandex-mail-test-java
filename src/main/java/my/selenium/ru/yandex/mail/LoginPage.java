package my.selenium.ru.yandex.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passwordInput;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MailPage login(String login, String password) {
        loginInput.sendKeys(login);
        loginInput.sendKeys(Keys.ENTER);

        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.ENTER);

        return new MailPage(driver);
    }
}
