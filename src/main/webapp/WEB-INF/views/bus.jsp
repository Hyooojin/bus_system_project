<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<%-- <link rel="stylesheet" href="<c:url value="/resources/css/bubbles.css" />"> --%>
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript">
$(document).ready(function(){
	$('#inputbusname').keypress(function(e){
		if (e.which === 13){
			ajax_process();
		}
	});
	
	$('#btn_submit').click(function(){
		ajax_process();
	});
});



function ajax_process(_isay){
	var inputbusname = $.trim($('#inputbusname').val());
	var outputdata = "";
 	$.ajax({
		type : 'POST',
		url  : 'getkeyword',
		data : 'busname=' + inputbusname,
		async: false,
		success : function(data) {
			
			
			$('#display').append(data);	
			//$('html, body').animate({scrollTop: $(document).height()}, 500);
		}
	}); 
	
	 $.getJSON( "getkeyword?busname="+inputbusname, function( data ) {
		  //var items = [];
		  var tab = '<table class="table">';
		  tab = '<thead>'
		    	+ '<tr>'
		      	+ '<th>   </th>'
		      	+ '<th>정류장 이름</th>'
		   		+ '</tr>'
		  		+ '</thead><tbody>';
		  
		  $.each( data, function( key, val ) {
		    //items.push( val.stationName);
		    tab+='<tr><th scope="row">'+val.stationSeq+'</th><td>'+val.stationName+'</td></tr>';
		  });
		 tab += '</tbody></table>';
		 $('#display').html(tab);	
		}); 
	
		var image = '<img src="<c:url value="/resources/img/'+inputbusname+'.png"/>" usemap="#mapping" border = "2" width="500"/>';
		$('#timetable').html(image);
	
	
	
/* 	$.get(image_url)
		.done(function(){
			// Do something now you know the image exiests.
			
			.fail(function() {
			// Image doesn't exist - do something else.	
			})
		})
	
	
	function imageExists(image_url){
		var http = new XMLHttpRequest();
		
		http.open('HEAD', image_url, false);
		http.send();
		
		return http.status != 404;
	} */
		
		
}
</script>
</head>



<body>

	<h1>광역급행버스 시간표</h1>
	
		<a>지금 시각은 </a>${serverTime}<br/>
	
	Enter your bus: 
	<input name="busname" id="inputbusname" type="text" autofocus="autofocus" ><input type="Button" class="btn btn-success" value="검색" required id="btn_submit" > <br/>
	<P>The time on the server is ${serverTime}. </P>
	

<div class="cantainer">
<span id="timetable"></span>
</div>
<div id="display"></div>
</body>
</html>