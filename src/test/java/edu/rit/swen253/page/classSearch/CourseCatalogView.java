package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.TimingUtils;
import org.openqa.selenium.By;

/**
 * Represents the Course Catalog overlay/view.
 */
public class CourseCatalogView extends AbstractAngularPage {
    private static final String COURSE_CATALOG_ANGULAR_VIEW_TAG_NAME = "class-search";

    public CourseCatalogView() {
        super(COURSE_CATALOG_ANGULAR_VIEW_TAG_NAME);
    }

    /**
     * Interacts with the Course Catalog overlay to expand a subject area.
     * @param subjectArea The text of the subject area (e.g., "CAD - College of Art and Design").
     */
    public void expandCourseCatalogSubject(String subjectArea) {
        DomElement subjectElement = angularView.findChildBy(By.xpath(String.format("//mat-expansion-panel-header[contains(., '%s')]", subjectArea)));
        subjectElement.click();
        TimingUtils.sleep(3);   //Wait for details to load
    }

    /**
     * Interacts with the Course Catalog overlay to select a specific course.
     * @param courseName The text of the course name (e.g., "Child Development in Art").
     * @return A new CourseDetailView page object.
     */
    public CourseDetailView selectCourseFromCatalog(String courseName) {
        DomElement courseElement = angularView.findChildBy(By.xpath(String.format("//div[.//span[contains(text(), '%s')]]", courseName)));
        courseElement.click();
        TimingUtils.sleep(3);   //Wait for details to load
        return new CourseDetailView();
    }
}
