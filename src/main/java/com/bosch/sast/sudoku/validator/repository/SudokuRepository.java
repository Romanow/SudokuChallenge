package com.bosch.sast.sudoku.validator.repository;

import com.bosch.sast.sudoku.validator.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SudokuRepository
        extends JpaRepository<Board, Long> {
}
