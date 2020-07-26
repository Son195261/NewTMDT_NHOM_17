package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.ChiTietDonHang;
import nhom17.thuongmaidientu.model.SanPham;

@Repository
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Integer> {
	@Transactional
	@Modifying
	@Query(value = "insert into chitietdonhang (madonhang,masanpham,soluong) values (?1,?2,?3)",nativeQuery = true)
	void insertChiTietHoaDon(int madonhang,int masanpham,int soluong);
	
	@Query(value = "select * from chitietdonhang where madonhang = ?1",nativeQuery = true)
	List<ChiTietDonHang> findSanPhamHoaDon(int mahoadon);
	
}
