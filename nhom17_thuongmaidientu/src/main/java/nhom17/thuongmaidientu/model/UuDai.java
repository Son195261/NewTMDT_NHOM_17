package nhom17.thuongmaidientu.model;

import javax.persistence.Table;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "uudai")
public class UuDai {
/*mauudai int not null primary key identity(1,1),
	tenuudai nvarchar(20) not null*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mauudai")
	private int mauudai;
	
	@Column(name = "tenuudai",nullable = false)
	private String tenuudai;
	
	@Column(name = "sotiengiam")
	private float sotiengiam;
	
	@OneToMany(mappedBy = "uuDai_quanlyUuDai",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Set<QuanLyUuDai> quanLyUuDais;
	
	public Set<QuanLyUuDai> getQuanLyUuDais()
	{
		return this.quanLyUuDais;
	}

	public String getTenuudai() {
		return tenuudai;
	}

	public void setTenuudai(String tenuudai) {
		this.tenuudai = tenuudai;
	}

	public int getMauudai() {
		return mauudai;
	}

	public float getSotiengiam() {
		return sotiengiam;
	}

	public void setSotiengiam(float sotiengiam) {
		this.sotiengiam = sotiengiam;
	}
	
}
