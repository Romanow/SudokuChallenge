package com.bosch.sast.sudoku.validator.repository;

import com.bosch.sast.sudoku.validator.model.Board;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class SudokuRepositoryTest {
    private static final int[][] FULL_COMPLETED_GRID = new int[][]{
            {7, 2, 6, 4, 9, 3, 8, 1, 5},
            {3, 1, 5, 7, 2, 8, 9, 4, 6},
            {4, 8, 9, 6, 5, 1, 2, 3, 7},
            {8, 5, 2, 1, 4, 7, 6, 9, 3},
            {6, 7, 3, 9, 8, 5, 1, 2, 4},
            {9, 4, 1, 3, 6, 2, 7, 5, 8},
            {1, 9, 4, 8, 3, 6, 5, 7, 2},
            {5, 6, 7, 2, 1, 4, 3, 8, 9},
            {2, 3, 8, 5, 7, 9, 4, 6, 1}
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SudokuRepository sudokuRepository;

    @Test
    void testSave() {
        // Given
        final var board = new Board()
                .setCells(FULL_COMPLETED_GRID);

        // When
        var saved = sudokuRepository.saveAndFlush(board);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isPositive();
        assertThat(saved.getCells()).isDeepEqualTo(FULL_COMPLETED_GRID);
        final var boardId = saved.getId();

        // And
        saved = sudokuRepository
                .findById(boardId)
                .orElse(null);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isPositive();
        assertThat(saved.getCells()).isDeepEqualTo(FULL_COMPLETED_GRID);

        // And
        final var cells = jdbcTemplate
                .queryForObject("SELECT cells AS cells FROM board WHERE id = " + boardId, String.class);

        final var gson = new Gson();
        assertThat(gson.fromJson(cells, int[][].class)).isDeepEqualTo(FULL_COMPLETED_GRID);
    }
}