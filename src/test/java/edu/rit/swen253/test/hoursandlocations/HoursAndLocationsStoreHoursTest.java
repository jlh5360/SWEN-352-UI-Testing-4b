package edu.rit.swen253.test.hoursandlocations;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.*;


/**
 * A simple test that uses the TigerCenter <em>Maps at RIT</em> button to navigate
 * to a new tab that displays the {@code maps.rit.edu} web page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HoursAndLocationsStoreHoursTest extends AbstractWebTest {

  private TigerCenterHomePage homePage;
  private BrowserWindow<TigerCenterHomePage> homeWindow;

  //
  // Test sequence
  //

  @Test
  @Order(1)
  @DisplayName("First, navigate to the Tiger Center Home page.")
  void navigateToHomePage() {
    homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
    assertNotNull(homePage);
    homeWindow = getCurrentWindow();
  }

  @Test
  @Order(2)
  @DisplayName("Second, click on the Hours & Locations button and validate navigation.")
  void navigateToHoursAndLocations() {
    homePage.selectHoursAndLocations();
    assertTrue(seleniumUtils.getCurrentUrl().contains("hours-and-locations"));
  }
}
