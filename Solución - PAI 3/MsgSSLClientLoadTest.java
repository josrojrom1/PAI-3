import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;


public class MsgSSLClientLoadTest {

    public static void main(String[] args) {
        int numClients = 300;

        // Crear y ejecutar los 300 hilos
        for (int i = 0; i < numClients; i++) {
            new ClientThread().start();
        }
    }

    private static class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                // Configurar el cliente SSL
                SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 3343);

               // create BufferedReader for reading server response
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // create PrintWriter for sending login to server
                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                // prompt user for user name
                //String msg = "Hola";
                String msg = "Hola";
                // send user name to server
                output.println(msg);

                output.flush();

                // read response from server
                String response = input.readLine();

                // display response to user
                System.out.println("OK");
        		//JOptionPane.showMessageDialog(null, response);


                // clean up streams and Socket
                output.close();
                input.close();
                socket.close();

            }   

            catch (IOException e) {
                e.printStackTrace();
            }
            // exit application

            finally {
                System.exit(0);
            }
            
        }
    }
}
