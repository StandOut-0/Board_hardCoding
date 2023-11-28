package com.sanghee.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanghee.board.vo.BoardVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

@Repository
public class BoardDao {
	

	@Autowired
	private SqlSession SqlSession;
	
	//  리스트 + 리스트 검색 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public int totalList(String selectField, String searchText){
		Map<String, Object> search_data = new HashMap<>();
	    search_data.put("selectField", selectField);
	    search_data.put("searchText", searchText);
		return SqlSession.selectOne("com.sanghee.board.totalList", search_data);
	}

	public List<BoardVO> list(String selectField, String searchText, int startRecord, int pageSize) {
		
		 // 검색 조건이 없을 경우 전체 리스트 가져오는 로직
//	    if (selectField == null && searchText == null) {
//	    	System.out.println("검색조건없음");
//	        return SqlSession.selectList("com.sanghee.board.list");
//	    }
	 // 검색 조건이 있을 경우 해당 조건으로 리스트 가져오는 로직
	    Map<String, Object> search_data = new HashMap<>();
	    search_data.put("selectField", selectField);
	    search_data.put("searchText", searchText);
	    search_data.put("startRecord", startRecord);
	    search_data.put("pageSize", pageSize);
	    System.out.println("검색조건있음" + search_data);

	    return SqlSession.selectList("com.sanghee.board.list", search_data);
	}
	
	
//  등록하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public BoardVO write(BoardVO vo) {
		BoardVO save = (BoardVO) SqlSession.selectOne("com.sanghee.board.write", vo);
	    return save;
	}
	
//  상세페이지 + 새글쓰기페이지 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public BoardVO sub(int id) {
		BoardVO sub = (BoardVO) SqlSession.selectOne("com.sanghee.board.sub", id);
	    return sub;
	}
	
	//  수정하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public BoardVO save(BoardVO vo) {
		BoardVO save = (BoardVO) SqlSession.selectOne("com.sanghee.board.save", vo);
	    return save;
	}
	
	//  삭제하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void delete(int id) {
	    int deleteCount = SqlSession.delete("com.sanghee.board.delete", id);
	}
	
}
