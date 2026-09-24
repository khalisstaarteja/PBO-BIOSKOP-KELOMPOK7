public class Admin extends User implements Login {
    
    private boolean loginStatus = false;

    public Admin(String username, String password) {
        super(username, password); 
    }
    
    public boolean isLoginBerhasil() {
        return loginStatus;
    }
    
    void showMenu() {
        System.out.println("\n=== Menu Admin ===");
        System.out.println("1. Tambah Bioskop");
        System.out.println("2. Edit Bioskop");
        System.out.println("3. Tambah Studio");
        System.out.println("4. Edit Studio");
        System.out.println("5. Tambah Film");
        System.out.println("6. Edit Film");
        System.out.println("7. Tambah Jadwal Film");
        System.out.println("8. Lihat Jadwal Film");
    }

    // Implementasi metode signUp dan signIn dari interface Login
    @Override
    public void signUp() {
        System.out.println("Admin berhasil mendaftar.");
    }

    @Override
    public void signIn() {
        // Untuk sementara, kita atur agar admin selalu berhasil masuk setiap kali fungsi ini dipanggil
        System.out.println("Admin berhasil masuk.");
        this.loginStatus = true; // Mengubah status menjadi true agar menu terbuka
    }
}