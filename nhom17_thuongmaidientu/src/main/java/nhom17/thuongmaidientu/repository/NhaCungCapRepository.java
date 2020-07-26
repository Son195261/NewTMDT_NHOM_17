package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nhom17.thuongmaidientu.model.LoaiSanPham;
import nhom17.thuongmaidientu.model.NhaCungCap;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {

	@Query(value = "select * from nhacungcap",nativeQuery = true)
	List<NhaCungCap> TimTatCaNhaCungCap();
	
	@Query(value = "select * from nhacungcap where manhacc = ?1",nativeQuery = true)
	NhaCungCap TimNhaCungCap(int manhacc);
	
}
