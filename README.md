# 내차네차
<img src="https://user-images.githubusercontent.com/91525800/160237890-4aa95d20-47b4-405c-a579-e3fc8186641d.png" width="200" height="50" />

## 프로젝트 소개
본 웹사이트는 개인 대 개인, 판매자와 구매자 간에 실시간 채팅을 통해 차를 사고팔 수 있는 중고차 거래 웹사이트입니다.
판매하고 싶은 차량을 자유롭게 등록할 수 있고, 이용자가 원하는 거래지역과 조건에 따라 상품을 볼 수 있습니다.
그 외 자동차 관련 뉴스, 보험, 인기차량, 현재날씨 등 유용한 정보를 사이트 이용자에게 제공해 줍니다.
팀 프로젝트로 진행되었으며, 당근마켓, 엔카, KB차차차 사이트를 참고하여 개발하였습니다.

## 개발 환경 
* Intellij
* Visual Studio Code
* Apache Tomcat 9
* Maria DB

## 배포 환경
* AWS (http로 배포 성공, http -> https 적용 구현 중)  

## 사용 언어 및 기술
* HTML5
* CSS3
* JavaScript ES 6
* Jquery
* BootStrap
* JAVA11
* Mysql
* SpringBoot
* ThymeLeaf
* Json

## 특징적 기술
* OAuth 2.0 인증 방식 - 카카오 로그인, 네이버 로그인 
* Naver News API - 매일 업데이트되는 차량 관련 새로운 소식 제공 
* OpenWeatherMap API - 해당 위치의 위도, 경도 정보를 통해 날씨 데이터 제공
* 이메일 인증 - 아이디, 비밀번호 찾기시 인증 필요
* 웹소켓통신 - 구매자/판매자 간의 채팅
* 차량 검색 및 정렬시 해당 결과가 페이지 이동 없이 나타남

## 구현 기능
* 계정
  * 로그인, 로그아웃, 회원가입, OAuth 2.0 로그인
  * ID찾기, 비밀번호찾기(새로설정)
  * 이메일 인증
  * MyPage에서 비밀번호 변경
  * 관리자 계정으로 게시글 답변 등록
* 게시판
  * 게시글 CRUD
  * 답변(댓글)달기, 상세게시글이동, 이전글, 다음글
  * 조회수, 페이징처리
* 상품(차량)
  * 상품 등록
  * 사이드검색, 통합검색 
  * 카테고리별 상품 조회
  * 최신순, 조회순, 좋아요순, 낮은가격순, 높은가격순 보기
  * 페이징처리, 좋아요
* 그 외
  * 채팅, Ajax통신 
  * 날씨 Api, 뉴스 Api, 소셜 로그인 Api
  * 국산 및 수입별로 상품 보기, 좋아요 전체 삭제


