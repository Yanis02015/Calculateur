package com.l3.moyennecalculateur.control;

public class MarksView {
    private String intitule;
    private int coefficient;
    private double moyenne;

    public MarksView(String intitule, int coefficient, double moyenne) {
        this.intitule = intitule;
        this.coefficient = coefficient;
        this.moyenne = moyenne;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public double getMoyenne() {
        return moyenne;
    }
}
