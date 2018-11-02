
import Dama.Dama2;
import TicTocToe.t3Applet;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class ClientChat extends javax.swing.JFrame implements Runnable {

    ClientMsjBekle cmb;
    String userName;// Bu kişinin adı
    String userCode;//Bu kişinin Kdu
    String aliciCode;//Alıcının Kodu
    String alici;// Alıcının ismi
    ClientUsers cus;// Merkez
    String konusmalar;// Konuşma Geçmişi
    int imlec = 0;
    public boolean oyun = true;
    String mesaj;
    String fontName;
    String fontSize;
    String fontColor;

    public ClientChat(ClientUsers cu, String uName, String uCode, String alan, String alanCode) {
        try {
            cus = cu;
            userName = uName;
            userCode = uCode;
            alici = alan;
            aliciCode = alanCode;
        } catch (Exception e) {
        }
        initComponents();
        jLabel1.setText("Konuşma: " + alici);
        setTitle("Konuşma: " + alici);
        jLabel2.setVisible(false);
        jEditorPane1.setText("");
        //jEditorPane2.setText("");
        //jEditorPane2.removeAll();
        konusmalar = "<div style=\"font-family:Arial; size:10px;\"><i>Konuşma başladı</i>";
    }

    public String getUserName() {
        return alici;
    }

    public String getUserCode() {
        return aliciCode;
    }

    public void titret() {
        try {
            for (int t = 0; t < 20; t++) {
                if (t % 4 == 0) {
                    setLocation(getLocation().x + 5, getLocation().y + 5);
                } else if (t % 4 == 1) {
                    setLocation(getLocation().x - 5, getLocation().y - 5);
                } else if (t % 4 == 2) {
                    setLocation(getLocation().x - 5, getLocation().y + 5);
                } else if (t % 4 == 3) {
                    setLocation(getLocation().x + 5, getLocation().y - 5);
                }
                Thread.sleep(10);
            }
            konusmalar += "<br><i>Titreşim aldınız.</i>";
            jEditorPane1.setText(konusmalar);
        } catch (Exception e) {
        }
    }

    public void yaziyor(boolean y) {
        jLabel2.setVisible(y);
    }

    public void smiley(String img) {
        String bas, son, rsm, txt;
        //jLabel1.setIcon(new ImageIcon(this.getClass().getResource("1.png")));
        txt = jTextArea1.getText();
        bas = txt.substring(0, imlec);
        //System.out.println("bas:" + imlec + "\n" + bas);
        son = txt.substring(imlec);
        //System.out.println("son:" + imlec + "\n" + son);
        rsm = img;
        //rsm = this.getClass().getResource(img).toString();
        jTextArea1.setText(bas + rsm + son);
    //System.out.println("Filtreli:" + filtrasyon(jEditorPane2.getText()));
    //cus.mesajYolla("<Writing>", alici);

    }

    public String filtrasyon(String m) {
        String f = m;
        f = f.replaceAll("<html>", "");
        f = f.replaceAll("</html>", "");
        f = f.replaceAll("<head>", "");
        f = f.replaceAll("</head>", "");
        f = f.replaceAll("<body>", "");
        f = f.replaceAll("</body>", "");
        f = f.replaceAll("<p style=\"margin-top: 0\">", "");
        f = f.replaceAll("</p>", "");
        f = f.replaceAll("\n", "");
        f = f.replaceAll("   ", "");




        /**f = f.replaceAll("<img src=\"file:///C:/smileys/1.png\">", ":D");
        f = f.replaceAll("<img src=\"file:///C:/smileys/2.png\">", ":)");
        f = f.replaceAll("<img src=\"file:///C:/smileys/3.png\">", ":|");
        f = f.replaceAll("<img src=\"file:///C:/smileys/4.png\">", ":(");
        f = f.replaceAll("<img src=\"file:///C:/smileys/5.png\">", ":o");
        f = f.replaceAll("<img src=\"file:///C:/smileys/6.png\">", ":p");
        f = f.replaceAll("<", "");
        f = f.replaceAll(">", "");
        f = f.replaceAll("\n", "");**/
        return f;
    }

    public String defiltrasyon(String m) {
        String f = m;
        f = f.replaceAll(":D", "<img src=\"file:///C:/smileys/1.png\">");
        f = f.replaceAll(":)", "<img src=\"file:///C:/smileys/2.png\">");
        f = f.replaceAll(":|", "<img src=\"file:///C:/smileys/3.png\">");
        f = f.replaceAll(":(", "<img src=\"file:///C:/smileys/4.png\">");
        f = f.replaceAll(":o", "<img src=\"file:///C:/smileys/5.png\">");
        f = f.replaceAll(":p", "<img src=\"file:///C:/smileys/6.png\">");
        f = f.replaceAll("\n", "<br>");
        f = f.trim();
        System.out.println(f + "");
        return f;
    }

    public void mesajYolla() {
        String m = jTextArea2.getText();

        if (m.trim().length() > 0) {
            cus.mesajYolla(m, aliciCode);
            konusmalar += "<br><b>" + userName + ": </b>" + m.trim();
        }
        jEditorPane1.setText(konusmalar);
        jTextArea2.setText("");
    }

    public void oyna(JApplet oyunApplet, String baslik, int w, int h) {
        if (oyun == true) {
            AppletViewer av = new AppletViewer(this, oyunApplet, baslik, w, h);
            setOyna(false);
        }
    }

    public void setOyna(boolean o) {
        oyun = o;
    }

    public void mesajAl(String m) {
        if (m.equals("<Titret>")) {
            titret();
            setVisible(true);
        } else if (m.equals("<Writing>")) {
            yaziyor(true);
        } else if (m.equals("</Writing>")) {
            yaziyor(false);
        } else if (m.equals("<TicTocToe?>")) {
            Object[] options = {"Kabul et", "Reddet"};
            int a = JOptionPane.showOptionDialog(null, "TicTocToe Oynamak istiyor musunuz?", "Oyun Oyna",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
        } else {
            konusmalar += "<br><b>" + alici + ": </b>" + m;
            jEditorPane1.setText(konusmalar);
            setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jFormattedTextField1.setText("jFormattedTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setTitle("Konuşma:");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1.png"))); // NOI18N
        jButton2.setToolTipText("Gül");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2.png"))); // NOI18N
        jButton3.setToolTipText("Tebessüm Et");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.png"))); // NOI18N
        jButton4.setToolTipText("İstifini Bozma");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/4.png"))); // NOI18N
        jButton5.setToolTipText("Sıkıl");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.png"))); // NOI18N
        jButton6.setToolTipText("Şaşır");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6.png"))); // NOI18N
        jButton7.setToolTipText("Umursama");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/titret.png"))); // NOI18N
        jButton8.setToolTipText("Titret");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/font.png"))); // NOI18N
        jButton9.setToolTipText("Yazı Tipini Değiştir");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yaziyor.png"))); // NOI18N
        jLabel2.setText("Yazıyor...");

        jSplitPane1.setDividerLocation(170);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setAutoscrolls(false);
        jTextArea2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea2FocusLost(evt);
            }
        });
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea2);

        jSplitPane1.setRightComponent(jScrollPane3);

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setCaretColor(new java.awt.Color(255, 0, 51));
        jScrollPane1.setViewportView(jEditorPane1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jMenu1.setText("Durum");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comments_delete.png"))); // NOI18N
        jMenuItem1.setText("Konuşmayı Bitir");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Oyun Oyna");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oyun.png"))); // NOI18N
        jMenuItem2.setText("Dama");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oyun.png"))); // NOI18N
        jMenuItem3.setText("Taş Kağıt Makas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oyun.png"))); // NOI18N
        jMenuItem4.setText("TicTocToe");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseClicked(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("jMenuItem5");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //smiley("1.png");
        smiley(":D");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        cus.mesajYolla("<Titret>", aliciCode);
        konusmalar += "<br><i>Titreşim yolladınız...</i>";
        jEditorPane1.setText(konusmalar);
    }//GEN-LAST:event_jButton8ActionPerformed
    boolean smileyNob = false;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        smiley(":)");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        smiley(":|");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        smiley(":(");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        smiley(":o");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        smiley(":p");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JApplet d = new Dama2();
        oyna(d, "Dama", 600, 600);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cus.mesajYolla("</Writing>", aliciCode);
    }//GEN-LAST:event_formWindowClosing

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
        cus.mesajYolla("</Writing>", aliciCode);
    }//GEN-LAST:event_formFocusLost

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
        cus.mesajYolla("</Writing>", aliciCode);
    }//GEN-LAST:event_formWindowDeactivated

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JApplet ttt = new t3Applet();
        oyna(ttt, "Tic Toc Toe", 400, 400);
        cus.mesajYolla("<TicTocToe?>", aliciCode);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4MouseClicked

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        // TODO add your handling code here:
        cus.mesajYolla("<Writing>", aliciCode);
        if (evt.getKeyCode() == 10) {
            mesajYolla();
        }
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void jTextArea2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea2FocusLost
        // TODO add your handling code here:
        cus.mesajYolla("</Writing>", aliciCode);
    }//GEN-LAST:event_jTextArea2FocusLost

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Kabul et", "Reddet"};
        int a = JOptionPane.showOptionDialog(null, "Oynamak istiyor musunuz?", "Oyun Oyna",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (a == 1) {
            System.out.println("egeeeeeeetttt");
        } else {
            System.out.println("anlamsızzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void run() {
        jLabel1.setText(alici);
        setTitle("Konuşma : " + alici);
        setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
