package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* Description: Product Management Request Tire DTO
* @author William Theo
* @date 28/3/2025
*/
public class ProductManagementRequestTireDTO extends ProductManagementRequestDTO{

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

    @NotBlank(message = "Tire Season is required")
    private TireSeason tireSeason;

    @NotBlank(message = "Tread Pattern is required")
    private TreadPattern treadPattern;

    @NotBlank(message = "Tire Type is required")
    private TireType tireType;

    @NotNull(message = "Run-flat status is required")
    private Boolean runFlat;

    public ProductManagementRequestTireDTO() {
    }

    public ProductManagementRequestTireDTO(Integer width, Integer aspectRatio, Integer rimDiameter, String constructionType, String loadIndex, String speedRating, TireSeason tireSeason, TreadPattern treadPattern, TireType tireType, Boolean runFlat) {
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

    public @NotNull(message = "Width is required") @Min(value = 100, message = "Width must be at least 100mm") @Max(value = 400, message = "Width cannot exceed 400mm") Integer getWidth() {
        return width;
    }

    public void setWidth(@NotNull(message = "Width is required") @Min(value = 100, message = "Width must be at least 100mm") @Max(value = 400, message = "Width cannot exceed 400mm") Integer width) {
        this.width = width;
    }

    public @NotNull(message = "Aspect ratio is required") @Min(value = 20, message = "Aspect ratio must be at least 20") @Max(value = 80, message = "Aspect ratio cannot exceed 80") Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(@NotNull(message = "Aspect ratio is required") @Min(value = 20, message = "Aspect ratio must be at least 20") @Max(value = 80, message = "Aspect ratio cannot exceed 80") Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public @NotNull(message = "Rim diameter is required") @Min(value = 10, message = "Rim diameter must be at least 10 inches") @Max(value = 30, message = "Rim diameter cannot exceed 30 inches") Integer getRimDiameter() {
        return rimDiameter;
    }

    public void setRimDiameter(@NotNull(message = "Rim diameter is required") @Min(value = 10, message = "Rim diameter must be at least 10 inches") @Max(value = 30, message = "Rim diameter cannot exceed 30 inches") Integer rimDiameter) {
        this.rimDiameter = rimDiameter;
    }

    public @NotBlank(message = "Construction type is required") String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(@NotBlank(message = "Construction type is required") String constructionType) {
        this.constructionType = constructionType;
    }

    public @NotBlank(message = "Load index is required") String getLoadIndex() {
        return loadIndex;
    }

    public void setLoadIndex(@NotBlank(message = "Load index is required") String loadIndex) {
        this.loadIndex = loadIndex;
    }

    public @NotBlank(message = "Speed rating is required") String getSpeedRating() {
        return speedRating;
    }

    public void setSpeedRating(@NotBlank(message = "Speed rating is required") String speedRating) {
        this.speedRating = speedRating;
    }

    public @NotBlank(message = "Tire Season is required") TireSeason getTireSeason() {
        return tireSeason;
    }

    public void setTireSeason(@NotBlank(message = "Tire Season is required") TireSeason tireSeason) {
        this.tireSeason = tireSeason;
    }

    public @NotBlank(message = "Tread Pattern is required") TreadPattern getTreadPattern() {
        return treadPattern;
    }

    public void setTreadPattern(@NotBlank(message = "Tread Pattern is required") TreadPattern treadPattern) {
        this.treadPattern = treadPattern;
    }

    public @NotBlank(message = "Tire Type is required") TireType getTireType() {
        return tireType;
    }

    public void setTireType(@NotBlank(message = "Tire Type is required") TireType tireType) {
        this.tireType = tireType;
    }

    public @NotNull(message = "Run-flat status is required") Boolean getRunFlat() {
        return runFlat;
    }

    public void setRunFlat(@NotNull(message = "Run-flat status is required") Boolean runFlat) {
        this.runFlat = runFlat;
    }
}
