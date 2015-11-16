<%@page import="java.util.List"%>
<%@page import="tony.project.language.formatter.JSONHashMap"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Level 1 Grammar</title>


<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet"
	href="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/css/bootstrap-editable.css">
<script src="assets/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/bootstrap-table/src/bootstrap-table.js"></script>
<script
	src="assets/bootstrap-table/src/extensions/editable/bootstrap-table-editable.js"></script>
<script
	src="//rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script
	src="assets/bootstrap-table/src/extensions/export/bootstrap-table-export.js"></script>
<script
	src="//rawgit.com/hhurz/tableExport.jquery.plugin/master/tableExport.js"></script>
<script src="js/ga.js"></script>

</head>
<body>

	<%
		String uploadingTag = request.getParameter("uploadingTag");
	//	System.out.println(uploadingTag);
		String term = request.getParameter("t");
	//	System.out.println(term);
		String level = request.getParameter("l");
	//	System.out.println(level);
		String course = request.getParameter("c");
	//	System.out.println(course);
		String loadingRequest = term + level + course;

		List<String> attributeNames = null;

	//	ArrayList<HashMap<String, String>> resultJSON = (ArrayList<HashMap<String, String>>) request
	//			.getAttribute("resultJSON");
	
	ArrayList<String> tableAttributes = (ArrayList<String>)request.getAttribute("tableAttributes");
	
		System.out.println(tableAttributes == null);
		if (level != null && course != null && tableAttributes == null && term != null && uploadingTag == null) {
			//System.out.println(level+course);
			//try{
			System.out.println("No resultJSON and Saving!------");
			
			request.setAttribute("loadingRequest", loadingRequest);
			request.getRequestDispatcher("/loadScores.do").forward(request, response);

			//System.out.println(json.size());
			//} catch(NullPointerException e) {
			//	e.printStackTrace();
			//}


		}

		if (tableAttributes != null & level != null && course != null && term != null && uploadingTag == null) {

			System.out.println("No savingTag!!------");
		//	System.out.println(resultJSON);
		//	System.out.println(resultJSON.size());

			if (!tableAttributes.isEmpty()) {
			/*	HashMap<String, String> item = resultJSON.get(0);

				attributeNames = new ArrayList<String>();

				for (String key : item.keySet()) {
					if (!key.equals("FirstName") && 
							!key.equals("Surname") && 
							!key.equals("StudentID") && 
							!key.equals("FinalMarks") &&
							!key.equals("CourseCode")) {
						attributeNames.add(key);
					}

				}*/
				attributeNames = new ArrayList<String>();
				for(String key : tableAttributes){
					if (!key.equals("FirstName") && 
							!key.equals("Surname") && 
							!key.equals("StudentID") && 
							!key.equals("FinalMarks") && 
							!key.equals("CourseCode")) {
						attributeNames.add(key);
					}
				}
				
				

			} else {

				loadingRequest = "empty";
			}

		}

		if (uploadingTag != null && tableAttributes == null) {
			System.out.println("SavingTag is Here --------");
		//	System.out.println("------attributeNames: " + (attributeNames == null));

			request.setAttribute("uploadingTag", uploadingTag);
			request.getRequestDispatcher("/uploadFile.do").forward(request, response);

		}

		if (uploadingTag != null && tableAttributes != null) {
			System.out.println(uploadingTag + "====");
			System.out.println("uploadingTag and resultJSON!!!====");
			loadingRequest = uploadingTag;

			if (!tableAttributes.isEmpty()) {
				/*HashMap<String, String> item = resultJSON.get(0);

				attributeNames = new ArrayList<String>();

				for (String key : item.keySet()) {
					if (!key.equals("FirstName") && 
							!key.equals("Surname") && 
							!key.equals("StudentID") && 
							!key.equals("FinalMarks") && 
							!key.equals("CourseCode")) {
						attributeNames.add(key);
					}
					
					

				}*/
				attributeNames = new ArrayList<String>();
				for(String key : tableAttributes){
					if (!key.equals("FirstName") && 
							!key.equals("Surname") && 
							!key.equals("StudentID") && 
							!key.equals("FinalMarks") && 
							!key.equals("CourseCode")) {
						attributeNames.add(key);
					}
				}
				
				
				
					
			} else {
				loadingRequest = "empty";
			}

		}
	%>


	<table id="table" data-toggle="table"
		data-url="json/<%=loadingRequest%>.json" data-pagination="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-toolbar="#toolbar"
		data-show-refresh="true" data-show-columns="true"
		data-minimum-count-columns="2" data-show-export="true"
		data-search="true">

		<thead>
			<tr>
				<th data-field="StudentID" data-sortable="true">StudentID</th>
				<th data-field="FirstName" data-editable="true" data-sortable="true">First
					Name</th>
				<th data-field="Surname" data-editable="true" data-sortable="true">Surname</th>
				<%
					if (attributeNames != null) {
						for (int i = 0; i < attributeNames.size(); i++) {
				%>
				<th data-field="<%=attributeNames.get(i)%>" data-editable="true"
					data-sortable="true"><%=attributeNames.get(i)%></th>


				<%
					}
					}
				%>
				<th data-field="FinalMarks" data-editable="true"
					data-sortable="true">FinalMarks</th>
				<th data-field="CourseCode" data-editable="false"
					data-sortable="false">CourseCode</th>

			</tr>
		</thead>
	</table>
	<div class="btn-group btn-group-lg pull-right" role="group"
		aria-label="Large button group" id="save">
		<button type="button" class="btn btn-success ">Save</button>
	</div>


	<%
		if (loadingRequest != null) {
	%>
	<!-- change "data-url" depending on different uploadTag or checkTag  -->
	<script type="text/javascript">
		$(document).ready(function(){
	
		$('table').attr("data-url","json/<%=loadingRequest%>.json");

		});
	</script>

	<%
		}
	%>

	<!-- Pass table data into back-end -->
	<script>
		var $table = $('#table'), $button = $('#save');

		$(function() {
			$button.click(function() {
				alert("Testing!");
				alert("<%= loadingRequest%>");
				alert(JSON.stringify($table.bootstrapTable('getData')));
				$.ajax({
					url:'saveServlet',
					type:'POST',
					dataType: 'json',
					data:{'tableData': JSON.stringify($table.bootstrapTable('getData')), 'courseCode': "<%= loadingRequest%>"},
					success: function(data){
						alert(data);
						
					}
					
					
				});

				/*$.get('saveServlet', {
					tableData : JSON
							.stringify($table.bootstrapTable('getData'))
				}, function(responseText) {
					alert(responseText);

				});*/

			});
		});
	</script>

</body>
</html>