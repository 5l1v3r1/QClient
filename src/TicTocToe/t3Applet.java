package TicTocToe;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class t3Applet extends javax.swing.JApplet {

    BufferedImage tahta;
    BufferedImage x;
    BufferedImage o;
    int sira = 0;
    int[][] t = new int[3][3];

    public void init() {
        try {

                    initComponents();

            tahta = ImageIO.read(this.getClass().getResource("arka.png"));
            x = ImageIO.read(this.getClass().getResource("x.png"));
            o = ImageIO.read(this.getClass().getResource("o.png"));
        } catch (Exception e) {
        }
        setSize(250, 250);
    }

    public void paint(Graphics g) {
        g.drawImage(tahta, 0, 0, null);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t[i][j] == 1) {
                    g.drawImage(x, i * 75, j * 75, null);
                } else if (t[i][j] == 2) {
                    g.drawImage(o, i * 75, j * 75, null);
                }
            }
        }
                
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Tic Toc Toe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel1)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1)
                .addContainerGap(155, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        int getX, getY;
        getX = getMousePosition().x / 75;
        getY = getMousePosition().y / 75;
        if (++sira % 2 == 1) {
            t[getX][getY] = 1;
        } else {
            t[getX][getY] = 2;
        }
        repaint();
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
