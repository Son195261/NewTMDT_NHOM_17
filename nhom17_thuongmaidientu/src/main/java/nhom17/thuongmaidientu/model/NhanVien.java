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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
	/*manv int not null primary key identity(1,1),
	tennv nvarchar(20) not null,
	ngaysinh date null,
	sodienthoai varchar(11) not null,
	diachi nvarchar(30) null,
	hesoluong float*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manv")
	private int manv;
	
	@Column(name = "tennv",nullable = false)
	private String tennv;
	
	@Column(name = "ngaysinh",nullable = true)
	private Date ngaysinh;
	
	@Column(name = "sodienthoai",nullable = false)
	private String sodienthoai;
	
	@Column(name = "diachi",nullable = true)
	private String diachi;
	
	@Column(name = "hesoluong")
	private float hesoluong;
	
	@Column(name = "matkhau")
	private String matkhau;
	
	@OneToMany(mappedBy = "nhanVien_quanlyNhanVien",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<QuanLyNhanVien> quanLyNhanViens;
	
	public Set<QuanLyNhanVien> getQuanLyNhanViens() {
		return quanLyNhanViens;
	}

	public void setQuanLyNhanViens(Set<QuanLyNhanVien> quanLyNhanViens) {
		this.quanLyNhanViens = quanLyNhanViens;
	}

	public String getTennv() {
		return tennv;
	}

	public void setTennv(String tennv) {
		this.tennv = tennv;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
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

	public float getHesoluong() {
		return hesoluong;
	}

	public void setHesoluong(float hesoluong) {
		this.hesoluong = hesoluong;
	}

	public int getManv() {
		return manv;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	
}
