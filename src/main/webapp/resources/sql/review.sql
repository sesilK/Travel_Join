-- 리뷰ID 시퀀스 생성
CREATE SEQUENCE review_id_seq
START WITH 1
INCREMENT BY 1
;

-- 댓글ID 시퀀스 생성
CREATE SEQUENCE comment_id_seq
START WITH 1
INCREMENT BY 1
;

-- 리뷰 테이블 생성
CREATE TABLE review_bbs (
review_id NUMBER PRIMARY KEY,
user_id VARCHAR2(20) NOT NULL,
plan_id NUMBER(20) NOT NULL,
title VARCHAR2(300) NOT NULL,
content VARCHAR2(4000) NOT NULL,
stars NUMBER(2,1) NOT NULL,
views NUMBER DEFAULT 0,
like_count NUMBER DEFAULT 0,
comment_count NUMBER DEFAULT 0,
report_count NUMBER DEFAULT 0,
create_date DATE DEFAULT SYSDATE,
update_date DATE,
delete_at CHAR(1) DEFAULT 'N',
delete_date DATE,
CONSTRAINT fk_review_bbs_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT fk_review_bbs_plan_id FOREIGN KEY (plan_id) REFERENCES join_board(plan_id),
CONSTRAINT review_bbs_check_stars CHECK (stars IN (0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5)),
CONSTRAINT check_review_delete_at CHECK (delete_at IN ('Y', 'N'))
);

-- 리뷰 임시저장 테이블
CREATE TABLE review_temp (
user_id VARCHAR2(20) NOT NULL PRIMARY KEY,
plan_id NUMBER DEFAULT 0,
stars NUMBER(2,1),
title VARCHAR2(300),
content VARCHAR2(4000),
CONSTRAINT fk_review_temp_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT review_temp_check_stars CHECK (stars IN (0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5))
);

-- 리뷰 댓글 테이블 생성
CREATE TABLE review_comment (
comment_id NUMBER PRIMARY KEY,
review_id NUMBER NOT NULL,
user_id VARCHAR2(20) NOT NULL,
parent_comment_id NUMBER,
comment_lv NUMBER DEFAULT 1,
content VARCHAR2(1000) NOT NULL,
create_date DATE DEFAULT SYSDATE,
update_date DATE,
delete_at CHAR(1) DEFAULT 'N',
delete_date DATE,
CONSTRAINT fk_comment_review_id FOREIGN KEY (review_id) REFERENCES review_bbs(review_id),
CONSTRAINT fk_comment_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT check_comment_delete_at CHECK (delete_at IN ('Y', 'N'))
);

-- 리뷰 추천 테이블 생성
CREATE TABLE review_like (
review_id NUMBER,
user_id VARCHAR2(20),
CONSTRAINT pk_review_like PRIMARY KEY(review_id, user_id),
CONSTRAINT fk_review_like_review_id FOREIGN KEY (review_id) REFERENCES review_bbs(review_id),
CONSTRAINT fk_review_like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 리뷰 이미지 테이블 생성
CREATE TABLE review_image (
review_id NUMBER,
file_name VARCHAR2(300) PRIMARY KEY, -- randomUUID 36Byte + originalFileName 255Byte + ect
CONSTRAINT fk_review_image_review_id FOREIGN KEY (review_id) REFERENCES review_bbs(review_id)
);


-- 테이블 조회
--select * from review_bbs;
--select * from review_temp;
--select * from review_image;
--select * from review_comment;
--select * from review_like;

-- 테이블 데이터 삭제
--truncate table review_bbs;
--truncate table review_temp;
--truncate table review_comment;
--truncate table review_like;
--truncate table review_image;

-- 테이블 삭제
--drop table review_temp;
--drop table review_image;
--drop table review_comment;
--drop table review_like;
--drop table review_bbs;

-- 시퀀스 삭제
--DROP SEQUENCE review_id_seq;
--DROP SEQUENCE comment_id_seq;



-- 리뷰 테이블 코멘트
COMMENT ON COLUMN review_bbs.review_id IS '글번호';
COMMENT ON COLUMN review_bbs.user_id IS '작성자';
COMMENT ON COLUMN review_bbs.plan_id IS '여행';
COMMENT ON COLUMN review_bbs.title IS '제목';
COMMENT ON COLUMN review_bbs.content IS '내용';
COMMENT ON COLUMN review_bbs.stars IS '별점';
COMMENT ON COLUMN review_bbs.views IS '조회수';
COMMENT ON COLUMN review_bbs.like_count IS '추천수';
COMMENT ON COLUMN review_bbs.comment_count IS '댓글수';
COMMENT ON COLUMN review_bbs.report_count IS '신고횟수';
COMMENT ON COLUMN review_bbs.create_date IS '작성일';
COMMENT ON COLUMN review_bbs.update_date IS '수정일';
COMMENT ON COLUMN review_bbs.delete_at IS '삭제여부';
COMMENT ON COLUMN review_bbs.delete_date IS '삭제일';
-- 리뷰 임시저장 테이블 코멘트
COMMENT ON COLUMN review_temp.user_id IS '작성자';
COMMENT ON COLUMN review_temp.plan_id IS '여행';
COMMENT ON COLUMN review_temp.title IS '제목';
COMMENT ON COLUMN review_temp.content IS '내용';
COMMENT ON COLUMN review_temp.stars IS '별점';
-- 리뷰 댓글 테이블 코멘트
COMMENT ON COLUMN review_comment.comment_id IS '댓글번호';
COMMENT ON COLUMN review_comment.review_id IS '해당글';
COMMENT ON COLUMN review_comment.user_id IS '작성자';
COMMENT ON COLUMN review_comment.parent_comment_id IS '부모댓글번호';
COMMENT ON COLUMN review_comment.comment_lv IS '계층';
COMMENT ON COLUMN review_comment.content IS '내용';
COMMENT ON COLUMN review_comment.create_date IS '작성일';
COMMENT ON COLUMN review_comment.update_date IS '수정일';
COMMENT ON COLUMN review_comment.delete_at IS '삭제여부';
COMMENT ON COLUMN review_comment.delete_date IS '삭제일';
-- 리뷰 추천 테이블 코멘트
COMMENT ON COLUMN review_like.review_id IS '해당글';
COMMENT ON COLUMN review_like.user_id IS '추천인';
-- 리뷰 이미지 테이블 코멘트
COMMENT ON COLUMN review_image.review_id IS '해당글';
COMMENT ON COLUMN review_image.file_name IS '파일명';

