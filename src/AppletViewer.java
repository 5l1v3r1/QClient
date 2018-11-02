
import Dama.Dama2;
import java.applet.Applet;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class AppletViewer extends JFrame {
    public ClientChat cc;

    public AppletViewer(ClientChat c, JApplet a,String b,int w,int h) {
        this.cc = c;
        basla(a,b,w, h);
    }

    private AppletViewer() {
        initComponents();
        setSize(400, 400);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("AppletViewer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        cc.oyun = true;
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    public static void basla(JApplet a,String b,int w,int h) {
        AppletViewer fp = new AppletViewer();
        fp.setTitle(b+" - Qostebek");
        Applet d = a;
        if(a==null){
        a=new Dama2();
        }

        fp.getContentPane().add(d);
        d.init();
        d.setSize(w, h);
        fp.setSize(w, h);
        fp.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("--------------psvm");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
