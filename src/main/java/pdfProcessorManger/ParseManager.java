package pdfProcessorManger;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

public class ParseManager {
public org.json.JSONObject getRequestAsJson(HttpServletRequest request) throws IOException{
	StringBuilder sb = new StringBuilder();
    BufferedReader reader = request.getReader();
    try {
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
    } finally {
        reader.close();
    }
    System.out.println(sb.toString());
	
	//new String(Files.readAllBytes(path));
	//ocr Image
	//String path_of_file = request.get
    String req_body = sb.toString();
    org.json.JSONObject obj = null;
    try {
		obj = new org.json.JSONObject(req_body);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return obj;
}
}
