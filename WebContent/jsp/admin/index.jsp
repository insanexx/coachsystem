<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>首页</title>

    <link href="${pageContext.request.contextPath }/bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/jumbotron.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/offcanvas.css" rel="stylesheet">
    <style type="text/css">
	    .input-group-lg>.form-control {
	   		font-size: 1rem;
	    }
	    .input-group-lg{
	   		padding-bottom: 2px;
	    }
    </style>
  </head>

  <body class="bg-light">
  
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="#">私教预订系统(${sessionScope.admin.adminid })</a>
      
      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
        </ul>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath }/admin/AdminServlet?method=logout" method="POST">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">注销</button>
        </form>
      </div>
    </nav>

    <main role="main">

      <div class="container">
      
		  <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow">
	        <img class="mr-3" src="${pageContext.request.contextPath }/img/2.png" alt="" width="48" height="48">
	        <div class="lh-100">
	          <h6 class="mb-0 text-white lh-100">私教预订系统</h6>
	        </div>
      	  </div>
      	  <a class="btn btn-outline-success my-2 my-sm-0" role="button" href="${pageContext.request.contextPath }/admin/AdminServlet?method=coachlist" target="_blank">查询教练</a>
      	  <a class="btn btn-outline-success my-2 my-sm-0" role="button" href="${pageContext.request.contextPath }/admin/AdminServlet?method=userlist" target="_blank">查询用户</a>
	      <div class="my-3 p-3 bg-white rounded box-shadow">
	         <c:forEach items="${courseList }" var="course">
		       <div class="media text-muted pt-3">
		          <img src="${pageContext.request.contextPath }/img/3.png" alt="" class="mr-2 rounded">
		          <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
		            <div class="d-flex justify-content-between align-items-center w-100">
		              <span class="d-block">课程时间：${course.time }</span>
		              <c:if test="${!course.pass }">
			          	<a href="${pageContext.request.contextPath }/admin/AdminServlet?method=check&courseid=${course.courseid}" style="color:red;">审核</a>
		              </c:if>
		              <c:if test="${course.pass }">
		             	<a href="#" style="color:green;">已审核</a>
		              </c:if>
		            </div>
	            	<span class="d-block">课程地点：${course.place }</span>
	            	<span class="d-block">课程简介：${course.content }</span>
	            	<span class="d-block">课程教练：${course.coach.name }</span>
	            	<span class="d-block">联系方式：${course.coach.phone }</span>
		            <div>
		            	<br/>
		            	 <a target="_blank" href="${pageContext.request.contextPath }/admin/AdminServlet?method=listuser&courseid=${course.courseid}">查看预订列表</a>
		            </div>
		          </div>
		        </div>
	        </c:forEach>
	      </div>
      </div>
    </main>

   <script src="${pageContext.request.contextPath }/jquery-3.1.0/jquery-3.2.1.slim.min.js"></script>
   <script src="${pageContext.request.contextPath }/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath }/js/popper.min.js"></script>
  </body>
</html>
