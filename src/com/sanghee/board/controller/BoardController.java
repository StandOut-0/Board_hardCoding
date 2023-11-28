package com.sanghee.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.io.InputStream;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//import org.springframework.jdbc.core.JdbcTemplate;
//import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sanghee.board.service.BoardService;
import com.sanghee.board.vo.BoardVO;

@Controller
public class BoardController {

//	2) start
//	private final JdbcTemplate jdbcTemplate;
//	@Autowired
//	public BoardController(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	2) end
	
	
//	3) sqlsession 사용해 쿼리실행 start
//	@Autowired
//	private SqlSession SqlSession;
//	3) sqlsession 사용해 쿼리실행 end

	@Autowired
	BoardService boardService;
	
	//  리스트 + 리스트 검색 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/", "/board/list.do" })
	public ModelAndView list(@RequestParam(value = "selectField", required = false) String selectField,
	                          @RequestParam(value = "searchText", required = false) String searchText,
	                          @RequestParam(value = "currentPage", defaultValue = "0") int currentPage) {
		
		// 전체 게시물 수 가져오기
	    int totalRecords = boardService.totalList(selectField, searchText);
	    System.out.println("전체 게시물 수: "+totalRecords);
	    
		int pageSize = 10;
	    int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		
//		가져오기 시작할 순서
	    int startRecord = currentPage * pageSize;
	    
	    System.out.println("\n--------------------------------------"
	    		+"\n"+"전체 게시물 수" + totalRecords
//	    		+"\n"+"전체 페이지 수" + totalPages
	    		+"\n"+"시작게시물"+startRecord
	    		+ "\n--------------------------------------");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/list");
		
		// 특정 페이지에 대한 게시물 가져오기
		List<BoardVO> list= boardService.list(selectField, searchText, startRecord, pageSize);
		
		mv.addObject("list", list);
		mv.addObject("totalPages", totalPages);
		mv.addObject("currentPage", currentPage);
		

//		return "/board/list";
		return mv;
		
		
//	    System.out.println("search_title: " + search_title);
//	    System.out.println("search_text: " + search_text);

//		1) start
		// JDBC 드라이버 로드
		// JDBC를 직접 사용하여 연결을 시도하고 예외 처리를 통해 연결 상태를 확인하는 전통적인 방법
//		Class.forName("org.mariadb.jdbc.Driver");
//		String jdbcUrl = "jdbc:mariadb://127.0.0.1:3307/mysql";
//        String username = "root";
//        String password = "12341234";
//
//        try {
//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//            System.out.println("연결 성공!");
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("연결 실패: " + e.getMessage());
//        }
//		1) end
		
		
//		2) start
		// Spring의 JdbcTemplate을 사용하여 연결을 시도하고, Spring이 예외 처리를 대신 수행하도록 하는 방법
//		try {
//			// 데이터베이스 연결 상태를 확인하기 위해 쿼리를 실행하지 않고도 연결을 체크할 수 있습니다.
//			jdbcTemplate.getDataSource().getConnection();
//			System.out.println("Database connection successful!");
//		} catch (Exception e) {
//			System.out.println("Database connection failed: " + e.getMessage());
//		}
//		2) end
		
		
		
		
//		클래스패스 경로상 파일확인 start
//		ClassLoader classLoader = getClass().getClassLoader();
//		
//		// 클래스패스 상의 리소스의 경로를 지정하여 읽습니다.
//		InputStream resourceStream = classLoader.getResourceAsStream("mybatis/mybatis-config.xml");
//		if (resourceStream != null) {
//		    System.out.println("mybatis/mybatis-config.xml 파일이 클래스패스 상에 존재합니다.");
//		} else {
//		    System.out.println("mybatis/mybatis-config.xml 파일을 클래스패스에서 찾을 수 없습니다.");
//		}
//		클래스패스 경로상 파일확인 end
		
		
		
		
//		3) sqlsession 사용해 쿼리실행 start
//		System.out.println((Object)SqlSession.selectOne("com.sanghee.board.count"));
//		3) sqlsession 사용해 쿼리실행 end

//		 Map<String, Object> search_data = new HashMap<>();
//		 search_data.put("search_title", search_title);
//		 search_data.put("search_text", search_text);
		 
		
	}

	//  상세페이지 + 새글쓰기페이지 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/board/sub.do" })
	public ModelAndView sub(@RequestParam(value = "id", required = false) Integer id) {

		ModelAndView mv = new ModelAndView();
		 if (id != null) {
		        mv.setViewName("/board/sub");
		        System.out.println("상세페이지로 진입했습니다.");
		        
		        BoardVO sub = boardService.sub(id);
		        mv.addObject("sub", sub);
		        mv.addObject("btn", new String[]{"edit", "상세페이지"});
		    } else {
		        mv.setViewName("/board/sub");
		        mv.addObject("btn", new String[]{"write", "새글쓰기페이지"});
		    }
		
		
		return mv;
		}
	
	//  등록하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/board/write.do" })
	public ModelAndView write(@ModelAttribute("boardVO") BoardVO boardVO) {
	    // title과 text를 직접 받아 사용
		 System.out.println("Received Title: " + boardVO.getTitle());
		    System.out.println("Received Text: " + boardVO.getText());

		    boardService.write(boardVO);
		    
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("redirect:/board/list.do");
	    return mv;
	}

	//  수정페이지 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/board/edit.do" })
	public ModelAndView edit(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/sub");
		
		BoardVO sub= boardService.sub(id);
		mv.addObject("sub", sub); 
		mv.addObject("btn", new String[]{"save", "수정페이지"});
		return mv;}

	//	수정하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/board/save.do" })
	public ResponseEntity save(@ModelAttribute BoardVO boardVO) {
		BoardVO vo = new BoardVO(boardVO.getId(), boardVO.getTitle(), boardVO.getText());
		
		ResponseEntity resEntity = null;
		String message = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			BoardVO save = boardService.save(vo);
			message = "<script>";
			message += " alert('수정이 완료되었습니다.');";
			message += " location.href='" + "/board/sub.do?id=" + vo.getId() + "';";
			message += ("</script>");
		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += ("</script>");
		}


		//각 경우에 따른 message를 가지고 resEntity 리턴한다.
				resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
				return resEntity;
	}
	
	//  삭제하기 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	@RequestMapping(value = { "/board/delete.do" })
	public ModelAndView delete(@RequestParam(value = "id") Integer id) {

		    boardService.delete(id);
		    
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("redirect:/board/list.do");
	    return mv;
	}

}
