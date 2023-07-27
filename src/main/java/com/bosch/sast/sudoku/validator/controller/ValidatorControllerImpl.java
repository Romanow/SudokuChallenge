package com.bosch.sast.sudoku.validator.controller;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.service.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ValidatorControllerImpl
        implements ValidatorController {
    private final ValidatorService validatorService;

    @Override
    public BoardDTO getBoard(long id) {
        return validatorService.getBoard(id);
    }

    @Override
    public boolean validateBoard(long id) {
        return validatorService.isValidSudoku(id);
    }

    @Override
    public BoardDTO addBoard(NewBoardDTO newBoardDTO) {
        return validatorService.saveBoard(newBoardDTO);
    }
}
