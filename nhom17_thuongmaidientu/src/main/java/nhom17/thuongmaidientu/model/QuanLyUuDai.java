package nhom17.thuongmaidientu.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quanlyuudai")
public class QuanLyUuDai {
/*maquanlyuudai int not null primary key identity(1,1),
	makh int not null,
	constraint fk_makh_uudai foreign key(makh)
	references khachhang(makh) on delete cascade,
	mauudai int not null,
	constraint fk_mauudai_uudai foreign key(mauudai)
	references uudai(mauudai) on delete cascade,
	ngaybatdau date not null,
	ngayketthuc date not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maquanlyuudai")
	private int maquanlyuudai;
	
	@Column(name = "ngaybatdau",nullable = false)
	private Date ngaybatdau;
	
	@Column(name = "ngayketthuc",nullable = false)
	private Date ngayketthuc;
	
	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachHang_quanlyUuDai;
	
	@ManyToOne
	@JoinColumn(name = "mauudai")
	private UuDai uuDai_quanlyUuDai;

	public Date getNgaybatdau() {
		return ngaybatdau;
	}

	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}

	public Date getNgayketthuc() {
		return ngayketthuc;
	}

	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}

	public KhachHang getKhachHang_quanlyUuDai() {
		return khachHang_quanlyUuDai;
	}

	public void setKhachHang_quanlyUuDai(KhachHang khachHang_quanlyUuDai) {
		this.khachHang_quanlyUuDai = khachHang_quanlyUuDai;
	}

	public UuDai getUuDai_quanlyUuDai() {
		return uuDai_quanlyUuDai;
	}

	public void setUuDai_quanlyUuDai(UuDai uuDai_quanlyUuDai) {
		this.uuDai_quanlyUuDai = uuDai_quanlyUuDai;
	}

	public int getMaquanlyuudai() {
		return maquanlyuudai;
	}
	
}
