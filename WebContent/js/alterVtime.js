/**
 * 
 */
$(document).ready(function(){
	$("#vtimeDetail .modal-body").on("click",".delete",function(){
		var title=$(this).parents(".modal").find(".modal-title").text().split(" ");
		var Aname=title[0];
		var Adate=title[1];
		var No=$(this).parents("tr").find(".No").text();
		var $this=$(this);
		$.ajax({
    		url:"../vtimeDeleteServlet",
    		type:"post",
    		cache:false,
    		data:{
    			'Aname':Aname,
    			'Adate':Adate,
    			'No':No
    		},
    		success:function(data){
    			if(data!="0"){
    				$($this).parents("tr").remove();
    			}
    			else{
    				alert("系统错误，请向管理员反应");
    			}
    		}
		});
	});
		
	
	
	$("#vtimeDetail .modal-body").on("click",".alterDetail",function(){
		var $btn="<div class='input-group'>"+
                   " <input type='text' class='form-control'>"+
                    "<span class='input-group-btn'>"+
                   "     <button class='btn btn-default alterSub' type='button'>提交</button>"+
                   " </span>"+
               " </div><!-- /input-group -->";
		$(this).parents("tr").find(".vtime").html($btn);
		$(".alterSub").on("click",function(){
			var vtime=$(this).parent().prev().val();
			var title=$(this).parents("tr").attr("id").split("/");
			var $td=$(this);
			var Aname=title[0];
			var Adate=title[1];
			var No=title[2];
			$.ajax({
	    		url:"../alterVtimeServlet",
	    		type:"post",
	    		cache:false,
	    		data:{
	    			'Aname':Aname,
	    			'Adate':Adate,
	    			'No':No,
	    			'vtime':vtime
	    		},
	    		success:function(data){
	    			if(data=="ok"){
	    				$($td).parents("td").html(vtime);
	    			}
	    		}
			});
		});
	});
	
	$("tbody").on("click",".alter",function(){
		var Aname=$(this).parents("tr").children(".Aname").text();
		var Adate=$(this).parents("tr").children(".Adate").text();
		$("#vtimeDetail .modal-title").text(Aname+" "+Adate);
		No=$(this).attr("id");
		$.ajax({
    		url:"../vtimeDetailServlet",
    		type:"post",
    		cache:false,
    		data:{
    			'Aname':Aname,
    			'Adate':Adate
    		},
    		success:function(data){
    			var detail=eval("("+data+")");
    			var $str="<table class='table table-bordered table-hover'>"+
							"<thead>"+
							"<tr>"+
								"<th>姓名</th>"+
								"<th>学号</th>"+
								"<th>工时</th>"+
								"<th>操作</th></tr></thead><tbody>";
    			for(var i=0;i<detail.length;i++){
    				$str+="<tr id='"+Aname+"/"+Adate+"/"+detail[i].No+"'><td class='Name'>"+detail[i].Name+"</td><td class='No'>"+detail[i].No+"</td><td class='vtime'>"+detail[i].vtime+"</td>"+
    						"<td><a class='tooltip-test' data-toggle='tooltip' title='修改'>"+
	      		"<span class='glyphicon glyphicon-pencil alterDetail'></span>"+
	      	"</a>"+
	      	"<a class='tooltip-test' data-toggle='tooltip' title='删除'>"+
	      	"	<span class='glyphicon glyphicon-trash delete'></span>"+
	      	"</a></td></tr>";
    			}
    			$str+="</tbody></table>";
    			$("#vtimeList").html($str);
    			$("#vtimeDetail").modal({backdrop: 'static', keyboard: false});
    		}
		});
	});
	
	$(".modal-body").on("click","#addVtime",function(){
		var title=$(".modal-title").text().split(" ");
		var Aname=title[0];
		var Adate=title[1];
		var No=$("#No").val();
		var vtime=$("#vtime").val();
		$.ajax({
    		url:"../addVtimeServlet",
    		type:"post",
    		cache:false,
    		data:{
    			'No':No,
    			'Avtime':vtime,
    			'Aname':Aname,
    			'Adate':Adate
    		},
    		success:function(data){
    			if(data!="no"){
    				var $str="<tr id='"+Aname+"/"+Adate+"/"+No+"'><td class='Name'>"+data+"</td><td class='No'>"+No+"</td><td class='vtime'>"+vtime+"</td>"+
					"<td><a class='tooltip-test' data-toggle='tooltip' title='修改'>"+
		      		"<span class='glyphicon glyphicon-pencil alterDetail'></span>"+
		      	"</a>"+
		      	"<a class='tooltip-test' data-toggle='tooltip' title='删除'>"+
		      	"	<span class='glyphicon glyphicon-trash delete'></span>"+
		      	"</a></td></tr>";
    				$($str).prependTo("#vtimeList tbody");
    				$("#No").val("");
    				$("#vtime").val("");
    			}
    			else{
    				alert("添加失败，请向管理员反应");
    			}
    		}
		});
	});
});
	