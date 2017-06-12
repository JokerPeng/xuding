<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	url += context;
	Object isExsitCtx=application.getAttribute("ctx");	
	if(isExsitCtx==null||!isExsitCtx.toString().startsWith("http")){
		application.setAttribute("ctx", url);
	}
%>
<!DOCTYPE>
<html>
  <head>
    <title>旭鼎</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery1.9.1.min.js"></script>
    <!-- 文件上传插件js -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.form.js"></script>
	<style type="text/css">
	.div_row{
		width:300px;
		margin: 15px 0px;
	}
	.div1{
		position: absolute;
		left: 50%;
		top: 30%;
		width:300px;
		height: 100px;
		margin-left:-150px;
		margin-top:-50px;
	}
	.input_right{
		width:215px;
		float:right;
	}
	.btn{
		border: 1px;
    	border-radius: 5px;
	}
	</style>
  </head>
  
  <body>
  	<form id="ff" enctype="multipart/form-data" method="post">
  	<div class="div1">
	    <div class="div_row">
	    	<label style="width:100px;">标题：</label>
	    	<input id="title" name="title" type="text" class="input_right">
	    </div>
    	<div class="div_row">
	    	<label style="width:100px;">选择文件：</label>
	    	<input id="fileId" name="file" type="file" accept="video/mp4" class="input_right">
	    </div>
    	<div style="text-align:center;">
	    	<a href="javascript:void(0);" class="btn" onclick="upload()" ondblclick="returnFalse()">保存</a>&nbsp;&nbsp;
	    	<a href="javascript:void(0);" class="btn" onclick="windowClose()">取消</a>
	    	<%--<button class="button" onclick="upload()" ondblclick="returnFalse()">保存</button>&nbsp;&nbsp;
	    	<button class="button" onclick="close()">取消</button>--%>
	    </div>
    </div>
    </form>
  </body>
<script type="text/javascript">
	
	function upload() {
		if ($("#fileId").val() == "") {
			alert("请选择文件！");
			return;
		}
		//var formParam = $("#ff").serialize();
		$("#ff").ajaxSubmit({
			type : 'post',
			url : "${ctx }/movieController/addMovie.do",
			dataType : 'json',
			success : function(data) {
				alert(data.msg);
				if(data.result){
					close();
				}
			}
		});

	}
	
	function returnFalse(){
		return false;
	}
	
	function windowClose(){
		window.close();
	}
	
</script>
</html>
