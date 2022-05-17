/**
 * list.jsp 스크립트
 */
$(function() {
	//전체 책 정보 가져오기, 조건없이 무조건 실행됨
	$.getJSON({
		url: '/book/rest-list',
		success: function(data) {
			console.log(data);

			let str = "";

			$.each(data, function(idx, element) {
				str += "<tr>";
				str += "<td>" + element.code + "</td>";
				str += "<td><a href='" + element.code + "' class='move'>" + element.title + "</a></td>";
				str += "<td>" + element.writer + "</td><td>" + element.price + "</td>";
				str += "</tr>";
			})
			//tbody안에 위에 만들어놓은 자료들을 넣기
			$("tbody").html(str);
		}

	})


	//이벤트 위임 - 책 하나의 정보 가져오기
	//tbody에 걸린 이벤트를 .move에게 넘겨주겠다는 의미임
	$("tbody").on("click", ".move", function(e) {

		//a 태그 속성 중지
		e.preventDefault();

		//제목을 클릭하면 href 값 가져오기
		let code = $(this).attr("href");

		//Ajax
		//Json 방식으로 넘어온다면 getJSON()으로 받으면 됨
		$.getJSON({
			url: code,
			success: function(data) {
				//console.log(data);


				//책 타이틀을 클릭하면 책의 대한 내용 테이블 밑에 보여주기
				let str = '<ul>';

				str += "<li>code :" + data.code + "</li>";
				str += "<li>title :" + data.title + "</li>";
				str += "<li>writer :" + data.writer + "</li>";
				str += "<li>price :" + data.price + "</li>";
				str += "</ul>";

				$("#result").html(str);
			}

		})

	})

	//도서 삭제
	$("#delete").click(function() {
		$.ajax({
			url: "1049",
			type: 'delete',
			success: function(data) {
				alert(data);
			},
			error: function(xhr, xhrStatus, error) {
				alert(xhr.responseText);
			}
		})
	})

	//도서 수정
	$("#update").click(function() {

		//보낼 데이터 자바스크립트 객체
		let param = {
			code: '1006',
			price: 40000
		};


		$.ajax({
			url: 'update',
			type: 'put',
			contentType: 'application/json',
			data: JSON.stringify(param),
			success: function(data) {
				alert(data);
			},
			error: function(xhr, xhrStatus, error) {
				alert(xhr.responseText);
			}

		})
	})



})























