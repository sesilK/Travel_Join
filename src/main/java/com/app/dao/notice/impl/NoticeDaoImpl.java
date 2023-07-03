package com.app.dao.notice.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.notice.NoticeDao;
import com.app.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	@Override
	public int insertNotice(NoticeDto noticeDto) {
		
		int result = sqlsessionTemplate.insert("notice_mapper.insert_notice", noticeDto);
		
		return result;
	}
	
}
