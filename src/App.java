import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    static ArrayList<Bioskop> listBioskop = new ArrayList<>();
    static ArrayList<Studio> listStudio = new ArrayList<>();
    static ArrayList<Film> listFilm = new ArrayList<>();
    static ArrayList<JadwalFilm> listJadwal = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        DataFile.readBioskop(listBioskop);
        DataFile.readStudio(listStudio, listBioskop);
        DataFile.readFilm(listFilm);
        DataFile.readJadwal(listJadwal, listStudio, listFilm);


        addStudio();
        showStudio();

        


    }

    static void addBioskop () {
        listBioskop.add(new Bioskop(listBioskop));
        showBioskop();
        DataFile.writeBioskop(listBioskop);
    }

    static void addStudio () {
        if (listBioskop.isEmpty()) {
            System.out.println("Belum ada bioskop yang terdaftar!");
            return;
        }
        listStudio.add(new Studio(listStudio, listBioskop));
        showStudio();
        DataFile.writeStudio(listStudio);
    }

    static void addFilm() {
        listFilm.add(new Film(listFilm));
        showFilm();
        DataFile.writeFilm(listFilm);
    }

    static void addJadwal() {
        if (listBioskop.isEmpty() || listStudio.isEmpty() || listFilm.isEmpty()) {
            System.out.println("Data bioskop, studio, dan film harus ada dulu!");
            return;
        }
        else{
            listJadwal.add(new JadwalFilm(listJadwal, listBioskop, listStudio, listFilm));
            showJadwal();
            DataFile.writeJadwal(listJadwal);
        }
    }

    static void showBioskop() {
        if (listBioskop.isEmpty()) {
             System.out.println("Belum ada data bioskop!");
             return;
         }
        else {
             for (Bioskop b : listBioskop) {
                b.showBioskop();
            }
        }
    }

    static void showStudio() {
        if (listStudio.isEmpty()) {
            System.out.println("Belum ada data studio!");
            return;
        }
        else {
            for (Studio s : listStudio) {
                s.showStudio();
            }
        }
    }

    static void showFilm() {
        if (listFilm.isEmpty()) {
            System.out.println("Belum ada data film!");
            return;
        }
        for (Film f : listFilm) {
            f.showFilm();
        }
    }

    static void showJadwal() {
        if (listJadwal.isEmpty()) {
            System.out.println("Belum ada data jadwal!");
            return;
        }
        for (JadwalFilm j : listJadwal) {
            j.showJadwal();
        }
    }

    static void editBioskop() {
        if (listBioskop.isEmpty()) {
            System.out.println("Belum ada data bioskop!");
            return;
        }

        Bioskop target;
        do{
            showBioskop();
            System.out.print("Masukkan kode bioskop yang diubah: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();

            // Cari datanya
            target = null;
            for (Bioskop b : listBioskop) {
                if (b.getKodeBioskop() == inputKode) {
                    target = b; // simpan objeknya
                    break;
                }
            }
            // jika data tidak ada
            if (target == null) {
                System.out.println("Bioskop tidak ditemukan!");
            }
        } while (target == null);
            
        // Pilih bagian yang ingin diubah
        int pilihan;
        do {
            System.out.println("Apa yang ingin diubah?");
            System.out.println("1. Nama Bioskop : " + target.getNamaBioskop());
            System.out.println("2. Lokasi : " + target.getLokasi());
            System.out.println("0. Kembali");
            System.out.print("Pilih(1/2/0): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Nama baru: ");
                    target.setNamaBioskop(scanner.nextLine());
                    DataFile.writeBioskop(listBioskop);
                    System.out.println("Nama berhasil diubah.");
                    break;
                case 2:
                    System.out.print("Lokasi baru: ");
                    target.setLokasi(scanner.nextLine());
                    DataFile.writeBioskop(listBioskop);
                    System.out.println("Lokasi berhasil diubah.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while(pilihan != 0);
        // Tampilkan hasil akhir
        target.showBioskop();
    }

    static void editStudio() {
        if (listStudio.isEmpty()) {
            System.out.println("Belum ada data studio!");
            return;
        }

        Studio target;
        do{
            showStudio();
            System.out.print("Masukkan kode studio yang diubah: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();

            target = cariStudio(inputKode);
        } while(target == null);
            
        // Pilih bagian yang ingin diubah
        int pilihan;
        do {
            System.out.println("Apa yang ingin diubah?");
            System.out.println("1. Nomor Studio : " + target.getNomorStudio());
            System.out.println("2. Jenis Studio : " + target.getJenisStudio());
            System.out.println("3. Harga Studio : " + target.getHarga());
            System.out.println("0. Kembali");
            System.out.print("Pilih(1/2/3/0): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Nomor studio baru: ");
                    target.setNomorStudio(scanner.nextInt());
                    DataFile.writeStudio(listStudio);
                    System.out.println("Nomor studio berhasil diubah.");
                    break;
                case 2:
                    System.out.print("Jenis studio baru: ");
                    target.setJenisStudio(scanner.nextLine());
                    DataFile.writeStudio(listStudio);
                    System.out.println("Jenis studio berhasil diubah.");
                    break;
                case 3:
                    System.out.print("Harga studio baru: ");
                    target.setHarga(scanner.nextFloat());
                    DataFile.writeStudio(listStudio);
                    System.out.println("Harga studio berhasil diubah.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while(pilihan != 0);
        // Tampilkan hasil akhir
        target.showStudio();
    }

    static void deleteBioskop() {
        if (listBioskop.isEmpty()) {
            System.out.println("Belum ada data bioskop!");
            return;
        }
    
        Bioskop target;
        do {
            showBioskop();
    
            System.out.print("Masukkan kode bioskop yang dihapus: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();
    
            // Cari datanya
            target = null;
            for (Bioskop b : listBioskop) {
                if (b.getKodeBioskop() == inputKode) {
                    target = b;
                    break;
                }
            }
    
            // Jika data tidak ada
            if (target == null) {
                System.out.println("Bioskop tidak ditemukan!");
            }
        } while (target == null);
    
        // Cek apakah bioskop ini masih memiliki studio
        boolean adaStudio = false;
        for (Studio s : listStudio) {
            if (s.getBioskop() == target) {
                adaStudio = true;
                break;
            }
        }
        // Jika masih memiliki studio
        if (adaStudio) {
            System.out.println("Bioskop tidak bisa dihapus karena masih memiliki studio!");
            return;
        }
        // Hapus data
        listBioskop.remove(target);
        DataFile.writeBioskop(listBioskop);
        System.out.println("Bioskop berhasil dihapus!");
    }

    static void editFilm() {
        if (listFilm.isEmpty()) {
            System.out.println("Belum ada data film!");
            return;
        }
    
        Film target;
        do{
            showFilm();
            System.out.print("Masukkan kode film yang diubah: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();

            target = cariFilm(inputKode);
        } while(target == null);
    
        // Pilih bagian yang ingin diubah
        int pilihan;
        do {
            System.out.println("Apa yang ingin diubah?");
            System.out.println("1. Judul Film : " + target.getJudul());
            System.out.println("2. Genre Film : " + target.getGenre());
            System.out.println("3. Durasi Film : " + target.getDurasi());
            System.out.println("0. Kembali");
            System.out.print("Pilih(1/2/3/0): ");
    
            pilihan = scanner.nextInt();
            scanner.nextLine();
    
            switch (pilihan) {
                case 1:
                    System.out.print("Judul film baru: ");
                    target.setJudul(scanner.nextLine());
                    DataFile.writeFilm(listFilm);
                    System.out.println("Judul film berhasil diubah.");
                    break;
                case 2:
                    System.out.print("Genre film baru: ");
                    target.setGenre(scanner.nextLine());
                    DataFile.writeFilm(listFilm);
                    System.out.println("Genre film berhasil diubah.");
                    break;
                case 3:
                    System.out.print("Durasi film baru: ");
                    target.setDurasi(scanner.nextInt());
                    scanner.nextLine();
                    DataFile.writeFilm(listFilm);
                    System.out.println("Durasi film berhasil diubah.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    
        // Tampilkan hasil akhir
        target.showFilm();
    }
    
    static void editJadwalFilm() {
        if (listJadwal.isEmpty()) {
            System.out.println("Belum ada data jadwal film!");
            return;
        }

        JadwalFilm target;
        do{
            showJadwal();
            System.out.print("Masukkan kode jadwal yang diubah: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();

            target = cariJadwal(inputKode);
        } while(target == null);

        // Pilih bagian yang ingin diubah
        int pilihan;
        do {
            System.out.println("Apa yang ingin diubah?");
            System.out.println("1. Studio : " + target.getStudio().getNomorStudio());
            System.out.println("2. Film : " + target.getFilm().getJudul());
            System.out.println("3. Tanggal : " + target.getTanggal());
            System.out.println("4. Jam : " + target.getJam());
            System.out.println("5. Kapasitas : " + target.getKapasitas());
            System.out.println("0. Kembali");
            System.out.print("Pilih(1/2/3/4/5/0): ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {

                case 1:
                    System.out.println("\nDaftar Studio:");
                    showStudio();

                    Studio studioTerpilih = null;
                    do {
                        System.out.print("Masukkan Kode Studio baru: ");
                        int inputKodeStudio = scanner.nextInt();
                        scanner.nextLine();

                        studioTerpilih = null;
                        for (Studio s : listStudio) {
                            if (s.getKodeStudio() == inputKodeStudio) {
                                studioTerpilih = s;
                                break;
                            }
                        }

                        if (studioTerpilih == null) {
                            System.out.println("Kode studio tidak ditemukan!");
                        }

                    } while (studioTerpilih == null);

                    target.setStudio(studioTerpilih);
                    DataFile.writeJadwal(listJadwal);

                    System.out.println("Studio berhasil diubah.");
                    break;

                case 2:
                    System.out.println("\nDaftar Film:");
                    showFilm();

                    Film filmTerpilih = null;
                    do {
                        System.out.print("Masukkan Kode Film baru: ");
                        int inputKodeFilm = scanner.nextInt();
                        scanner.nextLine();

                        filmTerpilih = null;
                        for (Film f : listFilm) {
                            if (f.getKodeFilm() == inputKodeFilm) {
                                filmTerpilih = f;
                                break;
                            }
                        }

                        if (filmTerpilih == null) {
                            System.out.println("Kode film tidak ditemukan!");
                        }

                    } while (filmTerpilih == null);

                    target.setFilm(filmTerpilih);
                    DataFile.writeJadwal(listJadwal);
                    System.out.println("Film berhasil diubah.");
                    break;

                case 3:
                    LocalDate tanggalBaru = null;

                    while (tanggalBaru == null) {
                        System.out.print("Masukkan tanggal baru (yyyy-mm-dd): ");

                        try {
                            tanggalBaru = LocalDate.parse(scanner.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Format tanggal salah!");
                        }
                    }

                    target.setTanggal(tanggalBaru);

                    DataFile.writeJadwal(listJadwal);

                    System.out.println("Tanggal berhasil diubah.");
                    break;

                case 4:
                    LocalTime jamBaru = null;

                    while (jamBaru == null) {
                        System.out.print("Masukkan jam baru (hh:mm): ");

                        try {
                            jamBaru = LocalTime.parse(scanner.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Format jam salah! Contoh: 14:30");
                        }
                    }

                    target.setJam(jamBaru);

                    DataFile.writeJadwal(listJadwal);

                    System.out.println("Jam berhasil diubah.");
                    break;

                case 5:
                    System.out.print("Kapasitas baru: ");
                    target.setKapasitas(scanner.nextInt());
                    scanner.nextLine();

                    DataFile.writeJadwal(listJadwal);

                    System.out.println("Kapasitas berhasil diubah.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);

        // Tampilkan hasil akhir
        target.showJadwal();
    }

    static void detailBioskop() {
        if (listBioskop.isEmpty()) {
            System.out.println("Belum ada data bioskop!");
            return;
        }

        showBioskop();
        Bioskop target;
        do {
            System.out.print("Masukkan kode bioskop yang ingin dilihat: ");
            int inputKode = scanner.nextInt();
            scanner.nextLine();

            target = cariBioskop(inputKode);

            if (target == null) {
                System.out.println("Bioskop tidak ditemukan!");
                return;
            }
        } while (target == null);
    
        target.detailBioskop();
    }

    static Studio cariStudio(int inputKode) {
        for (Studio s : listStudio) {
            if (s.getKodeStudio() == inputKode) {
                return s;
            }
        }
        return null;
    }

    static Bioskop cariBioskop(int inputKode) {
        for (Bioskop b : listBioskop) {
            if (b.getKodeBioskop() == inputKode) {
                return b;
            }
        }
        return null;
    }

    static Film cariFilm(int inputKode) {
        for (Film f : listFilm) {
            if (f.getKodeFilm() == inputKode) {
                return f;
            }
        }
        return null;
    }

    static JadwalFilm cariJadwal(int inputKode) {
        for (JadwalFilm j : listJadwal) {
            if (j.getKodeJadwal() == inputKode) {
                return j;
            }
        }
        return null;
    }

}
