package tony.project.language.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tony.project.language.domain.ScoresDetail;
import tony.project.language.interfaces.ScoresDetailDM;



@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ScoresDetailDM scoresDetailDM = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		scoresDetailDM = new ScoresDetail();
		String jsonStr = request.getParameter("tableData");
		String courseCode = request.getParameter("courseCode");
		System.out.println(jsonStr);
		System.out.println(courseCode);
		
		List<ScoresDetail> scores = scoresDetailDM.getScoresDetailFromJSON(jsonStr);
		System.out.println("scores are saving ===== "+scores);
		scoresDetailDM.batchSaveScoresDetail(scores, courseCode);
		
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("Success!");
		String uploadingTag = courseCode;
	    request.getRequestDispatcher("index.jsp?uploadingTag="+uploadingTag).forward(request, response);
		
	
	
	}

}
