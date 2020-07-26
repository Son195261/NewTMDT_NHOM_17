package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.KhachHang;
import nhom17.thuongmaidientu.model.SanPham;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
	
	@Query(value = "select * from khachhang",nativeQuery = true)
	List<KhachHang> TimTatCaKhachHang();
	
	@Query(value = "select * from khachhang where email = ?1 and matkhau = ?2",nativeQuery = true)
	KhachHang findKhachHang(String emailString,String matkhauString);
	
	@Query(value = "select * from khachhang where makh = ?1",nativeQuery = true)
	KhachHang findKhachHangbyid(int id);
	
	@Query(value = "select makh,tenkh,sodienthoai,diachi,email,matkhau from danhgia inner join khachhang on danhgia.makh = khachhang.makh where masanpham = ?1",nativeQuery = true)
	KhachHang findKHFromDanhGia(int masanpham);
	
	@Transactional
	@Modifying
	@Query(value = "update khachhang set sodienthoai = ?1, diachi = ?2 where makh = ?3",nativeQuery = true)
	void UpdateTTKhachHang(String sodienthoaiString,String diachiString,int id);

	@Transactional
	@Modifying
	@Query(value = "update khachhang set matkhau = ?1 where makh = ?2",nativeQuery = true)
	void Thaydoimatkhau(String mkmoi,int makh);
	
	@Transactional
	@Modifying
	@Query(value = "update khachhang set matkhau = ?1 where email = ?2",nativeQuery = true)
	void Thaydoimatkhau1(String mkmoi,String email);
	
	@Transactional
	@Modifying
	@Query(value = "delete from khachhang where makh = ?1",nativeQuery = true)
	void XoaTaiKhoan(int makh);
	
	@Query(value = "select * from khachhang where tenkh like %?1%",nativeQuery = true)
	List<KhachHang> findByNameContaining(String tenkhachhang);
	
}
