/**
 * insert.jsp 와 관련된 스크립트 작성
 */

$(function() {

	$(":submit").click(function(e) {
		e.preventDefault();

		//사용자 입력값 가져오기
		// 1. 개별로 처리하기 (자바 스크립트 객체 생성)
		let param = {
			code:$("#code").val(),
			title:$("#title").val(),
			writer:$("#writer").val(),
			price:$("#price").val()
		};


		//Ajax 방식으로 사용자의 입력값 서버로 전송
		//JSON.stringify(param) : 자바스크립트 값이나 객체를 json 문자열로 변환시켜줌
		$.ajax({
			//url: 'insert', //요청주소(일반 컨트롤러로 보낸 것)
			uri:'insert-rest',
			type: 'post', //데이터전송방식
			contentType: "application/json", //보내는 데이터 유형
			data: JSON.stringify(param), //전송데이터
			
			//성공하던 실패하던 우선 여기로 들어옴
			// 서버가 전송하는 응답결과(200) : 200이 넘어오면 자동으로 얘가 호출
			success: function(data) { 
				alert(data);
			},
			error: function(xhr, txtStatus, error) { // 서버가 전송하는 응답결과(500 or 400)
				console.log(xhr.status); //400
				console.log(xhr.responseText); //fail
				console.log(txtStatus); //error
			}
		}) //ajax 종료

	}) //submit 종료












})


