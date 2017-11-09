package remoteDriver;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import net.sourceforge.jFuzzyLogic.FIS;

 
public class RemoteDriver {
	
	static int port = 4321;
	static String host = "localhost";
	
    public static void main(String[] args) throws IOException {
        	    	
        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            kkSocket = new Socket(host, port);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:"  + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
 
        double x, y;
        double angle;
        
        /* importando o arquivo de regras Fuzzy*/
        String fileName = "res/fuzzy.fcl";
		FIS fz = FIS.load(fileName, true);

		if (fz== null) { // Error while loading?
			System.err.println("Erro ao ler o arquivo fcl");
			return;
		}

		/* Requisição da posição do caminhão */
		out.println("r");

		/* Loop de controle */ 	
		int passos = 1; 
		while ((fromServer = in.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(fromServer);
			x = Double.valueOf(st.nextToken()).doubleValue();
			y = Double.valueOf(st.nextToken()).doubleValue();
			angle = Double.valueOf(st.nextToken()).doubleValue();
		
			System.out.println("Pos X: " + x + " - Pos Y: " + y + " - Ângulo volante: " + angle);

			fz.setVariable("eixo_x", x);
			fz.setVariable("eixo_y", y);
			fz.setVariable("truck_angle", angle);

			fz.evaluate();

			double volante = fz.getVariable("angulo_volante").defuzzify();
			System.out.println("Angulo Calculado do Volante: " + volante + "\n");

			/* Envio dos dados para o o caminhão */
			out.println(volante);
			out.println("r");
			
			passos = passos +1;
		}
 
        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
        System.out.println("Execução terminada em " + passos + " passos");
    }
}