package com.focus.service.board.impl;

import com.focus.dao.BoardDao;
import com.focus.po.Board;
import com.focus.service.board.BoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDao boardDao;

    /*
    @Override
    @Transactional(readOnly = true)
    public Board selectById(String id) {
        return boardDao.selectById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Board selectByUserName(String username) {
        return boardDao.selectByUserName(username);
    }
    */
    @Override
    @Transactional(readOnly = true)
    public List<Board> selectAll() {
        return boardDao.selectAll();
    }

    /*
    @Override
    public Integer insert(Board board) {
        boardDao.insert(board);
        return board.getId();
    }

    @Override
    public boolean update(Board board) {
        return boardDao.update(board) == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return boardDao.deleteById(id) == 1;
    }
    */
}