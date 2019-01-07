/**
 * 
 */
$(document).ready(function(){
	$("button").on("click",function(){
		var No=$("#No").val();
		if(No==""){
			alert("请输入学号");
			return;
		}
		$.ajax({
    		url:"/volunteer/vtimeSearch",
    		type:"post",
    		cache:false,
    		data:{
    			'pk.No':No
    		},
    		success:function(data){
    			if(data=="请输入学号"){
    				alert(data);
    			}
    			else if(data=="500"){
    				alert("该学号暂无记录或未在平台注册");
    			}
    			else{
    				var detail=eval("("+data+")");
        			var $str="";
        			var name=detail[0].Name;
        			for(var i=0;i<detail.length;i++){
        				for(var j=0;j<detail[i].manhours.length;j++){
        					$str+="<tr><td>"+name+"</td><td>"+detail[i].manhours[j].pk.Aname+"</td><td>"+detail[i].manhours[j].pk.Adate.split(" ")[0]+"</td><td>"+detail[i].manhours[j].Avtime+"</td></tr>"
        	    			
        				}
        				}
        			$("tbody").html($str);
    			}
    			
    		}
		});
	});
});