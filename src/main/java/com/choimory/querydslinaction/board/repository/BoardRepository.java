package com.choimory.querydslinaction.board.repository;

import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.repository.custom.CustomBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, CustomBoardRepository {
}
