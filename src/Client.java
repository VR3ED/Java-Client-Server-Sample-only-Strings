import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client{
	
	String nome;
	String operazione;
	InetAddress IP;
	Socket socket; 
	
	BufferedReader in;
	PrintWriter out;
	
	public Client(String nome, String operazione ) throws UnknownHostException {
		IP = InetAddress.getByName(null);
		this.nome = nome;
		this.operazione = operazione;
	}
	
	public void exec() throws IOException, InterruptedException{
		
		try {
			socket = new Socket(IP, 8080);
			
			in = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
			
			int op1 = (new Random()).nextInt(10);
			int op2 = (new Random()).nextInt(10);
			String risultato;
			
			out.println(operazione);
			//Thread.sleep(100);
			out.println(op1+"");
			out.println(op2+"");
			
			System.out.println("il server ritorna:" + in.readLine() + "per la richiesta di operazione: " + operazione + "tra: " + op1 + "," + op2);
			
		} catch (IOException e) {
			System.out.print("errore");
			socket.close();
		}
		
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new Client("marco", "SUB").exec();
		new Client("claudio", "SUM").exec();
		new Client("claudio", "END").exec();
	}
}
