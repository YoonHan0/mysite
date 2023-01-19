show tables;

SELECT * FROM emaillist;
DESC emaillist;
insert INTO emaillist VALUES(null, '윤', '한영', 'dbsgksdud@test.com');


SELECT * FROM user;
insert INTO user VALUES(null, '윤한영', 'dbsgksdud@test.com', '1234', 'male', now());

SELECT * FROM guestbook;
DESC guestbook;
insert INTO guestbook VALUES(null, '윤한영', '1234', '안녕하세요', now());