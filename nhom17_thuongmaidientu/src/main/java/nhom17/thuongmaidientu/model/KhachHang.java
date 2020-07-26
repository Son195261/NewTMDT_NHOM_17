package nhom17.thuongmaidientu.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class KhachHang {
	/*makh int not null primary key identity(1,1),
	tenkh nvarchar(25) not null,
	sodienthoai varchar(11) not null,
	diachi nvarchar(30) not null,
	email varchar(25) not null,
	matkhau varchar(11) not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "makh")
	private int makh;
	
	@Column(name = "tenkh",nullable = false)
	private String tenkh;
	
	@Column(name = "sodienthoai",nullable = false)
	private String sodienthoai;
	
	@Column(name = "diachi",nullable = false)
	private String diachi;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "matkhau",nullable = false)
	private String matkhau;

	@OneToMany(mappedBy = "khachHang_danhgia",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<DanhGia> danhGias;
	
	@OneToMany(mappedBy = "khachHang_dondathang",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<DonDatHang> khachHangs;
	
	@OneToMany(mappedBy = "khachHang_quanlyUuDai",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<QuanLyUuDai> quanLyUuDais;
	
	public Set<QuanLyUuDai> getQuanLyUuDais()
	{
		return this.quanLyUuDais;
	}
		
	public Set<DonDatHang> getKhachHangs() {
		return khachHangs;
	}

	public Set<DanhGia> getDanhGias()
	{
		return this.danhGias;
	}
	
	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public int getMakh() {
		return makh;
	}
	
}
