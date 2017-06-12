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
    <style type="text/css">
    	.center{
    		text-align:center;
    	}
    	ul li {list-style-type:none;}
    	.margin_li{
    		margin:5px 0px;
    	}
    </style>
  </head>
  
  <body>
  	<div class="center" style="margin: 40px 0px;">
    	<input id="title" style="height:30px;width:400px;">&nbsp;
	    <a href="javascript:void(0);" onclick="queryMovie()">查询</a>&nbsp;&nbsp;
	    <a href="${pageContext.request.contextPath }/movieController/toAdd.do" target="_blank">添加</a>
    </div>
    <hr>
    <div class="center">
    	<ul id="movieUL" style="margin-left: -100px;">
    	</ul>
    </div>
  </body>
<script type="text/javascript">
	function queryMovie(){
		var titleValue = $("#title").val();
		
		$.post("${ctx }/movieController/queryMovieList.do",{title:titleValue},function(data){
			var movieUL = $("#movieUL");
			movieUL.html("");
			if(data.length > 0){
				for(var i = 0;i<data.length;i++){
					//movieUL.append("<li onclick='openMovie("+data[i].id+")'>"+data[i].title+"</li>");
					movieUL.append("<li class='margin_li'><a href='${ctx }/movieController/toShow.do?id="+data[i].id+"' target='_blank'>"+data[i].title+"</a></li>");
				}
			}
		},"json");
	}
	
	function openMovie(id){
		window.open("${ctx }/movieController/toShow.do?id=" + id);
	}
</script>
</html>
