package nhom17.thuongmaidientu.model;

import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name = "sanpham")
public class SanPham {
/*masanpham int not null primary key identity(1,1),
	tensanpham nvarchar(150) not null,
	ngaysanxuat date not null,
	hansudung date null,
	noidung nvarchar(100) null,
	giaca float,
	hinhanh varchar(250),
	soluong int not null,
	maloaisanpham int,
	constraint fk_maloaisanpham foreign key(maloaisanpham)
	references loaisanpham(maloaisanpham),
	manhacc int,
	constraint fk_manhacc foreign key(manhacc)
	references nhacungcap(manhacc)*/
	
	public SanPham() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masanpham")
	private int masanpham;
	
	@Column(name = "tensanpham",nullable = false)
	private String tensanpham;
	
	@Column(name = "ngaysanxuat",nullable = false)
	private Date ngaysanxuat;
	
	@Column(name = "hansudung",nullable = true)
	private Date hansudung;
	
	@Column(name = "noidung",nullable = true)
	private String noidung;
	
	@Column(name = "giaca")
	private float giaca;
	
	@Column(name = "hinhanh")
	private String hinhanh;
	
	@Column(name = "soluong")
	private int soluong;
	
	@ManyToOne
	@JoinColumn(name = "maloaisanpham")
	private LoaiSanPham loaisanpham_sp;
	
	@ManyToOne
	@JoinColumn(name = "manhacc")
	private NhaCungCap manhacc_sp;
	
	@OneToMany(mappedBy = "sanPham_danhgia",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<DanhGia> danhGias;
	
	@OneToMany(mappedBy = "sanPham_chitietDatHang",fetch = FetchType.EAGER)
	private Set<ChiTietDonHang> chiTietDonHangs;
	
	public LoaiSanPham getLoaisanpham_sp() {
		return loaisanpham_sp;
	}

	public void setLoaisanpham_sp(LoaiSanPham loaisanpham_sp) {
		this.loaisanpham_sp = loaisanpham_sp;
	}

	public NhaCungCap getManhacc_sp() {
		return manhacc_sp;
	}

	public void setManhacc_sp(NhaCungCap manhacc_sp) {
		this.manhacc_sp = manhacc_sp;
	}

	public Set<ChiTietDonHang> getChiTietDonHangs() {
		return chiTietDonHangs;
	}

	public void setChiTietDonHangs(Set<ChiTietDonHang> chiTietDonHangs) {
		this.chiTietDonHangs = chiTietDonHangs;
	}

	public Set<DanhGia> getDanhGias()
	{
		return this.danhGias;
	}

	public String getTensanpham() {
		return tensanpham;
	}

	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}

	public Date getNgaysanxuat() {
		return ngaysanxuat;
	}

	public void setNgaysanxuat(java.util.Date ngaysx) {
		this.ngaysanxuat = ngaysx;
	}

	public Date getHansudung() {
		return hansudung;
	}

	public void setHansudung(Date hansudung) {
		this.hansudung = hansudung;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public float getGiaca() {
		return giaca;
	}

	public void setGiaca(float giaca) {
		this.giaca = giaca;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public LoaiSanPham getloaisanpham() {
		return loaisanpham_sp;
	}

	public void setloaisanpham(LoaiSanPham loaisanpham) {
		this.loaisanpham_sp = loaisanpham;
	}

	public NhaCungCap getManhacc() {
		return manhacc_sp;
	}

	public void setManhacc(NhaCungCap manhacc) {
		this.manhacc_sp = manhacc;
	}

	public int getMasanpham() {
		return masanpham;
	}
	
}
