package com.app.service.acpBoard.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dto.acpBoard.AcpBoardDto;
import com.app.service.acpBoard.AcpBoardService;

public class AcpBoardServiceImpl implements AcpBoardService{

    private Connection getConnection() throws SQLException {
        // 데이터베이스 연결 정보 설정
    	String db_url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String db_id = "scott";
		String db_pw = "tiger";
        
        // JDBC 드라이버 로드
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        // 데이터베이스 연결
        Connection connection = DriverManager.getConnection(db_url, db_id, db_pw);
        return connection;
    }

    @Override
    public void create(AcpBoardDto dto) throws SQLException {
        // INSERT 문을 실행하여 데이터 생성
        String sql = "INSERT INTO TestBoard (id, is_closed, title, content_text, content_img, hashtags, start_date, end_date, view_count, participants, reg_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 파라미터 설정
            statement.setString(1, dto.getId());
            statement.setString(2, dto.getIsClosed());
            statement.setString(3, dto.getTitle());
            statement.setString(4, dto.getContent_text());
            statement.setString(5, dto.getContent_img());
            statement.setString(6, dto.getHashtags());
            statement.setString(7, dto.getStart_date());
            statement.setString(8, dto.getEnd_date());
            statement.setInt(9, dto.getView_count());
            statement.setInt(10, dto.getParticipants());
            statement.setDate(11,dto.getReg_date());
            
            // SQL 실행
            statement.executeUpdate();
        }
    }

    @Override
    public AcpBoardDto read(String id) throws SQLException {
        // SELECT 문을 실행하여 데이터 조회
        String sql = "SELECT * FROM TestBoard WHERE id = ?";
        AcpBoardDto dto = null;
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 파라미터 설정
            statement.setString(1, id);
            
            // SQL 실행
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // 결과 처리
                    dto = new AcpBoardDto();
                    dto.setId(resultSet.getString("id"));
                    dto.setIsClosed(resultSet.getString("is_closed"));
                    dto.setTitle(resultSet.getString("title"));
                    dto.setContent_text(resultSet.getString("content_text"));
                    dto.setContent_img(resultSet.getString("content_img"));
                    dto.setHashtags(resultSet.getString("hashtags"));
                    dto.setStart_date(resultSet.getString("start_date"));
                    dto.setEnd_date(resultSet.getString("end_date"));
                    dto.setView_count(resultSet.getInt("view_count"));
                    dto.setParticipants(resultSet.getInt("participants"));
                    dto.setReg_date(resultSet.getDate("reg_date"));
                }
            }
        }
        
        return dto;
    }

    @Override
    public void delete(String id) throws SQLException {
        // DELETE 문을 실행하여 데이터 삭제
        String sql = "DELETE FROM TestBoard WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 파라미터 설정
            statement.setString(1, id);
            
            // SQL 실행
            statement.executeUpdate();
        }
    }


    @Override
    public void update(AcpBoardDto dto) throws SQLException {
        // UPDATE 문을 실행하여 데이터 수정
        String sql = "UPDATE TestBoard SET is_closed = ?, title = ?, content_text = ?, content_img = ?, hashtags = ?, start_date = ?, end_date = ?, view_count = ?, participants = ?, reg_date = ? WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 파라미터 설정
            statement.setString(1, dto.getIsClosed());
            statement.setString(2, dto.getTitle());
            statement.setString(3, dto.getContent_text());
            statement.setString(4, dto.getContent_img());
            statement.setString(5, dto.getHashtags());
            statement.setString(6, dto.getStart_date());
            statement.setString(7, dto.getEnd_date());
            statement.setInt(8, dto.getView_count());
            statement.setInt(9, dto.getParticipants());
            statement.setDate(10, new java.sql.Date(dto.getReg_date().getTime()));
            statement.setString(11, dto.getId());
            
            // SQL 실행
            statement.executeUpdate();
        }
    }
}
