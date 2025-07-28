package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represents the page displaying available sections for a specific course.
 */
public class CourseSectionsPage extends AbstractAngularPage {
    private static final String ANGULAR_VIEW_TAG_NAME = "class-search";

    public CourseSectionsPage() {
        super(ANGULAR_VIEW_TAG_NAME);
    }

    /**
     * Asserts that a list of available sections is displayed.
     */
    public void assertSectionsDisplayed() {
        DomElement sectionsList = findOnPage(By.cssSelector("div.courseCatalogCourseHeaders"));
        assertTrue(sectionsList.isDisplayed(), "Course sections should be displayed.");
    }
}
