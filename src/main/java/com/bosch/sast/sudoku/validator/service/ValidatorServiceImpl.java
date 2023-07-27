package com.bosch.sast.sudoku.validator.service;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.mappers.BoardMapper;
import com.bosch.sast.sudoku.validator.repository.SudokuRepository;
import com.bosch.sast.sudoku.validator.service.validators.GridValidator;
import com.bosch.sast.sudoku.validator.service.validators.RowsAndColumnsValidator;
import com.bosch.sast.sudoku.validator.service.validators.SizeValidator;
import com.bosch.sast.sudoku.validator.service.validators.SubGridValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
public class ValidatorServiceImpl
        implements ValidatorService {
    private final SudokuRepository sudokuRepository;
    private final BoardMapper boardMapper;
    private final List<? extends GridValidator> gridValidators;

    public ValidatorServiceImpl(SudokuRepository sudokuRepository, BoardMapper boardMapper) {
        this.sudokuRepository = sudokuRepository;
        this.boardMapper = boardMapper;
        this.gridValidators = List.of(new SizeValidator(), new RowsAndColumnsValidator(), new SubGridValidator());
    }

    @Override
    public boolean isValidSudoku(long boardId) {
        final var board = getBoard(boardId);
        return isValidSudoku(board.getGrid());
    }

    @Override
    public boolean isValidSudoku(int[][] grid) {
        var valid = true;
        for (var validator : gridValidators) {
            valid = valid && validator.validate(grid);
        }
        return valid;
    }

    @Override
    @Transactional
    public BoardDTO saveBoard(NewBoardDTO dto) {
        final var board = sudokuRepository.save(boardMapper.toEntity(dto));
        return boardMapper.toDto(board);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDTO getBoard(long boardId) {
        var board = sudokuRepository
                .findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board '" + boardId + "' not found"));

        return boardMapper.toDto(board);
    }
}
