import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSlave extends Thread {
	
	Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ServerSlave(Socket socket) throws IOException {
		this.socket = socket;
		
		// 2: definisco input e output stream
		in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		out = new PrintWriter( new BufferedWriter (new OutputStreamWriter ( socket.getOutputStream() )), true );
		
		this.start();
		
	}
	
	
	public void run() {
		
		boolean end = false;
		
		try {
			while(!end) {
				String str = in.readLine();
				if(str.equals("SUM")) {
					str = in.readLine(); //ricezione operando 1
					int op1 = Integer.parseInt(str);
					str = in.readLine(); //ricezione operando 2
					int op2 = Integer.parseInt(str);
					
					System.out.println(op1+op2+ " risultato operazione");
					
					out.println(op1+op2+""); //invio al client il risultato dell'operazione
				}
				if (str.equals("SUB")) {
					str = in.readLine(); //ricezione operando 1
					int op1 = Integer.parseInt(str);
					str = in.readLine(); //ricezione operando 2
					int op2 = Integer.parseInt(str);
					
					System.out.println(op1-op2+" risultato operazione");
					
					out.println(op1-op2+""); //invio al client il risultato dell'operazione
				}
				if (str.equals("END")) {
					end = true;
				}
			}
		} catch (IOException e) {
			System.out.println("errore ricezione messaggio");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
