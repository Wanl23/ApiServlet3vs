package Servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.activation.Repository;

import Repository.xmlRepository;

@WebServlet ("/api/spxtags")
public class xmlToJson extends HttpServlet {

    xmlRepository xmlRep = new xmlRepository();    
    Map<String, String> json = null;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	//here will code witch should send json to client
    	    	
//    	try {
//			json = xmlRep.getMap();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	//PrintWriter out = response.getWriter();
//    	for(Map.Entry<String, String> entry : json.entrySet()) 
//    	{
//    		System.out.println(entry.getKey() + "=" + entry.getValue());
//    	}
//    	//out.close();
    	
 
        // 6. Send List<Article> as JSON to client
        //json.writeValue(response.getOutputStream());
    }
}