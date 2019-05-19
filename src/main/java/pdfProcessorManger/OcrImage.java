package pdfProcessorManger;

import java.io.BufferedReader;
import java.io.File;

/*
 * "req:"{
		"Token":"",
		"image":"image_to_string"
	 }

"res":{
	"imageData":"OCR Data.......asdsadjahjdjasdh"
}*/

import java.io.IOException;
import java.nio.file.Files;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONObject;
/**
 * Servlet implementation class OcrImage
 */
public class OcrImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OcrImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Enumeration<String> headerNames = request.getHeaderNames();
		 while (headerNames.hasMoreElements()) {
			 String headerName = headerNames.nextElement();
			 System.out.println(headerName +":"+request.getHeader(headerName));
		 }
		 //Validatiion
		 String clinet_token = request.getHeader("token");
		
	    org.json.JSONObject obj = null;
	    try {
			obj = new org.json.JSONObject(new ParseManager().getRequestAsJson(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //Authenticate A Request
	  //Connect to oracle
	    //Check username and password
	    //If authenticated Proceed else send 404 Error back
	    
	    if(clinet_token == null ) {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 response.getWriter().append("{\"msg\":\" " +"UnAuthorized" + "\"}");
			 return;
		 }
		//Vlidate Client_token
	    boolean validUser =  jwtGenerateManager.getInstance().verify(clinet_token);
	    if(!validUser) {
	    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 response.getWriter().append("{\"msg\":\" " +"UnAuthorized" + "\"}");
			 return;
	    }
	    
		OcrEngine1 img = new OcrEngine1();
		String ocrData = "";
		try {
			ocrData = img.getSampleOCR(obj.getString("path"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Send Response bvack with OCR data
		response.addHeader("Content-Type", "application/json");
		String value =   JSONObject.escape(ocrData);
		String json =  "{\"ocrData\":\" " +value + "\"}";
		System.out.println(json);
		response.getWriter().append(json);
	}

}
