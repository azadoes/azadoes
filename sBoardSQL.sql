--sboard ���̺� ����
create table sboard
(sno number(6) constraint sboard_sno_pk primary key, --�۹�ȣ(pk)
stitle varchar2(200), --����
scontent clob,--����
swriter	varchar2(50), -- �ۼ���
sdate date default sysdate, -- �ۼ���
shit number(6) default 0, --��ȸ��
spass varchar2(50),--(������)��й�ȣ
sisShow char(1) default 'Y', --'Y':�������	'N':�����
splace varchar2(150), --�������(���� ex.����� ������)
sdplace varchar2(350), --�������(�� ex.����� ������ ���Ѻ��� 2��)
scost varchar2(150), --���Ӻз�(����/����)
stype varchar2(200), --���Ӻз�(���Ȱ�� / �Ҹ���.ģ��Ȱ��)
sday varchar2(150), --��������(���� ex. 2019.12.25)
sdday varchar2(200),--��������(�� ex. 2019.12.25 20:00-22:00)
simglink varchar2(250) --�̹�����ũ(�ܺ�,���� �����ּ� ���)
);
update set sdplace='#' where sno='*'

SELECT nvl(max(sno),0)+1
 FROM   sboard;

--DB �������� �־�� 
 
INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '[12�� ����] ������ 7�� �޼�/��Ʃ�� ����� �˰� ��������','��Ʃ��, ũ�������͸� �����Ϸ��� ������ ���۰� ����� ���� �������� ���ߴ� �е��� ���� Ư�� ������Ʈ'
        ,'member03',SYSDATE,0,'1234','Y','����Ư���� ������','����Ư���� ������ ���Ѻ��� 2��',
        '����','���Ȱ��','2020.1.10','2020.1.10 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/171868/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '�۽Ǹ����̼��� Ȱ���� Ideation(������ ��û����) 31��',
        '�ѱ��۽Ǹ���������ȸ CF �����ɻ翡 ����Ͽ� ������û���� �۽Ǹ����̼� ��������� Ȯ���ϱ� ���� �����Դϴ�.'
        ,'member03', SYSDATE,0,'1234','Y','����Ư���� ���ʱ�','����Ư���� ���ʱ� ���κ��� 2��',
        '����','�Ҹ���','2020.1.12','2020.1.12 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/124646/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
		    '�ٰ�ݰ� ������������ ���� ����ް� �ִ� �е��� �ʴ��մϴ�.',
		    '���� ���� : �ٰ�ݰ� ���������� ���� �ľ� �� �ذ� ��� ���� '
		    ,'member03', SYSDATE,0,'1234','Y','����Ư���� ������','����Ư���� ������ �츮���� 2��',
		    '����','���Ȱ��','2020.1.14','2020.1.14 20:00-22:00',
		    'https://cfile1.onoffmix.com/images/event/203263/s');

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
				'�Ѿ絵�� ����',
				'�۳����� �Ѿ絵���� �� �����Դϴ�.'
				,'member03',SYSDATE,0,'1234','Y','����Ư���� ���빮��','����Ư���� ���빮�� ��Ÿ���� 2��',
				'����','����','2020.1.17','2020.1.17 20:00-22:00',
				'https://cfile1.onoffmix.com/images/event/203123/s');
				
INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
				'���Ÿ/�Ϸ�/�巳 ������Ŭ����!',
				'���� ��������û���� ��ġ�� �����̾� ��Ÿ�п�/�巳�п� ����Ÿ�Դϴ�^^ ī�䰰�� �̻� �������� �������� �����ϸ鼭 ���������� �Բ� �Ǳ⸦ ������ƿ�^^'
				,'member03',SYSDATE,0,'1234','Y','����Ư���� ���빮��','����Ư���� ���빮�� �¾���� 2��',
				'����','���Ȱ��','2020.1.17','2020.1.17 20:00-22:00',
				'https://cfile1.onoffmix.com/images/event/99981/s');

commit;
				
--���Է�  ==>�������� �Էºκ��� �߰��� ����				

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '���� ����','���� ����'
        ,'�ۼ���',SYSDATE,0,'1234','Y','���� ����','���� ���',
        '���� ���','���� ����','���� ��¥','���� �ð�',
        '�����̹�����ũ');

commit;

select * from sboard
delete sboard

select * from sfileinfo

--��Ϻ���
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

--���Է� 

INSERT INTO sboard
VALUES( (  SELECT nvl(max(sno),0)+1  FROM   sboard   ),
        '����'?,'����',SYSDATE,0,'1234','Y','����Ư���� ������','����Ư���� ������ ���Ѻ��� 2��',
        '����','���Ȱ��','2020.1.10','2020.1.10 20:00-22:00',
        'https://cfile1.onoffmix.com/images/event/202385/s');
        
--��������
 create table sFileInfo(
   sFno  	   number(15)
				constraint sFileInfo_sFno_pk primary key,	--���Ϲ�ȣ(pk)
   sForiNo    number(6),     										--�Խù����۹�ȣ 
   sFpath     varchar2(200), 										--������ ����� ���
   sForiName  varchar2(200), 										--������ �����̸�
   sFsaveName varchar2(200), 										--������ ���� ���� �̸�
   sFlength   number(12)		 											--����ũ��
 );        

select * from sboard; 
select * from sFileInfo; 