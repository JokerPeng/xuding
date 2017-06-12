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
    <title>${movie.title }</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery1.9.1.min.js"></script>
  </head>
  
  <body>
	    <center>
	    	<p>${movie.title }</p>
	    	<video src="${ctx }/${movie.moviePath }" controls="controls">
			您的浏览器不支持 video 标签。
			</video>
	    </center>
  </body>
<script type="text/javascript">
	
	
</script>
</html>
