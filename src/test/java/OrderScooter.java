import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import practicum.Bronirovanie;
import practicum.WhoIsScooter;
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
        assertTrue(bronirovanie.getOrderModal(),"Заказ не оформлен(модальное окно не открыто).");

    }
    @AfterEach
    public void outdriver(){
factory.getDriver().quit();
    }
}
