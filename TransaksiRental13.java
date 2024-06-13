public class TransaksiRental13 {
    private static int autoIncrement = 1;
    int kodeTransaksi;
    String namaPeminjam;
    int lamaPinjam;
    double totalBiaya;
    BarangRental13 br;

    public TransaksiRental13(String namaPeminjam, int lamaPinjam, BarangRental13 br, boolean isMember) {
        this.kodeTransaksi = autoIncrement++;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.br = br;
        this.totalBiaya = hitungTotalBiaya(lamaPinjam, br.biayaSewa, isMember);
    }

    private double hitungTotalBiaya(int lamaPinjam, int biayaSewa, boolean isMember) {
        double total = lamaPinjam * biayaSewa;

        // Potongan harga untuk member rental serba-serbi
        if (isMember) {
            total -= 25000;
        }

        // Potongan harga berdasarkan lama pinjam rental serba serbi. 48 sampai 78 jam
        // mendapat potongan 10%, lebih dari 78 jam mendapat potongan 20%
        if (lamaPinjam > 78) {
            total *= 0.8;
        } else if (lamaPinjam >= 48) {
            total *= 0.9;
        }

        return total;
    }
}
