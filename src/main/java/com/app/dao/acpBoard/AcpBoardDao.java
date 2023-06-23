package com.app.dao.acpBoard;

import java.util.List;

import com.app.dto.acpBoard.AcpBoardDto;

public interface AcpBoardDao {
	
	public void create(AcpBoardDto dto) throws Exception;
	public AcpBoardDto read(int bno) throws Exception;
	public void delete(int bno) throws Exception;
	public void update(AcpBoardDto dto) throws Exception;
	public List<AcpBoardDto> listAll() throws Exception;
}
