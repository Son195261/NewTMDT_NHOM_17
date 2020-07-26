package nhom17.thuongmaidientu.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quanlynhanvien")
public class QuanLyNhanVien {
/*maquanlynhanvien int not null primary key identity(1,1),
	manv int not null,
	constraint fk_nhanvien_quanlynhanvien foreign key(manv)
	references nhanvien(manv) on delete cascade,
	madonhang int not null,
	constraint fk_donhang_quanlynhanvien foreign key(madonhang)
	references dondathang(madonhang) on delete cascade,
	ngaytiepnhan date not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maquanlynhanvien")
	private int maquanlynhanvien;
	
	@Column(name = "ngaytiepnhan",nullable = false)
	private Date ngaytiepnhan;
	
	@ManyToOne
	@JoinColumn(name = "manv")
	private NhanVien nhanVien_quanlyNhanVien;
	
	@OneToOne
	@JoinColumn(name = "madonhang")
	private DonDatHang donDatHang;

	public Date getNgaytiepnhan() {
		return ngaytiepnhan;
	}

	public void setNgaytiepnhan(Date ngaytiepnhan) {
		this.ngaytiepnhan = ngaytiepnhan;
	}

	public int getMaquanlynhanvien() {
		return maquanlynhanvien;
	}

	public NhanVien getNhanVien_quanlyNhanVien() {
		return nhanVien_quanlyNhanVien;
	}

	public DonDatHang getDonDatHang() {
		return donDatHang;
	}
	
}
