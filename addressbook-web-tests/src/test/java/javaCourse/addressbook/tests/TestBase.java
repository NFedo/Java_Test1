package javaCourse.addressbook.tests;

import javaCourse.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Nadejda.Fedorova on 23.04.2016.
 */
public class TestBase {
  // static - функция статическая не ассоциированная с объектом  -  глобальная переменная app
  // protected static final ApplicationManager app = new ApplicationManager(BrowserType.IE);
  // protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
