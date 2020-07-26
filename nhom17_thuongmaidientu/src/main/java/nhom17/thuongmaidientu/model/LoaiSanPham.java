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
@Table(name = "loaisanpham")
public class LoaiSanPham {
/*maloaisanpham int not null primary key identity(1,1),
	tenloaisanpham nvarchar(30) not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maloaisanpham")
	private int maloaisanpham;
	
	@Column(name = "tenloaisanpham",nullable = false)
	private String tenloaisanpham;
	
	@OneToMany(mappedBy = "loaisanpham_sp",fetch = FetchType.EAGER)
	private Set<SanPham> sanPhams;
	
	public Set<SanPham> getSanPhams()
	{
		return this.sanPhams;
	}

	public int getMaloaisanpham() {
		return maloaisanpham;
	}

	public String getTenloaisanpham() {
		return tenloaisanpham;
	}

	public void setTenloaisanpham(String tenloaisanpham) {
		this.tenloaisanpham = tenloaisanpham;
	}
	
	
}
