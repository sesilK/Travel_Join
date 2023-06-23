package com.app.service.acpBoard;

import java.sql.SQLException;

import com.app.dto.acpBoard.AcpBoardDto;
public interface AcpBoardService {

	void create(AcpBoardDto dto) throws SQLException;

	AcpBoardDto read(String id) throws SQLException;

	void delete(String id) throws SQLException;

	void update(AcpBoardDto dto) throws SQLException;


}
