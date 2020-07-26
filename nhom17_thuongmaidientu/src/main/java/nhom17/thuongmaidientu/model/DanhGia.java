package nhom17.thuongmaidientu.model;

import javax.persistence.Table;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "danhgia")
public class DanhGia {
/*madanhgia int not null primary key identity(1,1),
	ngaydanhgia date not null,
	sosao int null,
	masanpham int not null,
	constraint fk_masanpham_danhgia foreign key(masanpham)
	references sanpham(masanpham) on delete cascade,
	makh int not null,
	constraint fk_makh_danhgia foreign key(makh)
	references khachhang(makh) on delete cascade*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madanhgia")
	private int madanhgia;
	
	@Column(name = "ngaydanhgia",nullable = false)
	private Date ngaydanhgia;
	
	@Column(name = "binhluan")
	private String binhluan;
	
	@ManyToOne
	@JoinColumn(name = "masanpham")
	private SanPham sanPham_danhgia;
	
	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachHang_danhgia;

	public Date getNgaydanhgia() {
		return ngaydanhgia;
	}

	public void setNgaydanhgia(Date ngaydanhgia) {
		this.ngaydanhgia = ngaydanhgia;
	}

	public String getBinhluan() {
		return binhluan;
	}

	public void setBinhluan(String binhluan) {
		this.binhluan = binhluan;
	}

	public SanPham getSanPham_danhgia() {
		return sanPham_danhgia;
	}

	public void setSanPham_danhgia(SanPham sanPham_danhgia) {
		this.sanPham_danhgia = sanPham_danhgia;
	}

	public KhachHang getKhachHang_danhgia() {
		return khachHang_danhgia;
	}

	public void setKhachHang_danhgia(KhachHang khachHang_danhgia) {
		this.khachHang_danhgia = khachHang_danhgia;
	}

	public int getMadanhgia() {
		return madanhgia;
	}
	
	
}
