/**

 * 
 */

var height=$(window).height();
var width=$(window).width();
$(document).ready(function(){
	$("tbody").on("click",".look",function(){
		$.ajax({
    		url:"/volunteer/showActivity",
    		type:"post",
    		cache:false,
    		data:{
    			'info.Ano':$(this).parents("tr").children("input").val()
    		},
    		success:function(data){
    			var jsonArr=new Array();
    			//$("html").html(data);
    			var info=eval("("+data+")");
    			$(".modal-title").text(info.Aname);
    			var $str="<table class='table table-bordered table-hover'>"+
    						"<tbody>"+
    							"<input type='hidden' id='hidAno' value='"+info.Ano+"'>"+
    							"<input type='hidden' id='hidAdate' value='"+info.Adate+"'>"+
    							"<input type='hidden' id='hidAname' value='"+info.Aname+"'>"+
    							"<tr>"+
    								"<td>活动时间</td>"+
    								"<td>"+info.Adate+"</td>"+
    							"</tr><tr>"+
    								"<td>活动地址</td>"+
    								"<td>"+info.Address+"</td>"+
        						"</tr><tr>";
    			var req=info.ActReq;
    			var prevTime="";
    			for(var i=0;i<req.length;i++){
    				if(req[i].Atime!=prevTime){
        				$str+="<tr><td>时间段</td><td>"+req[i].Atime+"</td></tr>";
        				prevTime=req[i].Atime;
    				}
    				if(req[i].Ajobstate=="无"){
    					$str+="<tr><td>需求:"+req[i].Ajobcount+"</td><td>已招:"+req[i].DoneAccount+"</td></tr>";
    				}
    				else{
    					$str+="<tr><td rowspan='2'>"+req[i].Ajobstate+"</td><td>需求:"+req[i].Ajobcount+"</td></tr><tr><td>已招:"+req[i].DoneAccount+"</td></tr>";
    	    		}
    			}
    			$str+="<tr  rowspan='4'><td>活动要求</td>"+
    				"<td style='overflow:auto'>"+info.Arequest.replace(/\n/g,"<br>")+"</td></tr>"+
    				"<tr><td>发布人</td>"+
    				"<td>"+info.publishName+"</td></tr>"+
    				"<tr><td>发布时间</td>"+
    				"<td>"+info.publishTime+"</td></tr>"+
    				"<tr><td>所属学院</td>"+
    				"<td>"+info.college+"</td></tr>"+
    				"</tbody>"+
    				" </table>";	
    			$(".modal-body").html($str);
    			$("#lookActivity").modal({backdrop: 'static', keyboard: false});
    		}
    	});
		
	});
	
	$("tbody").on("click",".delete",function(){
		var $tr=$(this).parents("tr");
		$.ajax({
    		url:"/volunteer/deleteActivity",
    		type:"post",
    		cache:false,
    		data:{
    			'info.Ano':$(this).parents("tr").children("input").val()
    		},
    		success:function(data){
    			if(data=="0"){
    				alert("删除失败");
    			}
    			else{
    				$tr.remove();
    			}
    		}
		});
	});
	
	$("#lookActivity").on("click","#export",function(){
		var $str="<iframe src='/volunteer/excelJobState?info.Ano="+$(this).parents(".modal-content").find("#hidAno").val()+"&fileName="+$(this).parents(".modal-content").find("#hidAname").val()+"_"+$(this).parents(".modal-content").find("#hidAdate").val()+"' style='display:none;'></iframe>";
		$(".modal-body").append($str);
	});

});