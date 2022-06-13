<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반려견 객실 예약 페이지</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
	.topImg{
		width: 1280px;
		height: 480px;
		margin: 50px auto;
		background-image: url(img/pet/puppy_02.jpg);
		background-size: contain;
		border-radius: 10px;
	}
	.middleImg{
		width: 1279px;
		height: 361px;
		margin: 50px auto;
		background-image: url(img/pet/puppy_03.jpg);
		background-size: contain;
		border-radius: 10px;
	}

	#check_in{
		width: 240px;
		height: 200px;
	}
	#check_out{
		width: 240px;
		height: 200px;
	}
	
	.ui-datepicker {
	  width: 220px;
	  height: 180px;
	  margin: 5px auto 0;
	  font: 10pt Arial, sans-serif;
	  -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
	  -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
	  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
	}
	
	.ui-datepicker a {
	  text-decoration: none;
	}
	
	.ui-datepicker table {
	  width: 100%;
	}
	
	.ui-datepicker-title {
	  text-align: center;
	}
	
	.ui-datepicker-prev {
	  float: left;
	  background-position: center -30px;
	}
	.ui-datepicker-next {
	  float: right;
	  background-position: center 0px;
	}
	
	.ui-datepicker thead {
	  background-color: #f7f7f7;
	  background-image: -moz-linear-gradient(top,  #f7f7f7 0%, #f1f1f1 100%);
	  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f7f7f7), color-stop(100%,#f1f1f1));
	  background-image: -webkit-linear-gradient(top,  #f7f7f7 0%,#f1f1f1 100%);
	  background-image: -o-linear-gradient(top,  #f7f7f7 0%,#f1f1f1 100%);
	  background-image: -ms-linear-gradient(top,  #f7f7f7 0%,#f1f1f1 100%);
	  background-image: linear-gradient(top,  #f7f7f7 0%,#f1f1f1 100%);
	  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f7f7f7', endColorstr='#f1f1f1',GradientType=0 );
	  border-bottom: 1px solid #bbb;
	}
	
	.ui-datepicker th {
	  text-transform: uppercase;
	  font-size: 6pt;
	  padding: 5px 0;
	  color: #666666;
	  text-shadow: 1px 0px 0px #fff;
	  filter: dropshadow(color=#fff, offx=1, offy=0);
	}
	
	.bodyWrap_pet{
		height: 640px;
		padding-top: 20px;
	}
	.sentence{
		width: 1280px;
		margin: 30px auto;
		padding-left: 10px;
		border-bottom: 2px solid #9c836a;
	}
	.sentence>h3{
		margin: 10px;
	}
	
	.body_pet{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: flex-start;
		width: 1280px;
		height: 590px;
		margin: auto;
	}
	.contentL_pet{
		background-color : rgb(235,235,235);
		margin: 10px;
		width: 904px;
		height: 100%;
	}
	.room_pet{
		display: flex;
		flex-flow: row;
		justify-content: center;
	}
	.room_pet:hover{
		cursor: pointer;
	}
	.room_pet div{
		width: 88px;
		height: 88px;
		margin: 1px;
		background-color : rgb(190,190,190);
		text-align: center;
	}
	.room_pet p{
		margin-top: 30px;
	}
	
	.emptySpace_pet{
		height: 65%;
		background-image: url(img/logo.png);
		background-repeat: no-repeat;
		background-position: 50% 50%;
	}
	
	.sRoom_pet{
		display: flex;
		flex-flow: row;
		justify-content: center;
	}
	.sRoom_pet div{
		width: 112px;
		height: 112px;
		margin: 1px;
		background-color : rgb(190,190,190);
	}
	.sRoom_pet p{
		margin-top: 44px;
	}
	.room_pet>.occupiedRoom{
		background-color : rgba(255,0,0,0.7);
	}
	.room_pet>.selectedRoom{
		background-color : #00a3d2;
	}
	.contentR_pet{
		margin: 10px;
		width: 260px;
		height: 100%;
	}
	.contentR_pet h4{
		margin: 8px;
	}
	
	.rsvBtn button[type=submit], .rsvBtn button[type=button]{
		border: 1px solid #9c836a;
		border-radius: 8px;
		background-color: #9c836a;
		color: white;
		opacity: 0.7;
		width: 70px;
		height: 25px;
	}
	
	.pbWrap_pet{
		display: flex;
		flex-flow: row;
		justify-content: center;
		width: 1280px;
		height: 540px;
		padding: 20px;
		margin: auto;
	}
	.boardL_pet{
		width: 600px;
		margin: 20px;
		margin-right: 40px;
	}
	
	#pageNavi{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: flex-start;
		height: 25px;
		margin: 5px;
	}
	#pageNavi ul li{
		list-style-type : none;
		float : left;
		margin: 0;
		padding-left: 8px;
		padding-right: 8px;
		font-size: 16px;
	}
	#pageNavi ul li a{
		text-decoration : none;
		color : black;
	}
	.pagination{
		margin: 5px;
		padding: 0;
		height: 25px;
	}
	.active>a{
		font-weight : bold;
	}
	#pbWrite{
		float: right;
		width: 70px;
		height: 30px;
		margin-right: 5px;
		border: 1px solid #9c836a;
		border-radius: 8px;
		background-color: #9c836a;
		color: white;
		opacity: 0.7;
	}
	.pbTitle button{
		border: 1px solid #9c836a;
		border-radius: 8px;
		background-color: #9c836a;
		color: white;
		opacity: 0.7;
		width: 50px;
		height: 25px;
	}
	.pbUpdateFrm input[type=submit], .pbUpdateFrm button[type=reset]{
		border: 1px solid #9c836a;
		border-radius: 8px;
		background-color: #9c836a;
		color: white;
		opacity: 0.7;
		width: 70px;
		height: 25px;
	}
	#updateFrmBtn{
		text-align: center;
	}
	
	.alertModal{
	    position: fixed;
	    z-index: 10;
	    width: 600px;
	    height: 480px;
	    top: 12%;
	    left: 30%;
	    display: none;
	    justify-content: center;
	    align-items: center;
	    border-radius: 10px;
	    background-color: rgb(255,255,255);
    	border-style: solid;
    	border-color: #9c836a;
    }
    
    .alertModal input[type=submit], .alertModal button[type=button]{
    	border: 1px solid #9c836a;
		border-radius: 8px;
		background-color: #9c836a;
		color: white;
		opacity: 0.7;
		width: 70px;
		height: 25px;
    }
    
    .boardR_pet{
		width: 720px;
		height: 520px;
		margin: 20px;
		margin-left: 40px;
	}
	
	.pbResult{
		height: 520px;
	}
    .pbTitle{
    	width: 600px;
    	height: 24px;
    }
    .pbTitle button{
    	float: right;
    	margin-left: 4px;
    }
    .pbWriter{
    	width: 200px;
    	height: 24px;
    }
    .pbDate{
    	width: 200px;
    }
    .startScore{
    	width: 400px;
    }
    .pbContent{
    	width: 600px;
    	height: 80px;
    	padding: 5px;
    }
    .pbContent>pre{
    	height: 80px;
    	margin: 0;
    	overflow: auto;
    	white-space: pre-wrap;
    }
    .pbImg{
    	width: 600px;
    	height: 250px;
    	text-align: center;
    }
    .pbImgDiv{
    	padding: 7px;
    	display: inline-block;
    }
    .pbImgSize{
    	width: 280px;
    	max-height: 280px;
    }
    .pbImgSize:hover{
    	cursor: pointer;
    }
    .img-view img{
    	padding: 7px;
    }
    .imgModal{
    	display: none;
    	position: fixed;
    	z-index: 1;
    	left: 0;
    	top: 0;
    	width: 100%;
    	height: 100%;
    	background-color: rgba(0,0,0,0.9);
    }
    .modalContent{
    	margin: auto;
    	max-width: 1280px;
    	max-height: 720px;
    	align-items: center;
    	
    	-webkit-animation-name: fadeIn;
    	-webkit-animation-duration: 0.7s;
    }
    @-webkit-keyframes fadeIn{
    	from {-webkit-opacity: 0}
    	to {-webkit-opacity: 1}
    }
    
    .modalCloseBtn{
    	position: absolute;
    	top: 15px;
    	right: 35px;
    	color: #f1f1f1;
    	font-size: 40px;
    	font-weight: bold;
    	transition: 0.3s;
    }
    .modalCloseBtn:hover{
    	color: #bbb;
    	cursor: pointer;
    }
    
    
    
    .pbUpdateFrm{
    	display: none;
    }
    
    input[name=pbTitle]{
    	width: 98%;
    	height: 25px;
    	border-style: none;
    }
    
    input[name=pbWriter]{
    	width: 120px;
    	height: 25px;
    	border-style: none;
    }
    
    textarea{
    	width: 98%;
    	border-style: none;
    }
    
    .text_cnt{
    	font-size: 12px;
    }
    
    .btnWrap{
    	text-align: center;
    }
    
    .btnWrap *{
    	width: 80px;
    	height: 28px;
    }
    
	table{
		border-top: 1px solid #9c836a;
		border-collapse: collapse;
	}
	th, td{
		border-bottom: 1px solid #9c836a;
		padding: 2px;
	}
	
	.updateTitle{
		width: 600px;
	}
	
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>	
	<div class="topImg"></div>
	<div class="sentence"><h3 style="color: #9c836a;">반려견 객실 예약</h3></div>
	<div class="bodyWrap_pet">
		<br>
		<div class="body_pet">
			<div class="contentL_pet">
				<div class="room_pet">
					<div><p>Room1</p></div>
					<div><p>Room2</p></div>
					<div><p>Room3</p></div>
					<div><p>Room4</p></div>
					<div><p>Room5</p></div>
					<div><p>Room6</p></div>
					<div><p>Room7</p></div>
					<div><p>Room8</p></div>
					<div><p>Room9</p></div>
					<div><p>Room10</p></div>
				</div>
				<div class="emptySpace_pet"></div>
				<div class="room_pet sRoom_pet">
					<div><p>Room11</p></div>
					<div><p>Room12</p></div>
					<div><p>Room13</p></div>
					<div><p>Room14</p></div>
					<div><p>Room15</p></div>
					<div><p>Room16</p></div>
					<div><p>Room17</p></div>
					<div><p>Room18</p></div>
				</div>
			</div>
			<div class="contentR_pet">
				<div>
					<h4>체크인 날짜 선택</h4>
					<div id="check_in"></div>
					<h4>체크아웃 날짜 선택</h4>
					<div id="check_out"></div>
					<h4>이용 기간 : <span id="stay_period"></span></h4>
					<h4>요금 : <span id="total_charge"></span><span></span></h4>
				</div>
				<div class="rsvBtn">
					<form action="/insertReserve.do" method="post">
						<!-- 아이디,객실번호,체크인날짜,체크아웃날짜 -->
						<input type="hidden" id="memberId" name="memberId" value="${sessionScope.m.memberId }">
						<input type="hidden" id="roomNo" name="rooms">
						<input type="hidden" id="roomNo2" name="rooms">
						<input type="hidden" id="chkinDate" name="petChkin">
						<input type="hidden" id="chkoutDate" name="petChkout">
						<input type="hidden" id="petDays" name="petDays">
						<button type="submit">예약하기</button>
						<button type="button" id="petRoomPayment">결제하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	<div class="middleImg"></div>
	<a name="pbAnchor"/>
	<div class="sentence"><h3 style="color: #9c836a;">이용 후기</h3></div>
	<div class="pbWrap_pet">
		<div class="boardL_pet">
				<c:if test="${not empty m }">
					<div class="alertModal">
						<form action="/pbWrite.do" method="post" enctype="multipart/form-data">
							<table>
								<tr>
									<th width="100px" height="30px">제목</th>
									<td width="440px"><input type="text" name="pbTitle" id="titleCheck" maxlength='33'></td>
								</tr>
								<tr>
									<th height="30px">작성자</th>
									<td><input type="text" name="pbWriter" value="${sessionScope.m.memberId }" readonly></td>
								</tr>
								<tr>
									<th>별점</th>
									<td>
										<div class="starImgWrap">
											<img class="starImg" src="img/pet/star-icon-off.jpg">
											<img class="starImg" src="img/pet/star-icon-off.jpg">
											<img class="starImg" src="img/pet/star-icon-off.jpg">
											<img class="starImg" src="img/pet/star-icon-off.jpg">
											<img class="starImg" src="img/pet/star-icon-off.jpg">
										</div>
										<input type="hidden" class="score" name="starScore" id="starScoreCheck">
									</td>
								</tr>
								<tr>
									<th height="30px">첨부파일</th>
									<td>
										<input type="file" name="files" accept="image/*" multiple onchange="loadImg(this);">
										<div class="img-view"></div>
									</td>
								</tr>
								<tr>
									<th height="80px">
										내용
										<div class="text_cnt">(0 / 333)</div>
									</th>
									<td>
										<textarea rows="5" name="pbContent" style="resize:none" class="textChk" id="contentCheck"></textarea>
										
									</td>
								</tr>
								<tr>
									<td colspan="2" height="30px" class="btnWrap">
										<input type="submit" value="완료" id="pbSubmit">
										<button type="button" id="closeBtn">취소</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:if>
				<table>
					<tr>
						<th width="260px" height="30px">제목</th>
						<th width="80px">작성자</th>
						<th width="80px">별점</th>
						<th width="100px">작성일</th>
					</tr>
					<c:forEach items="${list }" var="pb">
						<tr>
							<td class="viewPetBoard" height="30px"><input type="hidden" value="${pb.pbNo}">${pb.pbTitle }</td>
							<td>${pb.pbWriter }</td>
							<td>
								<c:forEach begin="1" end="${pb.starScore }" step="1">
									<img src="img/pet/star-icon-on.jpg" style="float: left">
								</c:forEach>
							</td>
							<td>${pb.pbDate }</td>
						</tr>
					</c:forEach>
				</table>
				<div id="pageNavi">${pageNavi }</div>
				<c:if test="${not empty m }">
					<div><button id="pbWrite">글쓰기</button></div>
				</c:if>
		</div>
		<div class="boardR_pet">
			<div class="pbResult">
				<table>
					<tr>
						<th colspan="3" class="pbTitle"></th>
					</tr>
					<tr>
						<th class="pbWriter"></th>
						<th class="pbDate"></th>
						<td class="starScore"></td>
					</tr>
					<tr>
						<td colspan="3" class="pbImg"></td>
					</tr>
					<tr>
						<td colspan="3" class="pbContent"></td>
					</tr>
				</table>
			</div>
			<div class="pbUpdateFrm">
				<form action="/pbUpdate.do" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td colspan="3" class="updateTitle">
								<input type="hidden" name="pbNo" id="updatePbNo">
								<input type="text" name="pbTitle" maxlength='33' style='font-size:16px; width:80%;'>
							</td>
						</tr>
						<tr>
							<th class="pbWriter"></th>
							<th class="pbDate"></th>
							<td class="updateScore">
								<div class="updateStarImgWrap">
									<img class="starImg" src="img/pet/star-icon-off.jpg">
									<img class="starImg" src="img/pet/star-icon-off.jpg">
									<img class="starImg" src="img/pet/star-icon-off.jpg">
									<img class="starImg" src="img/pet/star-icon-off.jpg">
									<img class="starImg" src="img/pet/star-icon-off.jpg">
								</div>
								<input type="hidden" class="score" name="starScore">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="file" name="updateFiles" accept="image/*" multiple onchange="loadImg(this);">
								<div class="img-view"></div>
							</td>
						</tr>
						<tr id="removeRow">
							<td colspan="3" class="pbImg"></td>
						</tr>
						<tr>
							<td colspan="3" class="updateContent">
								<textarea rows="5" name="pbContent" style="resize:none" class="textChk"></textarea>
								<div class="text_cnt">(0 / 333)</div>
							</td>
						</tr>
						<tr>
							<td colspan="3" id="updateFrmBtn">
								<input type="submit" value="수정 완료">
								<button type="reset" onclick="cancelUpdate();">취소</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
	
	<script>
		var checkinDate;	//체크인 날짜
		var checkoutDate;	//체크아웃 날짜
		var nextDay;		//체크인 날짜 선택시 그 다음날부터 체크아웃이 가능하도록 설정하기 위한 날짜변수
		var stay_period;	//숙박일수를 저장할 변수
		var d = new Date();
		var today = d.getFullYear()+"/"+(d.getMonth()+1)+"/"+d.getDate();	//오늘 날짜(문자열) ""는 문자열로써 연결시키기위해 필요. 그렇지않으면 덧셈연산이 됨. getMonth는 0부터 시작하기때문에 +1해줌.
		var roomNo = 0;
		var roomNo2 = 0;
		
		//사용자가 선택한 날짜(체크인)기준 객실 현황
		function showOnDate(checkinDate){
			$.ajax({
				url : "/selectRoomList.do",
				data : {checkinDate:checkinDate},
				type : "post",
				success : function(data){	//data : 선택한 날짜 기준 이용중인 객실 list
					$(".room_pet>div").removeAttr("class","occupiedRoom");
					for(var i=0;i<data.length;i++){
						$(".room_pet>div").eq(data[i].roomNo-1).attr("class","occupiedRoom");
					}
				}
			});
		}
		
		//사용자가 선택한 날짜 범위(체크인~체크아웃)기준 객실 현황
		function showOnDateRange(checkinDate,checkoutDate){
			$.ajax({
				url : "/selectRoomList2.do",
				data : {checkinDate:checkinDate,checkoutDate:checkoutDate},
				type : "post",
				success : function(data){	//data : 선택한 날짜범위 기준 이용중인 객실 list
					$(".room_pet>div").removeAttr("class","occupiedRoom");
					for(var i=0;i<data.length;i++){
						$(".room_pet>div").eq(data[i].roomNo-1).attr("class","occupiedRoom");
					}
				}
			});	
		}
		
		//페이지 로드 완료 후 현재 날짜를 표시(선택)하고, 사용자로부터 체크인/체크아웃 날짜를 입력(선택)받음
		$(function(){
			showOnDate(today);
			
			$("#check_in").datepicker({
				dateFormat: 'yy/mm/dd' //달력 날짜 형태
	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
	           ,changeYear: true //option값 년 선택 가능
	           ,changeMonth: true //option값  월 선택 가능
	           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        	   ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	           ,minDate: '0'
	           ,maxDate: '+30D' //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
			   ,onSelect : function(selCiDate,inst){
				   showOnDate(selCiDate);
				   checkinDate = $(this).val();
				   $("#total_charge").html("");
					nextDay = new Date(checkinDate);
					nextDay.setDate(nextDay.getDate()+1);
					$("#stay_period").html("");
					$("#check_out").datepicker("destroy");
					$("#check_out").datepicker({
						dateFormat: 'yy/mm/dd' //달력 날짜 형태
			           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
			           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
			           ,changeYear: true //option값 년 선택 가능
			           ,changeMonth: true //option값  월 선택 가능
			           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
		        	   ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
			           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
			           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
			           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
			           ,minDate: new Date(nextDay)
			           ,maxDate: '+30D' //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
					   ,onSelect : function(selCoDate,inst){
						   checkoutDate = $(this).val();
						   $("#total_charge").html("");
						   showOnDateRange(selCiDate,selCoDate);
						   var date1 = new Date(checkinDate);
						   var date2 = new Date(checkoutDate);
						   stay_period = Math.ceil(date2.getTime()-date1.getTime())/(1000*3600*24);
						   $("#stay_period").html(stay_period+"일");
					   }
					});
			   }
			});
			$("#check_out").datepicker({
				dateFormat: 'yy/mm/dd' //달력 날짜 형태
	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
	           ,changeYear: true //option값 년 선택 가능
	           ,changeMonth: true //option값  월 선택 가능
	           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        	   ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	           ,minDate: '+1D'
	           ,maxDate: '+30D' //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
			});
		});
		
		//이용중인 객실을 제외한 객실을 클릭한 경우(문서 자체에 이벤트를 걸어줌으로써 페이지로드 완료 후에 동작하는 이벤트를 처리)
		$(document).on("click",".room_pet>div:not(.occupiedRoom):not(.selectedRoom)",function(){
			if(checkinDate == null || checkoutDate == null){
				alert("체크인/체크아웃 날짜를 먼저 선택해주세요.");
			}else{
				var cntSelRoom = $(".selectedRoom").length;
				if(cntSelRoom == 2){
					alert("반려견 객실은 최대 2개까지 예약 가능합니다.");
				}else{
					$(this).attr("class","selectedRoom");//선택한 객실 파란색으로 표시
				}				
			}
		});
		$(document).on("click",".selectedRoom",function(){
			$(this).removeAttr("class","selectedRoom");
		});
		
		//선택된 기간, 객실의 총 요금 실시간 출력
		$(document).on("click",".room_pet>div",function(){
			if(checkinDate == null || checkoutDate == null){
				return;
			}
			roomNo = $(".room_pet>div").index($("div").find(".selectedRoom").eq(0))+1;
			roomNo2 = $(".room_pet>div").index($("div").find(".selectedRoom").eq(1))+1;
			$.ajax({
				url : "/selectRoomCharge.do",
				data : {roomNo:roomNo,roomNo2:roomNo2,stay_period:stay_period},
				type : "post",
				success : function(data){
					if(data==null){
						$("#total_charge").html("");
					}else{
						$("#total_charge").html(data);
						$("#total_charge").next().html("원");
					}
				}
			});
		});
		
		//(객실)예약하기 버튼 클릭시 동작
		$(document).on("click","button[type=submit]",function(){
			if(loginCheck()){
				return false;	
			}else if(checkinDate == null || checkoutDate == null){
				alert("체크인/체크아웃 날짜를 선택해주세요");
				return false;
			}else if(roomNo == 0 && roomNo2 == 0){
				alert("객실을 선택해주세요");
				return false;
			}else{
				if(window.confirm('예약하시겠습니까?')){
					$("#roomNo").val(roomNo);
					$("#roomNo2").val(roomNo2);
					$("#chkinDate").val(checkinDate);
					$("#chkoutDate").val(checkoutDate);					
				}else{
					return false;
				}
			}
		});
		
		function loginCheck(){
			if($("#memberId").val() == ""){
				alert("로그인이 필요한 서비스입니다.");
				return true;
			}
			return false;
		}
		
		//게시글 상세보기
		$(".viewPetBoard").click(function(){
			$(".pbResult").css("display","block");
			$(".pbUpdateFrm").css("display","none");
			$(".viewPetBoard").removeAttr("style");
			$(this).css("color","blue").css("font-weight","bold");
			var pbNo = $(this).children('input').val();
			var html = "";
			var sessionId = '${sessionScope.m.memberId}';
			$.ajax({
				url : "/viewPetBoard.do",
				data : {pbNo:pbNo},
				success : function(data){
					$(".pbTitle").html("<span>"+data.pbTitle+"</span>");
					$(".pbWriter").html(data.pbWriter);
					$(".pbDate").html(data.pbDate);
					if(data.fileList.length == 0){
						html = "<div class='pbImgDiv'><img src='img/logo.png'></div>"
					}else{
						for(var i=0;i<data.fileList.length;i++){
							html += "<div class='pbImgDiv'>"
							html += 	"<img src='/resources/upload/petboard/"+data.fileList[i].filepath+"' class='pbImgSize'>"
							html += 	"<div class='imgModal'>"
							html +=			"<span class='modalCloseBtn'>&times;</span>"
							html +=			"<img src='/resources/upload/petboard/"+data.fileList[i].filepath+"' class='modalContent'>"
							html +=		"</div>"							
							html += "</div>"
						}						
					}
					var starScore = "";
					for(var i=0;i<data.starScore;i++){
						starScore += "<img src='img/pet/star-icon-on.jpg' style='float: left'>";
					}
					$(".starScore").html(starScore);
					$(".pbImg").html(html);
					$(".pbContent").html("<pre>"+data.pbContent+"</pre>");
					if(data.pbWriter == sessionId){
						$(".pbTitle").append("<button onclick='deletePetBoard("+data.pbNo+");'>삭제</button>");
						$(".pbTitle").append("<button onclick='updatePetBoard("+data.pbNo+","+data.starScore+");'>수정</button>");
					}
				}
			});
		});
		
		//게시글 삭제
		function deletePetBoard(pbNo){
			var confirm = window.confirm("게시글을 삭제하시겠습니까?")
			if(confirm){
				location.href = "/deletePetBoard.do?pbNo="+pbNo;
			}
		}
		
		//게시글 수정
		function updatePetBoard(pbNo,starScore){
			$(".pbResult").css("display","none");
			$(".pbUpdateFrm").css("display","block");
			
			var updateTitle = $(".pbTitle").children().first().text();
			var updateContent = $(".pbContent").children('pre').text();
			$(".updateTitle").children('input').val(updateTitle);
			$(".updateContent").children('textarea').val(updateContent);
			
			$(".updateStarImgWrap>img").each(function(index,item){
				if(index<starScore){
					$(item).attr("src","img/pet/star-icon-on.jpg");
				}else{
					$(item).attr("src","img/pet/star-icon-off.jpg");
				}
			});
			$(".score").val(starScore);
			$("#updatePbNo").val(pbNo);
		}
		
		//게시글 수정에서 취소 버튼 클릭
		function cancelUpdate(){
			$(".pbResult").css("display","block");
			$(".pbUpdateFrm").css("display","none");
		}
		
		
		/*
		//페이지네비 클릭시 ajax를 통해 처리하는 함수(미구현)
		$(document).on("click",".page-item",function(){
			var pageNo = $(this).children('input').val();
			$.ajax({
				url : "/petRoomReserveAjax.do",
				data : {pageNo:pageNo},
				success : function(data){
					
					console.log(data.list);
					console.log(data.pageNavi);
				}
			});
		}); 
		*/
		
		//글쓰기 버튼 클릭시 모달 동작, 취소 버튼 클릭시 모달 off
		$(function(){
			$("#pbWrite").click(function(){
				$(".alertModal").css("display","flex");
				$(".score").val("");
			});
			$("#closeBtn").click(function(){
				$(".alertModal").hide();
			});
		});
		
		//게시글 작성 및 수정에서 파일첨부시 해당파일 이미지 표시
		function loadImg(f){
			$(".img-view").empty();
			$("#removeRow").remove();
			if(f.files.length > 0 && f.files.length < 3){
				for(var i=0;f.files.length;i++){
					var reader = new FileReader();
					reader.readAsDataURL(f.files[i]);
					reader.onload=function(e){
						var html = "<img src='"+e.target.result+"' width='200px' height='150px'>";
						$(".img-view").append(html);
					}					
				}
			}else if(f.files.length > 2){
				alert("이미지는 최대 2개까지 업로드 가능합니다.");
				$("input[name=files]").val("");
			}else{
				$(".img-view").attr("src","");
			}
		}
		
		//게시글 작성시 별점 선택 기능
		$(".starImgWrap>img").click(function(){
			var idx = $(".starImgWrap>img").index(this);
			$(".starImgWrap>img").each(function(index,item){
				if(index<=idx){
					$(item).attr("src","img/pet/star-icon-on.jpg");
				}else{
					$(item).attr("src","img/pet/star-icon-off.jpg");
				}
			});
			$(".score").val(idx+1);
		});
		
		//게시글 수정시 별점 수정 기능
		$(".updateStarImgWrap>img").click(function(){
			var idx = $(".updateStarImgWrap>img").index(this);
			$(".updateStarImgWrap>img").each(function(index,item){
				if(index<=idx){
					$(item).attr("src","img/pet/star-icon-on.jpg");
				}else{
					$(item).attr("src","img/pet/star-icon-off.jpg");
				}
			});
			$(".score").val(idx+1);
		});
		
		//결제 버튼 클릭시 결제모듈 동작
		$("#petRoomPayment").click(function(){
			if(loginCheck()){
				return false;
			}else{
				var priceWithComma = $("#total_charge").html();
				var priceArray = priceWithComma.split(",");
				var price = priceArray.join("");
				if(price == ""){
					alert("선택이 완료되지 않았습니다.");
				}else{
					var today = new Date();
					var date = today.getFullYear()+""+(today.getMonth()+1)+""+today.getDate()+""+today.getHours()+""+today.getMinutes()+""+today.getSeconds();
					IMP.init('imp91182309');	//결제 api사용을 위한 코드 입력
					IMP.request_pay({
						merchant_uid : "반려견호텔_"+date,		//거래 ID
						name : "final_project_petHotel",	//결제 이름
						amount : price,						//결제 금액
						buyer_email : "bodoreda@gmail.com",	//구매자 email주소
						buyer_name : 'memberName',			//구매자 이름
						buyer_phone : 'memberPhone',			//구매자 전화번호
						buyer_addr : 'memberAddr',			//구매자 주소,
						buyer_postcode : "76621"			//구매자 우편번호
					}, function(rsp){
						if(rsp.success){		//결제 성공시
							//결제 성공시 DB에 결제정보 저장하고 사용자 화면처리가 필요함
							alert("결제 성공");
							console.log("카드 승인번호 : "+rsp.apply_num);
						}else{
							//결제 실패
							//실패시 로직 구현
							alert("결제 실패");
						}
					});
				}
			}
		});
		
		//페이지 로드 완료 후 첫번째 게시글 선택하여 출력
		$(function(){
			var pbNo = $(".viewPetBoard").first().children('input').val();
			$(".pbResult").css("display","block");
			$(".pbUpdateFrm").css("display","none");
			$(".viewPetBoard").removeAttr("style");
			$(".viewPetBoard").first().css("color","blue").css("font-weight","bold");
			var html = "";
			var sessionId = '${sessionScope.m.memberId}';
			$.ajax({
				url : "/viewPetBoard.do",
				data : {pbNo:pbNo},
				success : function(data){
					$(".pbTitle").html("<span>"+data.pbTitle+"</span>");
					$(".pbWriter").html(data.pbWriter);
					$(".pbDate").html(data.pbDate);
					if(data.fileList.length == 0){
						html = "<div class='pbImgDiv'><img src='img/logo.png'></div>"
					}else{
						for(var i=0;i<data.fileList.length;i++){
							html += "<div class='pbImgDiv'>"
							html += 	"<img src='/resources/upload/petboard/"+data.fileList[i].filepath+"' class='pbImgSize'>"
							html += 	"<div class='imgModal'>"
							html +=			"<span class='modalCloseBtn'>&times;</span>"
							html +=			"<img src='/resources/upload/petboard/"+data.fileList[i].filepath+"' class='modalContent'>"
							html +=		"</div>"							
							html += "</div>"
						}
					}
					var starScore = "";
					for(var i=0;i<data.starScore;i++){
						starScore += "<img src='img/pet/star-icon-on.jpg' style='float: left'>";
					}
					$(".starScore").html(starScore);
					$(".pbImg").html(html);
					$(".pbContent").html("<pre>"+data.pbContent+"</pre>");
					if(data.pbWriter == sessionId){
						$(".pbTitle").append("<button onclick='deletePetBoard("+data.pbNo+");'>삭제</button>");
						$(".pbTitle").append("<button onclick='updatePetBoard("+data.pbNo+","+data.starScore+");'>수정</button>");
					}
				}
			});
		});
		
		//글 제목, 내용, 별점을 입력하지않고 완료를 클릭할 경우 제한
		$(document).on("click","#pbSubmit",function(){
			var title = $("#titleCheck").val();
			var score = $("#starScoreCheck").val();
			var content = $("#contentCheck").val();
			if(title == ""){
				alert("제목을 입력해주세요");
				return false;
			}else if(score == ""){
				alert("별점을 선택해주세요");
				return false;
			}else if(content == ""){
				alert("내용을 입력해주세요");
				return false;
			}else{
				return true;
			}
		});
		
		//textarea 글자수 제한
		$(document).ready(function(){
			$(".textChk").on("keyup",function(){
				$(".text_cnt").html("("+$(this).val().length+" / 333)");
				
				if($(this).val().length > 333){
					$(this).val($(this).val().substring(0, 333));
					$(".text_cnt").html("(333 / 333)");
					alert("글자수는 1000byte를 초과할 수 없습니다.");
				}
			})
		})
		
		//게시글에서 이미지 클릭시 확대(modal)
		$(document).on("click",".pbImgSize",function(){
			$(this).next().css("display","flex");
		});
		
		//close modal
		$(document).on("click",".modalCloseBtn",function(){
			$(".imgModal").css("display","none");
		});
		
		
	</script>
</body>
</html>