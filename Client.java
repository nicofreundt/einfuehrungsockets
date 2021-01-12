package fbs.vey.lf6.einfuehrung_sockets;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Client {
	
	Client() throws IOException {
		Socket server = new Socket( "localhost", 1234);
		
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput = "Hallo Server";
		out.println(userInput);
		System.out.println("> " + in.readLine());
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("Type something: ");
			String input = scanner.nextLine();
			
			out.println(input);
			
			String res = "";
			
			res = in.readLine();
			System.out.println("> " + res);
			if(res.contains("Ergebnis")) {
				res = in.readLine();
				System.out.println("> " + res);
			}
			
				
			if(res.equals("OK: Bye")) {
				break;
			}
			
			/*if(res.equals("OK: Bye")) {
				server.close();
				scanner.close();
				break;
			}*/
			
		}
	}

	public static void main(String[] args) {

		try {
			Client client = new Client();
		} catch (IOException e) {
			System.out.print(e);
		}
		
	}

}
