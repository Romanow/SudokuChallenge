package com.bosch.sast.sudoku.validator.service.validators;

import com.bosch.sast.sudoku.validator.utils.Constants;

import java.util.BitSet;

public final class ValidatorHelper {

    public static boolean checkDigit(int digit, BitSet occurrences) {
        boolean valid = false;
        if (digit != 0) {
            if (digit > 0 && digit <= Constants.BOARD_SIZE) {
                if (!occurrences.get(digit)) {
                    occurrences.set(digit);
                    valid = true;
                }
            }
        } else {
            valid = true;
        }
        return valid;
    }
}
