package fbs.vey.lf6.einfuehrung_sockets;

import java.net.*;
import java.io.*;

public class KleinerServer {

	ServerSocket server = new ServerSocket(1234);
	
	KleinerServer() throws IOException {
		while(true) {
			Socket client = server.accept();
			
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			String inputLine;
			int count = 1;
			int returnVal = 0;
			
			while((inputLine = in.readLine()) != null) {
				if(inputLine.equals("Hallo Server")) {
					out.println("Hallo Client");
				} else if(inputLine.equals("Start") || inputLine.equals("J")) {
					out.println("Bitte " + count + ". Summanden schicken");
					returnVal = 0;
				} else if(isNumeric(inputLine) && count < 2) {
					returnVal += Integer.parseInt(inputLine);
					count++;
					out.println("Bitte " + count + ". Summanden schicken");
				} else if(isNumeric(inputLine) && count >=2 ) {
					returnVal += Integer.parseInt(inputLine);
					out.println("Das Ergebnis lautet: " + returnVal + "\nNochmal rechnen J/N?");
					count = 1;
				} else if(inputLine.equals("N")) {
					out.println("OK: Bye");
					break;
				} else {
					out.println("Error: Bitte " + count + ". Summanden schicken");
				}
			}
		}
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	
	public static void main(String[] args) {
		
		try {
			KleinerServer server = new KleinerServer();
		} catch (IOException e) {
			System.out.print(e);
		}
		
	}

}
