/**
 * 
 */
var windowHeight=$(window).height();
$("#container").height(windowHeight*0.5);
var map, geolocation;
	var position=[120.15436,30.269383];
    //加载地图，调用浏览器定位服务
	
	map = new AMap.Map('container', {
        resizeEnable: true, //是否监控地图容器尺寸变化
        zoom:18, //初始化地图层级
        center: position //初始化地图中心点
    });
	// 创建一个 Marker 实例：
	var marker = new AMap.Marker({
	    position: position,  // 经纬度对象
	    icon:"//vdata.amap.com/icons/b18/1/2.png"
	});
	
	var marker1 = new AMap.Marker({
	    position: [120.15435,30.2693],  // 经纬度对象
	    title:"吴宇丁"
	});


	var marker2 = new AMap.Marker({
	    position: [120.154,30.26938],  // 经纬度对象
		title:"高铁器"
	});
	var markerList=[marker,marker1,marker2];
	map.add(markerList);
	
    var auto = new AMap.Autocomplete();
    var placeSearch=new AMap.PlaceSearch({map:map});
    
    var city="全国";
    map.getCity(function(e){
    	city=e.city;
    	auto.setCity(city);
    	placeSearch.setCity(city);
    });
$(document).ready(function(){
	/*var t=setInterval(function(){
		
	},1000*60*3)*/
});