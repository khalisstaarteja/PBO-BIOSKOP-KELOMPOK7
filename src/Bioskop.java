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
    public Bioskop() {
        System.out.print("Masukkan Kode Bioskop: ");
        this.kodeBioskop = scanner.nextInt();
        scanner.nextLine(); // clear buffer newline

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