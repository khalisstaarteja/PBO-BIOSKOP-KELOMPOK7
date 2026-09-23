import java.util.ArrayList;
import java.util.Scanner;

public class Pemesanan implements Tiket{
    private static Scanner scanner = new Scanner(System.in);

    // Attribut private
    private static int counter = 0; // kodePemesanan otomatis
    private int nomorPemesanan;
    private JadwalFilm jadwal;
    private int jumlahPemesanan;
    private String metodePembayaran;
    private float totalBayar;
    private User user;

    // Constructor Parameter (Tetap ada sesuai diagram)
    public Pemesanan(int nomorPemesanan, JadwalFilm jadwal, int jumlahPemesanan, String metodePembayaran, float totalBayar, User user) {
        this.nomorPemesanan = nomorPemesanan;
        this.jadwal = jadwal;
        this.jumlahPemesanan = jumlahPemesanan;
        this.metodePembayaran = metodePembayaran;
        this.totalBayar = totalBayar;
        this.user = user;
    }

    // Constructor Default yang MINTA INPUT DARI USER
    public Pemesanan(ArrayList<Film> listFilm, ArrayList<JadwalFilm> listJadwal, ArrayList<Bioskop> listBioskop) {
        System.out.println("=== FORM PEMESANAN ===");
        
        // Input nomorPemesanan otomatis
        this.nomorPemesanan = ++counter;

        // Menampilkan daftar bioskop
        System.out.println("\nDaftar Bioskop: ");
        for (Bioskop b : listBioskop) {
            System.out.println(b.getKodeBioskop() + ". " + b.getNamaBioskop() + " | " + b.getLokasi());
        }
        // Input bioskop
        Bioskop bioskopTerpilih = null;
        int inputKodeBioskop;
        do{
            System.out.print("Pilih Kode Bioskop: ");
            inputKodeBioskop = scanner.nextInt();
            
            // Memastikan input user benar
            for (Bioskop b : listBioskop) {
                if (b.getKodeBioskop() == inputKodeBioskop) {
                    bioskopTerpilih = b;
                    break;
                }
            }
            // Jika input kode tidak ada di data maka input tidak valid
            if (bioskopTerpilih == null) {
                System.out.println("Kode bioskop tidak ditemukan!");
                continue;
            }
        } while (bioskopTerpilih == null);


        // Tampilin list film
        System.out.println("\nDaftar Film Tersedia: ");
        // Akses data film lewat JadwalFilm karna Film tidak memiliki atribut studio
        for (JadwalFilm j : listJadwal) {
            if(j.getStudio().getBioskop() == bioskopTerpilih){
                Film f = j.getFilm();
                System.out.println(f.getKodeFilm() + ". " + f.getJudul() + " | " + f.getGenre() + " | " + f.getDurasi());
            } 
        }
        // Memastikan kode film yang diinput sesuai
        Film filmTerpilih = null;
        int inputKodeFilm;
        do{
            System.out.print("Pilih Kode Film: ");
            inputKodeFilm = scanner.nextInt();
    
            for (JadwalFilm j : listJadwal) {
                if (j.getStudio().getBioskop() == bioskopTerpilih && j.getFilm().getKodeFilm() == inputKodeFilm) {
                    filmTerpilih = j.getFilm();
                    break;
                }
            }
            if (filmTerpilih == null) {
                System.out.println("Kode film tidak ditemukan!");
                continue;
            }
        } while (filmTerpilih == null);
       
        // Tampilin jadwal
        System.out.println("\nJadwal tersedia untuk " + filmTerpilih.getJudul() + ":");
        boolean adaJadwal = false;
        // filter jadwal untuk film yang dipilih
        for (JadwalFilm j : listJadwal) {
            // Jika film yang user pilih sama dengan kodefilm yang memiliki jadwal/ ada di jadwal
            if (j.getFilm().getKodeFilm() == inputKodeFilm && j.getStudio().getBioskop() == bioskopTerpilih) {
                System.out.println(j.getKodeJadwal() + ". Studio " + j.getStudio().getNomorStudio() + " - " + j.getTanggal() + " " + j.getJam());
                adaJadwal = true;
            }
        }
        // Jika jadwal untuk film yang dipilih tidak ada
        if (!adaJadwal) {
            System.out.println("Tidak ada jadwal untuk film " + filmTerpilih.getJudul());
            return;
        }

        // Mmeilih jadwal
        JadwalFilm jadwalTerpilih = null;
        do{
            System.out.println("Pilih Kode Jadwal: ");
            int inputKodeJadwal= scanner.nextInt();

            for (JadwalFilm j : listJadwal) {
                // Jika input user kode jadwal sesuai data kode jadwal di JadwalFilm dan jika kode film yang tadi dipilih juga sesuai sama film yang di jadwal serta bioskop yang dipilih
                // ada 2 kondisi agar mencegah user sudah pilih film tapi salah pilih jadwal
                if (inputKodeJadwal == j.getKodeJadwal() && inputKodeFilm == j.getFilm().getKodeFilm() && j.getStudio().getBioskop() == bioskopTerpilih) {
                    jadwalTerpilih = j;
                    break;
                }
            }
            // Jika user input jadwalnya salah
            if(jadwalTerpilih == null) {
                System.out.println("Kode jadwal tidak ditemukan!");
                continue;
            }
        } while (jadwalTerpilih == null);
        
        // tinggal masukkin dari jadwal yang udah dipilih
        this.jadwal = jadwalTerpilih;

        System.out.print("Masukkan Jumlah Pemesanan: ");
        this.jumlahPemesanan = scanner.nextInt();

        totalBayar = jumlahPemesanan * this.jadwal.getStudio().getHarga();
        
        int pilihan;
        do {
            // Pilihan Metode Pembayaran (1, 2, 3)
            System.out.println("\nPilih Metode Pembayaran:");
            System.out.println("1. Transfer Bank");
            System.out.println("2. QRIS / E-Wallet");
            System.out.println("3. Kartu Kredit");
            System.out.print("Pilihan Anda (1-3): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    this.metodePembayaran = "Transfer Bank";
                    break;
                case 2:
                    this.metodePembayaran = "QRIS / E-Wallet";
                    break;
                case 3:
                    this.metodePembayaran = "Kartu Kredit";
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            } 
            if (pilihan < 1 || pilihan > 3) {
                continue;
            }
        } while (pilihan < 1 || pilihan > 3);
    }

    @Override
    public void cetakTiket() {
        System.out.println("\n========== TIKET PEMESANAN ==========");
        System.out.println("Nomor Pemesanan : " + this.nomorPemesanan);
        System.out.println("Jumlah Tiket    : " + this.jumlahPemesanan);
        System.out.println("Metode Bayar    : " + this.metodePembayaran);
        System.out.println("Total Bayar     : Rp " + this.totalBayar);
        System.out.println("=====================================");
    }

    // Setter
    public void setKodePemesanan(int nomorPemesanan) {
        this.nomorPemesanan = nomorPemesanan;
    }

    public void setKodeJadwal(JadwalFilm jadwal) {
        this.jadwal = jadwal;
    }

    public void setJumlahPemesanan(int jumlahPemesanan) {
        this.jumlahPemesanan = jumlahPemesanan;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setTotalBayar(float totalBayar) {
        this.totalBayar = totalBayar;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter
    public int getKodePemesanan() {
        return nomorPemesanan;
    }

    public JadwalFilm getKodeJadwal() {
        return jadwal;
    }

    public int getJumlahPemesanan() {
        return jumlahPemesanan;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public float getTotalBayar() {
        return totalBayar;
    }

    public User getUser() {
        return user;
    }
}