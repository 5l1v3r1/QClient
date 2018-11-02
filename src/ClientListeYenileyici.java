
public class ClientListeYenileyici extends Thread {

    private ClientUsers cus;

    public ClientListeYenileyici(ClientUsers c) {
        cus = c;
    }

    public void run() {
        try {
            while (true) {
                cus.refreshListe();
                cus.kullaniciSay();
                Thread.sleep(3000);
            }
        } catch (Exception e) {
        }

    }
}
