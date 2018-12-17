/**
 * 
 */
$(document).ready(function(){
	$("button").on("click",function(){
		var No=$("#No").val();
		$.ajax({
    		url:"/Volunteer/vtimeSearch",
    		type:"post",
    		cache:false,
    		data:{
    			'manhour.No':No
    		},
    		success:function(data){
    			var detail=eval("("+data+")");
    			var $str="";
    			for(var i=0;i<detail.length;i++){
    				$str+="<tr><td>"+detail[i].Name+"</td><td>"+detail[i].Aname+"</td><td>"+detail[i].Adate+"</td><td>"+detail[i].vtime+"</td></tr>"
    			}
    			$("tbody").html($str);
    		}
		});
	});
});