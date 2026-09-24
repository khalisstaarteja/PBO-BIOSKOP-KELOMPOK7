import java.util.Scanner;

public class Pelanggan extends User implements Login {
    // private attributes
    private String email;
    private static Scanner scanner = new Scanner(System.in);

    public Pelanggan(String nama, String password, String email) {
        super(nama, password);
        this.email = email;
    }

    public Pelanggan() {
        super("", "");

        System.out.print("Nama: ");
        this.setNama(scanner.nextLine());

        System.out.print("Email: ");
        this.email = scanner.nextLine();

        System.out.print("Password: ");
        this.setPassword(scanner.nextLine());
        
        // scanner.close() Dihapus agar program di App.java tidak error saat kembali ke menu utama
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void showMenu() {
        System.out.println("=== Menu Pelanggan ===");
        System.out.println("1. Lihat Film");
        System.out.println("2. Pesan Tiket");
        System.out.println("3. Lihat Jadwal Film");
        System.out.println("4. Riwayat Pesanan");
        System.out.println("5. Logout");
    }

    // Implementasi metode signUp dan signIn dari interface Login
    @Override
    public void signUp() {
        System.out.println("Pelanggan berhasil mendaftar.");
    }

    @Override
    public void signIn() {
        System.out.println("Pelanggan berhasil masuk.");
    }
}