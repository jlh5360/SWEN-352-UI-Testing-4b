package edu.rit.swen253.page.tiger.GPA;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class GPACalculator extends AbstractAngularPage{
    public GPACalculator() {
        super("gpa-calc");
    }

    /*
     * Inputs the earned RIT credits 
     */
    public void enterCredits(int credits) {
        DomElement creditsInput = findOnPage(By.cssSelector("input[type='number'][min='0'][max='999']"));
        creditsInput.clear();
        creditsInput.sendKeys(String.valueOf(credits));
    }

    /*
     * Enter current cumulative GPA if user wants to calculate
     * cummulative GPA after this term's grades.
     */
    public void enterCummulativeGPA(float gpa) {
        DomElement gpaInput = findOnPage(By.cssSelector("input[type='number'][min='0.00'][max='4.00']"));
        gpaInput.clear();
        gpaInput.sendKeys(String.valueOf(gpa));
    }

    /*
     * User is a graduate student
     */
    public void isGradStudent() {
        DomElement gradCheckbox = findOnPage(By.cssSelector("span.mat-checkbox-inner-container"));
        if (!gradCheckbox.isSelected()) {
            gradCheckbox.click();
        }
    }

    /*
     * Given a list of courses, it inputs each course into the site
     */
    public void insertCourses(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            if(i > 0) {
                DomElement addButton = findOnPage(By.xpath("//button[contains(@class, 'secondaryButton')]/span[text()='Add']/.."));
                addButton.click();
            }

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            // Get all course name inputs
            List<DomElement> courseNameInputs = findAllOnPage(By.cssSelector(".inputBox.courseInputWidth"));
            if (i < courseNameInputs.size()) {
                courseNameInputs.get(i).clear();
                courseNameInputs.get(i).sendKeys(course.getName());
            }
            
            // Get all credit inputs
            List<DomElement> creditInputs = findAllOnPage(By.cssSelector("input[type='number'][min='0'][max='12']"));
            if (i < creditInputs.size()) {
                creditInputs.get(i).clear();
                creditInputs.get(i).sendKeys(String.valueOf(course.getCredits()));
            }
                
            // Get all grade dropdowns
            List<DomElement> grade = findAllOnPage(By.xpath("//span[contains(text(),'Grade:') and not(contains(text(),'Past Grade:'))]/following::select[1]"));
            Select gradeSelect = new Select(grade.get(i).getWebElement());
            gradeSelect.selectByVisibleText(course.getGrade());

            if(!course.getPastGrade().isEmpty()) {
                List<DomElement> pastGrade = findAllOnPage(By.cssSelector("[title*='Not applicable for Graduate Students']"));
                Select pastGradeSelect = new Select(pastGrade.get(i).getWebElement());
                pastGradeSelect.selectByVisibleText(course.getPastGrade());
            }

        }
    }

    /*
     * Calculates and returns the GPA
     */
    public GPAResult calculateGPA() {
        DomElement calculateButton = findOnPage(By.cssSelector("button.primaryButton"));
        calculateButton.click();

        // Wait for calculation
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<DomElement> results = findAllOnPage(By.cssSelector(".results, [class*='results']"));

        if (results == null || results.isEmpty()) {
            throw new RuntimeException("GPA calculation results not found on page");
        }
        
        if (results.size() < 2) {
            throw new RuntimeException("Expected at least 2 result elements, but found " + results.size());
        }

        String term = results.get(0).getText();
        String cummulative = results.get(1).getText();

        return new GPAResult(Float.parseFloat(term), Float.parseFloat(cummulative));
    }
}
