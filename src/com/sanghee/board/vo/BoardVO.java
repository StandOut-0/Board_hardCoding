package com.sanghee.board.vo;

public class BoardVO {

	int id;
	String title;
	String text;
	String writeDate;
	int count;
	
	 // 매개변수가 없는 생성자 추가
    public BoardVO() {
    }
    
	public BoardVO(int id, String title, String text) {
		this.id = id;
        this.title = title;
        this.text = text;
	}
	
	public BoardVO(String title, String text) {
        this.title = title;
        this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
