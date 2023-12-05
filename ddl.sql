-- 로그 테이블 생성 query
create table logs(

                     log_Id int primary key auto_increment,
                     access_time datetime not null,
                     access_url varchar(1000) not null,
                     referrer_url varchar(1000) null


);
-- 로그2 테이블 생성 query
CREATE TABLE logs2 (
                       log_Id INT PRIMARY KEY AUTO_INCREMENT,
                       access_info VARCHAR(255), -- 길이 조정 가능
                       UNIQUE KEY unique_access_info (access_info)
);

create table tbl_access_log(

                               log_seq int primary key auto_increment,
                               access_time datetime not null,
                               access_code int not null

);



-- 더미데이터 넣는 프로시저
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
DELIMITER //
CREATE PROCEDURE generate_logs(IN num_rows INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE access_datetime DATETIME;
    DECLARE access_url_val VARCHAR(1000);
    DECLARE referrer_url_val VARCHAR(1000);

    WHILE i <= num_rows DO
        SET access_datetime = DATE_ADD(NOW(), INTERVAL -FLOOR(RAND() * 365) DAY); -- Random date within the past year
        SET access_datetime = DATE_ADD(access_datetime, INTERVAL FLOOR(RAND() * 24) HOUR); -- Random hour
        SET access_datetime = DATE_ADD(access_datetime, INTERVAL FLOOR(RAND() * 60) MINUTE); -- Random minute
        SET access_datetime = DATE_ADD(access_datetime, INTERVAL FLOOR(RAND() * 60) SECOND); -- Random second

        SET access_url_val = CONCAT('/page_', FLOOR(RAND() * 50)); -- Random page URL
        SET referrer_url_val = CONCAT('/referrer_', FLOOR(RAND() * 50)); -- Random referrer URL

INSERT INTO logs (access_time, access_url, referrer_url)
VALUES (access_datetime, access_url_val, referrer_url_val);

SET i = i + 1;
END WHILE;
END //

DELIMITER ;

CALL generate_logs(1000000);

-- 프로시저 삭제
DROP PROCEDURE IF EXISTS generate_logs;


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 연도별 셀렉트  query
-- 성능 : 0.301 sec / 0.000033 sec

select DATE_FORMAT(access_time, '%Y'), count(DATE_FORMAT(access_time, '%Y'))
from tbl_access_log
group by DATE_FORMAT(access_time, '%Y')
order by DATE_FORMAT(access_time, '%Y') asc;

SELECT
    YEAR(access_time) AS year,
    COUNT(*) AS access_count
FROM
    tbl_access_log
GROUP BY
    YEAR(access_time)
ORDER BY
    YEAR(access_time) DESC;


-- 월별 셀렉트  query
-- 성능 : 0.292 sec / 0.000034 sec
select DATE_FORMAT(access_time, '%Y-%m'), count(DATE_FORMAT(access_time, '%Y-%m'))
from tbl_access_log
WHERE DATE_FORMAT(access_time, '%Y') = '2023'
group by DATE_FORMAT(access_time, '%Y-%m')
order by DATE_FORMAT(access_time, '%Y-%m') asc;


SELECT
    DATE_FORMAT(access_time, '%Y-%m') AS month,
    COUNT(*) AS access_count
FROM
    tbl_access_log
GROUP BY
    DATE_FORMAT(access_time, '%Y-%m')
ORDER BY
    DATE_FORMAT(access_time, '%Y-%m') DESC;



-- 주간별 셀렉트  query
-- 성능 : 0.250 sec / 0.0000079 sec
SELECT
    DATE_FORMAT(MIN(access_time), '%Y-%m-%d') AS start_of_week,
    DATE_FORMAT(MAX(access_time), '%Y-%m-%d') AS end_of_week,
    COUNT(*) AS access_count
FROM
    tbl_access_log
WHERE
        access_time >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)
GROUP BY
    YEAR(access_time), WEEK(access_time)
ORDER BY
    YEAR(access_time) DESC, WEEK(access_time) DESC;


SELECT
    DATE_FORMAT(MIN(access_time), '%Y-%m-%d') AS start_of_week,
    DATE_FORMAT(MAX(access_time), '%Y-%m-%d') AS end_of_week,
    COUNT(*) AS access_count
FROM
    tbl_access_log
WHERE
        access_time >= 'start_date' AND access_time <= 'end_date'
GROUP BY
    YEAR(access_time), WEEK(access_time)
ORDER BY
    YEAR(access_time) DESC, WEEK(access_time) DESC;






-- 일자별 셀렉트  query
-- 성능 : 0.247 sec / 0.0011 sec
select DATE_FORMAT(access_time, '%Y-%m-%d'), count(DATE_FORMAT(access_time, '%Y-%m-%d'))
from logs
WHERE DATE_FORMAT(access_time, '%Y-%m') = '2023-07'
group by DATE_FORMAT(access_time, '%Y-%m-%d')
order by DATE_FORMAT(access_time, '%Y-%m-%d') asc;


SELECT
    DATE_FORMAT(access_time, '%Y-%m-%d') AS date,
    COUNT(*) AS access_count
FROM
    tbl_access_log
WHERE
    DATE_FORMAT(access_time, '%Y-%m') = '특정년-특정월'
GROUP BY
    DATE_FORMAT(access_time, '%Y-%m-%d')
ORDER BY
    date ASC;

-- 시간대별 셀렉트  query
-- 성능 : 0.225 sec / 0.000054 sec
SELECT
    DATE_FORMAT(access_time, '%Y-%m-%d') AS date,
    HOUR(access_time) AS hour,
    COUNT(log_seq) AS access_count
FROM
    tbl_access_log
GROUP BY
    DATE_FORMAT(access_time, '%Y-%m-%d'),
    HOUR(access_time)
ORDER BY
    date, hour;

SELECT
    HOUR(access_time) AS hour,
    COUNT(log_seq) AS access_count
FROM
    tbl_access_log
WHERE
    DATE(access_time) = '특정날짜'
GROUP BY
    HOUR(access_time)
ORDER BY
    hour;


