
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

public class ClientUsers extends javax.swing.JFrame {

    private DataOutputStream giden;
    private DataInputStream gelen;
    private String UserName;
    private String UserCode; // Kullanıcıya has olan bir rastgele sayı
    private Vector kullanicilar = new Vector(); // Konuşulan pencereleri tutan vektör
    ClientMsjBekle nobetci; // Client tarafının mesaj bekleyen nöbetçisi, tellalı, threadi
    String[] userCodes = new String[0];
    String[] userNames = new String[0];

    public ClientUsers(DataOutputStream os, DataInputStream is, String unamesi, String uCodu) { //os=OutputStream, is=InputStream
        try {
            UserName = unamesi; // oluşturma zamanında kullanıcı adını al
            gelen = is; // InputStreami al
            giden = os; // Output Streami al
            giden.flush();
            UserCode = uCodu;

            initComponents(); // Komponentleri kur
            setVisible(true); // Görünür yap
            new Thread(new ClientMsjBekle(this, jList1, userCodes, userNames, is, UserName, UserCode)).start(); //Mesaj bekleyen threadi çalıştır
            new Thread(new ClientListeYenileyici(this)).start();
            //giden.writeUTF("<KullaniciListesi>"); // Serverden kullanıcı listesini iste
            jLabel1.setText(unamesi); // Labele kulanıcı adını yaz
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Qostebek");
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jList1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList1FocusGained(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jList1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connect.png"))); // NOI18N
        jLabel2.setToolTipText("Bağlı");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qost.png"))); // NOI18N

        jLabel1.setText("jLabel1");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user.png"))); // NOI18N
        jLabel4.setText("Kullanıcı bağlı");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    public synchronized void updateUserList() {

    boolean kont = true;
    String okunan;
    try {
    //nobetci.wait();
    giden.writeUTF("<KullaniciListesi>");
    System.out.println("-Liste istendi");
    DefaultListModel userList = new DefaultListModel();
    while (kont) {
    System.out.println("Bişey beklenior..");
    okunan = gelen.readUTF();
    System.out.println("bişey geldi..");
    if (okunan.equals("</KullaniciListesi>")) {
    System.out.println("Liste Bitti..");
    okunan = "";
    jList1.setModel(userList);
    kont = false;
    break;
    } else {
    userList.addElement(okunan);
    System.out.println("-alındı");
    }
    }
    //nobetci.interrupt();
    System.out.println("-Liste Güncellendi.");
    } catch (Exception e) {
    e.printStackTrace();
    }

    }
     **/
    public synchronized int getUserIndex(String uCode) {// Kodu kontrol eden
        // Vektörden kullanıcı adına göre indexi getiren metod
        int uIndex = -1;
        ClientChat uO;
        for (int i = 0; i < kullanicilar.size(); i++) {
            uO = (ClientChat) kullanicilar.elementAt(i);
            if (uO.getUserCode().equals(uCode)) {
                uIndex = i;
                break;
            }
        }
        return uIndex;
    }

    public synchronized int konusuyorMu(String uName) {// ismi kontrol eden
        // Vektörden kullanıcı adına göre indexi getiren metod
        int uIndex = -1;
        ClientChat uO;
        for (int i = 0; i < kullanicilar.size(); i++) {
            uO = (ClientChat) kullanicilar.elementAt(i);
            if (uO.getUserName().equals(uName)) {
                uIndex = i;
                break;
            }
        }
        return uIndex;
    }

    public synchronized String getUserNameByCode(String uCode) {
        System.out.println("KODA GÖRE İSMİ GETİR");
        String name = "";
        String deneyTupu;
        System.out.println("Kulllanıcı sayısı : : : " + userCodes.length);
        for (int i = 0; i < userCodes.length; i++) {
            System.out.println("DDÖNGÜYE GİRDİİİİİİİİİİİİİ");
            deneyTupu = userCodes[i];
            System.out.println("deneyTupune alındı : " + deneyTupu);
            if (!deneyTupu.equals(null) && deneyTupu.equals(uCode)) {
                name = userNames[i];
                System.out.println("kullllanaıcı adıııııı bulunduuuuuuuuuu");
                return name;
            }
            System.out.println("BU DEĞİLLLLLLLL : " + name);
        }
        System.out.println("bittttiiiii bu k araaaadddddrrrd");
        return name;
    }

    public synchronized void mesajYonlendir(String gondCode, String mesaj) {
        //Gelen mesajı buradaki (vektörde tutlan) pencereye yönlendiren metod
        //System.out.println("YÖNLENDİRİLİYOOOOOOOOOOOOOOORRRRRRRR");
        ClientChat cc;
        String gondName;
        int uID = getUserIndex(gondCode);
        System.out.println("Mesaj Yönlendiriliyorrrrrrrrrrrrrrrrrrrrrrrrr...................");
        gondName = getUserNameByCode(gondCode);
        System.out.println("gonderen Adı : " + gondName);
        if (uID == -1) {
            cc = new ClientChat(this, UserName, UserCode, gondName, gondCode);// ... ,alici_isim,kod);
            kullanicilar.add(cc);
            cc.setTitle("Konuşma : " + gondName);
        } else {
            cc = (ClientChat) kullanicilar.elementAt(uID);
        }
        cc.mesajAl(mesaj);
    }

    public void mesajYolla(String msj, String aliciCode) {
        // Pencerelerden mesaj yollama isteği geldiğinde akışa bırak.
        try {
            giden.writeUTF("<msg:" + UserCode + ":" + aliciCode + ">" + msj + "</msg>");
            giden.flush();
        } catch (Exception e) {
        }
    }

    public void setUserCodeList(String[] uCL) {
        userCodes = uCL;
    }

    public void setUserNameList(String[] uN) {
        userNames = uN;
    }

    public synchronized void konusmaBaslat() {
        //<msg:23456781968529685:57689624186933>MESAJ</msg>
        if (jList1.getSelectedIndex() > -1) {
            ClientChat cc;
            String kiminle = (String) jList1.getSelectedValue();
            int kontrol = konusuyorMu(kiminle);
            //System.out.println("Kontrol : : :  : . : : . : .: " + kontrol);
            if (kontrol == -1) {
                int kimIndex = jList1.getSelectedIndex();
                cc = new ClientChat(this, UserName, UserCode, kiminle, userCodes[kimIndex]);
                kullanicilar.add(cc);
                cc.setTitle("Konuşma :" + kiminle);
            } else {
                kontrol = konusuyorMu(kiminle);
                cc = (ClientChat) kullanicilar.elementAt(kontrol);
            }
            cc.setVisible(true);
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            giden.writeUTF("<Disconnect>");
            giden.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    public void refreshListe() {
        try {
            jList1.removeAll();
            giden.writeUTF("<KullaniciListesi>");
            giden.flush();
        } catch (Exception e) {
        }
    }

    public void kullaniciSay() {
        jLabel4.setText((jList1.getLastVisibleIndex() + 2) + " kullanıcı bağlı");
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try {
            giden.writeUTF("<Disconnect>");
            giden.flush();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowClosed

    private void jList1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList1FocusGained
        // TODO add your handling code here:
        //updateUserList();
    }//GEN-LAST:event_jList1FocusGained
    long ilkT = 0;
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        long sonT;
        sonT = System.currentTimeMillis();
        if ((sonT - ilkT) < 1000) {
            konusmaBaslat();
        }
        ilkT = sonT;

    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ValueChanged

    private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
        // TODO add your handling code here:
        if (jList1.getSelectedIndex() > -1 && evt.getKeyCode() == 10) {
            konusmaBaslat();
        }
    }//GEN-LAST:event_jList1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
