<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/css/review.css" rel="stylesheet" type="text/css"/>
<link href="/css/reviewView.css" rel="stylesheet" type="text/css"/>
<%@ include file="header.jsp" %>

	<div class="main-header anim">
		<span id="headline" id="reviewId" data-reviewid="${item.reviewId}">${item.title}</span>
		<div>
			<c:if test="${item.userId == sessionScope.userId}">
				<button id="modifyBtn">수정</button>
				<button id="deleteBtn">삭제</button>
			</c:if>
			<c:if test="${item.userId != sessionScope.userId}">
				<button id="like">추천</button>
				<button id="report">신고</button>
			</c:if>
		</div>
	</div>
	
	<div class="planRrating anim">
		<span id="sessionId" data-sessionid="<%= session.getAttribute("userId") %>">
			<span class="rating_box">
				<span class="rating">★★★★★
					<span class="rating_star" id="stars" data-stars="${item.stars}">★★★★★</span>
				</span>&nbsp; | &nbsp; ${item.planInfo}
			</span>
		</span>
	</div>
	<div class="planRrating anim">
		<p id="userId" data-userid="${item.userId}">
			${item.nick} &nbsp; | &nbsp;
			<c:if test="${item.updateDate == null}">
				${item.createDate} &nbsp; | &nbsp;
			</c:if>
			<c:if test="${item.updateDate != null}">
				${item.updateDate} 수정 &nbsp; | &nbsp;
			</c:if>
			조회 ${item.views} &nbsp; | &nbsp;
			추천 <span id="likeCount">${item.likeCount}</span> &nbsp; | &nbsp;
			댓글 <span class="commentCount">${item.commentCount}</span>
		</p>
	</div><br/>
	<div class="scrollable"><hr class="anim"/><br/>
	<div class="planRrating anim">
		<div>${item.content}</div>
	</div><br/><br/><hr class="anim"/><br/>
	<div class="planRrating anim">
		<span class="commentSpan">댓글(<span class="commentCount">${item.commentCount}</span>)</span>
		<input type="text" name="comment" id="comment">
		<button id="commentBtn" type="button">&emsp;✔&emsp;</button>
	</div><br/>
	<div class="planRrating anim">
	<table class="anim">
		<colgroup>
			<col style="width: 20%;">
			<col style="width: 67%;">
			<col style="width: 5%;">
			<col style="width: 5%;">
		</colgroup>
        <tbody id="commentList">
        	<c:forEach var="comment" items="${commentList}">
	            <tr data-comment-id="${comment.commentId}"
	            	data-comment-lv="${comment.commentLv}"
	            	data-userid="${comment.userId}">
	            <c:if test="${comment.deleteAt == 'N'}">
	            	<td data-content="${comment.content}"
	            		data-createdate="${comment.createDate}"
	            		data-updatedate="${comment.updateDate}">
	            		<c:if test="${comment.commentLv > 1}">
		            		<c:forEach var="i" begin="1" end="${comment.commentLv-1}">
		            			&emsp;
		            		</c:forEach>
	            		</c:if>
	            		<span class="commentNick">
		            		<c:if test="${comment.commentLv > 1}">
		            			┗
		            		</c:if>
			            	<c:if test="${comment.userId == item.userId}">
			            		<b>${comment.nick}</b>
			            	</c:if>
			            	<c:if test="${comment.userId != item.userId}">
			            		${comment.nick}
			            	</c:if>
	            		</span>
	            	</td>
	            	<td>
						<span>
							<span>${comment.content}</span>
							<span class="unimportant">
			            		<c:if test="${comment.updateDate == null}">
									(${comment.createDate})
								</c:if>
			            		<c:if test="${comment.updateDate != null}">
									(${comment.updateDate} 수정)
								</c:if>
							</span>
							<c:if test="${comment.commentLv < 3}">
		            			<button class="replyBtn emojiBtn unimportant" type="button">답글</button>
		            		</c:if>
						</span>
	            	</td>
	            	<c:if test="${comment.userId == sessionScope.userId}">
	            		<td><button class="modifyBtn emojiBtn" type="button">↩</button></td>
	            		<td><button class="deleteBtn emojiBtn" type="button">✖</button></td>
	            	</c:if>
	            	<c:if test="${comment.userId != sessionScope.userId}">
	            		<td></td>
	            		<td></td>
	            	</c:if>
	            </c:if>
	            <c:if test="${comment.deleteAt == 'Y'}">
					<td colspan="4">
						<c:if test="${comment.commentLv > 1}">
							<c:forEach var="i" begin="1" end="${comment.commentLv-1}">
								&emsp;
							</c:forEach>
						</c:if>
						삭제된 댓글입니다.
					</td>
				</c:if>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	</div>
	</div>
	
<%@ include file="footer.jsp" %>
<script src="/js/reviewView.js"></script>