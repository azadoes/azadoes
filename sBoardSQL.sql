--sboard 테이블 생성
create table sboard
(sno number(6) constraint sboard_sno_pk primary key, --글번호(pk)
stitle varchar2(200), --제목
scontent clob,--내용
swriter	varchar2(50), -- 작성자
sdate date default sysdate, -- 작성일
shit number(6) default 0, --조회수
spass varchar2(50),--(삭제용)비밀번호
sisShow char(1) default 'Y', --'Y':노출허용	'N':비노출
splace varchar2(150), --모임장소(간단 ex.서울시 강남구)
sdplace varchar2(350), --모임장소(상세 ex.서울시 강남구 신한빌딩 2층)
scost varchar2(150), --모임분류(유료/무료)
stype varchar2(200), --모임분류(취미활동 / 소모임.친목활동)
sday varchar2(150), --모임일정(간단 ex. 2019.12.25)
sdday varchar2(200),--모임일정(상세 ex. 2019.12.25 20:00-22:00)
simglink varchar2(250) --이미지링크(외부,내부 동일주소 사용)
);
update set sdplace='#' where sno='*'

SELECT nvl(max(sno),0)+1
 FROM   sboard;

--DB 시험으로 넣어보기 
 
INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '[12차 앵콜] 구독자 7만 달성/유튜브 제대로 알고 시작하자','유튜버, 크리에이터를 시작하려고 하지만 시작과 방법을 몰라 시작하지 못했던 분들을 위한 특별 프로젝트'
        ,'member03',SYSDATE,0,'1234','Y','서울특별시 강남구','서울특별시 강남구 신한빌딩 2층',
        '유료','취미활동','2020.1.10','2020.1.10 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/171868/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '퍼실리테이션을 활용한 Ideation(공모전 신청모임) 31기',
        '한국퍼실리테이터협회 CF 인증심사에 대비하여 인증신청자의 퍼실리테이션 실행실적을 확보하기 위한 모임입니다.'
        ,'member03', SYSDATE,0,'1234','Y','서울특별시 서초구','서울특별시 서초구 국민빌딩 2층',
        '무료','소모임','2020.1.12','2020.1.12 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/124646/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
		    '근골격계 만성통증으로 인해 고통받고 있는 분들을 초대합니다.',
		    '모임 목적 : 근골격계 만성통증의 원인 파악 및 해결 방안 제시 '
		    ,'member03', SYSDATE,0,'1234','Y','서울특별시 마포구','서울특별시 마포구 우리빌딩 2층',
		    '유료','취미활동','2020.1.14','2020.1.14 20:00-22:00',
		    'https://cfile1.onoffmix.com/images/event/203263/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
				'한양도성 돌기',
				'송년으로 한양도성을 돌 예정입니다.'
				,'member03',SYSDATE,0,'1234','Y','서울특별시 서대문구','서울특별시 서대문구 스타빌딩 2층',
				'무료','교육','2020.1.17','2020.1.17 20:00-22:00',
				'https://cfile1.onoffmix.com/images/event/203123/s');
				
INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
				'통기타/일렉/드럼 원데이클래스!',
				'서울 영등포구청역에 위치한 프리미엄 기타학원/드럼학원 블루기타입니다^^ 카페같이 이쁜 공간에서 음악으로 힐링하면서 좋은사람들과 함께 악기를 배워보아요^^'
				,'member03',SYSDATE,0,'1234','Y','서울특별시 동대문구','서울특별시 동대문구 태양빌딩 2층',
				'유료','취미활동','2020.1.17','2020.1.17 20:00-22:00',
				'https://cfile1.onoffmix.com/images/event/99981/s');

commit;
				
--글입력  ==>파일정보 입력부분을 추가할 예정				

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '모임 제목','모임 내용'
        ,'작성자',SYSDATE,0,'1234','Y','모임 지역','모임 장소',
        '모임 비용','모임 종류','모임 날짜','모임 시간',
        '모임이미지링크');

commit;

select * from sboard
delete sboard

select * from sfileinfo

--목록보기
select stitle,sdate,splace,simglink 
from sboard
where sisShow='Y'

select 
	*
	from(select
							sno,
							stitle,
							sday,
							splace,
							simglink,
							sdate,
							shit,							
					 		row_number() over(order by sdate desc) as rno				
					FROM sboard)
		 where rno Between #{start} and #{end}


SELECT COUNT(*)   
	  FROM   sboard
	  WHERE  sisShow='Y'

--글입력 

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '제목'?,'내용',SYSDATE,0,'1234','Y','서울특별시 강남구','서울특별시 강남구 신한빌딩 2층',
        '유료','취미활동','2020.1.10','2020.1.10 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/202385/s');
        
--파일정보
 create table sFileInfo(
   sFno  	   number(15)
				constraint sFileInfo_sFno_pk primary key,	--파일번호(pk)
   sForiNo    number(6),     										--게시물원글번호 
   sFpath     varchar2(200), 										--파일이 저장된 경로
   sForiName  varchar2(200), 										--파일의 원래이름
   sFsaveName varchar2(200), 										--파일의 실제 저장 이름
   sFlength   number(12)		 											--파일크기
 );        

select * from sboard; 
select * from sFileInfo; 