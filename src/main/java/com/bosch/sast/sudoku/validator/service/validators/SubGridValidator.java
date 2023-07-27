package com.bosch.sast.sudoku.validator.service.validators;

import java.util.BitSet;

import static com.bosch.sast.sudoku.validator.service.validators.ValidatorHelper.checkDigit;
import static com.bosch.sast.sudoku.validator.utils.Constants.BOARD_SIZE;
import static com.bosch.sast.sudoku.validator.utils.Constants.SUBGRID_SIZE;

public class SubGridValidator
        implements GridValidator {

    @Override
    public boolean validate(int[][] grid) {
        var valid = true;
        for (int row = 0; row < BOARD_SIZE && valid; row += SUBGRID_SIZE) {
            for (int column = 0; column < BOARD_SIZE && valid; column += SUBGRID_SIZE) {
                var occurrences = new BitSet(SUBGRID_SIZE);

                for (int i = 0; i < SUBGRID_SIZE && valid; i++) {
                    for (int j = 0; j < SUBGRID_SIZE && valid; j++) {
                        valid = checkDigit(grid[row + i][column + j], occurrences);
                    }
                }
            }

        }
        return valid;
    }
}
