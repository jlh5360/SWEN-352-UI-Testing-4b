package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import edu.rit.swen253.utils.TimingUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.openqa.selenium.TimeoutException;


/**
 * Represents the Class Search page of the TigerCenter Angular web application.
 */
public class ClassSearchPage extends AbstractAngularPage {
    private static final String CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME = "class-search";
    private static final By SEARCH_INPUT_FINDER = By.cssSelector("input[type='search']");
    private static final By SEARCH_BUTTON_FINDER = By.cssSelector("button[class='classSearchSearchButton ng-star-inserted']");
    private static final By SEARCH_TERM_FINDER = By.cssSelector("select[name='termSelector']");
    private static final By COURSE_CATALOG_TERM_FINDER = By.cssSelector("select[name='courseCatalogTermSelector']");

    public ClassSearchPage() {
        super(CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME);

        //Validate basic page structure
        try {
            findOnPage(SEARCH_INPUT_FINDER);
            findOnPage(SEARCH_TERM_FINDER);
        } catch (TimeoutException e) {
            fail("Class Search page elements not found: " + e.getMessage());
        }
    }

    /**
     * Enters a keyword into the class search input field.
     * @param keyword The keyword to enter.
     */
    public void enterKeyword(String keyword) {
        DomElement keywordInputField = findKeywordInputField();
        keywordInputField.clear(); // Clear any existing text
        keywordInputField.sendKeys(keyword);
        // keywordInputField.submit();
    }

    /**
     * Selects a term from the term dropdown.
     * @param term The visible text of the term to select (e.g., "2025-26 Fall (2251)").
     */
    public void selectTerm(String term) {
        DomElement termDropdown = findTermDropdown();
        Select select = new Select(termDropdown.getWebElement());
        select.selectByVisibleText(term);
    }

    /**
     * Clicks the Search button.
     */
    public void clickSearchButton() {
        DomElement searchButton = findSearchButton();
        // DomElement searchButton = findOnPage(SEARCH_BUTTON_FINDER);
        searchButton.click();
        TimingUtils.sleep(3);
    }

    /**
     * Asserts that course results are displayed. This is a generic check and might need
     * to be more specific based on the actual DOM structure of search results.
     */
    public void assertSearchResultsDisplayed() {
        DomElement searchResultsContainer = findOnPage(By.cssSelector("div.classSearchBasicResultsMargin"));
        assertTrue(searchResultsContainer.isDisplayed(), "Search results should be displayed.");
    }

    /**
     * Asserts that a message prompting to select a term is displayed.
     */
    public void assertSelectTermMessageDisplayed() {
        DomElement selectTermMessage = findOnPage(By.xpath("//*[contains(text(), 'You must select a term')]"));
        assertTrue(selectTermMessage.isDisplayed(), "Message to select a term should be displayed.");
    }

    /**
     * Clicks the Course Catalog link/button.
     */
    public void clickCourseCatalogLink() {
        DomElement courseCatalogLink = findCourseCatalogLink();
        courseCatalogLink.click();
        TimingUtils.sleep(3);
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
     */
    public void selectCourseFromCatalog(String courseName) {
        // Placeholder: Assuming the course names are clickable elements.
        DomElement courseElement = angularView.findChildBy(By.xpath(String.format("//div[.//span[contains(text(), '%s')]]", courseName)));
        courseElement.click();
        TimingUtils.sleep(3);   //Wait for details to load
    }

    /**
     * Selects a term from the dropdown within the course details view of the catalog.
     * @param term The visible text of the term to select.
     */
    public void selectTermInCourseDetails(String term) {
        DomElement courseDetailTermDropdown = findOnPage(By.cssSelector("select[name='courseCatalogTermSelector']"));
        Select select = new Select(courseDetailTermDropdown.getWebElement());
        select.selectByVisibleText(term);
    }

    /**
     * Asserts that a list of available sections is displayed.
     */
    public void assertSectionsDisplayed() {
        DomElement sectionsList = findOnPage(By.cssSelector("div.courseCatalogCourseHeaders"));
        assertTrue(sectionsList.isDisplayed(), "Course sections should be displayed.");
    }

    /**
     * Helper functions to find particular elements
     */
    private DomElement findKeywordInputField() {
        return angularView.findChildBy(SEARCH_INPUT_FINDER);
    }

    private DomElement findTermDropdown() {
        return angularView.findChildBy(SEARCH_TERM_FINDER);
    }

    private DomElement findSearchButton() {
        return angularView.findChildBy(By.cssSelector("button.classSearchSearchButton"));
    }

    private DomElement findCourseCatalogLink() {
        return angularView.findChildBy(By.linkText("Course Catalog"));
    }
}
