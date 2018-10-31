<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
		body{
			padding-top:50px;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
		<form class="form-inline" role="form">
		  <div class="form-group col-sm-5">
		    <label  for="No" style="margin-bottom:10px;">学号</label>
		    <input type="text" class="form-control" id="No" placeholder="请输入学号" style="margin-bottom:10px;">
		  </div>
		</form>
		<button class="btn btn-info col-sm-1" style="margin-bottom:10px;">查询</button> 
		<table class="table table-bordered table-hover">
		  <thead>
		    <tr>
		      <th>姓名</th>
		      <th>活动</th>
		      <th>时间</th>
		      <th>工时</th>
		    </tr>
		  </thead>
		  <tbody></tbody>
	</div> 
	<script src="../js/vtimeSearch.js"></script>
</body>
</html>