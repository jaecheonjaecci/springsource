/**
 * register.jsp
 */

$(function() {
	//submit 버튼 클릭 시 첨부파일 정보 추가하기
	$(":submit").click(function(e){
		e.preventDefault();
		
		//첨부된 파일 정보 수집하기
		var str = "";
		$(".uploadResult ul li").each(function(i,obj){ 
			var ele = $(obj);
			
			//uuid,filename,path,filetype의 정보를 수집해 hidden tag에 담음
			str +="<input type='hidden' name='attachList["+ i +"].uuid' value='"+ ele.data('uuid')+"'>";
			str +="<input type='hidden' name='attachList["+ i +"].uploadPath' value='"+ ele.data('path')+"'>";
			str +="<input type='hidden' name='attachList["+ i +"].fileName' value='"+ ele.data('filename')+"'>";
			str +="<input type='hidden' name='attachList["+ i +"].fileType' value='"+ ele.data('type')+"'>";
		})
		
		console.log("form");
		console.log(str);
		
		//게시글 등록 폼에 추가하기
		$("form[role='form']").append(str).submit();
		
	})

	
	$("input[type='file']").change(function(e) {
		e.preventDefault();

		console.log("업로드 요청");

		//FormData 객체 생성 - ajax 형태로 데이터를 보낼 때
		//key/value 형태로 쌍을 생성해줌
		var formData = new FormData();

		//첨부파일 목록 가져오기
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;

		//가져온 데이터를 formData에 추가하기
		for (var i = 0; i < files.length; i++) {
			//추가하기 전에 파일 확장자와 사이즈 확인하기
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			formData.append("uploadFile", files[i]);
		}

		//	processData:false => 데이터를 query string(주소줄로 따라가는 방식) 형태로 변환 여부
		//	contentType:false => (application/x-www...) 기본형으로 보낼 것인지 여부

		$.ajax({
			url: '/uploadAjax',
			type: 'post',
			processData: false,
			contentType: false,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data: formData,
			success: function(result) {
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val("");

			},
			error: function(xhr, status, error) {
				console.log(xhr.responseText);
			}

		})
	})//uploadBtn end


	//첨부된 파일 목록 보여주기
	function showUploadedFile(uploadResultArr) {

		//첨부파일 결과를 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");  

		var str = "";
		$(uploadResultArr).each(function(idx, obj) {
			//이미지 파일인 경우
			if(obj.fileType){
				//썸네일 이미지 경로 생성
									//encodeURI() : 영어,숫자, 특수문자는 인코딩을 하지 않음(공백은 인코딩을 함)
									//encodeURIComponent() : 영어,숫자와 일부 특수문자는 인코딩을 하지 않음
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				
				//원본 이미지 경로 생성
				var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g),"/");
				
	            str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+ obj.uuid +"'";
				str += " data-filename='"+ obj.fileName +"' data-type='"+ obj.fileType +"'>";
	            str += "<a href=\"javascript:showImage(\'"+originPath+"\')\">";

								//display는 서버폴더가 아니라, controller임
	            str += "<img src='/display?fileName="+ fileCallPath + "'>";
	            str += "<div>"+obj.fileName+"</a> ";
	            str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+ fileCallPath +"' data-type='image'><i class='fa fa-times'></i></button>";
	            str += "</div></li>";

				
			}else{
				//다운로드 경로 설정
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
					
	            str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+ obj.uuid +"'";
				str += " data-filename='"+ obj.fileName +"' data-type='"+ obj.fileType +"'>";
	            str += "<a href='/download?fileName="+fileCallPath+"'>";
	            str += "<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a> ";
	            str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+ fileCallPath +"' data-type='file'><i class='fa fa-times'></i></button>";
	            str += "</div></li>";

				
			}
		})
		console.log(str);
		
		uploadResult.append(str);

	}

	//업로드 할 수 있는 파일의 종류 제한하기
	function checkExtension(fileName, fileSize) {
		//적혀있는 확장자만 허용
		var regex = new RegExp("(.*?)\.(txt|jpg|gif|png)$")
		var maxSize = 2097152; //약 2MB

		if (fileSize > maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}
		//기재해놓은 확장자가 아닌 경우 메시지 띄우기
		if (!regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}//check Extension end
	
	//원본파일 이미지를 클릭하면 다시 섬네일만 보여주기
	$(".bigPictureWrapper").on("click",function(){
		$(".bigPicture").animate({
								width:'0%',
								height:'0%'
							}, 1000);
			setTimeout(function(){
				$(".bigPictureWrapper").hide();
			},1000);
	})

	//첨부파일 목록에서 X 누르면 목록에서 지우기
	//현재 있는 uploadResult에 이벤트를 걸고, 나중에 생기는 span에 이벤트를 넘김
	$(".uploadResult").on("click","button",function(){
		
		//삭제할 파일 가져오기
		let targetFile = $(this).data("file");
	
		//삭제할 파일 타입 가져오기 : 이미지인 경우 섬네일도 삭제가 필요하기 때문
		let type = $(this).data("type");
	
		//x버튼의 부모 li 가져오기
		var targetLi = $(this).closest("li");
			
		$.ajax({
			url:'/deleteFile',
			type:'post',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:{
				fileName:targetFile,
				type:type
			},
			success:function(data){
				//목록에서 지우기
				targetLi.remove();
				
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
		
	})



})


function showImage(fileCallPath){
	console.log(fileCallPath);
	
	//안보였던 영역 보이기
	$(".bigPictureWrapper").css("display","flex").show();
	
	//이미지를 클릭하면 원본 파일 보여주기
	$(".bigPicture").html("<img src='/display?fileName="+ fileCallPath +"'>")
					.animate({
						width:'100%',
						height:'100%'
					}, 1000);

}				


































