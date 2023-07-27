package com.bosch.sast.sudoku.validator.service.validators;

import static com.bosch.sast.sudoku.validator.utils.Constants.BOARD_SIZE;

public class SizeValidator
        implements GridValidator {

    @Override
    public boolean validate(int[][] grid) {
        var valid = grid.length == BOARD_SIZE;
        for (int i = 0; i < BOARD_SIZE && valid; i++) {
            valid = grid[i].length == BOARD_SIZE;
        }

        return valid;
    }
}
