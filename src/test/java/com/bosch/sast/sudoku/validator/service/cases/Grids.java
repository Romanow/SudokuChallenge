package com.bosch.sast.sudoku.validator.service.cases;

public final class Grids {
    public static final int[][] FULL_COMPLETED_GRID = new int[][]{
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

    public static final int[][] CORRECT_NOT_FULL_GRID_1 = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] CORRECT_NOT_FULL_GRID_2 = new int[][]{
            {0, 2, 6, 0, 0, 0, 8, 1, 0},
            {3, 0, 0, 7, 0, 8, 0, 0, 6},
            {4, 0, 0, 0, 5, 0, 0, 0, 7},
            {0, 5, 0, 1, 0, 7, 0, 9, 0},
            {0, 0, 3, 9, 0, 5, 1, 0, 0},
            {0, 4, 0, 3, 0, 2, 0, 5, 0},
            {1, 0, 0, 0, 3, 0, 0, 0, 2},
            {5, 0, 0, 2, 0, 4, 0, 0, 9},
            {0, 3, 8, 0, 0, 0, 4, 6, 0}
    };

    public static final int[][] WRONG_COLUMN_GRID = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] WRONG_ROW_GRID = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] WRONG_SUBGRID_GRID_2 = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 9, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] WRONG_SUBGRID_GRID_1 = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 9, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] ROWS_UNCOMPLETED = new int[][]{
            {0, 2, 6, 0, 0, 0, 8, 1, 0},
            {3, 0, 0, 7, 0, 8, 0, 0, 6}
    };

    public static final int[][] COLUMN_UNCOMPLETED = new int[][]{
            {0, 2, 6, 0, 0, 0, 8, 1, 0},
            {3, 0, 0, 7, 0, 8, 0, 0, 6},
            {4, 0, 0, 0, 5, 0, 0, 0, 7},
            {0, 5, 0, 1, 0, 7, 0, 9},
            {0, 0, 3, 9, 0, 5, 1, 0, 0},
            {0, 4, 0, 3, 0, 2, 0, 5, 0},
            {1, 0, 0, 0, 3, 0, 0, 0, 2},
            {5, 0, 0, 2, 0, 4, 0, 0, 9},
            {0, 3, 8, 0, 0, 0, 4, 6, 0}
    };
}
