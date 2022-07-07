import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static final int PORT = 8080;
	ServerSocket ss;
	
	
	public Server() throws IOException {
		ss = new ServerSocket(PORT);
		System.out.println("server created");
	}
	
	private void exec() {
			
		try {
			while(true) {
				Socket socket;
				socket = ss.accept();
				try { new ServerSlave (socket); }
				catch(IOException e) { socket.close(); }
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			try {
				ss.close();
			} catch (IOException e) {
				System.out.println("failed to close server socket");
			}
		}
	}

	public static void main(String[] args) throws IOException {
	/* Si realizzi un programma client-server che permette a diversi client di chiedere al server di svolgere operazioni matematiche per lui. */ 
		new Server().exec();
	}

}
