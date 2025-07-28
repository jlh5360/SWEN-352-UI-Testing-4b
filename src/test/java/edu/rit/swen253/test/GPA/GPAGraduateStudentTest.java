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
public class GPAGraduateStudentTest extends AbstractWebTest {
    private TigerCenterHomePage homePage;
    private BrowserWindow<TigerCenterHomePage> homeWindow;

    @Test
    @Order(1)
    @DisplayName("Navigate to the Tiger Center Home page and then GPA Calculator")
    void navigateToHomePage() {
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        homeWindow = getCurrentWindow();
        assertNotNull(homePage);
        assertNotNull(homeWindow);
    }

    @Test
    @Order(2)
    @DisplayName("Verify we're on the GPA Calculator page")
    void verifyGPACalculatorPage() {
        homePage.selectGPACalculator();
        SimplePage gpaPage = assertNewPage(SimplePage::new);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc", gpaPage.getURL());
    }

    @Test
    @Order(3)
    @DisplayName("Calculating GPAs - Term and Cummulative as a Graduate Student ")
    void calculateGPA() {
        switchToWindow(homeWindow);
        switchToWindow(homeWindow);
        GPACalculator gpaPage = new GPACalculator();

        gpaPage.enterCummulativeGPA((float) 3.68);
        gpaPage.enterCredits(12);
        gpaPage.isGradStudent();

        List<Course> courses = List.of(
            new Course("SWEN601", 3, "A+"),
            new Course("SWEN610", 3, "B+"),
            new Course("SWEN640", 3, "B+"),
            new Course("SWEN746", 3, "A"),
            new Course("SWEN732", 3, "B")
        );

        gpaPage.insertCourses(courses);

        GPAResult result = gpaPage.calculateGPA();
        assertNotNull(result);
        System.out.println("Term GPA (Grad): " + result.getTermGPA());
        System.out.println("Cumulative GPA (Grad): " + result.getCummulativeGPA());
    }   
}