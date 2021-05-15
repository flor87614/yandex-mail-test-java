package my.selenium.ru.yandex.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
    private WebDriver driver;

    @FindBy(xpath ="//a[contains(@class, 'HeadBanner-Button-Enter')]")
    private WebElement enterButton;

    public WelcomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public LoginPage enter() {
        enterButton.click();
        return new LoginPage(driver);
    }
}
