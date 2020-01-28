from selenium import webdriver
import time
from bs4 import BeautifulSoup

#모임 게시판 : 문화플랫폼 온오프믹스 데이터 크롤링 py파일 - 모임게시판 담당 남택구

#-- 셀레니움과 뷰티풀 숩이용하여
#-- ONOFFMIX 모임 베스트 3가지 카테고리 크롤링한 후,
#-- 오라클 연동으로 DB 저장 후, 모임게시판에 크롤링 데이터 사용

onoffmix=['https://www.onoffmix.com/event/main/?c[0]=094&c[1]=095&c[2]=023&c[3]=096',
        'https://www.onoffmix.com/event/main/?c[0]=085&c[1]=086',
        'https://www.onoffmix.com/event/main/?c[0]=091&c[1]=090']
# 'https://www.onoffmix.com/event/main/?c[0]=091&c[1]=090'  #소모임・친목행사・취미활동
# 'https://www.onoffmix.com/event/main/?c[0]=085&c[1]=086'  #교육・강연
# 'https://www.onoffmix.com/event/main/?c[0]=094&c[1]=095&c[2]=023&c[3]=096'  # 패션・뷰티・이벤트・파티・후원금・기타

for o in onoffmix:

    driver=webdriver.Chrome('chromedriver')
    url=o
    driver.get(url)
    time.sleep(3)
    # print(driver.page_source)

    webdom=BeautifulSoup(driver.page_source,'lxml')
    articledoms=webdom.find_all('article',{'class':'event_area event_main'})
    # print(len(articledoms))
    # print(articledoms)

    import cx_Oracle
    conn=cx_Oracle.connect('hong/1234@localhost:1521/xe') # 1. 오라클 DB연결하기
    cursor = conn.cursor()     # 2.커서얻기

    for articledom in articledoms:
        i = 0
        i=i+1
        adom= articledom.find('a')['href']
        idom = articledom.find('img')['src']
        hdom = articledom.find('h5').text.strip()
        pdom = articledom.find("span",{'class':'payment_type pay'})
        if pdom==None:      # pdom 이상 처리 if문
            pdom="무료"
        else:
            pdom=pdom.text
        cdom = articledom.find("span",{'class':'category_type'}).text
        ddom = articledom.find("div", {'class': 'date'}).text
        d2dom = articledom.find("span",{'class':'date'}).text
        p2dom = articledom.find("span",{'class':'place'}).text
        print(hdom,type(hdom))

        if p2dom.find("서울")<0:  #주소에 '서울'이란 글자가 들어가 있지 않다면 continue에서 앞으로 되돌아감.
            continue

        # 3.쿼리작성하기
        sql = "insert into sboard values ((select nvl(max(sno),0)+1 from sboard),:a,null,'onoffmix20',SYSDATE,0,'0301','Y',:b,null,:c,:d,:e,:f,:g)"
        print(sql)

        cursor.execute(sql, a=hdom,b=p2dom,c=pdom,d=cdom,e=ddom,f=d2dom,g=idom)  # 4.오라클 DB에 데이터 저장하기
        conn.commit()

    conn.close() # 5.해제하기

#-- 셀레니움과 뷰티풀 숩이용하여 ONOFFMIX 각 모임 상세 사이트 크롤링

for o in onoffmix:

    driver=webdriver.Chrome('chromedriver')
    url=o

    # onoffmix=['https://www.onoffmix.com/event/main/?c[0]=094&c[1]=095&c[2]=023&c[3]=096',
    #           'https://www.onoffmix.com/event/main/?c[0]=085&c[1]=086',
    #           'https://www.onoffmix.com/event/main/?c[0]=091&c[1]=090']
    # 소모임・친목행사・취미활동  /  교육・강연  / 패션・뷰티・이벤트・파티・후원금・기타
    driver.get(url)
    time.sleep(3)
    # print(driver.page_source)

    webdom=BeautifulSoup(driver.page_source,'lxml')
    atdoms=webdom.find_all('article',{'class':'event_area event_main'})

    #hlist에 각각의 상세보기 사이트 주소 넣기
    hlist=[]
    for atdom in atdoms:
        adom = atdom.find('a')['href']
        h='https://www.onoffmix.com'+adom
        # print(h)
        hlist.append(h)

    print(hlist)

    import cx_Oracle

    conn = cx_Oracle.connect('hong/1234@localhost:1521/xe')  # 1. 오라클 DB연결하기
    cursor = conn.cursor()  # 2.커서얻기

    # hlist에 담긴 각각의 상세보기 사이트로 들어 가서 각각의 썸네일 이미지 링크 긁어온 후
    # 기존에 오라클 DB에 넣어놓은 자료와 썸네일 이미지가 같은 DB에 null값인 모임 상세 소개, 상세 장소 업데이트 하기.

    for h in hlist:
        i = 0
        url = h
        driver.get(url)
        time.sleep(3)
        i = i + 1
        webdom2 = BeautifulSoup(driver.page_source, 'lxml')
        a2dom = webdom2.find('span', {'class': 'adress'}).text
        p4dom = webdom2.find('p', {'class': 'summary_txt'}).text
        divdom= webdom2.find('div', {'class': 'event_thumbnail main_thumbnail'})
        idom2 = divdom.find('img')['src']
        p4dom = webdom2.find('p', {'class': 'summary_txt'}).text.strip()
        p4dom=p4dom[0:200]
        print(i,hdom,p4dom,idom2)

        # 3.쿼리작성하기
        sql = "update sboard set scontent=:a,sdplace=:b where simglink='" + idom2 + "'"
        print(sql)
        print(url)

        cursor.execute(sql, a=p4dom, b=a2dom)  # 4.오라클 DB에 데이터 저장하기
        conn.commit()
    conn.close()  # 5.해제하기