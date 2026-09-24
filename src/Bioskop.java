import java.util.ArrayList;
import java.util.Scanner;
public class Bioskop {
    private static Scanner scanner = new Scanner(System.in);
    // Atribut private
    private int kodeBioskop;
    private String namaBioskop;
    private String lokasi;
    // menyimpan ArrayList yang isinya objek2 studio
    private ArrayList<Studio> listStudio;

    // Constructor public
    public Bioskop(int kodeBioskop, String namaBioskop, String lokasi) {
        this.kodeBioskop = kodeBioskop;
        this.namaBioskop = namaBioskop;
        this.lokasi = lokasi;
        this.listStudio = new ArrayList<Studio>();
    }

    // Constructor user
    public Bioskop(ArrayList<Bioskop> listBioskop) {
        boolean kodeSudahAda;
        do {
            System.out.print("Masukkan Kode Bioskop: ");
            this.kodeBioskop = scanner.nextInt();
            scanner.nextLine(); // clear buffer newline

            kodeSudahAda = false;
            for (Bioskop b : listBioskop) {
                if (this.kodeBioskop == b.getKodeBioskop()) {
                    kodeSudahAda = true;
                    break;
                }
            }
            if (kodeSudahAda) {
                 System.out.println("Kode bioskop sudah dipakai! Coba kode lain");
            }
        } while (kodeSudahAda);
       
        System.out.print("Masukkan Nama Bioskop: ");
        this.namaBioskop = scanner.nextLine();

        System.out.print("Masukkan Lokasi: ");
        this.lokasi = scanner.nextLine();

        //A ArrayList listStudio kosong
        this.listStudio = new ArrayList<Studio>();
    }

    // ArrayList studio yang masih kosong tadi, kita masukkin object Studio
    public void addStudio(Studio studio) {
        this.listStudio.add(studio);
    }

    void showBioskop() {
        System.out.println("Kode Bioskop : " + kodeBioskop);
        System.out.println("Nama Bioskop : " + namaBioskop);
        System.out.println("Lokasi       : " + lokasi);
    }

    void detailBioskop() {        
        System.out.println("=== Detail Bioskop ===");
        System.out.println(kodeBioskop + ". " + namaBioskop);
        System.out.println("List Studio:");
        // Jika bioskop yang dipilih belum ada studio
        for (Studio s : listStudio) {
            s.showStudio();
        }
    }

    // Setter
    public void setKodeBioskop(int kodeBioskop) {
        this.kodeBioskop = kodeBioskop;
    }
    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    
    // Getter
    public int getKodeBioskop() {
        return kodeBioskop;
    }
    public String getNamaBioskop() {
        return namaBioskop;
    }
    public String getLokasi() {
        return lokasi;
    }
    public ArrayList<Studio> getListStudio() {
        return listStudio;
    }  
}