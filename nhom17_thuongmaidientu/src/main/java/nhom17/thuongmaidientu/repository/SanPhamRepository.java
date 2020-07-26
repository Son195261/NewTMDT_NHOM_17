package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
	@Query(value = "select * from sanpham where masanpham = ?1",nativeQuery = true)
	SanPham findSanPhamById(int idsanpham);
	
	@Query(value = "select top 4 * from sanpham where maloaisanpham = ?1",nativeQuery = true)
	List<SanPham> findSanPhamByLoai(int maloaisanpham);
	
	@Transactional
	@Modifying
	@Query(value = "update sanpham set soluong = soluong - ?1 where masanpham = ?2",nativeQuery = true)
	void CapNhatSoLuong(int soluong, int masanpham);
	
	@Transactional
	@Modifying
	@Query(value = "update sanpham set tensanpham = ?1, giaca = ?2 where masanpham = ?3",nativeQuery = true)
	void CapNhatThongTinSanPham(String tensanpham,float giaca,int masanpham);
	
	@Transactional
	@Modifying
	@Query(value = "delete from sanpham where masanpham = ?1",nativeQuery = true)
	void XoaSanPham(int masanpham);
	
	@Query(value = "select * from sanpham where tensanpham like %?1%",nativeQuery = true)
	List<SanPham> findByNameContaining(String tensanpham);
	
}
