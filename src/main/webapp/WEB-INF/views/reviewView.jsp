<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- <link href="/css/reviewView.css" rel="stylesheet" type="text/css"/> -->
<%@ include file="header.jsp" %>


	<h1>reviewView</h1>
	
	<a href="review"><button>글목록</button></a>
	
	<p id="sessionId" data-sessionid="<%= session.getAttribute("userId") %>">${item.stars} | ${item.planInfo}</p>
	<p id="reviewId" data-reviewid="${item.reviewId}">제목 ${item.title}</p>
	<p id="userId" data-userid="${item.userId}">${item.nick} | ${item.createDate} | 조회 ${item.views} | 
		추천 <span id="likeCount">${item.likeCount}</span> | 
		댓글 <span class="commentCount">${item.commentCount}</span></p>
	<c:if test="${item.updateDate != null}">
		<p>${item.updateDate} 수정됨</p>
	</c:if>

	<c:if test="${item.userId == sessionScope.userId}">
	<%-- <c:if test="${item.userId eq sessionScope.userId}"> --%>
	  <button id="modifyBtn" type="button">수정</button>
	  <button id="deleteBtn" type="button">삭제</button><br/>
	</c:if>

	<p>내용 : ${item.content}</p>

	<button id="like" type="submit">추천</button>
	<button id="report" type="submit">신고</button>

		
	<hr/>
	<p>댓글(<span class="commentCount">${item.commentCount}</span>)</p>
	<input type="text" name="comment">
	<button id="commentBtn" type="button">등록</button>

	
	<table>
        <tbody id="commentList">
        	<c:forEach var="comment" items="${commentList}">
	            <tr data-comment-id="${comment.commentId}"
	            	data-comment-lv="${comment.commentLv}">
	            <c:if test="${comment.deleteAt == 'N'}">
	            	<td data-content="${comment.content}"
	            		data-createdate="${comment.createDate}"
	            		data-updatedate="${comment.updateDate}">
	            		<c:if test="${comment.commentLv > 1}"><c:forEach var="i" begin="1" end="${comment.commentLv-1}">&emsp;</c:forEach>
	            		</c:if>${comment.nick}<c:if test="${comment.userId == item.userId}">(글쓴이)</c:if> | 
						<span><span>${comment.content}</span>
		            		<c:if test="${comment.updateDate == null}">
								(${comment.createDate})
							</c:if>
		            		<c:if test="${comment.updateDate != null}">
								(${comment.updateDate} 수정)
							</c:if>
							<c:if test="${comment.commentLv < 3}">
	            				<button class="replyBtn" type="button">답글</button>
	            			</c:if>
						</span>
	            	</td>
	            	<c:if test="${comment.userId == sessionScope.userId}">
					<%-- <c:if test="${item.userId eq sessionScope.userId}"> --%>
	            		<td><button class="modifyBtn" type="button">수정</button></td>
	            		<td><button class="deleteBtn" type="button">삭제</button></td>
	            	</c:if>
	            </c:if>
	            <c:if test="${comment.deleteAt == 'Y'}">
					<td>
						<c:if test="${comment.commentLv > 1}"><c:forEach var="i" begin="1" end="${comment.commentLv-1}">&emsp;</c:forEach></c:if>삭제된 댓글입니다.
					</td>
				</c:if>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	
	
<%@ include file="footer.jsp" %>
<script src="/js/reviewView.js"></script>