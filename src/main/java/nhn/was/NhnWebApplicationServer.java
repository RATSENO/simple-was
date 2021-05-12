package nhn.was;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nhn.util.SettingUtil;

public class NhnWebApplicationServer implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(NhnWebApplicationServer.class);
	
	public void run() {
		ExecutorService executorService = Executors.newFixedThreadPool(SettingUtil.getClientThreadPoolCount().intValue());
		try {
			ServerSocket serverSocket = new ServerSocket(SettingUtil.getServerPort().intValue());
			log.info("Accepting connections on port " + serverSocket.getLocalPort());
			log.info("Document Root: " + SettingUtil.getRootDirectoryName());
			
            Socket request = serverSocket.accept();
            InputStreamReader in = new InputStreamReader(new BufferedInputStream(request.getInputStream()), "UTF-8");
            OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(request.getOutputStream()));
            
            StringBuilder requestLine = new StringBuilder();
            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n')
                    break;
                requestLine.append((char) c);
            }
            
            String requestInfo = requestLine.toString();
            String[] tokens = requestInfo.split("\\s+");
            String method = tokens[0];
            String version = "";
            if (method.equals("GET")) {
            	String fileName = tokens[1];
            	//if(fileName.endsWith("/")) {
            	//	fileName += "index.html";
            	//}
            	
            	//String contentType = URLConnection.getFileNameMap().getContentTypeFor("index.html");
            	//if (tokens.length > 2) {
                //    version = tokens[2];
                //}
            	
                String body = new StringBuilder("<HTML>\r\n")
                        .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                        .append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                        .append("</BODY></HTML>\r\n")
                        .toString();
                if (version.startsWith("HTTP/")) { // send a MIME header
                    sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
                }
                out.write(body);
                out.flush();
                
            }else {
                // method does not equal "GET"
                String body = new StringBuilder("<HTML>\r\n")
                		.append("<HEAD><TITLE>Not Implemented</TITLE>\r\n")
                		.append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
                        .append("</BODY></HTML>\r\n").toString();
                if (version.startsWith("HTTP/")) { // send a MIME header
                    sendHeader(out, 
                    		"HTTP/1.0 501 Not Implemented",
                            "text/html; charset=utf-8", 
                            body.length());
                }
                out.write(body);
                out.flush();            	
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    private void sendHeader(Writer out, String responseCode, String contentType, int length) throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }
}
