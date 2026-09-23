// library untuk memakai arraylist
import java.util.ArrayList;
import java.util.Scanner;

public class Film {
    private static Scanner scanner = new Scanner(System.in);

    // private attributes
    private Integer kodeFilm;
    private String judul;
    private String genre;
    private Integer durasi;
    private ArrayList<JadwalFilm> listJadwal;

    // Constructor 1: lengkap
    public Film(Integer kodeFilm, String judul, String genre, Integer durasi) {
        this.kodeFilm = kodeFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.listJadwal = new ArrayList<>();
    }

    // Constructor 2: user input
    public Film() {
        System.out.print("Masukkan Kode Film: ");
        this.kodeFilm = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan Judul: ");
        this.judul = scanner.nextLine();

        System.out.print("Masukkan Genre: ");
        this.genre = scanner.nextLine();

        System.out.print("Masukkan Durasi: ");
        this.durasi = scanner.nextInt();

        this.listJadwal = new ArrayList<JadwalFilm>();
    }

     // ArrayList jadwal yang masih kosong tadi, kita masukkin object Jadwal
     public void addJadwal(JadwalFilm jadwal) {
        this.listJadwal.add(jadwal);
    }

    // setters and getters
    public Integer getKodeFilm() {
        return kodeFilm;
    }

    public void setKodeFilm(Integer kodeFilm) {
        this.kodeFilm = kodeFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }
}