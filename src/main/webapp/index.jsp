<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    %>
    
<%@ page import="java.util.List" %>  
<%@ page import="com.tom.Problem" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fraction Math</title>
<meta name="description" content="Web page performs fraction math calculations and shows all the steps needed to calculate
the answer."/>
<meta name="keywords" content="fraction,math,calculator,show steps,fraction addition, fraction subtraction,
fraction multiplication, fraction division, homework help, elementary math"/>
<script language="JavaScript">
function switchid(id){	
	var elementId = document.getElementById(id);
	if (elementId.style.display=='none') {
		elementId.style.display='block';
	} else {
		elementId.style.display='none';
	}
}
</script>
</head>
<body>
<font size="+1">
<center>
<h1>Fraction Math</h1>
<form name="mathProblem" method="get" action="/mathweb">
<% String mathProb = (String)request.getParameter("mathProb"); %>
<input type="text" name="mathProb" id="mathProb" value="<%= mathProb == null ? "" : mathProb %>"/ size="55" maxsize="55">
<script>document.getElementById('searchArg').focus()</script>
<input type="submit" value="Solve" class="button" />
&nbsp;<a href="javascript:switchid('s1')" style="font-size:smaller">What is this?</a>
</form>
</center>
<p>
<table width=100%>
<tr>
<td width=30%>&nbsp;</td>
<td width=40%>
<div id="s1" style="display:none;font-size:smaller;font-style:italic;">
This web page performs fraction math and shows all the steps needed to calculate
the answer.  Equations are entered using spaces and operation signs. For example:
3 1/2 + 4 3/4.  Please note the space between the whole number and the fraction in
the mixed numbers.  Supported operations include addition (+), subtraction (-),
multiplication (*) and division (/).
</div>
<%
Problem problem = (Problem)request.getAttribute("problem");
if (problem == null) {
	if (mathProb != null) {
%>
	<center>Sorry, I couldn't figure-out that problem.</center>
<%		
	}
} else {
%>
<hr />
<ol>
<%	
for (String str : problem.getSteps()) {
%>
<li><%= str %>
<%	
}}
%>
</ol>
</td>
<td width=30%>&nbsp;</td>
</tr>
</table>
</font>
</body>
</html>