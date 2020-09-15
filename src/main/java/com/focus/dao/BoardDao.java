package com.focus.dao;

import com.focus.po.Board;

import java.util.List;

public interface BoardDao  {
    Board selectById(String id);

    List<Board> selectAll();

    Board selectByTitle(String title);

    void insert(Board board);

    int update(Board board);

    int deleteById(String id);
}
