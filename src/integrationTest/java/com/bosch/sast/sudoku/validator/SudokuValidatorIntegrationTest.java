package com.bosch.sast.sudoku.validator;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.google.gson.Gson;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class SudokuValidatorIntegrationTest {
    private static final int[][] THE_ONE_BOARD_GRID = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 0},

            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},

            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    private static final int[][] THE_BAD_BOARD_GRID = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {9, 8, 7, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 0, 0, 0, 0, 0, 0},

            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},

            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 0}};

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadingTheOneBoard() throws Exception {
        final var board = new NewBoardDTO().setGrid(THE_ONE_BOARD_GRID);
        mockMvc.perform(post("/board")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(toJson(board)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(isA(Integer.class)))
               .andExpect(jsonPath("$.grid").value(isJsonArray(THE_ONE_BOARD_GRID)));
    }

    @Test
    void retrievingAJustUploadedBoard() throws Exception {
        long id = upload(THE_ONE_BOARD_GRID);
        mockMvc.perform(get("/board/{id}", id))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void theValidBoardIsValid() throws Exception {
        long id = upload(THE_ONE_BOARD_GRID);
        mockMvc.perform(get("/board/{id}/isvalid", id))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("true"));
    }

    @Test
    void theBadBoardIsInvalid() throws Exception {
        long id = upload(THE_BAD_BOARD_GRID);
        mockMvc.perform(get("/board/{id}/isvalid", id))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("false"));
    }

    private Matcher<Iterable<? extends Iterable<? extends Integer>>> isJsonArray(int[][] grid) {
        return contains(Arrays.stream(grid)
                              .map(array -> contains(Arrays.stream(array)
                                                           .mapToObj(Matchers::is)
                                                           .collect(Collectors.toList())))
                              .collect(Collectors.toList()));
    }

    private long upload(int[][] board) throws Exception {
        final var request = new NewBoardDTO().setGrid(board);
        final var body = mockMvc.perform(post("/board")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(toJson(request)))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

        final var resultingBoard = fromJson(body, BoardDTO.class);

        return resultingBoard.getId();
    }

    @Nullable
    private String toJson(@Nullable Object obj) {
        return gson.toJson(obj);
    }

    @Nullable
    private <T> T fromJson(@Nullable String json, @NotNull Class<T> cls) {
        return gson.fromJson(json, cls);
    }
}