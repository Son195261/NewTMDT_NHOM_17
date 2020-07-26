package nhom17.thuongmaidientu.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
/*
	manhacc int not null primary key identity(1,1),
	tennhacc nvarchar(30) not null,
	sodienthoai varchar(11) not null,
	email varchar(20) not null,
	diachi nvarchar(40) null
*/	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manhacc")
	private int manhacc;
	
	@Column(name = "tennhacc", nullable = false)
	private String tennhacc;
	
	@Column(name = "sodienthoai", nullable = false)
	private String sodienthoai;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "diachi",nullable = false)
	private String diachi;
	
	@OneToMany(mappedBy = "manhacc_sp",fetch = FetchType.EAGER)
	private Set<SanPham> sanPhams;
	
	public Set<SanPham> getSanPhams()
	{
		return this.sanPhams;
	}

	public int getManhacc() {
		return manhacc;
	}

	public String getTennhacc() {
		return tennhacc;
	}

	public void setTennhacc(String tennhacc) {
		this.tennhacc = tennhacc;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}	
}
