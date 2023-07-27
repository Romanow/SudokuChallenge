package com.bosch.sast.sudoku.validator.service.validators;

import java.util.BitSet;

import static com.bosch.sast.sudoku.validator.service.validators.ValidatorHelper.checkDigit;
import static com.bosch.sast.sudoku.validator.utils.Constants.BOARD_SIZE;

public class RowsAndColumnsValidator
        implements GridValidator {

    @Override
    public boolean validate(int[][] grid) {
        var valid = true;
        for (int i = 0; i < BOARD_SIZE && valid; i++) {
            var columnDigits = new BitSet(BOARD_SIZE);
            var rowDigits = new BitSet(BOARD_SIZE);

            for (int j = 0; j < BOARD_SIZE && valid; j++) {
                valid = checkDigit(grid[i][j], rowDigits) && checkDigit(grid[j][i], columnDigits);
            }
        }
        return valid;
    }
}
