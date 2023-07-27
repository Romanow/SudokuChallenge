package com.bosch.sast.sudoku.validator.service;

import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.mappers.BoardMapper;
import com.bosch.sast.sudoku.validator.model.Board;
import com.bosch.sast.sudoku.validator.repository.SudokuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Stream;

import static com.bosch.sast.sudoku.validator.service.cases.Grids.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ValidatorServiceImplTest {
    private static final long BOARD_ID = 1;

    private ValidatorService validatorService;
    private SudokuRepository sudokuRepository;

    @BeforeEach
    void init() {
        // Consider BoardMapper as helper class with methods to convert entity to DTO and back.
        // Without MapStruct we will implement private method in service. That's why we can
        // test it with ValidatorServiceImpl class.
        final var mapper = Mappers.getMapper(BoardMapper.class);
        sudokuRepository = Mockito.mock(SudokuRepository.class);
        validatorService = new ValidatorServiceImpl(sudokuRepository, mapper);
    }

    @Test
    void isValidSudokuByIdSuccess() {
        // Given
        when(sudokuRepository.findById(BOARD_ID))
                .thenReturn(Optional.of(new Board().setId(BOARD_ID)
                                                   .setCells(FULL_COMPLETED_GRID)));

        // When
        final var valid = validatorService.isValidSudoku(BOARD_ID);

        // Then
        assertThat(valid).isTrue();
    }

    @Test
    void isValidSudokuByIdFailed() {
        // Given
        when(sudokuRepository.findById(BOARD_ID))
                .thenReturn(Optional.of(new Board().setId(BOARD_ID)
                                                   .setCells(WRONG_SUBGRID_GRID_1)));

        // When
        final var valid = validatorService.isValidSudoku(BOARD_ID);

        // Then
        assertThat(valid).isFalse();
    }

    @Test
    void isValidSudokuByIdNotFound() {
        assertThatThrownBy(() -> validatorService.isValidSudoku(1000))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @ParameterizedTest
    @MethodSource("factory")
    void isValidSudoku(int[][] grid, boolean result) {
        // When
        final var valid = validatorService.isValidSudoku(grid);

        // Then
        assertThat(valid).isEqualTo(result);
    }

    @Test
    void saveBoardSuccess() {
        // Given
        final var newBoard = new NewBoardDTO()
                .setGrid(FULL_COMPLETED_GRID);

        when(sudokuRepository.save(any(Board.class)))
                .thenAnswer(inv -> inv.getArgument(0, Board.class)
                                      .setId(BOARD_ID));

        // When
        final var board = validatorService.saveBoard(newBoard);

        // Then
        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo(BOARD_ID);
        assertThat(board.getGrid()).isDeepEqualTo(FULL_COMPLETED_GRID);
    }

    @Test
    void getBoardSuccess() {
        // Given
        when(sudokuRepository.findById(BOARD_ID))
                .thenReturn(Optional.of(new Board().setId(BOARD_ID)
                                                   .setCells(FULL_COMPLETED_GRID)));

        // When
        final var board = validatorService.getBoard(BOARD_ID);

        // Then
        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo(BOARD_ID);
        assertThat(board.getGrid()).isDeepEqualTo(FULL_COMPLETED_GRID);
    }

    @Test
    void getBoardNotFound() {
        assertThatThrownBy(() -> validatorService.getBoard(1000))
                .isInstanceOf(EntityNotFoundException.class);
    }

    static Stream<Arguments> factory() {
        return Stream.of(
                of(FULL_COMPLETED_GRID, true),
                of(CORRECT_NOT_FULL_GRID_1, true),
                of(CORRECT_NOT_FULL_GRID_2, true),
                of(WRONG_COLUMN_GRID, false),
                of(WRONG_ROW_GRID, false),
                of(WRONG_SUBGRID_GRID_1, false),
                of(WRONG_SUBGRID_GRID_2, false),
                of(ROWS_UNCOMPLETED, false),
                of(COLUMN_UNCOMPLETED, false)
        );
    }
}