
package Main;

import Arquivos.gerenciador;
import Cliente.buscaThread;
import Cliente.cliente;
import java.io.File;
import javax.swing.JOptionPane;



public class Tela extends javax.swing.JFrame {
   
    String musicaa;
    private static String DirFile = "/home/will/Music/";
    private static gerenciador file = null;
    
    public Tela() {
        initComponents();
        setSize(640,460);
        setLocationRelativeTo(null);
        file = new gerenciador(DirFile);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ip = new javax.swing.JTextField();
        musica = new javax.swing.JTextField();
        musicabusca = new javax.swing.JLabel();
        procurar = new javax.swing.JButton();
        faixadeip = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        musicasp2p = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ip.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        getContentPane().add(ip);
        ip.setBounds(120, 190, 120, 22);

        musica.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        getContentPane().add(musica);
        musica.setBounds(120, 230, 120, 22);

        musicabusca.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        musicabusca.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        musicabusca.setText("Música:");
        getContentPane().add(musicabusca);
        musicabusca.setBounds(40, 230, 75, 20);

        procurar.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        procurar.setText("PROCURAR");
        procurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procurarActionPerformed(evt);
            }
        });
        getContentPane().add(procurar);
        procurar.setBounds(130, 270, 100, 23);

        faixadeip.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        faixadeip.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        faixadeip.setText("Faixa de IP:");
        getContentPane().add(faixadeip);
        faixadeip.setBounds(40, 190, 75, 20);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 170, 270, 10);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(20, 300, 260, 20);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(20, 160, 20, 140);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(280, 170, 10, 130);

        musicasp2p.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        musicasp2p.setText("/MUSICAS P2P/");
        getContentPane().add(musicasp2p);
        musicasp2p.setBounds(260, 40, 110, 20);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/back11.jpg"))); // NOI18N
        getContentPane().add(back);
        back.setBounds(0, 0, 624, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void procurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procurarActionPerformed
        String faixaIP = ip.getText();
        cliente a = new cliente(faixaIP);
        musicaa = musica.getText();
        a.buscarArquivo(musicaa);
        
        JOptionPane.showMessageDialog(null, "VERIFIQUE SUA PASTA DE MÚSICA");
 
    }//GEN-LAST:event_procurarActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel faixadeip;
    private javax.swing.JTextField ip;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField musica;
    private javax.swing.JLabel musicabusca;
    private javax.swing.JLabel musicasp2p;
    private javax.swing.JButton procurar;
    // End of variables declaration//GEN-END:variables
}
