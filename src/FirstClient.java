//This code has been written by: Mohammad M. AlBanna.
//Website: www.MBanna.info
//Blog: www.OutOfPalBox.net
//Facebook Page: FB.com/MBanna.info
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Using Threads ..
public class FirstClient extends javax.swing.JFrame {

    static Socket SendSocket = null;

    public FirstClient() {
        //Draw GUI
        initComponents();
        SendArea.requestFocusInWindow();
    }
    
    //This code generated with netbeans for GUI and events.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        RecievedArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        SendArea = new javax.swing.JTextArea();
        SendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("First Client");

        jSplitPane1.setDividerLocation(240);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        RecievedArea.setColumns(20);
        RecievedArea.setRows(5);
        jScrollPane1.setViewportView(RecievedArea);

        jSplitPane1.setTopComponent(jScrollPane1);

        SendArea.setColumns(20);
        SendArea.setRows(1);
        SendArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SendAreaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(SendArea);

        jSplitPane1.setRightComponent(jScrollPane2);

        SendButton.setText("ارسال");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Done by : Mohammed M. AL-Banna ||  Website : www.MBanna.info");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(SendButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SendButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        SendMessage(SendArea.getText());
        SendArea.setText("");

    }//GEN-LAST:event_SendButtonActionPerformed
    
    //Click event, when the user press enter send the message the second client
    private void SendAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SendAreaKeyPressed
        if (KeyEvent.getKeyText(evt.getKeyChar()).equals("Enter")) {
            SendButtonActionPerformed(null);
            SendArea.setCaretPosition(0);
        }
    }//GEN-LAST:event_SendAreaKeyPressed
    
    //************************************************************************//
    public static void main(String args[]) {
        //Run the thread
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FirstClient().setVisible(true);
            }
        });

        OpenSocketAndRecieve();
    }
    
    //************************************************************************//
    public static void OpenSocketAndRecieve() {
        
        new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(12345);
                    while (true) {
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        RecievedArea.append("\n " + input.readUTF());

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }//end method
    
    //************************************************************************//
    public static void SendMessage(String text) {
        try {
            SendSocket = new Socket("127.0.0.1", 12346);
            DataOutputStream out = new DataOutputStream(SendSocket.getOutputStream());
            out.writeUTF("The Server Said : " + text);
            RecievedArea.append("\n I said : " + text);
        } catch (Exception e) {
            System.out.println(e);
        }

    }//end method 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea RecievedArea;
    private javax.swing.JTextArea SendArea;
    private javax.swing.JButton SendButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
