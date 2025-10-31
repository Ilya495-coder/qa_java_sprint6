import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private WebDriver driver;

    public DriverFactory(){
   //     if("firefox".equals(System.getProperty("browser"))){
          setupFirefox();
     //   }else{
     //       setupChrome();
    //    }
   }

    public void setupFirefox(){

        driver = new FirefoxDriver();
    }
    public void setupChrome(){
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}

