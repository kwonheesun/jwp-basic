
## < 강의 마지막 >
* 흐름을 이해하도록 한다.
* MVC 기반
* Servlet으로 연결해 주는 것은 단 하나 -> Controller로 모두 연결됨

* 단축키 T를 누르면 클래스 바로 검색 가능
* Object... : 인자가 정해지지 않았을 때 이런 식으로 사용 가능. 배열같이 사용 가능 (가변적 전달 가능)
* try ( 자원 제시 ) : 굳이 finally에서 close하지 않아도 됨. jdk1.7 부터 등장.
* RowMapper< > : 유연성을 높이기 위해 Callback interface 사용. - 리팩토링 동영상 참고





#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* WebServerLauncher의 시작 과정이 아닌 clone한 프로젝트의 초기화 과정.

* ContextLoaderListener : DB 초기화 담당
  * 1. contextInitialized 실행
  * 2. ServletContextEvent 를 전달받음
>3. DB의 정보가 담겨있는 jwp.sql을 담은 ClassPathResource 생성
>4. 생성된 ResourceDatabasePopulator에 관련된 정보를 담음
>5. DB연동, 값 전달

* DispatcherServlet : Mapping 담당
>>(name = "dispatcher", urlPatterns = "*.next", loadOnStartup = 1)
>1. RequestMapping.initMapping() : Map<String, Controller> mappings를 포함하고 있으며 각 url별 연결할 Controller를 저장한다.
>2. 서버와 연동되면 받은 url을 출력한다
>3. 받은 url과 비교하여 해당되는 화면을 받아와 Controller에 저장시킨다.
>4. 해당 화면을 web에 뿌려준다.



#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.

- WebSErverLauncher 호출 : 1
- tomcat 초기화 : 2, 
- 포트 번호를 8080으로 세팅함 : 3, 4, 5
- tomcat에 webapp을 추가함 : 6
- jwp.sql을 통해 데이터베이스 세팅함 : 7, 8
- ContextLoaderListener 호출 : 9
- DispatcherServlet 호출 : 10
- tomcat 시작 : 11
- 서버 시작 : 12

* log를 통해 분석
  1. next.WebServerLauncher 호출 (main으로 시작)
  2. org.apache.coyote.AbstractProtocol 초기화
	: ProtocolHandler 초기화
  3. org.apache.tomcat.util.net.NioSelectorPool 공유 Selector를 얻어옴
	: 공유 selector를 이용하여 servlet을 읽고 씀
  4. org.apache.catalina.core.StandardService Internal 시작
	: Tomcat 시작
  5. org.apache.catalina.core.StandardEngine Internal 시작
	: Servlet Engine 시작 - Apache Tomcat/8.0.15
  6. org.apache.catalina.startup.ContextConfig getDefaultWebXmlFragment (디폴트 설정 된 web xml fragment 가져옴)
	: No global web.xml 찾음
  7. org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript
	: Executing SQL script from class path resource [jwp.sql]
  8. org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript
	: Done executing SQL script from class path resource [jwp.sql] in 71 ms.
  9. n.s.context.ContextLoaderListener 호출
	: ServletComtext 로드에 성공
  10. core.mvc.DispatcherServlet 호출
	: Request Mapping 초기화
  11. org.apache.coyote.AbstractProtocol 시작
	: ProtocolHandler 시작 ["http-nio-8080"]
  12. DispatcherServlet를 통해 서버 시작
	: Method : GET, Request URI : /qna/list.next
	
	
#### 4. 필터 한번 더 찾아보기


#### 7. 
* 초기화 하는 부분이 두 군데가 있다.
* initMapping()은 한번 호출 되면 다시 호출 되진 않는다, 재사용 된다. 메모리 낭비 방지 -> single instance
* 잘못 프로그래밍 하면 문제 발생
	
* Stack vs Heap : 멀티쓰레드에서 중요
- 멤버변수는 Heap, 로컬변수는 Stack에 올라간다
- Stack은 별도의 메모리에 저장
- Heap은 공유된 메모리에 저장
- 따라서, Heap 메모리에서 문제가 발생한다
- 상태값이 없는 경우 instance를 하나만 만들어 써도 된다. 하지만 상태값이 없는 경우 instance가 stack에 올라가도록 해주어야 한다.
- 싱크로나이즈를 걸어서 해결이 가능하지만, 한 번에 한 명의 사용자 밖에 사용 할 수 없기 때문에 속도가 많이 떨어지게 된다. 되도록 차선책으로 선택해야 한


	
	