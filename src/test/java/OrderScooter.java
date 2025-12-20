import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import practicum.Bronirovanie;
import practicum.MainPage;
import practicum.WhoIsScooter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderScooter {
  private DriverFactory factory = new DriverFactory();

  @ParameterizedTest
    @CsvSource({
            "'Сколько это стоит? И как оплатить?', 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.'",
            "'Я живу за МКАДом, привезёте?', 'Да, обязательно. Всем самокатов! И Москве, и Московской области.'",
            "'Можно ли отменить заказ?', 'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "'Можно ли продлить заказ или вернуть самокат раньше?', 'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.'",
            "'Хочу сразу несколько самокатов! Так можно?', 'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.'",
            "'Как рассчитывается время аренды?', 'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.'",
            "'Вы привозите зарядку вместе с самокатом?', 'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.'"
    })
    public void getTextQuestion(String text, String expected) {
        var driver = factory.getDriver();
        WhoIsScooter whoIsScooter = new WhoIsScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        whoIsScooter.getCookie();
        MainPage mainPage = new MainPage(driver);
       String resoult =  mainPage.getText(text);
        assertEquals(expected,resoult, "Неверный ответ на вопрос, должен быть: "+expected);
    }

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
