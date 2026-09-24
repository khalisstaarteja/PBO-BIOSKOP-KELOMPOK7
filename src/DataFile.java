import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate; 
import java.time.LocalTime;

public class DataFile {
    public static void writeBioskop(ArrayList<Bioskop> listBioskop) {
        try {
            FileWriter fw = new FileWriter("src/bioskop.txt");
            for(Bioskop b : listBioskop) {
                fw.write(String.valueOf(b.getKodeBioskop()));  fw.write("|");
                fw.write(b.getNamaBioskop()); fw.write("|");
                fw.write(b.getLokasi());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Terjadi error!");
            e.printStackTrace();
        }
    }

    public static void readBioskop(ArrayList<Bioskop> listBioskop) {
        try {
            File file = new File("src/bioskop.txt");
            if (!file.exists()) return;
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] str = data.split("\\|");
                listBioskop.add(new Bioskop(Integer.parseInt(str[0]), (str[1]), (str[2])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Error!");
            e.printStackTrace();
        }
    }

    public static void writeStudio(ArrayList<Studio> listStudio) {
        try {
            FileWriter fw = new FileWriter("src/studio.txt");
            for(Studio s : listStudio) {
                fw.write(String.valueOf(s.getKodeStudio()));  fw.write("|");
                fw.write(String.valueOf(s.getNomorStudio())); fw.write("|");
                fw.write(s.getJenisStudio()); fw.write("|");
                fw.write(s.getBioskop().getNamaBioskop()); fw.write("|");
                fw.write(String.valueOf(s.getHarga())); 
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Terjadi error!");
            e.printStackTrace();
        }
    }

    public static void readStudio(ArrayList<Studio> listStudio, ArrayList<Bioskop> listBioskop) {
        File file = new File("src/studio.txt");
        if (!file.exists()) return;
    
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String[] str = myReader.nextLine().split("\\|");
                String namaBioskop = str[3];
    
                // cari objek Bioskop berdasarkan nama yang tersimpan di file
                Bioskop bioskop = null;
                for (Bioskop b : listBioskop) {
                    if (b.getNamaBioskop().equals(namaBioskop)) {
                        bioskop = b;
                        break;
                    }
                }
    
                if (bioskop != null) {
                    listStudio.add(new Studio(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
                            str[2], bioskop, Float.parseFloat(str[4])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Error!");
            e.printStackTrace();
        }
    }

    public static void writeFilm(ArrayList<Film> listFilm) {
        try (FileWriter fw = new FileWriter("src/film.txt")) {
            for (Film f : listFilm) {
                fw.write(f.getKodeFilm() + "|" + f.getJudul() + "|" + f.getGenre() + "|" + f.getDurasi());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Terjadi error!");
            e.printStackTrace();
        }
    }

    public static void readFilm(ArrayList<Film> listFilm) {
        File file = new File("src/film.txt");
        if (!file.exists()) return;
    
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split("\\|");
                listFilm.add(new Film(Integer.parseInt(p[0]), p[1], p[2], Integer.parseInt(p[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Error!");
            e.printStackTrace();
        }
    }
    
    public static void writeJadwal(ArrayList<JadwalFilm> listJadwal) {
        try (FileWriter fw = new FileWriter("src/jadwal.txt")) {
            for (JadwalFilm j : listJadwal) {
                fw.write(j.getKodeJadwal() + "|"
                        + j.getStudio().getBioskop().getKodeBioskop() + "|"
                        + j.getStudio().getKodeStudio() + "|"
                        + j.getFilm().getKodeFilm() + "|"
                        + j.getTanggal() + "|"
                        + j.getJam() + "|"
                        + j.getKapasitas());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Terjadi error!");
            e.printStackTrace();
        }
    }
    
    public static void readJadwal(ArrayList<JadwalFilm> listJadwal, ArrayList<Studio> listStudio, ArrayList<Film> listFilm) {
        File file = new File("src/jadwal.txt");
        if (!file.exists()) return;
    
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split("\\|");
                int kodeBioskop = Integer.parseInt(p[1]);
                int kodeStudio = Integer.parseInt(p[2]);
                int kodeFilm = Integer.parseInt(p[3]);
    
                Studio studio = null;
                for (Studio s : listStudio) {
                    if (s.getBioskop().getKodeBioskop() == kodeBioskop && s.getKodeStudio() == kodeStudio) {
                        studio = s;
                        break;
                    }
                }
                Film film = null;
                for (Film f : listFilm) {
                    if (f.getKodeFilm() == kodeFilm) {
                        film = f;
                        break;
                    }
                }
    
                if (studio != null && film != null) {
                    JadwalFilm j = new JadwalFilm(Integer.parseInt(p[0]), studio, film,
                            LocalDate.parse(p[4]), LocalTime.parse(p[5]), Integer.parseInt(p[6]));
                    listJadwal.add(j);
                    film.addJadwal(j);   // masuk ke listJadwal milik film itu
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Error!");
            e.printStackTrace();
        }
    }
}
