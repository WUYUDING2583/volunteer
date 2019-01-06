/**
 * 
 */
$("#admin").css("padding-top","20px");
var height=$(window).height();
var iframetop=$("iframe").offset().top;
$("iframe").height(height-iframetop);
$(document).ready(function(){
	$("#addActivity").on("click",function(){
		$("iframe").attr("src","/volunteer/Admin/addActivity.jsp");
	});
	
	$("#myActivity").on("click",function(){
		$("iframe").attr("src","/volunteer/myActivity");
	});
	
	$("#uploadVtime").on("click",function(){
		$("iframe").attr("src","/volunteer/endNotUp");
	});
	
	$("#vtimeSearch").on("click",function(){
		$("iframe").attr("src","/volunteer/Admin/vtimeSearch.jsp");
	});
	$("#alterVtime").on("click",function(){
		$("iframe").attr("src","/volunteer/endAndUp");
	});
	
	$("#change").on("click",function(){
		$("#changePsw").modal({backdrop: 'static', keyboard: false});
	});
	
	$("#sign").on("click",function(){
		//$("iframe").attr("src","../sign");
		alert("该功能暂未开放");
	});
	
	$("#alter").on("click",function(){
		var id=$("#id").val();
		var password=$("#password").val();
		if(id==""||password==""){
			alert("输入框不能为空");
			return;
		}
		$.ajax({
    		url:"/volunteer/alterPsw",
    		type:"post",
    		cache:false,
    		data:{
    			'admin.id':id,
    			'admin.password':password
    		},
    		success:function(data){
    			if(data!=null){
    				if(data=="400"){
    					alert("用户名或密码不能为空");
    				}
    				else if(data=="500"){
    					alert("身份证号错误");
    				}
    				else{
    					alert("密码修改成功");
    					$("#changePsw").modal("hide");
    				}
    			}
    		}
		});
	});
	
	$("#exit").on("click",function(){
		window.location.href="/volunteer/exit";
	});
});