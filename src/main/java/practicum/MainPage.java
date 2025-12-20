package practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getText(String text){
    String question = String.format(".//div[normalize-space()='%s']", text);
    try{
        WebElement webElement = driver.findElement(By.xpath(question));
        webElement.click();
        WebElement webElement1 = driver.findElement(By.xpath(question + "/following-sibling::div[1]"));
        return webElement1.getText();
    }catch (Exception e){
        throw new Error("Вопрос не найден: возможно, он не существует, удалён или содержит ошибку в формулировке.");
    }

}
}
