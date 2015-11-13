package tony.project.language.servlet;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tony.project.language.formatter.ExcelToJSON;
import tony.project.language.formatter.JSON2ArrayListFormatter;
import tony.project.language.interfaces.ExcelToJSONOM;
import tony.project.language.interfaces.JSON2ArrayFormatterOM;

@WebServlet("/handleServlet")
@MultipartConfig
public class HandleServlet extends HttpServlet {

	private ExcelToJSONOM excelToJSONOM = new ExcelToJSON();
	private JSON2ArrayFormatterOM json2ArrayFormatterOM = new JSON2ArrayListFormatter();
	private List<HashMap<String, String>> resultJSON = null;
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uploadingTag = request.getParameter("uploadTag");
		System.out.println(uploadingTag);
		
		
		
		File fold;
		InputStream fileContent = null;
		FileOutputStream fop = null;
		FileDescriptor fd = null;
		
		if(uploadingTag != null){
		
		try {

			Part a = request.getPart("file");
			String fileName = a.getSubmittedFileName();
			System.out.println(fileName);
			//excel input stream
			fileContent = a.getInputStream();
			
			
			
			System.out.println(fileContent);
			
			//Excel in JSON format
			String excel = excelToJSONOM.getJSON(fileContent);
			
			System.out.println("print excel!====="+excel);

			fold = new File("/home/tony/workspace/projectV1.4/WebContent/json/"+uploadingTag+".json");
			fop = new FileOutputStream(fold, false);

			fd = fop.getFD();
			if (!fold.exists()) {
				fold.createNewFile();
			}

			byte[] contentInByte = excel.getBytes();


			fop.write(contentInByte);
			fop.flush();
			fd.sync();
			
			//excel(String) JSON changed as a ArrayList<HashMap<String, String>>
//			resultJSON = json2ArrayFormatterOM.uploadingJSONFormat(excel);
//			System.out.println("==========="+resultJSON);
//			request.setAttribute("resultJSON", resultJSON);
			request.getRequestDispatcher("index.jsp?uploadingTag="+uploadingTag).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				fileContent.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				fop.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		
		}

	}

}
