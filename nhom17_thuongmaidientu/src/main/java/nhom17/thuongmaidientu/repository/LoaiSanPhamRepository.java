package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nhom17.thuongmaidientu.model.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {

	@Query(value = "select * from loaisanpham",nativeQuery = true)
	List<LoaiSanPham> TimTatCaLoaiSanPham();
	
	@Query(value = "select * from loaisanpham where maloaisanpham = ?1",nativeQuery = true)
	LoaiSanPham TimLoaiSanPham(int maloaisanpham);
	
}
