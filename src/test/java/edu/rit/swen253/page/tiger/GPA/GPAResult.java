package edu.rit.swen253.page.tiger.GPA;

/*
 * Holds the result of a GPA calculation.
 */
public class GPAResult {
    private float termGPA;
    private float cumulativeGPA;

    public GPAResult(float termGPA, float cumulativeGPA) {
        this.termGPA = termGPA;
        this.cumulativeGPA = cumulativeGPA;
    }

    public float getTermGPA() {
        return termGPA;
    }

    public float getCummulativeGPA() {
        return cumulativeGPA;
    }
}