package com.example.will.p2pandroid.Servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.example.will.p2pandroid.Servidor.ServerThread;
import com.example.will.p2pandroid.Arquivos.gerenciador;

public class server extends Thread{

    public gerenciador geren = null;

    public server(gerenciador ger) {
        geren = ger;
    }

    public void run() {
        try {
            @SuppressWarnings("resource")
            ServerSocket servidor = new ServerSocket(8000);
            System.out.println("[Servidor] Ouvindo a porta 8000");
            ArrayList<Thread> slotesThread = new ArrayList<Thread>();

            while(true){
                //Aguarda os pedidos de conexão
                Socket cliente = servidor.accept();
                //Verifica se a lista de threads está vazia
                if(slotesThread.isEmpty()){
                    //Se estiver ele cria uma nova thread
                    ServerThread novo = new ServerThread(cliente, geren);
                    Thread c1 = new Thread(novo);
                    c1.start();
                    slotesThread.add(c1);
                }
                else{
                    //se já existir algum elemento na lista de threads ele
                    //verificará se existe alguma thread morta
                    int adicionado = 0;
                    for(int i =0; i<slotesThread.size(); i++){
                        //Se encontrar uma thread mortar e a nova ainda não tiver sido
                        //adicionada, ele coloca a nova thread no lugar da morta
                        if(!slotesThread.get(i).isAlive() && adicionado == 0){

                            ServerThread novo = new ServerThread(cliente, geren);
                            Thread c1 = new Thread(novo);
                            c1.start();
                            slotesThread.set(i, c1);
                            adicionado = 1;
                        }
                    }
                    //Se não encontrou nunhuma thread morta, ele adiciona mais uma no final da lista
                    if(adicionado == 0){
                        //Adiciona a nova conexão ao arraylist
                        ServerThread novo = new ServerThread(cliente, geren);
                        Thread c1 = new Thread(novo);
                        c1.start();
                        slotesThread.add(c1);
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
