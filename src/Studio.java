import java.util.ArrayList;
import java.util.Scanner;

public class Studio {
    private static Scanner scanner = new Scanner(System.in);

    // Attribut private
    private Integer kodeStudio;
    private Integer nomorStudio;
    private String jenisStudio;
    private Bioskop bioskop;
    private float harga;

    // Constructor public
    public Studio(Integer kodeStudio, Integer nomorStudio, String jenisStudio, Bioskop bioskop, float harga) {
        this.kodeStudio = kodeStudio;
        this.nomorStudio = nomorStudio;
        this.jenisStudio = jenisStudio;
        this.bioskop = bioskop;
        this.harga = harga;
    }

    // Constructor user
    public Studio(ArrayList<Bioskop> listBioskop) {
        if (listBioskop.isEmpty()) {
            System.out.println("Belum ada bioskop yang terdaftar! Silahkan daftarkan terlebih dahulu.");
            return;
        }
        System.out.println("=== FORM INPUT STUDIO ===");

        System.out.print("Masukkan Kode Studio  : ");
        this.kodeStudio = scanner.nextInt();

        System.out.print("Masukkan Nomor Studio : ");
        this.nomorStudio = scanner.nextInt();
        scanner.nextLine(); // Clear buffer newline

        int pilihan;
        do{
            System.out.println("\n=== Jenis Studio ===");
            System.out.println("1. Regular");
            System.out.println("2. IMAX");
            System.out.println("3. Premiere");
            System.out.print("Masukkan Jenis Studio (1-3): ");
            pilihan = scanner.nextInt();
            switch(pilihan) {
                case 1 :
                    this.jenisStudio = "Regular";
                    break;
                case 2 :
                    this.jenisStudio = "IMAX";
                    break;
                case 3 :
                    this.jenisStudio = "Premiere";
                    break;
                default :
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        } while (pilihan < 1 || pilihan > 3);
        
        Bioskop bioskopTerpilih = null;
        do {
            System.out.println("\n Daftar Bioskop: ");
            for (Bioskop b : listBioskop) {
                System.out.println(b.getKodeBioskop() + ". " + b.getNamaBioskop() + " | " + b.getLokasi());
            }
            System.out.println("Pilih kode bioskop: ");
            int inputKodeBioskop = scanner.nextInt();
            
            for (Bioskop b : listBioskop) {
                if(inputKodeBioskop == b.getKodeBioskop()) {
                    bioskopTerpilih = b; // menyimpan objek saat ini
                    break;
                }
            }
            if (bioskopTerpilih == null) {
                System.out.println("Kode bioskop tidak valid!");
                continue;
            }
        } while (bioskopTerpilih == null);
        
        this.bioskop = bioskopTerpilih;

        System.out.print("Masukkan Harga: ");
        this.harga = scanner.nextFloat();       
    }

    // Setter
    public void setKodeStudio(Integer kodeStudio) {
        this.kodeStudio = kodeStudio;
    }

    public void setNomorStudio(Integer nomorStudio) {
        this.nomorStudio = nomorStudio;
    }

    public void setJenisStudio(String jenisStudio) {
        this.jenisStudio = jenisStudio;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    // Getter
    public Integer getKodeStudio() {
        return kodeStudio;
    }

    public Integer getNomorStudio() {
        return nomorStudio;
    }

    public String getJenisStudio() {
        return jenisStudio;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public float getHarga() {
        return harga;
    }
}