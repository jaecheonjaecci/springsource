/**
 *  list.jsp 스크립트
 */

$(function() {
	checkModal(result);

	//뒤로가기, 앞으로 가기를 했을 때 히스토리에 저장되어 있는 모달창을 제거하기
	//history.replaceState() : 기존의 히스토리를 변경하는 메소드
	//데이터를 null값으로 넘어가도록 지정
	history.replaceState({}, null, null);

	function checkModal(result) {
		//데이터가 null값이면 모달창을 띄우기 않고
		//바로 return하기
		if (result == '' || history.state) {
			return;
		}
		if (parseInt(result) > 0) {
			$(".modal-body").html("게시글" + parseInt(result) + " 번이 등록되었습니다.");
		}
		//모달창 띄우는 방법
		$("#myModal").modal("show");
	}

	//페이지 나누기 스크립트
	let actionForm = $("#actionForm");

	//번호를 클릭 시 클릭된 번호 가져와서 
	$(".paginate_button a").click(function(e) {

		//a 태그 속성 막기
		e.preventDefault();

		//클릭된 번호값을 의미
		let pageNum = $(this).attr("href");

		//actionForm안에 pageNum값으로 대체
		actionForm.find("input[name='pageNum']").val(pageNum);

		//actionForm 경로 설정
		actionForm.attr("action", "/board/list");

		//bno 제거
		actionForm.find("input[name='bno']").remove();

		//폼 전송
		actionForm.submit();

	})

	//amount의 값이 변화가 있으면 
	$("#amount").change(function() {

		//amount 값 가져오기
		let amount = $(this).val();

		//가져온 값을 actionForm에 amount값에 넣어 수정하기
		actionForm.find("input[name='amount']").val(amount);
		
		//bno 제거
		actionForm.find("input[name='bno']").remove();

		//actionForm 보내기
		actionForm.submit();

	})

	//글 제목 클릭 시 
	$(".move").click(function(e) {

		//a 태그 속성 중지
		e.preventDefault();

		let bno = $(this).attr('href');

		//actionForm에 bno 값을 추가
		actionForm.find("input[name='bno']").val(bno);

		//actionForm action 설정
		actionForm.attr("action", "/board/read");

		//actionForm 보내기
		actionForm.submit();

	})

	//검색버튼 클릭시
	$(".btn-default").click(function(e) {

		e.preventDefault();

		//type의 값이 들어있는지 확인
		//alert($("select[name='type']").val());
		//alert($("input[name='keyword']").val());

		let type = $("select[name='type']").val();
		let keyword = $("input[name='keyword']").val();

		//값이 없는 경우 메세지 띄우고 돌아가기
		if (type == '') {
			alert('검색기준을 입력해주세요.');
			return;
			//키워드의 값이 없는 경우 메시지 띄우고 돌아가기	
		} else if (keyword == '') {
			alert('검색어를 입력해주세요.');
			return;
		}
		
		//검색 후 검색한 데이터는 1페이지부터 보여주기 위해서
		//pageNum을 1로 변경하기
		$("#searchForm").find("input[name='pageNum']").val("1");

		//검색 폼을 보내기
		$("#searchForm").submit();


	})


})















































