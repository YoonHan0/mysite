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

SELECT b.title, u.name, b.hit, b.reg_date
FROM board b JOIN user u ON b.user_no = u.no
WHERE title
LIKE '%제목30%';


SELECT a.no, a.title, b.name, a.hit, a.reg_date, a.user_no, a.o_no, a.depth
			FROM board a JOIN user b ON a.user_no = b.no
			WHERE title LIKE '%제목30%'
			ORDER BY a.g_no DESC, a.o_no ASC;
            
            
			SELECT a.no, a.title, b.name, a.hit, a.reg_date, a.user_no, a.o_no, a.depth
			FROM board a JOIN user b ON a.user_no = b.no
			WHERE a.title LIKE '%제목30%'
			ORDER BY a.g_no DESC, a.o_no ASC;
            

DESC user;
ALTER TABLE user ADD COLUMN role enum("ADMIN", "USER") default "USER" after gender;
SELECT * FROM user;

INSERT INTO user VALUES(null, '관리자', 'admin@test.com', password('1234'), 'male', 'admin', now());

DESC site;

INSERT INTO site VALUES('Mysite', '안녕하세요. 윤한영의 mysite에 오신 것을 환영합니다.', '/assets/images/profil_img.jpeg', '이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹 프로그래밍 수\n업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.', null);

SELECT * FROM site;

SELECT title, welcome, profile, description
FROM site
ORDER BY no ASC
LIMIT 0, 1;


UPDATE site
SET title="My Site", welcome = "안녕하세요. 윤한영입니다!", profile =  "", description = "환영합니다!"
WHERE no = 1;



DESC site;
INSERT INTO site VALUES(null, 'Mysite', '안녕하세요. 윤한영의 mysite에 오신 것을 환영합니다.', '/assets/images/profil_img.jpeg', '이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹 프로그래밍 수\n업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.');


DESC gallery;
SELECT * FROM gallery;

SELECT no, url, comments
FROM gallery;

INSERT INTO gallery VALUES(null, "/assets/images/profil_img.jpeg", "윤한영입니다!");



