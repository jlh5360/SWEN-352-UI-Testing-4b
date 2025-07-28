package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.TimingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Represents the Course Details view, typically an overlay or a new section
 * that appears after selecting a course from search results or course catalog.
 */
public class CourseDetailView extends AbstractAngularPage {
    private static final String ANGULAR_VIEW_TAG_NAME = "class-search";

    public CourseDetailView() {
        super(ANGULAR_VIEW_TAG_NAME);
    }

    /**
     * Selects a term from the dropdown within the course details view of the catalog.
     * @param term The visible text of the term to select.
     * @return A new CourseSectionsPage page object, as selecting a term often leads to displaying sections.
     */
    public CourseSectionsPage selectTermInCourseDetails(String term) { // Modified return type
        DomElement courseDetailTermDropdown = findOnPage(By.cssSelector("select[name='courseCatalogTermSelector']"));
        Select select = new Select(courseDetailTermDropdown.getWebElement());
        select.selectByVisibleText(term);
        TimingUtils.sleep(2); // Wait for sections to potentially update
        return new CourseSectionsPage(); // Return the next logical page/view
    }
}
