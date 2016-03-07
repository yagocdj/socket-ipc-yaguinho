package com.gugawag.so.ipc;

/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.21
 *
 * @author Silberschatz, Gagne, and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013.
 */
import java.net.*;
import java.io.*;

public class DateServer{
	public static void main(String[] args)  {
		try {
			ServerSocket sock = new ServerSocket(6013);

			System.out.println("=== Servidor iniciado ===\n");
			// escutando por conexões
			while (true) {
				Socket client = sock.accept();
				// Se chegou aqui, foi porque algum cliente se comunicou
				System.out.println("Servidor recebeu comunicação.");
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

				// Escreve a data atual no socket
				pout.println(new java.util.Date().toString());

				// fechar o socket e volta no loop para escutar novas conexões
				client.close();
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
