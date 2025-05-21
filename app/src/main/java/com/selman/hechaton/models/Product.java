package com.selman.hechaton.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    @NonNull
    public String id;
    public String imageUrl;
    public double defectRate;
    public boolean isColorIssue;
    public boolean isStain;
    public boolean isCutIssue;
    public boolean isStructuralIssue;

    public Product(int i, String[] strings, double v) {
    }

    public Product(String id,String imageUrl, double defectRate, boolean isColorIssue, boolean isStain, boolean isCutIssue, boolean isStructuralIssue) {
        this.id = id;
        this.defectRate = defectRate;
        this.isColorIssue = isColorIssue;
        this.isStain = isStain;
        this.isCutIssue = isCutIssue;
        this.isStructuralIssue = isStructuralIssue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDefectRate() {
        return defectRate;
    }

    public void setDefectRate(double defectRate) {
        this.defectRate = defectRate;
    }

    public boolean isColorIssue() {
        return isColorIssue;
    }

    public void setColorIssue(boolean colorIssue) {
        isColorIssue = colorIssue;
    }

    public boolean isStain() {
        return isStain;
    }

    public void setStain(boolean stain) {
        isStain = stain;
    }

    public boolean isCutIssue() {
        return isCutIssue;
    }

    public void setCutIssue(boolean cutIssue) {
        isCutIssue = cutIssue;
    }

    public boolean isStructuralIssue() {
        return isStructuralIssue;
    }

    public void setStructuralIssue(boolean structuralIssue) {
        isStructuralIssue = structuralIssue;
    }
}


