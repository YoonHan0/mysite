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

SELECT *
FROM board;
DESC board;

SELECT *
FROM user;

ISERT INTO board VALUES(null, '오늘점심뭐먹', '제발 되라 부탁할께', 0, now(), 0, 0, 0, 1);