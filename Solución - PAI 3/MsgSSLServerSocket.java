import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.net.ssl.*;

public class MsgSSLServerSocket {
	
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		try {		
			SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(3343);

			
			//KeyStore setting
			System.setProperty("javax.net.ssl.keyStore", "/home/tric0/Escritorio/PAI3/PAI_3_code/k1.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "654321");
			//Accepted Cipher suites 
			System.setProperty("javax.net.ssl.cipherSuites", "TLS_AES_256_GCM_SHA384");

			
			// wait for client connection and check login information
			
			System.err.println("Waiting for connection...");

			SSLSocket socket = (SSLSocket) serverSocket.accept();
				
			// open BufferedReader for reading data from client
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = input.readLine();

			// open PrintWriter for writing data to client
			PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				

			if (msg.equals("Hola")) {
				output.println("Welcome to the Server");
			} else {
				output.println("Incorrect message.");
			}
			output.close();
			input.close();
			socket.close();

		} // end try

		// handle exception communicating with client
		catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}

}
