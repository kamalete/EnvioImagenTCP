package EnviarImagen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	private static final int PORTO = 4940;
	private static final int TAM_BUFFER = 1000 * 64;

	public static void main(String[] args) throws Exception {
		DatagramSocket server = new DatagramSocket(PORTO);
		byte[] bufferFoto = new byte[TAM_BUFFER];
		String pathFoto = "gato.jpg";
		File foto = new File(pathFoto);
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedInputStream bufferedInputStream = null;
		DatagramPacket conexionCliente = new DatagramPacket(new byte[1], 0, 1);
		server.receive(conexionCliente);
		InetAddress address = conexionCliente.getAddress();
		int port = conexionCliente.getPort();
		int resultado;
		try {
			fileInputStream = new FileInputStream(foto);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			while ((resultado = bufferedInputStream.read(bufferFoto)) != -1) {
				DatagramPacket packet = new DatagramPacket(bufferFoto, 0, bufferFoto.length, address, port);
				server.send(packet);
			}
			fileInputStream.close();
			bufferedInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		server.close();

	}
}
