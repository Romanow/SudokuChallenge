package com.bosch.sast.sudoku.validator.utils;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

public class ArrayConverter
        implements AttributeConverter<int[][], String> {

    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(int[][] attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, int[][].class);
    }
}
