package com.example.will.p2pandroid.Cliente;

import android.os.Environment;
import android.widget.TextView;
import com.example.will.p2pandroid.Arquivos.gerenciador;
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
    public String dirTosave = Environment.getExternalStorageDirectory().toString()+"/Download/";
    public TextView status;

    public buscaThread(String ip, String Buscar, TextView status){
        this.ip = ip;
        this.status = status;
        text2Search = Buscar;
    }

    public void run() {
        try {
            //Criando um objetos para comunicação
            Socket socket = new Socket(ip, 8000);
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

            }else {
                //Caso o peso não seja -1 o cliente vai
                //receber o nome do arquivo
                gerenciador a = new gerenciador(dirTosave);
                File filename = new File(dirTosave + text2Search);
                status.setText("[Cliente] Recebendo arquivo: " + text2Search);
                FileOutputStream output = new FileOutputStream(filename);
                BufferedOutputStream b_output = new BufferedOutputStream(output);
                BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
                int len;
                while ((len = input.read()) != -1) {
                    b_output.write(len);
                }
                input.close();
                b_output.close();

                status.setText("Arquivo: " + text2Search + ". Recebido com sucesso");

                os.close();
                oos.close();
                is.close();
            }
        }catch(Exception e) {
        }
        Thread.currentThread().interrupt();
    }

    public void destroy(){
        System.exit( 0 );
    }
}
