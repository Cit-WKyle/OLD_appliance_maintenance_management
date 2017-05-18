package com.kyle.ie.appliance.model;

import java.util.List;

/**
 * Created by kyle_williamson on 16/02/2017.
 */

public class Appliance {

    private int id;

    private String productNo;
    private String name;
    private String brand;
    private String type;
    private List<String> features;

    // mm
    private double width;
    private double height;
    private double depth;

    // kg
    private double weight;

    private String applianceImageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getApplianceImageUrl() {
        return applianceImageUrl;
    }

    public void setApplianceImageUrl(String applianceImageUrl) {
        this.applianceImageUrl = applianceImageUrl;
    }
}
