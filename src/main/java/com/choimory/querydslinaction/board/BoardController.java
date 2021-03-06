package com.choimory.querydslinaction.board;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.service.BoardService;
import com.choimory.querydslinaction.common.response.util.CommonResponseData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<CommonResponseData<?>> getBoards(final BoardRequestDto param, Pageable pageable) {
        return new ResponseEntity<>(CommonResponseData.builder()
                .data(boardService.getBoards(param, pageable))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<CommonResponseData<?>> getBoard(@PathVariable Long idx) {
        return new ResponseEntity<>(CommonResponseData.builder()
                .data(boardService.getBoard(BoardRequestDto.builder()
                        .idx(idx)
                        .build()))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/tuple")
    public ResponseEntity<CommonResponseData<?>> getBoardsTuple(final BoardRequestDto param, final Pageable pageable) {
        return ResponseEntity.ok(CommonResponseData.builder()
                .data(boardService.getBoardTuple(param, pageable))
                .build());
    }

    @GetMapping("/projections")
    public ResponseEntity<CommonResponseData<?>> getBoardsProjectionFields(final BoardRequestDto param, final Pageable pageable) {
        return ResponseEntity.ok(CommonResponseData.builder()
                .data(boardService.getBoardFields(param, pageable))
                .build());
    }

    @GetMapping("/dynamic-count")
    public ResponseEntity<CommonResponseData<?>> getBoardsWithDynamicTotalCount(final BoardRequestDto param, final Pageable pageable) {
        return ResponseEntity.ok(CommonResponseData.builder()
                .data(boardService.getBoardsWithDynamicTotalCount(param, pageable))
                .build());
    }

    @GetMapping("/select-sub-query/{nickname}")
    public ResponseEntity<CommonResponseData<?>> selectSubQuery(@PathVariable String nickname, final Pageable pageable) {
        return ResponseEntity.ok(CommonResponseData.builder()
                .data(boardService.selectSubQuery(BoardRequestDto.builder()
                        .nickname(nickname)
                        .build(), pageable))
                .build());
    }

    @GetMapping("/where-sub-query/{nickname}")
    public ResponseEntity<CommonResponseData<?>> whereSubQuery(@PathVariable String nickname, final Pageable pageable) {
        return ResponseEntity.ok(CommonResponseData.builder()
                .data(boardService.whereSubQuery(BoardRequestDto.builder()
                        .nickname(nickname)
                        .build(), pageable))
                .build());
    }
}
