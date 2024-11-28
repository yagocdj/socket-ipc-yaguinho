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
import java.util.Date;

public class DateServer{
	public static void main(String[] args) throws Exception {
			ServerSocket sock = new ServerSocket(6013);

			System.out.println("=== Servidor iniciado ===\n");
			// escutando por conexões
			while (true) {
				Socket client = sock.accept();
				Thread thread = new Thread(() -> {
					try {
						// Se chegou aqui, foi porque algum cliente se comunicou
						System.out.println("Servidor recebeu comunicação do ip: " + client.getInetAddress() + "-" + client.getPort());
						PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
	
						// Escreve a data atual no socket
						// Yaguinho mandou para Olívia
						pout.println(new Date().toString() + " -> Olívia da Costa Oliva");
	
						InputStream in = client.getInputStream();
						BufferedReader bin = new BufferedReader(new InputStreamReader(in));
	
						String line = bin.readLine();
						System.out.println("O cliente me disse:" + line);
	
						// fechar o socket e volta no loop para escutar novas conexões
						client.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				thread.start();
			}
		}
	}
