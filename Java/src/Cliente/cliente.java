package Cliente;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class cliente {

	public cliente() {
		
		
	}
	
	public ArrayList<String> listarIPs() {
		ArrayList<String> ips = new ArrayList<String>();
		for(int i = 0; i<256; i++ ) {
			ips.add("192.168.1."+i);
		}
		return ips;
	}
	
	public void buscarArquivo(String music) {
		ArrayList<String> ips = listarIPs();
		Collections.shuffle(ips);
		for (String ip : ips) {
			buscaThread buscar = new buscaThread(ip, music);
			Thread preBusca = new Thread(buscar);
			preBusca.start();
		}
	}
}
