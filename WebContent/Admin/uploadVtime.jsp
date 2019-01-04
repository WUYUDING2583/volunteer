<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
	 <link rel="stylesheet" href="/volunteer/bootstrap-fileinput/bootstrap-fileinput.css">
	 <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=cc17678642f59e975f39bb06fcb83665&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.Geolocation"></script>
    
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/volunteer/bootstrap-fileinput/bootstrap-fileinput.js"></script>
	<style>
		body{
			padding-top:50px;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
	<table class="table table-bordered table-hover">
	  <thead>
	    <tr>
	      <th>活动名称</th>
	      <th>发布人</th>
	      <th>活动时间</th>
	      <th>状态</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
	   <s:iterator value="infoList" var="info">
				<tr>
	      <input type="hidden" class="Ano" value="<s:property value="#info.Ano" />">
	      <input type="hidden" class="state" value="<s:property value="#info.state" /> %>">
	      
	      <td class="Aname"><s:property value="#info.Aname" /></td>
	      <td class="Pname"><s:property value="#info.publishName" /></td>
	      <td class="Adate"><s:property value="#info.Adate" /></td>
	      <td class="Astate"><s:property value="#info.state" /></td>
	      <td>
	      	<button class="btn btn-success sub" id="<s:property value="#info.Ano" />">提交工时表</button> 
	      </td>
		</tr>
		</s:iterator>
	  
	  </tbody>
	</table>
	</div>
	<div class="modal fade" id="vtimeSubmit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body text-center">
            	<div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv">
                	<div class="text-center">
                    	<div class="fileinput-preview fileinput-exists thumbnail text-center" style="max-width: 200px; max-height: 150px;"></div>
                    </div>
                    <div>
                    <form id="form">
                        <span class="btn default btn-file">
                        <button class="btn btn-success fileinput-new">选择文件</button>
                        <button class="btn btn-info fileinput-exists">更改</button>
                        <input type="file"name="file" id="upload" /></span>
                        <button class="btn btn-default fileinput-exists" data-dismiss="fileinput">移除</button>
                    </form>
                    </div>
                </div>
			</div>
			<div class="modal-footer">
				<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-4 text-center">
                	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
				<div class="col-sm-4 text-center">
                	<button type="button" class="btn btn-primary" id="submit">提交</button>
                </div>
				<div class="col-sm-2"></div>
				</div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div id="111"></div>
<script src="/volunteer/js/uploadVtime.js"></script>
<%session.removeAttribute("infoList"); %>
</body>
</html>