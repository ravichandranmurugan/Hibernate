package ravi.in.WebSocket.mypackage;

public abstract class HttpServletCustom {

	public void init() {
		System.out.println("httpservlet init");
	}

	public void service() {
		System.out.println("http service");
	}

	public void doget() {
		System.out.println("http get");
	}

	public void doPost() {
		System.out.println("http post");
	}
}