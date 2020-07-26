package nhom17.thuongmaidientu.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dondathang")
public class DonDatHang {
	/*madonhang int not null primary key identity(1,1),
	makh int,
	constraint fk_makh_dondathang foreign key(makh)
	references khachhang(makh) on delete cascade,
	ngaydathang datetime not null,
	trangthaidonhang int not null,*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madonhang")
	private int madonhang;

	@Column(name = "ngaydathang",nullable = false)
	private Date ngaydathang;
	
	@Column(name = "trangthaidonhang")
	private int trangthaidonhang;
	
	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachHang_dondathang;
	
	@OneToMany(mappedBy = "donDatHang_chitietDatHang",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<ChiTietDonHang> chiTietDonHangs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy = "donDatHang",cascade = CascadeType.REMOVE)
	private QuanLyNhanVien quanLyNhanVien;

	public QuanLyNhanVien getQuanLyNhanVien() {
		return quanLyNhanVien;
	}

	public Date getNgaydathang() {
		return ngaydathang;
	}

	public KhachHang getKhachHang_dondathang() {
		return khachHang_dondathang;
	}

	public Set<ChiTietDonHang> getChiTietDonHangs() {
		return chiTietDonHangs;
	}

	public void setNgaydathang(Date ngaydathang) {
		this.ngaydathang = ngaydathang;
	}

	public int getTrangthaidonhang() {
		return trangthaidonhang;
	}

	public void setTrangthaidonhang(int trangthaidonhang) {
		this.trangthaidonhang = trangthaidonhang;
	}

	public KhachHang getKhachHang_dondatHang() {
		return khachHang_dondathang;
	}

	public void setKhachHang_dondatHang(KhachHang khachHang_dondatHang) {
		this.khachHang_dondathang = khachHang_dondatHang;
	}

	public int getMadonhang() {
		return madonhang;
	}
	
}

