package Tugas;

import java.util.*;

public class Main {
    static List<Barang> daftarBarang = new ArrayList<>();
    static List<Order> daftarPesanan = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // seed data
        daftarBarang.add(new Barang(1, "Pulpen", 10, 5000));
        daftarBarang.add(new Barang(2, "Pensil", 15, 3000));
        daftarBarang.add(new Barang(3, "Buku Tulis", 20, 10000));
        daftarBarang.add(new Barang(4, "Penghapus", 12, 2000));

        while (true) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int menu = in.nextInt();

            if (menu == 1) {
                pesanBarang();
            } else if (menu == 2) {
                lihatPesanan();
            } else if (menu == 0) {
                System.out.println("Terima kasih sudah menggunakan program.");
                in.close();
                break;
            } else {
                System.out.println("Input tidak valid!");
            }
        }
    }

    static void pesanBarang() {
        System.out.println("\n=== Daftar Barang ===");
        for (Barang b : daftarBarang) {
            System.out.println(b.getId() + ". " + b.getNama() +
                    " (Stock: " + b.getStock() +
                    ", Harga: " + b.getHarga() + ")");
        }
        System.out.println("0. Kembali ke menu utama");
        System.out.print("Masukkan ID Barang: ");
        int id = in.nextInt();

        if (id == 0) return;

        Barang barangDipilih = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                barangDipilih = b;
                break;
            }
        }

        if (barangDipilih == null) {
            System.out.println("ID Barang tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int jumlah = in.nextInt();

        if (jumlah <= 0 || jumlah > barangDipilih.getStock()) {
            System.out.println("Jumlah tidak valid!");
            return;
        }

        System.out.print("Masukkan harga (seharusnya " + barangDipilih.getHarga() + "): ");
        int harga = in.nextInt();

        if (harga != barangDipilih.getHarga()) {
            System.out.println("Harga tidak sesuai!");
            return;
        }

        barangDipilih.minusStock(jumlah);
        daftarPesanan.add(new Order(id, barangDipilih, jumlah));
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    static void lihatPesanan() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        System.out.println("\n=== Daftar Pesanan ===");
        for (Order o : daftarPesanan) {
            System.out.println("Barang: " + o.getBarang().getNama() +
                    ", Jumlah: " + o.getJumlah() +
                    ", Harga: " + o.getBarang().getHarga() +
                    ", Subtotal: " + (o.getBarang().getHarga() * o.getJumlah()));
        }
        System.out.println("Total Biaya: " + Order.total);
    }
}
