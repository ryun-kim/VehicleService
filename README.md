# 내차네차
<img src="https://user-images.githubusercontent.com/91525800/160237890-4aa95d20-47b4-405c-a579-e3fc8186641d.png" width="200" height="50" />

## 프로젝트 소개
본 웹사이트는 개인 대 개인, 판매자와 구매자 간에 실시간 채팅을 통해 차를 사고팔 수 있는 중고차 거래 웹사이트입니다.
거래방식은 자유이며 판매하고 싶은 차량을 자유롭게 등록할 수 있고, 이용자가 원하는 거래지역과 조건에 따라 상품을 볼 수 있습니다.
그 외 자동차 관련 뉴스, 보험, 인기차량, 현재날씨 등 유용한 정보를 사이트 이용자에게 제공해 줍니다.
팀 프로젝트로 진행되었으며, 당근마켓, 엔카, KB차차차 사이트를 참고하여 개발하였습니다.

## 개발 환경 
* Intellij
* Visual Studio Code
* Apache Tomcat 9
* Maria DB

## 배포 환경
* AWS (http 배포 성공, https 에러 해결 중)

## 사용 언어 & 기술
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
* OpenWeatherMap API - 이용자가 접속한 지역의 위도, 경도 정보를 통해 날씨 데이터 제공
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
  * 조회수, 페이징
* 상품(차량)
  * 상품 등록
  * 사이드검색, 통합검색 
  * 카테고리별 상품 조회
  * 최신순, 조회순, 좋아요순, 낮은가격순, 높은가격순 보기
  * 페이징, 좋아요
* 그 외
  * 채팅, Ajax통신 
  * 날씨 Api, 뉴스 Api, 소셜 로그인 Api
  * 국산 및 수입별로 상품 보기, 좋아요 전체 삭제

## 팀원 & 담당 역할
* 신재완 - 로그인, 소셜로그인, 회원가입, 아이디/비밀번호 찾기, 이메일 인증, 고객센터(게시판), 날씨
* 이승준 - 차량등록, 사이드/통합 검색, 채팅, 정렬순 보기, 카테고리별 보기
* 김재륜 - 차량리스트(최신순) 로직 구현, 페이징처리, 좋아요, 좋아요 전체 삭제
* 하승화 - 메인페이지 제작, 뉴스 Api 이용, 반응형 구현
* 김민우(Helper) - HTML & CSS 보완

## 시연 영상
<img src="https://user-images.githubusercontent.com/91525800/160244531-659e3dae-bc96-40a4-b1b1-876681b757b8.png" width="700" height="500" />
(채팅 부분은 시연 영상에 포함되어 있지 않아 이미지로 대체합니다.)    

## 사이트 링크
* http://3.39.60.222:8080/home (https 미적용으로 인해 일부 기능이 작동되지 않습니다.)
* https://vehicleservice.tk/home (현재 SSL 인증서가 적용되었으나 AWS 로드밸런서 문제로 발생한 에러를 해결하고 있습니다.) 
