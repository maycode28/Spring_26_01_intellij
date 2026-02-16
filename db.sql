DROP
DATABASE IF EXISTS `Spring_26_01`;
CREATE
DATABASE `Spring_26_01`;
USE
`Spring_26_01`;

#
게시글 테이블 생성
CREATE TABLE article
(
    id         INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate    DATETIME  NOT NULL,
    updateDate DATETIME  NOT NULL,
    title      CHAR(100) NOT NULL,
    `body`     TEXT      NOT NULL,
    memberId   int(10) unsigned not null,
    boardId    int(10) unsigned not null,
    hitCount   int(100) unsigned not null default 0
);

#
회원 테이블 생성
CREATE TABLE `member`
(
    id           INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate      DATETIME  NOT NULL,
    updateDate   DATETIME  NOT NULL,
    loginId      CHAR(30)  NOT NULL,
    loginPw      CHAR(100) NOT NULL,
    `authLevel`  SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반,7=관리자)',
    `name`       CHAR(20)  NOT NULL,
    nickname     CHAR(20)  NOT NULL,
    cellphoneNum CHAR(20)  NOT NULL,
    email        CHAR(20)  NOT NULL,
    delStatus    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1=탈퇴 후)',
    delDate      DATETIME COMMENT '탈퇴 날짜'
);


#
게시판(board) 테이블 생성
CREATE TABLE board
(
    id         INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate    DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code`     CHAR(50) NOT NULL UNIQUE COMMENT 'notice(공지사항) free(자유) QnA(질의응답)...',
    `name`     CHAR(20) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제 여부 (0=삭제 전, 1=삭제 후)',
    delDate    DATETIME COMMENT '삭제 날짜'
);

#
좋아요 테이블 생성
CREATE TABLE reaction
(
    id              INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate         DATETIME NOT NULL,
    updateDate      DATETIME NOT NULL,
    memberId        int(10) unsigned not null,
    relDataTypeCode char(50) not null,
    relId           int(10) unsigned not null,
    reactionPoint   TINYINT(1) NOT NULL
);

#
게시판 테스트 데이터 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'QnA',
`name` = '질의응답';



#
게시글 TD
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = 2,
boardId=1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 2,
boardId=2;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 2,
boardId=3;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목4',
`body` = '내용4',
memberId = 3,
boardId=2;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목5',
`body` = '내용5',
memberId = 3,
boardId=1;


#
회원 TD
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`authLevel` = 7,
`name` = '관리자',
nickname = '관리자_별명',
cellphoneNum = '01012341234',
email = 'abc@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1',
nickname = '회원1_별명',
cellphoneNum = '01043214321',
email = 'abcd@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2',
nickname = '회원2_별명',
cellphoneNum = '01056785678',
email = 'abced@gmail.com';

#
좋아요 tD
INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relDataTypeCode='article',
relId=4,
reactionPoint =1;

INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
relDataTypeCode='article',
relId=4,
reactionPoint =1;

INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relDataTypeCode='article',
relId=4,
reactionPoint =-1;


INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relDataTypeCode='article',
relId=1,
reactionPoint =-1;


INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relDataTypeCode='article',
relId=2,
reactionPoint =1;


INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relDataTypeCode='article',
relId=1,
reactionPoint =-1;


INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relDataTypeCode='article',
relId=2,
reactionPoint =-1;


INSERT INTO reaction
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
relDataTypeCode='article',
relId=1,
reactionPoint =1;

#
article 테이블에 reactionPoint(좋아요) 컬럼 추가
ALTER TABLE article
    ADD COLUMN likePoint INT(10) UNSIGNED NOT NULL DEFAULT 0;
ALTER TABLE article
    ADD COLUMN dislikePoint INT(10) UNSIGNED NOT NULL DEFAULT 0;

#
update join -> 하나의 테이블의 데이터를 기반으로 다른 테이블의 컬럼에 추가
    # -> 기존 게시글의 good bad RP 값을 RP 테이블에서 추출해서 article 테이블에 채우기

UPDATE article AS A
    INNER JOIN (
    SELECT RP.relDataTypeCode, RP.relId,
    SUM (IF(RP.reactionPoint > 0, RP.reactionPoint ,0)) AS likePoint,
    SUM (IF(RP.reactionPoint < 0, RP.reactionPoint * -1 ,0)) AS dislikePoint
    FROM reaction AS RP
    GROUP BY RP.relDataTypeCode, RP.relId
    ) AS RP_SUM
ON A.id = RP_SUM.relId
    SET A.likePoint = RP_SUM.likePoint, A.dislikePoint = RP_SUM.dislikePoint;

# -------------------SELECT 확인용

SELECT *
FROM article
ORDER BY id DESC;

SELECT *
FROM article
where boardId = 1
ORDER BY id DESC;

SELECT *
FROM `member`;

SELECT *
FROM board;

select *
from reaction;



#----------------------대량 생성

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`body` = CONCAT('내용', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
memberID=FLOOR(RAND() * 3) + 1,
boardId=FLOOR(RAND() * 3) + 1
;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`body` = CONCAT('내용', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
memberID=FLOOR(RAND() * 3) + 1,
boardId=1
;

#-----------------싹 지우기
truncate table article;
