package com.focus.service.board;

import com.focus.po.Board;

import java.util.List;

public interface BoardService {

    /*
    Board selectById(String id);
    Board selectByTitle(String title);
*/
    List<Board> selectAll();

    /*
    Integer insert(Board user);

    boolean update(Board user);

    boolean delete(String id);
    */
}
