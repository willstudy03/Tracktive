package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;

/**
* Description: Product Management Tire DTO
* @author William Theo
* @date 25/3/2025
*/
public class ProductManagementTireDTO extends ProductManagementDTO{

    private String tireSku;

    private Integer width;

    private Integer aspectRatio;

    private Integer rimDiameter;

    private String constructionType;

    private String loadIndex;

    private String speedRating;

    private TireSeason tireSeason;

    private TreadPattern treadPattern;

    private TireType tireType;

    private Boolean runFlat;

    public ProductManagementTireDTO() {
    }

    public ProductManagementTireDTO(String tireSku, Integer width, Integer aspectRatio, Integer rimDiameter, String constructionType, String loadIndex, String speedRating, TireSeason tireSeason, TreadPattern treadPattern, TireType tireType, Boolean runFlat) {
        this.tireSku = tireSku;
        this.width = width;
        this.aspectRatio = aspectRatio;
        this.rimDiameter = rimDiameter;
        this.constructionType = constructionType;
        this.loadIndex = loadIndex;
        this.speedRating = speedRating;
        this.tireSeason = tireSeason;
        this.treadPattern = treadPattern;
        this.tireType = tireType;
        this.runFlat = runFlat;
    }

    public String getTireSku() {
        return tireSku;
    }

    public void setTireSku(String tireSku) {
        this.tireSku = tireSku;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Integer getRimDiameter() {
        return rimDiameter;
    }

    public void setRimDiameter(Integer rimDiameter) {
        this.rimDiameter = rimDiameter;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getLoadIndex() {
        return loadIndex;
    }

    public void setLoadIndex(String loadIndex) {
        this.loadIndex = loadIndex;
    }

    public String getSpeedRating() {
        return speedRating;
    }

    public void setSpeedRating(String speedRating) {
        this.speedRating = speedRating;
    }

    public TireSeason getTireSeason() {
        return tireSeason;
    }

    public void setTireSeason(TireSeason tireSeason) {
        this.tireSeason = tireSeason;
    }

    public TreadPattern getTreadPattern() {
        return treadPattern;
    }

    public void setTreadPattern(TreadPattern treadPattern) {
        this.treadPattern = treadPattern;
    }

    public TireType getTireType() {
        return tireType;
    }

    public void setTireType(TireType tireType) {
        this.tireType = tireType;
    }

    public Boolean getRunFlat() {
        return runFlat;
    }

    public void setRunFlat(Boolean runFlat) {
        this.runFlat = runFlat;
    }
}
