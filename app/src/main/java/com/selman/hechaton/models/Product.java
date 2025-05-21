package com.selman.hechaton.models;

public class Product {
    public String id;
    public double defect_rate;
    public boolean color_defect;
    public boolean stain_present;
    public boolean cut_defect;
    public boolean structural_issue;

    public Product(int i, String[] strings, double v) {
    }

    public Product(String id, double defect_rate, boolean color_defect, boolean stain_present, boolean cut_defect, boolean structural_issue) {
        this.id = id;
        this.defect_rate = defect_rate;
        this.color_defect = color_defect;
        this.stain_present = stain_present;
        this.cut_defect = cut_defect;
        this.structural_issue = structural_issue;
    }

}


