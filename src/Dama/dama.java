package Dama;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

public class dama extends JApplet {

    int oncekiX = 3, oncekiY = 29, tutX, tutY, tut;
    int[][] tahta = new int[8][8];
    boolean tuttu = false;
    private DataOutputStream giden;
    private DataInputStream gelen;
    private Socket tunel;

        BufferedImage mpul;
        BufferedImage kpul;
        BufferedImage skpul;
        BufferedImage smpul;
        BufferedImage slcm;
        BufferedImage slck;
        BufferedImage sslcm;
        BufferedImage sslck;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
    }// </editor-fold>

                public void run() {
                    for (int r = 0; r < 8; r++) {
                        for (int p = 0; p < 8; p++) {
                            tahta[r][p] = 0;
                        }
                        tahta[r][1] = 1;
                        tahta[r][2] = 1;

                        tahta[r][5] = 2;
                        tahta[r][6] = 2;
                    }
                    initComponents();

                }
    public void init() {
        try {
         mpul = ImageIO.read(this.getClass().getResource("mpul.png"));
         kpul = ImageIO.read(this.getClass().getResource("kpul.png"));
         skpul = ImageIO.read(this.getClass().getResource("skpul.png"));
         smpul = ImageIO.read(this.getClass().getResource("smpul.png"));
         slcm = ImageIO.read(this.getClass().getResource("slcm.png"));
         slck = ImageIO.read(this.getClass().getResource("slck.png"));
         sslcm = ImageIO.read(this.getClass().getResource("sslcm.png"));
         sslck = ImageIO.read(this.getClass().getResource("sslck.png"));
         run();
         repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /**try {
            System.out.println("Bağlantı kuruluyor...");
            tunel = new Socket("127.0.0.1", 5005);
            gelen = new DataInputStream(tunel.getInputStream());
            giden = new DataOutputStream(tunel.getOutputStream());
            giden.flush();
            giden.writeUTF("uname");
            if (gelen.readUTF().equals("<Baglanti:Basarili>")) {
                System.out.println("Bağlantı Başarılı");
            }else{
                System.out.println("Bağlantı Kurulamadı");
            }
        } catch (Exception e) {
        }**/
    }

    public boolean yenecekVarmi(int renk, int x, int y) {
        if (x > 7) {
            x = 0;
            y++;
            return yenecekVarmi(renk, x, y);
        }

        if (y >= 7) {
            return false;
        }

        if ((renk == 1) && tahta[x][y] == renk &&
                ((y + 2 < 8 && (tahta[x][y + 2] == 0 && tahta[x][y + 1] == 2)) || (x + 2 < 8 && (tahta[x + 1][y] == 2 && tahta[x + 2][y] == 0)) || (x - 2 > -1 && (tahta[x - 2][y] == 0 && tahta[x - 1][y] == 2)))) {
            return true;
        }

        if ((renk == 2) && tahta[x][y] == renk &&
                ((y - 2 > 0 && (tahta[x][y - 2] == 0 && tahta[x][y - 1] == 1)) || (x + 2 < 8 && (tahta[x + 1][y] == 1 && tahta[x + 2][y] == 0)) || (x - 2 > -1 && (tahta[x - 2][y] == 0 && tahta[x - 1][y] == 1)))) {
            return true;
        }

        return yenecekVarmi(renk, ++x, y);
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int araX, araY;
        araX = (int) (getMousePosition().x) / 70;
        araY = (int) (getMousePosition().y) / 70;
        System.out.println(araX + "," + araY);
        if (araX == tutX && araY == tutY) {
            tutX = -1;
            tutY = -1;
            tut = 0;
            tuttu = false;
            System.out.println("Seçili değil..");
            repaint();
        }
        if (tahta[araX][araY] != 0) {
            tuttu = true; //taşı tuuttu
            tut = tahta[araX][araY];
            tutX = araX;
            tutY = araY;
            System.out.println("tuttu");
        } else {
            tuttu = false;
            tut = 0;
            tutX = -1;
            tutY = -1;
            System.out.println("bıraktı");
        }
        repaint();
        if (yenecekVarmi(1, 0, 0)) {
            System.out.println("Mavi Yiyebilir");
        }
        if (yenecekVarmi(2, 0, 0)) {
            System.out.println("Kırmızı yiyebilir");
        }

    }

    private void formMouseReleased(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        //Mavi 1 üstte, Kırmızı 2 altta, Süper Mavi 3, Süper Kırmızı 4
        int araX, araY;

        araX = (int) (getMousePosition().x) / 70;
        araY = (int) (getMousePosition().y) / 70;
        if (tuttu = true && tahta[araX][araY] == 0) {

            if ((araX - tutX == 1 || araX - tutX == -1 || (tut == 1 && araY - tutY == 1) || (araY - tutY == -1 && tut == 2)) && (araY == tutY || araX == tutX)) {
                tahta[tutX][tutY] = 0;
                tuttu = false;
                if (araY == 0 || araY == 7) {
                    if (tut == 1) {
                        tut = 3;
                    }
                    if (tut == 2) {
                        tut = 4;
                    }
                }
                tahta[araX][araY] = tut;

            }



            if ((araY - tutY == 2 && araX == tutX && tut == 1 && tahta[araX][araY - 1] == 2)) {
                tahta[araX][araY - 1] = 0;
                if (araY == 0 && tut == 2) {
                    tut = 4;
                }
                tahta[araX][araY] = tut;
                tuttu = false;
                tahta[tutX][tutY] = 0;

            }

            if ((araY - tutY == -2 && araX == tutX && tut == 2 && tahta[araX][araY + 1] == 1)) {
                tahta[araX][araY + 1] = 0;
                if (araY == 7 && tut == 1) {
                    tut = 3;
                }
                tahta[araX][araY] = tut;
                tuttu = false;
                tahta[tutX][tutY] = 0;
            }

            if ((araX - tutX == 2 && araY == tutY && (tut != tahta[araX - 1][araY]) && (tahta[araX - 1][araY] != 0))) {
                tahta[araX - 1][araY] = 0;
                if (araY == 0 || araY == 7) {
                    if (tut == 1) {
                        tut = 3;
                    }
                    if (tut == 2) {
                        tut = 4;
                    }
                }

                tahta[araX][araY] = tut;
                tuttu = false;
                tahta[tutX][tutY] = 0;
            }

            if ((araX - tutX == -2 && araY == tutY && (tut != tahta[araX + 1][araY]) && (tahta[araX + 1][araY] != 0))) {
                tahta[araX + 1][araY] = 0;

                if (araY == 0 || araY == 7) {
                    if (tut == 1) {
                        tut = 3;
                    }
                    if (tut == 2) {
                        tut = 4;
                    }
                }
                tahta[araX][araY] = tut;
                tuttu = false;
                tahta[tutX][tutY] = 0;
            }
        }
        tut = 0;
        tutX = -1;
        tutY = -1;
        tuttu = false;

        repaint();
    }

    private void formMouseMoved(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    public void paint(Graphics g) {


        g.drawRect(0, 0, 560, 560);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i) % 2 == 1) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.gray);
                }
                g.fillRect((j * 70), (i * 70), 70, 70);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tahta[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    //g.fillOval(i * 70, j * 70, 70, 70);
                    g.drawImage(mpul, i * 70, j * 70, this);
                } else if (tahta[i][j] == 2) {
                    g.setColor(Color.RED);
                    //g.fillOval(i * 70, j * 70, 70, 70);
                    g.drawImage(kpul, i * 70, j * 70, this);
                } else if (tahta[i][j] == 3) {
                    g.setColor(Color.CYAN);
                    //g.fillOval(i * 70, j * 70, 70, 70);
                    g.drawImage(smpul, i * 70, j * 70, this);
                } else if (tahta[i][j] == 4) {
                    g.setColor(Color.MAGENTA);
                    //g.fillOval(i * 70, j * 70, 70, 70);
                    g.drawImage(skpul, i * 70, j * 70, this);
                }
            }
        }
        if ((tutX > -1 || tutY > -1) && tuttu) {
            if (tut == 1) {
                g.drawImage(slcm, tutX * 70, tutY * 70, this);
            } else if (tut == 2) {
                g.drawImage(slck, tutX * 70, tutY * 70, this);
            } else if (tut == 3) {
                g.drawImage(sslcm, tutX * 70, tutY * 70, this);
            } else if (tut == 4) {
                g.drawImage(sslck, tutX * 70, tutY * 70, this);
            }
        }
    }

    public void destroy() {
   }

    // Variables declaration - do not modify
    // End of variables declaration
    }
