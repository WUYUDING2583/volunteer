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
    			'pk.No':No
    		},
    		success:function(data){
    			
    			var detail=eval("("+data+")");
    			var $str="";
    			var name=detail[0].Name;
    			for(var i=0;i<detail.length;i++){
    				for(var j=0;j<detail[i].manhours.length;j++){
    					$str+="<tr><td>"+name+"</td><td>"+detail[i].manhours[j].pk.Aname+"</td><td>"+detail[i].manhours[j].pk.Adate+"</td><td>"+detail[i].manhours[j].Avtime+"</td></tr>"
    	    			
    				}
    				}
    			$("tbody").html($str);
    		}
		});
	});
});