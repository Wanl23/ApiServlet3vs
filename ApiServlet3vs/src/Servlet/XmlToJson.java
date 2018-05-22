package Servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.XmlRepository;

@WebServlet ("/api/spxtags")
public class XmlToJson extends HttpServlet { 
	
	private XmlRepository xmlRepository = new XmlRepository();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	 
    	String json = xmlRepository.getSpxTags();
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
}