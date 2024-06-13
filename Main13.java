import java.util.Scanner;

public class Main13 {
    static BarangRental13[] daftarBarang = new BarangRental13[100];
    static TransaksiRental13[] daftarTransaksi = new TransaksiRental13[100];
    static int jumlahBarang = 0;
    static int jumlahTransaksi = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiDataBarang();
        menuUtama();
    }

    private static void inisialisasiDataBarang() {
        daftarBarang[jumlahBarang++] = new BarangRental13("S 4567 YV", "Honda Beat", "Motor", 2017, 25000);
        daftarBarang[jumlahBarang++] = new BarangRental13("N 4511 VS", "Honda Vario", "Motor", 2018, 25000);
        daftarBarang[jumlahBarang++] = new BarangRental13("N 1453 AA", "Toyota Yaris", "Mobil", 2022, 40000);
        daftarBarang[jumlahBarang++] = new BarangRental13("AB 4321 A", "Toyota Innova", "Mobil", 2019, 40000);
        daftarBarang[jumlahBarang++] = new BarangRental13("B 1234 AG", "Toyota Avanza", "Mobil", 2021, 40000);
    }

    private static void menuUtama() {
        int pilihan;
        do {
            System.out.println("\nMenu");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Peminjaman");
            System.out.println("3. Tampilkan seluruh transaksi");
            System.out.println("4. Urutkan Transaksi urut no TNKB");
            System.out.println("5. Keluar");
            System.out.print("Pilih(1-5): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    lihatDataBarang();
                    break;
                case 2:
                    tambahTransaksi();
                    break;
                case 3:
                    lihatTransaksi();
                    break;
                case 4:
                    menuUrutkanTransaksi();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Gak valid. coba lagi.");
            }
        } while (pilihan != 5);
    }

    private static void lihatDataBarang() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Daftar Kendaraan Rental Serba Serbi");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("| %-11s | %-15s | %-5s | %-4s | %-17s | %-10s |\n", "Nomor TNKB", "Nama Kendaraan", "Jenis",
                "Tahun", "Biaya Sewa Perjam", "Status");
        for (int i = 0; i < jumlahBarang; i++) {
            BarangRental13 br = daftarBarang[i];
            String status = br.tersedia ? "Tersedia" : "Tidak Tersedia";
            System.out.printf("| %-11s | %-15s | %-5s | %-4d | %-17d | %-10s |\n", br.noTNKB, br.namaKendaraan,
                    br.jenisKendaraan, br.tahun, br.biayaSewa, status);
        }
    }

    private static void tambahTransaksi() {
        System.out.println("\n=== Tambah Transaksi ===");
        System.out.print("Nama Peminjam: ");
        String namaPeminjam = scanner.nextLine();
        System.out.print("Masukkan Nomer TNKB: ");
        String noTNKB = scanner.nextLine();
        System.out.print("Lama Pinjam (jam): ");
        int lamaPinjam = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Apakah Member (ya/tidak): ");
        boolean isMember = scanner.nextLine().equalsIgnoreCase("ya");

        BarangRental13 barang = cariBarang(noTNKB);
        if (barang == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }

        if (!barang.tersedia) {
            System.out.println("Tidak bisa diproses, kendaraan sudah dipinjam orang lain.");
            return;
        }

        TransaksiRental13 transaksi = new TransaksiRental13(namaPeminjam, lamaPinjam, barang, isMember);
        daftarTransaksi[jumlahTransaksi++] = transaksi;
        barang.tersedia = false;

        System.out.println("Transaksi berhasil ditambahkan.");
        System.out.println("Kode Transaksi: " + transaksi.kodeTransaksi);
        System.out.println("Nama Peminjam: " + transaksi.namaPeminjam);
        System.out.println("Lama Pinjam: " + transaksi.lamaPinjam);
        System.out.println("Total Biaya: " + transaksi.totalBiaya);
    }

    private static BarangRental13 cariBarang(String noTNKB) {
        for (int i = 0; i < jumlahBarang; i++) {
            BarangRental13 br = daftarBarang[i];
            if (br.noTNKB.equalsIgnoreCase(noTNKB)) {
                return br;
            }
        }
        return null;
    }

    private static void lihatTransaksi() {
        double totalPendapatan = 0;
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Daftar Transaksi Peminjaman Rental Serba Serbi");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-10s | %-17s |\n", "Kode", "No TNKB", "Nama Barang",
                "Nama Peminjam", "Lama Pinjam", "Total Biaya");
        for (int i = 0; i < jumlahTransaksi; i++) {
            TransaksiRental13 tr = daftarTransaksi[i];
            totalPendapatan += tr.totalBiaya;
            System.out.printf("| %-4d | %-11s | %-15s | %-15s | %-10d | %-17.0f |\n", tr.kodeTransaksi, tr.br.noTNKB,
                    tr.br.namaKendaraan, tr.namaPeminjam, tr.lamaPinjam, tr.totalBiaya);
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TOTAL PENDAPATAN RENTAL SERBA SERBI");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("Pendapatan hari ini :%.0f\n", totalPendapatan);
    }

    private static void menuUrutkanTransaksi() {
        System.out.println("\nUrutkan Transaksi");
        System.out.println("1. Huruf pertama Nama lengkap A-M, urutkan Nomor TNKB");
        System.out.println("2. Huruf pertama Nama lengkap N-Z, urutkan Nama ");
        System.out.print("Pilih(1-2): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1:
                urutkanTransaksiByTNKB();
                break;
            case 2:
                urutkanTransaksiByNama();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void urutkanTransaksiByTNKB() {
        TransaksiRental13[] transaksiAM = new TransaksiRental13[jumlahTransaksi];
        int countAM = 0;

        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].namaPeminjam.matches("^[A-Ma-m].*")) {
                transaksiAM[countAM++] = daftarTransaksi[i];
            }
        }

        for (int i = 0; i < countAM - 1; i++) {
            for (int j = 0; j < countAM - i - 1; j++) {
                if (transaksiAM[j].br.noTNKB.compareTo(transaksiAM[j + 1].br.noTNKB) < 0) {
                    TransaksiRental13 temp = transaksiAM[j];
                    transaksiAM[j] = transaksiAM[j + 1];
                    transaksiAM[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < countAM; i++) {
            TransaksiRental13 tr = transaksiAM[i];
            System.out.printf("| %-4d | %-11s | %-15s | %-15s | %-10d | %-17.0f |\n",
                    tr.kodeTransaksi, tr.br.noTNKB, tr.br.namaKendaraan, tr.namaPeminjam, tr.lamaPinjam,
                    tr.totalBiaya);
        }
    }

    private static void urutkanTransaksiByNama() {
        TransaksiRental13[] transaksiNZ = new TransaksiRental13[jumlahTransaksi];
        int countNZ = 0;

        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].namaPeminjam.matches("^[N-Zn-z].*")) {
                transaksiNZ[countNZ++] = daftarTransaksi[i];
            }
        }

        for (int i = 0; i < countNZ - 1; i++) {
            for (int j = 0; j < countNZ - i - 1; j++) {
                if (transaksiNZ[j].namaPeminjam.compareTo(transaksiNZ[j + 1].namaPeminjam) < 0) {
                    TransaksiRental13 temp = transaksiNZ[j];
                    transaksiNZ[j] = transaksiNZ[j + 1];
                    transaksiNZ[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < countNZ; i++) {
            TransaksiRental13 tr = transaksiNZ[i];
            System.out.printf("| %-4d | %-11s | %-15s | %-15s | %-10d | %-17.0f |\n",
                    tr.kodeTransaksi, tr.br.noTNKB, tr.br.namaKendaraan, tr.namaPeminjam, tr.lamaPinjam,
                    tr.totalBiaya);
        }
    }
}
