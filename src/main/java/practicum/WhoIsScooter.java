package practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoIsScooter {
    private WebDriver driver;

    public WhoIsScooter(WebDriver driver) {
        this.driver =  driver;
    }

    By clickOrderUp = By.xpath("(//button[text()='Заказать'])[1]");
    By clickCookie = By.id("rcc-confirm-button");
    By clickOrderUnder = By.xpath("(//button[text()='Заказать'])[2]");
    By nameField = By.xpath(".//input[contains(@placeholder, '* Имя')]");
    By surnameField = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");
    By adressField = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");
    By metroField = By.xpath(".//input[contains(@placeholder, '* Станция метро')]");
    By phoneField = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");
    By clickNext = By.xpath(".//button[text()='Далее']");

// закрываем куки
    public void getCookie(){
        driver.findElement(clickCookie).click();
    }
    //кликаем на кнопку заказать
    public void clickOrderUp() {
        driver.findElement(clickOrderUp).click();
    }
    public void clickOrderDown() {

        WebElement orderBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(clickOrderUnder));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", orderBtn);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(orderBtn))
                .click();
    }
    //заполняем поле имя
    public void setName(String username) {
        driver.findElement(nameField).sendKeys(username);
    }
    //заполняем поле Фамилия
    public void getSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    //заполняем поле Адрес
    public void getAdress(String street) {
        driver.findElement(adressField).sendKeys(street);
    }
    //кликаем на поле метро
    public void getMetro() {
        driver.findElement(metroField).click();
    }
    //заполняем поле метро
    public void setMetro(String stationName) {
        WebElement element = driver.findElement(By.xpath(String.format(".//div[text()='%s']", stationName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //заполняем поле телефон
    public void getPhoneNumber(String phoneNumber) {
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }
    //кликаем на кнопку далее
    public void clickNext() {
        driver.findElement(clickNext).click();
    }

}
