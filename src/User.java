// library untuk input user
import java.util.Scanner;

public abstract class User {
    private static Scanner scanner = new Scanner(System.in);

    // private attributes
    private String kodeUser;
    private String nama;
    private String password;

    // Constructor 1: public
    public User(String kodeUser, String nama, String password) {
        this.kodeUser = kodeUser;
        this.nama = nama;
        this.password = password;
    }

    // Constructor 2: user input
    public User() {
        System.out.print("Kode User = ");
        this.kodeUser = scanner.nextLine();

        System.out.print("Nama = ");
        this.nama = scanner.nextLine();

        System.out.print("Password = ");
        this.password = scanner.nextLine();
    }

    // setters and getters - public
    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }
}