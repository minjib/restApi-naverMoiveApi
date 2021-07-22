# restApi-naverMovieApi

네이버 검색 open api를 사용하여 api 서버를 구현하는 연습 프로젝트입니다.   
모든 입출력 결과는 json으로 주고 받습니다.

## 개발 환경
+ IDE: Intellij IDEA Community
+ OS: Windows 10

### 영화 목록 조회 API
+ 평점 높은 순으로 내림차순 정렬   
- 출력 예시   
```
[   
   {  "id":null,   
      "title":"<b>반지의 제왕</b>: 왕의 귀환",
      "link":"https://movie.naver.com/movie/bi/mi/basic.nhn?code=31796",   
      "image":"https://ssl.pstatic.net/imgmovie/mdi/mit110/0317/31796_P74_100250.jpg",   
      "subtitle":"The Lord Of The Rings: The Return Of The King",   
      "pubDate":"1970-01-01T00:00:02.003+0000",   
      "director":"피터 잭슨|",   
      "actor":"일라이저 우드|숀 애스틴|앤디 서키스|이안 맥켈런|리브 타일러|비고 모텐슨|올랜도 블룸|존 라이스 데이비스|빌리 보이드|도미닉 모나한|버나드 힐|미란다 오토|",   
      "userRating":9.38   
   }
 ...
   {  "id":null,
      "title":"<b>반지의 제왕</b>",
      "link":"https://movie.naver.com/movie/bi/mi/basic.nhn?code=98144",
      "image":"https://ssl.pstatic.net/imgmovie/mdi/mit110/0981/98144_P01_150352.jpg",
      "subtitle":"","pubDate":"1970-01-01T00:00:02.012+0000","director":"",
      "actor":"이휘재|김신영|",
      "userRating":5.83
   }
]
```