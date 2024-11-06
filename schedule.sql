DROP DATABASE IF EXISTS schedule_db;
CREATE DATABASE schedule DEFAULT CHARSET = utf8 COLLATE =utf8_bin;

USE schedule;
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `name` varchar(45) DEFAULT NULL,
                        `registration_date` date DEFAULT NULL,
                        `modification_date` date DEFAULT NULL,
                        PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS schedule;
CREATE TABLE `schedule` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `password` varchar(255) DEFAULT NULL,
                            `user_id` int DEFAULT NULL,
                            `detail` varchar(255) DEFAULT NULL,
                            `registration_date` date DEFAULT NULL,
                            `modification_date` date DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `userID_idx` (`user_id`),
                            CONSTRAINT `userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


#일정 생성 쿼리 예시
Insert into schedule (password, user_id , registration_date, modification_date)
values ('test1234','test_id',now(),now());

#전체일정 조회
Select * from schedule;

#선택일정 조회
Select * from schedule where id = 'select_schedule_id';

#선택일정 수정
Update schedule set detail='modify_detail', modification_date=now() where id ='select_schedule_id';

#선택일정 삭제
Delete from schedule where id = 'select_schedule_id';