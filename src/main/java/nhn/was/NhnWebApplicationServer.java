package nhn.was;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nhn.util.SettingUtil;

public class NhnWebApplicationServer implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(NhnWebApplicationServer.class);
	
	public void run() {
		ExecutorService executorService = Executors.newFixedThreadPool(SettingUtil.getClientThreadPoolCount().intValue());
		try(ServerSocket serverSocket = new ServerSocket(SettingUtil.getServerPort().intValue())){
			log.info("Accepting connections on port " + serverSocket.getLocalPort());
			log.info("Document Root: " + SettingUtil.getRootDirectoryName());
			while(true) {
				Socket socket = serverSocket.accept();
				Runnable runnable = new RequestProcessor(socket);
				executorService.submit(runnable);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
