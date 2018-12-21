/**
 * 
 */
$(document).ready(function(){
	
	var No="";
	var name="";
	var dat="";
	$(".sub").on("click",function(){
		$("#vtimeSubmit .modal-title").text($(this).parents("tr").children(".Aname").text()+" "+$(this).parents("tr").children(".Adate").text())
		No=$(this).attr("id");
		name=$(this).parents("tr").find(".Aname").text();
		dat=$(this).parents("tr").find(".Adate").text();
		$("#vtimeSubmit").modal({backdrop: 'static', keyboard: false});
	});
	
	$("#submit").on("click",function(){
		var formData = new FormData($("#form")[0]);
        var str=$("#upload").val();
        var file= getFileName(str);
        var fileName=file.substring(0,file.lastIndexOf('.'));
        var nameList=new Array();
        nameList=fileName.split(" ");
        var Aname=nameList[2];
        var date=nameList[1];
        var Adate=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
        if(Aname==name&&dat==Adate){
            $.ajax({
	      		url:"/Volunteer/uploadVtime",
	      		type:"post",
	    		data:formData,
	    		contentType: false,
	            processData: false,
	            dataType:"text",
	      		success:function(data){
	      			if(data!="0"){
	      				alert(data);
	      				$("#vtimeSubmit").modal("hide"); 
	      				$("#"+No).text("修改工时");
	      				$("#"+No).removeAttr("class");
	      				$("#"+No).off("click");
	      				$("#"+No).parents("tr").remove();
	      				$.ajax({
	      		    		url:"/Volunteer/setState",
	      		    		type:"post",
	      		    		cache:false,
	      		    		data:{
	      		    			'info.Ano':No
	      		    		},
	      		    		success:function(data){
	      		    			
	      		    		}
	      				});
	      			}
	      			else{
	      				alert("提交失败");
	      			}
	      			
	      		}
	  		});
        }
        else{
        	alert("报名表提交错误或文件格式错误");
        }
	});
	
	//获取文件名称
	function getFileName(path) {
	    var pos1 = path.lastIndexOf('/');
	    var pos2 = path.lastIndexOf('\\');
	    var pos = Math.max(pos1, pos2);
	    if (pos < 0) {
	        return path;
	    }
	    else {
	        return path.substring(pos + 1);
	    }
	}
});