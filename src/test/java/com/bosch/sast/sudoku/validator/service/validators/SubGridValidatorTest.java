package com.bosch.sast.sudoku.validator.service.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.bosch.sast.sudoku.validator.service.cases.Grids.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class SubGridValidatorTest {

    private final GridValidator validator = new SubGridValidator();

    @ParameterizedTest
    @MethodSource("factory")
    void validate(int[][] grid, boolean expected) {
        // When
        final var result = validator.validate(grid);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> factory() {
        return Stream.of(
                of(FULL_COMPLETED_GRID, true),
                of(CORRECT_NOT_FULL_GRID_1, true),
                of(CORRECT_NOT_FULL_GRID_2, true),
                of(WRONG_COLUMN_GRID, true),
                of(WRONG_ROW_GRID, false),
                of(WRONG_SUBGRID_GRID_1, false),
                of(WRONG_SUBGRID_GRID_2, false)
        );
    }
}