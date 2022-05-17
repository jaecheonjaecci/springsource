/**
 * reply와 관련된 스크립트
 */

//자바 스크립트 모듈화하기
//다양한 스크립트를 그룹화하는 방식
let replyService = (function() {

	function add(reply, callback) {
		console.log("add method 실행");

		$.ajax({
			url: '/replies/new',
			type: 'post',
			contentType: 'application/json',
			data: JSON.stringify(reply),
			success: function(result) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err) {
				error(err);
			}
		})
	}//add and

	//댓글 목록 가져오기
	function getList(param, callback) {

		let bno = param.bno;

		//페이지가 안들어올 경우 1로 넣어주기
		let page = param.page || 1;

		$.getJSON({
			url: '/replies/pages/' + bno + '/' + page,
			success: function(data) {
				callback(data.replyCnt,data.list);

			}
		})
	}//getList end

	//댓글 삭제
	function remove(rno, callback, error) {
		$.ajax({
			url: '/replies/' + rno,
			type: 'delete',
			success: function(result) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err) {
				if (error) {
					error(xhr.responseText);
				}
			}
		})

	}//remove end

	//댓글 수정
	function update(reply, callback, error) {

		$.ajax({
			url: '/replies/' + reply.rno,
			type: 'put',
			contentType: 'application/json',
			data: JSON.stringify(reply),
			success: function(data) {
				if (callback) {
					callback(data);
				}
			},
			error: function(xhr, status, err) {
				if (error) {
					error(xhr.responseText);
				}
			}

		})
	} //update end

	function get(rno, callback) {

		$.getJSON({
			url: '/replies/' + rno,
			success: function(data) {
				if (callback) {
					callback(data);
				}
			}
		})

	}//get end


	function displayTime(timeValue) {
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);

		var str = "";

		//댓글을 작성한 날짜가 24시간보다 작다면 댓글 작성일을 시분초로 보여주기
		if (gap < (1000 * 60 * 60 * 24)) { 
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			//숫자가 9보다 크다면 숫자앞에 0을 붙여라
			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
		//댓글을 작성한 날짜가 24시간보다 크다면 년월일로 보여주기
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; //월은 0부터 시작하기 때문에 1을 더해줌
			var dd = dateObj.getDate();
			
			// 2021/07/12
			return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');

		}

	}//displayTime end


	return {
		add: add,
		getList: getList,
		remove: remove,
		update: update,
		get: get,
		displayTime: displayTime

	};

})();






























