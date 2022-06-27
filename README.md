# ✈️ mileage-system

여행자 클럽 마일리지 서비스 API 개발 과제 입니다.

------------
* ### 개발환경
  #### - Back-end : Spring Boot 2.7
  #### - JDK: 1.8 
  #### - Front-end : Jsp, Jqurey
  #### - DB : Mysql 5.7
  #### - IDE : VSC

------------
* ### 애플리케이션 실행방법
  - VSC Extension인 Spring Boot Dashboard에서 프로젝트 Run As를 통해서 프로젝트를 실행 시킬 수 있습니다.  
  - 실행시 8080포트로 서버 포트가 열리게 되며,
  - 브라우저의 http://localhost:8080/login 주소로 접속하여 로그인화면을 조우 할 수 있습니다.  
  - 주의! 보안상 application.properties 파일은 해당 리포지토리에 업로드 되어있지 않습니다.(해당 파일은 회신 메일과 함께 첨부 해두었습니다.).  
  - 하단의 sql파일에 미리 회원계정을 생성해두었으니 로그인시 사용해주시면 되겠습니다.
  
------------

* ### 구현방식
  MVC 방식을 사용하여 백엔드를 구현하였으며, 프론트의 axios를 사용하여 백엔드와 비동기 통신 또한 구현하였습니다.  
  jdbc를 사용하여 DB와 연결하였고, mybatis를 사용하여 XML 쿼리문을 작성하였습니다.
  
------------  
  
* ### SQL 파일
  https://drive.google.com/file/d/1zb9IUC7qPwcUQZeTLC9P5caLm0XFN0CQ/view?usp=sharing
------------

* ### ERD
  - member 테이블 : 회원 정보를 담고있는 테이블
  - point_history 테이블 : 포인트 증감시 히스토리 기록 테이블
  - review 테이블 : 리뷰 정보 테이블
  - place 테이블 : 여행지 정보 테이블

  ![AQueryTool](https://user-images.githubusercontent.com/57612338/175874226-f8c7eb18-5f99-43ce-970a-9c5fb8c7cc04.png)

------------

* ### 회원목록

  ID | PW
  ---|---
  test1 | 1234
  test2 | 1234
  test3 | 1234
  
------------

* ### 사용된 VSC Extension
  - Spring Boot Dashboard.  
  - Spring Boot Extension Pach.  
  - Spring Boot Tools.  
  - Spring Initializr Java Support.  
  - Lombok Annotations Support.  
