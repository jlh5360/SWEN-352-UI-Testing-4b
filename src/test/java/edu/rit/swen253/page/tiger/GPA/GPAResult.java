package edu.rit.swen253.page.tiger.GPA;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

/*
 * Holds the result of a GPA calculation.
 */
public class GPAResult {

    private final List<DomElement> results;

    public GPAResult(final DomElement resultContainer) {
        // search for results
        this.results = resultContainer.findChildrenBy(By.cssSelector(".results, [class*='results']"));
        // validate results
        if (results == null || results.isEmpty()) {
            fail("GPA calculation results not found on page");
        }
        if (results.size() < 2) {
            fail("Expected at least 2 result elements, but found " + results.size());
        }
    }

    public float getTermGPA() {
        return Float.parseFloat(results.get(0).getText());
    }

    public float getCummulativeGPA() {
        return Float.parseFloat(results.get(1).getText());
    }
}