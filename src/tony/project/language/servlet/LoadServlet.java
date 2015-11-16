package tony.project.language.servlet;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import tony.project.language.domain.Course;
import tony.project.language.domain.ScoresDetail;
import tony.project.language.formatter.JSON2ArrayListFormatter;
import tony.project.language.formatter.JSONHashMap;
import tony.project.language.interfaces.CourseDM;
import tony.project.language.interfaces.JSON2ArrayFormatterOM;
import tony.project.language.interfaces.ScoresDetailDM;

@WebServlet("*.do")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScoresDetailDM scoresDetailDM = new ScoresDetail();
	private Map<String, String> item = null;
	private List<HashMap<String, String>> resultJSON = null;
	private JSON2ArrayFormatterOM json2ArrayFormatterOM = new JSON2ArrayListFormatter();
	private CourseDM courseDM = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//
		// String levelAndcourse = (String)
		// request.getAttribute("loadingRequest");
		//
		//
		// String loadingRequest = levelAndcourse.replaceAll("\"", "");
		//
		//
		//
		//
		//
		// try {
		// loadScoreDetails(request, response, loadingRequest);
		//
		// } catch ( Exception e) {
		//
		// response.sendRedirect("table.jsp");
		//
		// }

		String servletPath = request.getServletPath();

		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);

		System.out.println("====" + methodName);

		Method method = null;

		try {
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

			method.invoke(this, request, response);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	
private void uploadFile(HttpServletRequest request, HttpServletResponse response){
		courseDM = new Course();
		ArrayList<String> courseScoresOrder = new ArrayList<>();

		
		String tag = (String)request.getAttribute("uploadingTag");
		System.out.println(tag);
		String uploadingTag = tag.replaceAll("\"", "");
		System.out.println("===="+uploadingTag+"====");
		
		File file = null;
		FileInputStream fileInputStream = null;
		
		
		
		file = new File("/home/tony/workspace/projectV1.4/WebContent/json/"+ uploadingTag+".json");
		try {
		
			fileInputStream = new FileInputStream(file);

			
			byte[] data = new byte[(int) file.length()];
			fileInputStream.read(data);
			String str = new String(data, "UTF-8");
			System.out.println(str);
			
			
			System.out.println(str);
//			resultJSON = json2ArrayFormatterOM.uploadingJSONFormat(str);
			Course loadingCourse = courseDM.loadACourse(uploadingTag);
			if(loadingCourse != null){
			
				courseScoresOrder = loadingCourse.getScoresNamesInOrder();
			} 
//			System.out.println("uploadfile resultJSON: ==="+ resultJSON);
			request.setAttribute("tableAttributes", courseScoresOrder);
			request.getRequestDispatcher("table.jsp").forward(request, response);

			
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} finally {
			
			if(fileInputStream != null){
			
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		
		
		
		
	}
	
	
	private void loadScores(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		courseDM = new Course();
		ArrayList<String> courseScoresOrder = new ArrayList<>();
		String levelAndcourse = (String) request.getAttribute("loadingRequest");

		String filterConditionValue = levelAndcourse.replaceAll("\"", "");
		String filterConditionName = "CourseCode";

		resultJSON = new ArrayList<HashMap<String, String>>();

		List<ScoresDetail> scoresDetails = scoresDetailDM.loadScoresDetails(filterConditionName, filterConditionValue);
		System.out.println("reslutList----------");

		ArrayList<HashMap<String, String>> resultJSON = resultListToJSON(scoresDetails);
		System.out.println(resultJSON);
		overwriteJSON(resultJSON.toString(), filterConditionValue);
		
		Course loadingCourse = courseDM.loadACourse(filterConditionValue);
		System.out.println("===loadingCourse:==="+loadingCourse);
		if(loadingCourse != null){
			if(loadingCourse.getScoresNamesInOrder() != null){
			courseScoresOrder = loadingCourse.getScoresNamesInOrder();
			}
		} 
		
		System.out.println("courseScoresOrder: ====== "+ (courseScoresOrder == null));
//		request.setAttribute("resultJSON", resultJSON);
		request.setAttribute("tableAttributes", courseScoresOrder);
		request.getRequestDispatcher("table.jsp").forward(request, response);
		System.out.println(resultJSON);

	}

	private ArrayList<HashMap<String, String>> resultListToJSON(List<ScoresDetail> resultList) {

		Iterator<ScoresDetail> listIterator = resultList.iterator();
		System.out.println("reslutList----------");

		while (listIterator.hasNext()) {
			item = new JSONHashMap<>();

			ScoresDetail s = listIterator.next();
			item.put("StudentID", s.getStudentID().toString());
			item.put("CourseCode", s.getCourseCode());
			item.put("FirstName", s.getFirstName());
			item.put("Surname", s.getSurname());

			Iterator g = s.getGrades().entrySet().iterator();
			while (g.hasNext()) {

				Map.Entry<String, String> gMap = (Entry<String, String>) g.next();
				item.put(gMap.getKey(), gMap.getValue());

			}

			resultJSON.add((HashMap<String, String>) item);
		}

		return (ArrayList<HashMap<String, String>>) resultJSON;
	}

	private void overwriteJSON(String resultJSON, String loadingRequest) {

		System.out.println("/home/tony/workspace/projectV1.4/WebContent/json/" + loadingRequest + ".json");
		File fold = null;
		FileOutputStream fop = null;
		FileDescriptor fd = null;
		System.out.println("Overwriting: " + resultJSON);
		try {
			fold = new File("/home/tony/workspace/projectV1.4/WebContent/json/" + loadingRequest + ".json");
			fop = new FileOutputStream(fold, false);
			fd = fop.getFD();

			if (!fold.exists()) {
				fold.createNewFile();
			}

			byte[] contentInByte = resultJSON.getBytes();
			fop.write(contentInByte);
			fop.flush();
			fd.sync();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fop != null) {
				try {
					fop.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
