/**
 * insert.jsp 스크립트
 */
$(function(){
	//submit 클릭을 하면 form data 가져온 후
	$(":submit").click(function(e){
		e.preventDefault();
		
		let param={
			  userid:$("#userid").val(),
			  password:$("#password").val(),
			  name:$("#name").val(),
			  gender:$("#gender").val(),
			  email:$("#email").val()
		};
		//ajax 전송
		$.ajax({
			url:'insert',
			type:'post',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
		
		
	}) 
})