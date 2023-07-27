package com.bosch.sast.sudoku.validator.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewBoardDTO {
    private int[][] grid;
}
