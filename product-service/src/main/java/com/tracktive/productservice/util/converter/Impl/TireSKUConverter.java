package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.ProductManagementRequestTireDTO;
import com.tracktive.productservice.model.Enum.TireSeason;
import com.tracktive.productservice.model.Enum.TireType;
import com.tracktive.productservice.model.Enum.TreadPattern;

/**
* Description: Util for convert info into Tire SKU
* @author William Theo
* @date 28/3/2025
*/
public class TireSKUConverter {

    public static String generateSKU(ProductManagementRequestTireDTO tire) {
        String brandCode = getShortenedCode(tire.getProductBrand(), 4);
        String productCode = getShortenedCode(tire.getProductName(), 3);
        String sizeCode = tire.getWidth() + "/" + tire.getAspectRatio() + "-" + tire.getRimDiameter();
        String constructionCode = tire.getConstructionType().substring(0, 1).toUpperCase(); // 'R' for Radial
        String loadSpeedCode = tire.getLoadIndex() + tire.getSpeedRating();
        String seasonCode = getSeasonCode(tire.getTireSeason());
        String treadCode = getTreadCode(tire.getTreadPattern());
        String typeCode = getTypeCode(tire.getTireType());
        String runFlatCode = tire.getRunFlat() ? "Y" : "N";

        return String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s",
                brandCode, productCode, sizeCode, constructionCode, loadSpeedCode, seasonCode, treadCode, typeCode, runFlatCode);
    }

    private static String getShortenedCode(String value, int length) {
        if (value == null || value.isEmpty()) {
            return "UNK"; // Default for unknown values
        }
        String[] words = value.split("\\s+"); // Split by spaces
        StringBuilder code = new StringBuilder();
        for (String word : words) {
            code.append(word.substring(0, Math.min(2, word.length())).toUpperCase());
        }
        return code.length() > length ? code.substring(0, length) : code.toString();
    }

    private static String getSeasonCode(TireSeason season) {
        switch (season) {
            case SUMMER: return "SU";
            case WINTER: return "WI";
            case ALL_SEASON: return "AS";
            case SNOW: return "SN";
            case RAIN: return "RA";
            case TRACK: return "TR";
            default: return "OT"; // Other
        }
    }

    private static String getTreadCode(TreadPattern pattern) {
        switch (pattern) {
            case SYMMETRICAL: return "SYM";
            case ASYMMETRICAL: return "ASY";
            case DIRECTIONAL: return "DIR";
            case HYBRID: return "HYB";
            default: return "UNK"; // Unknown
        }
    }

    private static String getTypeCode(TireType type) {
        switch (type) {
            case SPORTS: return "SP";
            case OFF_ROAD: return "OR";
            case TOURING: return "TR";
            case TRACK: return "TK";
            default: return "GN"; // General
        }
    }

}
