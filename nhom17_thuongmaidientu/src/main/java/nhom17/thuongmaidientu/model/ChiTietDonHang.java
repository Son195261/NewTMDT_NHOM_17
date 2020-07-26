package nhom17.thuongmaidientu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHang {
	/*maquanlyhoadon int not null primary key identity(1,1),
	madonhang int not null,
	constraint fk_madonhang_chitietdonhang foreign key(madonhang)
	references dondathang(madonhang) on delete cascade,
	masanpham int not null,
	constraint fk_masanpham_chitietdonhang foreign key(masanpham)
	references sanpham(masanpham),
	soluong int not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maquanlyhoadon")
	private int maquanlyhoadon;
	
	@Column(name = "soluong")
	private int soluong;
	
	@ManyToOne
	@JoinColumn(name = "madonhang")
	private DonDatHang donDatHang_chitietDatHang;
	
	@ManyToOne
	@JoinColumn(name = "masanpham")
	private SanPham sanPham_chitietDatHang;

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public DonDatHang getDonDatHang_chitietDatHang() {
		return donDatHang_chitietDatHang;
	}

	public void setDonDatHang_chitietDatHang(DonDatHang donDatHang_chitietDatHang) {
		this.donDatHang_chitietDatHang = donDatHang_chitietDatHang;
	}

	public SanPham getSanPham_chitietDatHang() {
		return sanPham_chitietDatHang;
	}

	public void setSanPham_chitietDatHang(SanPham sanPham_chitietDatHang) {
		this.sanPham_chitietDatHang = sanPham_chitietDatHang;
	}

	public int getMaquanlyhoadon() {
		return maquanlyhoadon;
	}
	
}
