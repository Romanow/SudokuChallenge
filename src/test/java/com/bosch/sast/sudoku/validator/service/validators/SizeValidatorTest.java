package com.bosch.sast.sudoku.validator.service.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.bosch.sast.sudoku.validator.service.cases.Grids.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class SizeValidatorTest {

    private final GridValidator validator = new SizeValidator();

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
                of(ROWS_UNCOMPLETED, false),
                of(COLUMN_UNCOMPLETED, false)
        );
    }
}