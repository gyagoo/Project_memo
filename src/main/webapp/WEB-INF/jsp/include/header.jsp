<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<header class="bg-secondary d-flex align-items-center justify-content-between">
			<h1 class="ml-3">Memo</h1>
			<div class="mr-3">
				
				
				<%-- 
				session에 userId 값이 존재하는지? 
				-> 조건문 필요
				--%>
				
				<c:choose>
					<%-- 
						session에 있는 key를 그대로 사용
						-> model에서 사용하지 않도록 주의할 것 
					--%>
					<c:when test="${not empty userId }">	
						${userName }님 <a href="/user/sign_out">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a href="/user/signin_view">로그인</a>
					</c:otherwise>
				</c:choose>
			</div>
		</header>