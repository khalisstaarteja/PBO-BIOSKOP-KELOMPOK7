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
    public Film(ArrayList<Film> listFilm) {
        boolean kodeSudahAda;
        do {
            System.out.print("Masukkan Kode Film: ");
            this.kodeFilm = scanner.nextInt();
            scanner.nextLine(); // clear buffer newline

            kodeSudahAda = false;
            for (Film f : listFilm) {
                // jika kode yang dimasukkan ada yang sama dengan data
                if (this.kodeFilm == f.getKodeFilm()) {
                    kodeSudahAda = true;
                    break;
                }
            }
            if (kodeSudahAda) {
                 System.out.println("Kode film sudah dipakai! Coba kode lain");
            }
        } while (kodeSudahAda);

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

    void showFilm() {
        System.out.println("Kode Film : " + kodeFilm);
        System.out.println("Judul     : " + judul);
        System.out.println("Genre     : " + genre);
        System.out.println("Durasi    : " + durasi);
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