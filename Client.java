package EnviarImagen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 4940;
	private static final int TAM_BUFFER = 1000 * 64;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket client = null;
		try {
			byte[] buffer = new byte[TAM_BUFFER];
			InetAddress address = InetAddress.getByName(HOST);
			String pathFoto = "F:\\gateoe.jpg";
			File foto = new File(pathFoto);
			client = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(new byte[1], 0, 1, address, PORT);
			client.send(packet);
			FileOutputStream fos = new FileOutputStream(foto);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int res;

			while (true) {
				DatagramPacket packet2 = new DatagramPacket(buffer, 0, buffer.length);
				client.receive(packet2);
				bos.write(packet2.getData());
				System.out.println(" -- Datagrama enviado por el servidor");
				try {
					bos.close();
					fos.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		client.close();
	}

}
