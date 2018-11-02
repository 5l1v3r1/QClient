package Dama;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dama2 extends javax.swing.JApplet {

    BufferedImage siyah;
    BufferedImage beyaz;
    BufferedImage mPul;
    BufferedImage kPul;
    BufferedImage smPul;
    BufferedImage skPul;
    String[][] tahta = new String[8][8];
    int tutX, tutY;
    String tut;
    boolean tuttu;
    // mavi pul: m
    // kırmızı pul : k
    // Super mavi : sm
    // Super kırmızı : sk

    public void init() {
        try {
            siyah = ImageIO.read(this.getClass().getResource("siyah.png"));
            beyaz = ImageIO.read(this.getClass().getResource("beyaz.png"));
            mPul = ImageIO.read(this.getClass().getResource("mPul.png"));
            kPul = ImageIO.read(this.getClass().getResource("kPul.png"));
            smPul = ImageIO.read(this.getClass().getResource("smPul.png"));
            skPul = ImageIO.read(this.getClass().getResource("skPul.png"));
            for (int p = 0; p < 8; p++) {
                tahta[p][1] = "k";
                tahta[p][2] = "k";
                tahta[p][5] = "m";
                tahta[p][6] = "m";
            }
        } catch (IOException ex) {
        }
        initComponents();
        setSize(400, 400);
    }

    public void tahtayiCiz(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    g.drawImage(siyah, i * 50, j * 50, this);
                } else {
                    g.drawImage(beyaz, i * 50, j * 50, this);
                }
            }
        }
    }

    public void pullariCiz(Graphics g) {
        String pul;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pul = tahta[i][j];
                if (pul == "m") {
                    g.drawImage(mPul, i * 50, j * 50, this);
                } else if (pul == "k") {
                    g.drawImage(kPul, i * 50, j * 50, this);
                }
            }
        }
    }

    public void paint(Graphics g) {
        tahtayiCiz(g);
        pullariCiz(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        int cx, cy;
        cx = evt.getX() / 50;
        cy = evt.getY() / 50;
        System.out.println("cx:" + cx + " cy:" + cy);
        if (!tuttu && tahta[cx][cy]!="") {
            tuttu = true;
            tutX = cx;
            tutY = cy;
            tut=tahta[cx][cy];
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
