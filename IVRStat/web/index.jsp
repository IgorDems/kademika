<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="ks.db.OracleConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ks.utils.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ks.common.factory.MenuFactory"%>
<%@page import="ks.common.factory.ChartFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="res/css/style.css" rel="stylesheet">
<script type="text/javascript" src="res/js/change.js"></script>
<link type="text/css" rel="stylesheet" href="res/css/jscal2.css" />
<link type="text/css" rel="stylesheet" href="res/css/border-radius.css" />
<script src="res/js/jscal2.js"></script>
<script src="res/js/lang/ru.js"></script>
<title>Статистика IVR</title>
<script type="text/javascript">

	<% if(request.getParameter("type") == null){%>
	changeType(1);
	<%}%>

	function checkTimes(){
		var value = <%=request.getParameter("from") != null ? ("'" + request.getParameter("from") + "'") : ("'" + Constants.getStartDate() + "'")%>;
		
		if(value != "-1"){
		 	var SelectObject = document.getElementById("time_form").date_from;
		 	SelectObject.value = value;
			//for(index = 0; index < SelectObject.length; index++) {
			//	if(SelectObject[index].value == value)
			//   	SelectObject.selectedIndex = index;
			//}
		}
		value = <%=request.getParameter("to") != null ? ("'" + request.getParameter("to") + "'") : ("'" + Constants.getEndDate() + "'")%>;
		
		if(value != "-1"){
		 	var SelectObject = document.getElementById("time_form").date_to;
		 	SelectObject.value = value;
		 	
		}

		value = <%=request.getParameter("grouping") != null ? "'" + request.getParameter("grouping") + "'" : "-1"%>;
		if(value != "-1"){
		 	var SelectObject = document.getElementById("time_form").grouping;
		 	for(index = 0; index < SelectObject.length; index++) {
				if(SelectObject[index].value == value)
			   	SelectObject.selectedIndex = index;
			}
		}
	}
</script>
</head>
<body>
<div id="container">
<div id="header">
<div id="logo"></div>
<div id="top_menu">
<div style="float: left; margin-right: 5px;"><a href="<%=Constants.getUrl(request)%>">Информация по серверам</a></div>
<div style="float: left; margin-left: 5px;"><a href="<%=Constants.getUrl(request)%>direction.jsp">Информация по направлениям</a></div>
</div>
</div>
<div id="test_debug"></div>

<% 
	//try
	//{
	//	Connection cn = OracleConnection.getInstanse().getConnection();
		
	//	java.sql.Statement statement = cn.createStatement();
	//	java.sql.ResultSet rset = statement.executeQuery("select sysdate from dual");
		
	//	out.write(rset.getString(1));
	//}
	//catch(Exception e)
	//{
	//	out.write(e.getMessage());
	//}

%>

<div id="navigate">
<dl><%int index = 0; ArrayList <String[]> notActiveButtons = new ArrayList<String[]>();
ArrayList <String[]> buttons = MenuFactory.proccessRequest(request); if(buttons.size() > 0){%><dt><div class="menu_section">Активные сервера</div></dt><%} for(String [] button : buttons){%>
<%if (button[1].equals("1")){ if(index == 0){ index = 1;%><dd><div class="menu_row1" onclick="javascript:changeSelect(<%="'"+ button[0]+"'"%>);"><%=button[0]%></div></dd>
<%} else { index = 0; %><dd><div class="menu_row2" onclick="javascript:changeSelect(<%="'"+ button[0]+"'"%>);"><%=button[0]%></div></dd><%}
} else {notActiveButtons.add(notActiveButtons.size(), button);}} %><%if(notActiveButtons.size() > 0) { index = 0; %><dt><div class="menu_section">Не активные сервера</div></dt><%for(String [] button : notActiveButtons){
if(index == 0){ index = 1; %><dd><div class="menu_row1" onclick="javascript:changeSelect(<%="'"+ button[0]+"'"%>);"><%=button[0]%></div></dd>
<%} else {index = 0;%><dd><div class="menu_row2" onclick="javascript:changeSelect(<%="'"+ button[0]+"'"%>);"><%=button[0]%></div></dd><%}
}}%></dl></div>
<div id="content">
<%if (request.getParameter("type") != null && request.getParameter("name") != null) {%><div id="time_selector">
<form id="time_form" name="time_form" action="javascript:updateStat();">
<div style="float: left; width: 80px">Начало&nbsp;:&nbsp;&nbsp;&nbsp;</div>
<input id="date_from" name="date_from" style="width: 150px; float: left;"/>
<button type="button" id="date_from_trigger" style="width: 20px; float: left;">...</button>
<script type="text/javascript">
  new Calendar({
          inputField: "date_from",
          dateFormat: "%d.%m.%Y/%H:%M",
          trigger: "date_from_trigger",
          bottomBar: false,
          showTime:true,	
          titleFormat:"%B %Y",
          max: new Date(),
          onSelect: function() {
                  var date = Calendar.intToDate(this.selection.get());
                  LEFT_CAL.args.max = date;
                  LEFT_CAL.redraw();
                  this.hide();
          }
  });
</script>
<div style="float: left; width: 80px">Конец&nbsp;:&nbsp;&nbsp;&nbsp;</div>
<select id="grouping">
	<option selected value="1">По 1 мин</option>
	<option value="1">По 1 мин</option>
	<option value="2">По 1 часу</option>
	<option value="3">По 1 дню</option>
	<option value="4">По неделе</option>
	<option value="5">По месяцу</option>
</select>
<input id="date_to" name="date_to" style="width: 150px; float: left;"/>
<button type="button" id="date_to_trigger" style="width: 20px; float: left;">...</button>
<script type="text/javascript">
  var time_From = new Calendar({
          inputField: "date_to",
          dateFormat: "%d.%m.%Y/%H:%M",
          trigger: "date_to_trigger",
          bottomBar: false,
          showTime:true,
          titleFormat:"%B %Y",
          max: new Date(),
          onSelect: function() {
                  var date = Calendar.intToDate(this.selection.get());
                  LEFT_CAL.args.max = date;
                  LEFT_CAL.redraw();
                  this.hide();
          }
  });
  checkTimes();
</script>
<div style="float: left; width: 100px">Групировка&nbsp;:&nbsp;&nbsp;&nbsp;</div>
 <input type="submit" value="Обновить"></form>
</div>
<%if (request.getParameter("from") != null && request.getParameter("to") != null) { for(String trunkName : ChartFactory.proccessRequest(request)){%><div class="stat">
<img src="res/loading.gif" onload="javascript:getImage(this, '<%=Constants.getUrl(request)%>statistic<%=Constants.getParams(request, trunkName)%>');" width="235" height="235" border=0 alt="">
	</div>
<%}}%> <%}else {%>Выберите необходимый параметр в боковом меню<%}%>
</div>
</div>
</body>