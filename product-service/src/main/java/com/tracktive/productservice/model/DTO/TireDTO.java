package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* Description: Tire DTO
* @author William Theo
* @date 9/3/2025
*/
public class TireDTO {

    @NotNull(message = "Product ID is required")
    private String id;

    @NotBlank(message = "Tire SKU is required")
    private String tireSku;

    @NotNull(message = "Width is required")
    @Min(value = 100, message = "Width must be at least 100mm")
    @Max(value = 400, message = "Width cannot exceed 400mm")
    private Integer width;

    @NotNull(message = "Aspect ratio is required")
    @Min(value = 20, message = "Aspect ratio must be at least 20")
    @Max(value = 80, message = "Aspect ratio cannot exceed 80")
    private Integer aspectRatio;

    @NotNull(message = "Rim diameter is required")
    @Min(value = 10, message = "Rim diameter must be at least 10 inches")
    @Max(value = 30, message = "Rim diameter cannot exceed 30 inches")
    private Integer rimDiameter;

    @NotBlank(message = "Construction type is required")
    private String constructionType;

    @NotBlank(message = "Load index is required")
    private String loadIndex;

    @NotBlank(message = "Speed rating is required")
    private String speedRating;

    @NotNull(message = "Tire Season is required")
    @ValidEnum(enumClass = TireSeason.class, message = "Invalid Tire season")
    private TireSeason tireSeason;

    @NotNull(message = "Tread Pattern is required")
    @ValidEnum(enumClass =TreadPattern.class, message = "Invalid Tread Pattern")
    private TreadPattern treadPattern;

    @NotNull(message = "Tire Type is required")
    @ValidEnum(enumClass =TireType.class, message = "Invalid Tire Type")
    private TireType tireType;

    @NotNull(message = "Run-flat status is required")
    private Boolean runFlat;

    public TireDTO() {
    }

    public TireDTO(String id, String tireSku, Integer width, Integer aspectRatio, Integer rimDiameter, String constructionType, String loadIndex, String speedRating, TireSeason tireSeason, TreadPattern treadPattern, TireType tireType, Boolean runFlat) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
