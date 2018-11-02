
import java.io.DataInputStream;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ClientMsjBekle extends Thread {

    private DataInputStream gelen;
    ClientUsers cus;
    String userName;
    String userCode;
    JList jelist;
    String[] userCodes;
    String[] userNames;

    public ClientMsjBekle(ClientUsers cu, JList lst, String[] uCodes, String[] uNames, DataInputStream is, String uName, String uCode) {
        try {
            gelen = is;
            cus = cu;
            jelist = lst;
            userCodes = uCodes;
            userNames = uNames;
            userName = uName;
            userCode = uCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String msjParser(String m) {
        //<msg:ali:veli>Merhaba Ali! :)</msg>
        //System.out.println(m);
        int ilktag, sontag;
        ilktag = m.indexOf('>', 0) + 1;
        sontag = m.indexOf("</msg>", ilktag);
        return m.substring(ilktag, sontag);
    }

    public synchronized String aliciParser(String m) {
        //<msg:ali>Merhaba Ali! :)</msg>
        //<msg:GONDEREN:ALICI>MESAJ</msg>
        int ilktag1, ilktag, sontag;
        ilktag1 = m.indexOf(':', 0) + 1;
        ilktag = m.indexOf(':', ilktag1) + 1;
        sontag = m.indexOf('>', ilktag);
        return m.substring(ilktag, sontag);
    }

    public synchronized String gonderenParser(String m) {
        //<msg:ali>Merhaba Ali! :)</msg>
        //<msg:GONDEREN:ALICI>MESAJ</msg>
        int ilktag1, ilktag, sontag;
        ilktag = m.indexOf("<msg:", 0) + 5;
        sontag = m.indexOf(':', ilktag);
        return m.substring(ilktag, sontag);
    }

    public synchronized void basla() {
        boolean donBabaDonelim = true;
        String msjAl, okunan;
        String[] userDizi;
        try {
            while (donBabaDonelim) {
                msjAl = gelen.readUTF(); // gelen mesajı al
                if (msjAl.startsWith("<msg:")) {
                    if (gonderenParser(msjAl).length() > 10) {
                        cus.mesajYonlendir(gonderenParser(msjAl), msjParser(msjAl));
                        System.out.println(gonderenParser(msjAl) + ":" + msjParser(msjAl) + " - - - - - - - - - - - - - - - -");
                    } else {
                        System.out.println("Mesaj okunaMAdı");
                    }
                } else if (msjAl.equals("<ShotDown>")) {
                    System.out.println("Sistem kapandı..");
                    donBabaDonelim = false;
                    break;
                } else if (msjAl.equals("<KullaniciListesi>")) {
                    int kSay = gelen.readInt();
                    DefaultListModel userList = new DefaultListModel();
                    userCodes = new String[kSay];
                    userNames = new String[kSay];
                    for (int s = 0; s < kSay; s++) {
                        okunan = gelen.readUTF();
                        if (okunan.equals("</KullaniciListesi>")) {
                            okunan = "";
                            jelist.setModel(userList);
                            break;
                        } else {
                            userDizi = okunan.split(":");
                            // merter:76345530804578947946
                            if (!userName.equals(userDizi[0])) {
                                userList.addElement(userDizi[0]);
                            }
                            userCodes[s] = userDizi[1];
                            userNames[s] = userDizi[0];
                        }
                    }
                    cus.setUserCodeList(userCodes);
                    cus.setUserNameList(userNames);
                    jelist.setModel(userList);
                } else {
                    System.out.println("Okunamadı : " + msjAl);
                }
            }
            System.out.println("Mesaj bekleme Bitti");

        } catch (Exception e) {
        }
    }

    public void run() {
        basla();
    }
}
