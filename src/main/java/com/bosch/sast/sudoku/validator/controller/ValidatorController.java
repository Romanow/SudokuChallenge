package com.bosch.sast.sudoku.validator.controller;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Please think about other possible operations.
 */
public interface ValidatorController {

    @GetMapping(value = "/board/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    BoardDTO getBoard(@PathVariable("id") long id);

    @GetMapping("/board/{id}/isvalid")
    boolean validateBoard(@PathVariable("id") long id);

    @PostMapping(value = "/board",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    BoardDTO addBoard(@RequestBody NewBoardDTO boardDTO);
}
