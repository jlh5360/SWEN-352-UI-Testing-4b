package edu.rit.swen253.test.GPA;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.page.tiger.GPA.Course;
import edu.rit.swen253.page.tiger.GPA.GPACalculator;
import edu.rit.swen253.page.tiger.GPA.GPAResult;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GPACalculatorTest extends AbstractWebTest {
    private TigerCenterHomePage homePage;
    private GPACalculator gpaPage;

    @Test
    @Order(1)
    @DisplayName("Navigate to the Tiger Center Home page and then GPA Calculator")
    void navigateToHomePage() {
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    @Test
    @Order(2)
    @DisplayName("Verify we're on the GPA Calculator page")
    void verifyGPACalculatorPage() {
        homePage.selectGPACalculator();
        homePage.waitUntilGone();
        gpaPage = assertNewPage(GPACalculator::new);
    }

    @Test
    @Order(3)
    @DisplayName("Calculating GPAs - Term and Cummulative")
    void calculateGPA() {

        gpaPage.enterCummulativeGPA(3);
        gpaPage.enterCredits(18);

        List<Course> courses = List.of(
            new Course("GCIS123", 4, "A"),
            new Course("MATH182", 3, "B+"),
            new Course("PHYS-150", 3, "A"),
            new Course("SWEN250", 3, "B")
        );

        gpaPage.insertCourses(courses);

        GPAResult result = gpaPage.calculateGPA();
        assertNotNull(result);
        assertEquals(3.61, result.getTermGPA(), 0.01);
        assertEquals(3.26, result.getCummulativeGPA(), 0.01);
    }
}