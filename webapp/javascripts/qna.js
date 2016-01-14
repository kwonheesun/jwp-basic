$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
	    type : 'post',
	    url : '/api/qna/addanswer.next',
	    data : queryString,
	    dataType : 'json',
	    error: onError,
	    success : onSuccess
	});
}

function onSuccess(data, status){
	var answer = data.answer;
	var answerEle = "<div class='answer'><b>" + answer.writer + "</b><p>" + answer.contents + "</p>" + 
		"<a href='/api/qna/deleteanswer.next?answerId=" + answer.answerId + "'>삭제</a></div>";
    $(".answers").prepend(answerEle);
}

function onError(data, status) {
	alert("error");
}