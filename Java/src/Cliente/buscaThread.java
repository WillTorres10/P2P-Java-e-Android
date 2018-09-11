package Cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class buscaThread extends Thread{
	public String ip = null;
	public String text2Search = null;
	public String dirTosave = "/home/will/Music/";
	
	public buscaThread(String ip, String Buscar){
		this.ip = ip;
		text2Search = Buscar;
	}
	
	public void run() {
		try {
			//Criando um objetos para comunicação
			@SuppressWarnings("resource")
			Socket socket = new Socket(ip, 8000);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is;
			is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			//Mandando Pedido
			oos.writeObject(text2Search);
			int peso = (int)ois.readObject();
			//Quando o servidor retorna o peso -1 é por que ele não tem o arquivo
			//como o servidor não tem o arquivo, o cliente fechará a thread
			if(peso == -1) {
				os.close();
				oos.close();
				is.close();
			}else {
				//Caso o peso não seja -1 o cliente vai
				//receber o nome do arquivo
				
				File filename = new File(dirTosave+text2Search); 
				System.out.println("[Cliente] Recebendo arquivo: " + text2Search);
		        FileOutputStream output = new FileOutputStream(filename);
		        BufferedOutputStream b_output = new BufferedOutputStream(output);
		        BufferedInputStream input = new BufferedInputStream(socket.getInputStream());  
		        int len;
		        while((len = input.read()) != -1) {
		        	b_output.write(len);
		        }
		        input.close();
		        b_output.close();
		        System.out.println("[Cliente] Arquivo: " + text2Search + ". Recebido com sucesso");
				
				
				
				
			}
		}catch(Exception e) {
			Thread.currentThread().interrupt();
		}
	}
}
