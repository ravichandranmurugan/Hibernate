package ravi.in.WebSocket.mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

import ravi.in.WebSocket.Request;

public class SocketThread extends Thread {
	private Socket socket;
	PrintWriter out;
	BufferedReader in;
	Map<String, HttpServletCustom> handlers;

	public SocketThread(Socket socket, Map<String, HttpServletCustom> handlers) {
		this.socket = socket;
		this.handlers = handlers;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//			String line = in.readLine();
//
//			while (!line.isEmpty()) {
//				System.out.println(line);
//				line = in.readLine();
//			}

			Request request = new Request(in);
			if (!request.parse()) {
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.println("HTTP/1.1 500 Internal Server Error");
				out.println("Content-Type: text/plain");
				out.println(); // \r\n
				out.println("<html><body>Cannot process your request </body></html> ");
				out.flush();
			}

			out = new PrintWriter(socket.getOutputStream());
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type:text/html ");
			out.println();

			out.println("<html><body>Current Time");
			out.println(LocalDateTime.now());
			out.println("</body></html>");
			out.flush();
		} catch (Exception e) {

		} finally {

			try {
				socket.close();
//				out.close();
//				in.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
