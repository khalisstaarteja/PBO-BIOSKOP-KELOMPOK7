public class Admin extends User implements Login{
    // private attributes
    private String username;

    // Constructor 1: public
    public Admin(String kodeUser, String nama, String password, String username) {
        super(kodeUser, nama, password);
        this.username = username;
    }

    // public methods
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public void showMenu() {
        System.out.println("=== Menu Admin ===");
        System.out.println("1. Tambah Film");
        System.out.println("2. Hapus Film");
        System.out.println("3. Kelola Jadwal Film");
        System.out.println("4. Laporan Penjualan Tiket");
        System.out.println("5. Logout");
    }

    // Implementasi metode signUp dan signIn dari interface Login
    @Override
    public void signUp() {
        // Implementasi metode signUp untuk Admin
        System.out.println("Admin berhasil mendaftar.");
    }

    @Override
    public void signIn() {
        // Implementasi metode signIn untuk Admin
        System.out.println("Admin berhasil masuk.");
    }
}