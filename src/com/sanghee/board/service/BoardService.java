package com.sanghee.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanghee.board.dao.BoardDao;
import com.sanghee.board.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public int totalList(String selectField, String searchText){
		return boardDao.totalList(selectField, searchText);
	}
	
	public List<BoardVO> list(String selectField, String searchText, int startRecord, int pageSize) {
		return boardDao.list(selectField, searchText, startRecord, pageSize);
 	}
	
	public BoardVO sub(int id) {
		return boardDao.sub(id);
 	}
	
	public BoardVO save(BoardVO vo) {
		return boardDao.save(vo);
 	}
	
	public BoardVO write(BoardVO vo) {
		return boardDao.write(vo);
 	}
	
	public void delete(int id) {
		boardDao.delete(id);
 	}
	
	
}
