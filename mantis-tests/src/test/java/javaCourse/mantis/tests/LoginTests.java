package javaCourse.mantis.tests;


import org.testng.annotations.Test;

import java.io.IOException;
import javaCourse.mantis.appmanager.HttpSession;

import static org.testng.Assert.assertTrue;

/**
 * Created by Nadejda.Fedorova on 15.06.2016.
 */
public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
