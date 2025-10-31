import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.Bronirovanie;
import practicum.WhoIsScooter;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderScooter {

  private DriverFactory factory = new DriverFactory();


    @Test
    public void orderUpTitle(){
        var driver = factory.getDriver();
        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    whoIsScooter.getCookie();
       whoIsScooter.clickOrderUp();
        //driver.findElement(By.xpath(".//div[@class='Header_Logo__23yGT']" + "/following-sibling::div[1]//button[@class='Button_Button__ra12g']")).click();
        whoIsScooter.setName("Иван");
        whoIsScooter.getSurname("Распопов");
        whoIsScooter.getAdress("Дубай");
        whoIsScooter.getMetro();
        whoIsScooter.setMetro("Лубянка");
        whoIsScooter.getPhoneNumber("89777777777");
        whoIsScooter.clickNext();

        Bronirovanie bronirovanie = new Bronirovanie(driver);
        bronirovanie.setStartDate("29.10.2025");
        bronirovanie.setEndDate("двое суток");
        bronirovanie.clickOrder();
        bronirovanie.clickYes();
        //System.out.println(bronirovanie.getOrderModal());
        assertTrue(bronirovanie.getOrderModal(),"Заказ не оформлен(модальное окно не открыто).");
    }

    @Test
    public void orderUnderTitle(){
        var driver = factory.getDriver();
        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        whoIsScooter.getCookie();
       whoIsScooter.clickOrderDown();
        whoIsScooter.setName("Петя");
        whoIsScooter.getSurname("Иванов");
        whoIsScooter.getAdress("Лос-Анджелес");
        whoIsScooter.getMetro();
        whoIsScooter.setMetro("Черкизовская");
        whoIsScooter.getPhoneNumber("89777777777");
        whoIsScooter.clickNext();

        Bronirovanie bronirovanie = new Bronirovanie(driver);
        bronirovanie.setStartDate("29.10.2025");
        bronirovanie.setEndDate("трое суток");
        bronirovanie.clickOrder();
        bronirovanie.clickYes();
        //System.out.println(bronirovanie.getOrderModal());
        assertTrue(bronirovanie.getOrderModal(),"Заказ не оформлен(модальное окно не открыто).");

    }

    @ParameterizedTest
    @CsvSource({
            "Лубянка",
            "Черкизовская" ,
            "Сокольники" ,
            "Водный стадион" ,
            "Славянский бульвар" ,
            "Александровский сад"
    }
    )
    public void setValueMetroField(String station){
        var driver = factory.getDriver();
        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        whoIsScooter.clickOrderUp();
        // driver.findElement(By.xpath("(//button[text()='Заказать'])[1]")).click();
        whoIsScooter.setName("Иван");
        whoIsScooter.getSurname("Распопов");
        whoIsScooter.getAdress("Дубай");
        whoIsScooter.getMetro();
        whoIsScooter.setMetro(station);
        whoIsScooter.getPhoneNumber("89777777777");
        whoIsScooter.clickNext();

        Bronirovanie bronirovanie = new Bronirovanie(driver);
        bronirovanie.setStartDate("29.10.2025");
        bronirovanie.setEndDate("двое суток");
        bronirovanie.clickOrder();
        bronirovanie.clickYes();
        //System.out.println(bronirovanie.getOrderModal());
        assertTrue(bronirovanie.getOrderModal(),"Заказ не оформлен(модальное окно не открыто).");
    }

    @ParameterizedTest
    @ValueSource(strings = {"сутки","трое суток","семеро суток","пятеро суток"})
    public void setValueEndDateField(String endDate){
        var driver = factory.getDriver();
        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        whoIsScooter.getCookie();
        whoIsScooter.clickOrderDown();
        whoIsScooter.setName("Петя");
        whoIsScooter.getSurname("Иванов");
        whoIsScooter.getAdress("Лос-Анджелес");
        whoIsScooter.getMetro();
        whoIsScooter.setMetro("Черкизовская");
        whoIsScooter.getPhoneNumber("89777777777");
        whoIsScooter.clickNext();

        Bronirovanie bronirovanie = new Bronirovanie(driver);
        bronirovanie.setStartDate("29.10.2025");
        bronirovanie.setEndDate(endDate);
        bronirovanie.clickOrder();
        bronirovanie.clickYes();
        //System.out.println(bronirovanie.getOrderModal());
        assertTrue(bronirovanie.getOrderModal(),"Заказ не оформлен(модальное окно не открыто).");

    }



    //Дополнительный тестовый сценарий 1
//    @Test
//    public void headTitle(){
//        var driver = factory.getDriver();
//        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
//        driver.get("https://qa-scooter.praktikum-services.ru/");
//        whoIsScooter.getCookie();
//        whoIsScooter.clickOrderDown();
//       driver.findElement(By.xpath(".//img[@src='/assets/scooter.svg']")).click();
//       WebElement webElement = driver.findElement(By.className("Home_Header__iJKdX"));
//       String text = webElement.getText();
//       // System.out.println(text.contains("Самокат"));
//       assertTrue(text.contains("Самокат"),"Главная страница не открыта: текст 'Самокат' не найден на главной странице");
//    }
    //Дополнительный тестовый сценарий 2
//    @Test
//    public void headTitleYandex(){
//        var driver = factory.getDriver();
//        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
//        driver.get("https://qa-scooter.praktikum-services.ru/");
//        //закрываем куки
//        whoIsScooter.getCookie();
//        //клмкаем на нижнюю кнопку заказать
//       // whoIsScooter.clickOrderDown();
//        //нажимаем логотип яндекса
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/assets/ya.svg']"))).click();
//        //ждем попка урл изменится
//         new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.urlContains("dzen.ru/?yredirect=true"));
//       String url = driver.getCurrentUrl();
//        assertTrue(url.contains("dzen.ru/?yredirect=true"),"Главная страница яндекса не открыта");
//    }


    @AfterEach
    public void outdriver(){
factory.getDriver().quit();
    }
}
