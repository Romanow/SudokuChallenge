package com.bosch.sast.sudoku.validator.mappers;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.model.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    @Mapping(target = "grid", source = "cells")
    BoardDTO toDto(Board board);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cells", source = "grid")
    Board toEntity(NewBoardDTO dto);
}
