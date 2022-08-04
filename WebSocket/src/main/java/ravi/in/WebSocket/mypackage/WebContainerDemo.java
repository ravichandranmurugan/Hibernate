package ravi.in.WebSocket.mypackage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WebContainerDemo {

	private int port = 0;
	private String configProp;
	private Map<String, HttpServletCustom> handlers = new HashMap<>();

	public WebContainerDemo(int port, String Configprop) {
		this.port = port;
		this.configProp = Configprop;
	}

	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(this.port);

		while (true) {
			Socket socket = serverSocket.accept();
			Thread socketThread = new SocketThread(socket,handlers);

			socketThread.start();
		}
	}

	public void loadProperties() throws IOException {
		InputStream i = getClass().getClassLoader().getResourceAsStream(configProp);
		if (i == null) {
			throw new RuntimeException("Config properties not found" + configProp);
		}
		Properties prop = new Properties();
		prop.load(i);
		prop.forEach((key, value) -> {
			HttpServletCustom servlet =  getInstance((String)value);
			servlet.init();
			handlers.put((String)key,servlet);
		});
	}

	public HttpServletCustom getInstance(String className) {
		try {
			return (HttpServletCustom) Class.forName(className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebContainerDemo wcd = new WebContainerDemo(8008, "config.properties");
		wcd.loadProperties();
		wcd.handlers.forEach((key,HttpServletCustom)->{
			System.out.println(key);
			HttpServletCustom.doget();	
		});
		wcd.start();
	}

}
