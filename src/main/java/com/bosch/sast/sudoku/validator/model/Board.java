package com.bosch.sast.sudoku.validator.model;

import com.bosch.sast.sudoku.validator.utils.ArrayConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cells", columnDefinition = "VARCHAR(255)")
    @Convert(converter = ArrayConverter.class)
    private int[][] cells;
}
