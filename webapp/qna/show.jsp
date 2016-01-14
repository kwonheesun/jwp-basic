<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP</title>

<%@ include file="/include/header.jspf" %>

</head>
<body>
	<%@ include file="/include/navigation.jspf" %>

	<div class="container">
		<div class="row">
			<div class="span8">
			<div class="post">
			    <h2 class="post-title">
			        <a href="">${question.title}</a>
			    </h2>
			    <div class="post-metadata">
			        <span class="post-author">${question.writer}</span>,
			        <span class="post-date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}" /></span>
			    </div>
			    <div class="post-content">
			        ${question.contents}
			    </div>
			</div>
			
			<br /> 
		  	<a class="btn" href="">수정</a>&nbsp;&nbsp;
		  	<a class="btn" href="">삭제</a>&nbsp;&nbsp;
		  	<a class="btn" href="/qna/list.next">목록으로</a>
		  	<br/>
			<br/>
			<div class="answerWrite">
				<form name="answer" method="post">
					<input type="hidden" name="questionId" value="${question.questionId}">
					<table>
						<tr>
							<td class="span1">이름</td>
							<td><input type="text" name="writer" id="writer" class="span3"/></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="contents" id="content" class="span5" rows="5"></textarea></td>
						</tr>
					</table>
					<input type="submit" class="btn btn-primary" value="저장" />
				</form>
			</div>
			
		    <!-- answers start -->
		    <h3>
		        댓글 수 : ${question.countOfComment}
		    </h3>		    
			<div class="answers">
			    <div class="answer">
			    	<b>자바지기</b><p>Thread safe 랑 final은 관계가 있는거지만 다르게 봐야 하는게 아닌가?</p>
			    	<a class="answerDelete" href="/api/qna/deleteanswer.next?answerId=1">삭제</a>
			    </div>
			    <div class="answer">
			    	<b>강우</b><p>저도 잘은 모르겠지만, 그냥 몇글자 적어볼께요.
일단 변수의 생명 주기랑, 값이 아닌 레퍼런스에 의한 부수효과는 무시하고,
쓰레드 관점에서만 볼때에,
간단히 생각하면, 서블릿에서 인스턴스 변수를 사용하는 것은 쓰레드에 안전할까요? 안전하지 않을까요?</p>
			    	<a class="answerDelete" href="/api/qna/deleteanswer.next?answerId=2">삭제</a>
			    </div>			    
			</div>
		</div>
	</div>
<%@ include file="/include/footer.jspf" %>	
</body>
</html>