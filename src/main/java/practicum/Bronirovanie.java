package practicum;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Bronirovanie {
    private WebDriver driver;

    public Bronirovanie(WebDriver driver) {
        this.driver = driver;
    }
    By startDateField = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");
    By endDateField = By.cssSelector(".Dropdown-placeholder");
    By clickBack = By.cssSelector(".Button_Inverted__3IF-i");
    By clickBy = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and contains(., 'Заказать')]");
    By clickYes = By.xpath(".//button[text()='Да']");
    By clickNo = By.xpath(".//button[text()='Нет']");
    By orderModal = By.xpath(".//button[text()='Посмотреть статус']");

    public void setStartDate(String date){
        WebElement webElement = new WebDriverWait(driver , Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(startDateField));
        webElement.click();
        webElement.sendKeys(date);
        webElement.sendKeys(Keys.ENTER);
    }
    public void setEndDate(String andDate){
        driver.findElement(endDateField).click();
        WebElement webElement1 = driver.findElement(By.xpath(String.format(".//div[text()='%s']", andDate)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement1);
        webElement1.click();
    }
    public void clickOrder(){
        driver.findElement(clickBy).click();
    }
    public void clickYes(){
      WebElement webElement2 =  new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(clickYes));
        webElement2.click();
    }
    public boolean getOrderModal (){
        boolean resoult = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderModal)).isDisplayed();
        return resoult;
    }


}
