/**
 * 
 */

var map, geolocation;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    
    var auto = new AMap.Autocomplete();
    var placeSearch=new AMap.PlaceSearch({map:map});
    
    var city="全国";
    map.getCity(function(e){
    	city=e.city;
    	auto.setCity(city);
    	placeSearch.setCity(city);
    });
    
$(document).ready(function(){
	var job=0;
	
	$(".Atime").hover(function(){
		$(this).find(".button").show();
	},function(){
		$(this).find(".button").hide();
	});
	$(".Aneed").hover(function(){
		$(this).find(".button").show();
	},function(){
		$(this).find(".button").hide();
	});
	//点击添加时间段
	$("#addTime").on("click",function(){
		var $str="<div class='form-group Atime'>"+
		    		"<label  class='col-sm-2 control-label'>活动时间段</label>"+
		    		"<div class='col-sm-3'>"+
		      			"<input type='time' class='form-control Aintime' placeholder='请输入活动时间'>"+
		    		"</div>"+
		    		"<div class='col-sm-1  text-center'>"+
		    		"	<label class='control-label'>至</label>"+
		    		"</div>"+
		    		"<div class='col-sm-3'>"+
		      		"	<input type='time' class='form-control Aouttime' placeholder='请输入活动时间'>"+
		    		"</div>"+
		    		"<div class='col-sm-2 text-center button'>"+
	    			"<button type='button' class='btn btn-primary btn-circle minusTime pull-left'>"+
	    			"	<span class='glyphicon glyphicon-minus'></span>"+
	    			"</button>"+
	    			"<label class='control-label pull-left'>&nbsp;删除时间段</label>"+
	    			"</div>"+
	    		  "</div>"+
	    		  "<div class='form-group Aneed'>"+
		    		"<label class='col-sm-2 control-label'>需求人数</label>"+
		    		"<div class='col-sm-7'>"+
		      		"	<input type='text' class='form-control Apcount' placeholder='请输入需求人数'>"+
		    		"</div>"+
		    		"<div class='col-sm-2 text-center button'>"+
		    		"	<button type='button' class='btn btn-primary btn-circle pull-left addJob'>"+
					"		<span class='glyphicon glyphicon-plus'></span>"+
					"	</button>"+
					"	<label class='control-label pull-left'>&nbsp;增加岗位</label>"+
		    		"</div>"+
			    	"</div>"+
		    		"<div class='addJobGroup'>"+
		    		"</div>";
		$(this).parents(".requestGroup").append($str);
		$(".Atime").hover(function(){
			$(this).find(".button").show();
		},function(){
			$(this).find(".button").hide();
		});
		$(".Aneed").hover(function(){
			$(this).find(".button").show();
		},function(){
			$(this).find(".button").hide();
		});
	
	});
	
	//点击删除时间段
	$(".requestGroup").on("click",".minusTime",function(){
		$(this).parents(".Atime").next().remove();
		$(this).parents(".Atime").next(".addJobGroup").remove();
		$(this).parents(".Atime").remove();
	});
	
	//点击删除岗位
	$(".requestGroup").on("click",".minusJob",function(){
		//alert($(this).parents(".addJobGroup").prev().find(".Apcount").prop("disabled"));
		var length=$(this).parents(".addJobGroup").children(".Ajob").length-1;
		if(length==0){
			$(this).parents(".addJobGroup").prev(".Aneed").find("input").prop("disabled",false);
		}
		$(this).parents(".Ajob").remove();
		
	});
		
	$(".requestGroup").on("click",".addJob",function(){
		$(this).parents(".Atime").find(".Apcount").prop("disabled",true);
		var $str="<div class='form-group Ajob'>"+
		    		"<label  class='col-sm-2 control-label'>岗位</label>"+
		    		"<div class='col-sm-3'>"+
		      		"	<input type='text' class='form-control Ajobstate' placeholder='请输入岗位'>"+
		    		"</div>"+
		    		"<label  class='col-sm-1 control-label'>人数</label>"+
		    		"<div class='col-sm-3'>"+
		      		"	<input type='text' class='form-control Ajobcount' placeholder='请输入需求人数'>"+
		    		"</div>"+
		    		"<div class='col-sm-2 text-center minus'>"+
		    		"	<button type='button' class='btn btn-primary btn-circle pull-left minusJob'>"+
					"		<span class='glyphicon glyphicon-minus'></span>"+
					"	</button>"+
					"	<label class='control-label pull-left'>&nbsp;删除岗位</label>"+
		    		"</div>"+
		    	"</div>";
		$(this).parents(".form-group").next().append($str);
		$(this).parents(".Aneed").find("input").prop("disabled",true);
		var length=$(this).parents(".Aneed").next().children(".Ajob").length;
		$(".Ajob").hover(function(){
			$(this).find(".minus").show();
		},function(){
			$(this).find(".minus").hide();
		});
	});
	
	$("#Address").on("input propertychange",function(){
		var key=$(this).val();
		auto.search(key,function(status,result){
			if(status==='complete'){
			      	var count=result.count;
			      	var arr=result.tips;
			      	var str="";
			      	if(key.length>0){
			      		str="<ul id='placeList' class='list-group'>";
					      for(var i=0;i<count;i++){
					      	str=str+"<li class='list-group-item text-center main'><span class='mainName'>"+arr[i].name+
					      	"</span>&nbsp;<span class='text-muted small'>"+arr[i].district+"</span></li>";
					      }
					      str=str+"</ul>";
			      	}
			      
			      var $ul=$(str);
			      var ht=$("#ddd").html();
			     if(ht.length==0)
			      {
			    	  $($ul).appendTo("#ddd");
			      }
			      else
			      {
			    	  $("#placeList").replaceWith($ul);
			      }
			      $("#ddd").show();
			      $(".main").on("click", function() {
		            	 var t=$(this).children(".mainName").text();
		            	 $("#Address").val(t);
		            	//搜索插件
		            	 placeSearch.search(t,function(status,result){
		            		 if(status==='error'){
		            			 alert('暂无该地址信息');
		            		 }
		            		 else if(status==='complete'){
		            			 var arr=result.poiList.pois;
		            			 var position=arr[0].location;
		            			 $("#searchlng").val(position.getLng());
		             			 $("#searchlat").val(position.getLat());
		             			 $("#ddd").hide();
		            		 }
		            	 })
		            	 
		           });
			    }
			    
			  });
			
	});
	
	
	$("#sub").on("click",function(){
		if($("#uploadImage").val()==""){
			alert("请选择图片");
			return;
		}
		var flag=0;
		var Aname=$("#Aname").val();
		var Adate=$("#Adate").val();
		var Address=$("#Address").val();
		if(Aname==""||Adate==""||Address==""){
			flag=1;
		}
		var Aintime=new Array();
		var Aouttime=new Array();
		var Adeadline=$("#AdeadDate").val()+" "+$("#AdeadTime").val()+":00.706";
		$(".Aintime").each(function(key){
			if($(this).val()==""){
				flag=1;
				return false;
			}
			Aintime[key]=$(this).val();
		});
		$(".Aouttime").each(function(key){
			if($(this).val()==""){
				flag=1;
				return false;
			}
			Aouttime[key]=$(this).val();
		})
		var Ajobstate=[];
		var Ajobcount=[];
		$(".Ajobstate").each(function(key){
			if($(this).val()==""){
				flag=1;
				return false;
			}
			Ajobstate[key]=$(this).val();
		});
		
		var isNum=true;
		$(".Ajobcount").each(function(key){
			if($(this).val()==""){
				flag=1;
				return false;
			}
			Ajobcount[key]=$(this).val();
		});
		var Arequest=$("#Arequest").val();
		var lng=$("#searchlng").val();
		var lat=$("#searchlat").val();
		if(Arequest==""){
			flag=1;
		}
		var Apcount=[];
		$(".Apcount").each(function(key){
			if($(this).val()==""){
				flag=1;
				return false;
			}
			Apcount[key]=$(this).val();
		});
		if(flag==1){
			alert("输入框不能为空");
			return;
		}
		var joball=0;
		var formData = new FormData($("#form")[0]);
        $.ajax({
    		url:"/volunteer/uploadImage",  
    		type:"post",
    		data:formData,
    		contentType: false,
            processData: false,
            dataType:"text",
    		success:function(data){ 
    			if(data=="500"){
    				alert("请上传图片而非文件");
    				return;
    			}else if(data=="450"){
    				alert("请上传大小在1M以内的图片");
    				return;
    			}
    			var str="{'activity':[";
    			for(var i=0;i<Aintime.length;i++){
    				str+="{"+
    						"'Aname':'"+Aname+"',"+
    						"'Adate':'"+Adate+"',"+
    						"'Address':'"+Address+"',"+
    						"'Adeadline':'"+Adeadline+"',"+
    						"'picture':'"+data+"',"+
    						"'lng':'"+lng+"',"+
    						"'lat':'"+lat+"',"+
    						"'Atime':'"+Aintime[i]+"-"+Aouttime[i]+"',";
    				var length=$($(".Aneed").get(i)).next().children().length;
    				if(length>0){
    					str+="'Ajob':[";
    					for(var j=0;j<length;j++){
    						str+="{"+
    								"'Ajobstate':'"+Ajobstate[j+joball]+"',"+
    								"'Ajobcount':'"+Ajobcount[j+joball]+"'"+
    							 "}";
    						if(j<length-1){
    							str+=",";
    						}
    					}	
    					str+="],"	
    				}
    				else{
    					//alert(i+","+$($(".Apcount").get(i)).prop("disabled"));
    					str+="'Ajob':[{"+
    						 "'Ajobstate':'无',"+
    						 "'Ajobcount':'"+Apcount[i]+"'}],";
    				}

    				joball+=length;
    				str+="'Arequest':'"+Arequest+"'}";
    				if(i<Aintime.length-1){
    					str+=",";
    				}
    			}
    			str+="]}";
    			$.ajax({
    	    		url:"/volunteer/addActivity",
    	    		type:"post",
    	    		cache:false,
    	    		data:{
    	    			'jsonData':str
    	    		},
    	            dataType:"text",
    	    		success:function(data){
    	    			if(data=="活动添加成功"){
        	    			$("#result").text(data);
        	    			$('#myModal').modal('show');
        	    		    setTimeout(function(){  
        	    		        $("#myModal").modal("hide")
        	    		    },1200);
    	    			}
    	    			else
    	    				alert(data);
    	    		},
    	    		error:function(xhr){
    	    			alert('出错。。\n'+xhr.responseText);
    	    		}
    	    	});
    		},
    		error:function(xhr){
    			alert('出错。。\n'+xhr.responseText);
    		}
    	});
		
  });
	

  $("#clear").on("click",function(){
	  $("input").val("");
	  $("textarea").val("");
  });
	
});