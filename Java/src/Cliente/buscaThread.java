package Cliente;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class buscaThread {
	public Socket socket = null;
	public String text2Search = null;
	public String dirTosave = "/home/will/Music/save/";
	
	public buscaThread(Socket socket, String Buscar){
		this.socket = socket;
		text2Search = Buscar;
	}
	
	public Runnable run() {
		try {
			//Criando um objetos para comunicação
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
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
				return null;
			}else {
				//Caso o peso não seja -1 o cliente vai
				//receber o nome do arquivo
				String nomeArquivo = (String)ois.readObject();
				
				//receber arquivo
				int bytesRead, current=0;
				byte [] mybytearray  = new byte [peso];
			    fos = new FileOutputStream(dirTosave+nomeArquivo);
			    bos = new BufferedOutputStream(fos);
			    bytesRead = is.read(mybytearray,0,mybytearray.length);
			    current = bytesRead;
			    do {
			    	bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
			    	if(bytesRead >= 0){ 
			    		current += bytesRead;
			    	}
			   } while(bytesRead > -1);
			   bos.write(mybytearray, 0 , current);
			   bos.flush();
			   System.out.println("Arquivo " + nomeArquivo + " Transferido (" + current + " bytes)");
			   
			   fos.close();
			   bos.close();
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
