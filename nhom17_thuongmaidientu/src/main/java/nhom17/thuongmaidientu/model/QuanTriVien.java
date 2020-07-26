package nhom17.thuongmaidientu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quantrivien")
public class QuanTriVien {
	@Id
	@Column(name = "ten",nullable = false)
	private String ten;
	
	@Column(name = "matkhau",nullable = false)
	private String matkhau;

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
		
}
