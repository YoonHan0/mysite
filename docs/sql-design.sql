show tables;

SELECT * FROM emaillist;
DESC emaillist;
insert INTO emaillist VALUES(null, '윤', '한영', 'dbsgksdud@test.com');

-- join
SELECT * FROM user;
insert INTO user VALUES(null, '윤한영', 'dbsgksdud@test.com', password('1234'), 'male', now());

-- UPDATE
UPDATE user SET name = '둘리', gender = 'female' WHERE no = 1;

-- login
SELECT * FROM user WHERE email = 'dbsgksdud@test.com' AND password = password('1234');

SELECT * FROM guestbook;
DESC guestbook;
insert INTO guestbook VALUES(null, '윤한영', '1234', '안녕하세요', now());

SELECT a.no, a.title, b.name, a.hit, a.reg_date
FROM board a JOIN user b ON a.user_no = b.no;

SELECT *
FROM board
ORDER BY no ASC;
DESC board;

INSERT INTO board VALUES(null, '오늘점심뭐먹', '제발 되라 부탁할께', 0, now(), 0, 0, 0, 1);

SELECT title, contents
FROM board
WHERE no=1;

UPDATE board
SET title="수정완료 테스트380", contents="수정 완료 테스트 380"
WHERE no = 1;

DELETE
FROM board
WHERE no=2;

SELECT *
FROM board;

DESC board;

INSERT INTO board VALUES(null, "테스트 제목2", "테스트 내용2", 0, now(), (SELECT IFNULL(MAX(g_no)+1, 1) FROM board b), 1, 0, 1);


INSERT INTO board VALUES(null, "테스트 댓글1", "테스트 댓글 내용1", 0, now(), 1, 1, 0, 1);

SELECT *
FROM board a JOIN user b ON a.user_no = b.no
LIMIT 0, 5;


SELECT *
FROM board;

DESC user;
UPDATE board
SET o_no = o_no + 1
WHERE g_no = 4 AND o_no > 1;

SELECT count(no)
FROM board;
SELECT *
FROM board;
SELECT *
FROM user;
SELECT *
FROM guestbook;
SELECT *
FROM user;

SELECT a.no, a.title, b.name, a.hit, a.reg_date, a.user_no, a.o_no, a.depth
			FROM board a JOIN user b ON a.user_no = b.no
			ORDER BY a.g_no DESC, a.o_no ASC;

SELECT title, contents, user_no 
FROM board
WHERE no = 2;

SELECT no, title, contents, user_no AS userNo
			FROM board
			WHERE no = 2;



SELECT *
FROM user;

SELECT *
FROM board
WHERE g_no = 30;

SELECT title, u.name, hit, reg_date
FROM board b JOIN user u ON b.user_no = u.no
WHERE title
LIKE '%제목30%';
