package nhom17.thuongmaidientu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.DonDatHang;

@Repository
public interface DonDatHangRepository extends JpaRepository<DonDatHang, Integer> {
	@Transactional
	@Modifying
	@Query(value = "insert into dondathang (makh,ngaydathang,trangthaidonhang) values (?1,?2,?3)",nativeQuery = true)
	void insertHoaDon(int makh,Date ngaydathang,int trangthaidonhang);
	
	@Query(value = "select top 1 madonhang from dondathang order by madonhang desc",nativeQuery = true)
	int TraVeMaHoaDon();
	
	@Query(value = "select * from dondathang where makh = ?1",nativeQuery = true)
	List<DonDatHang> GetDanhsachHoaDon(int makhachhang);
	
	@Transactional
	@Modifying
	@Query(value = "delete from dondathang where madonhang = ?1",nativeQuery = true)
	void XoaHoaDon(int mahoadon);
	
	@Query(value = "select madonhang from dondathang where madonhang = ?1",nativeQuery = true)
	int TraVeMaHoaDonCu(int ma_hoadoncu);
	
	@Query(value = "select * from dondathang where makh = ?1",nativeQuery = true)
	List<DonDatHang> TimMaHoaDonTheoMaKH(int makh);
	
	@Query(value = "select * from dondathang",nativeQuery = true)
	List<DonDatHang> LayTatCaDonDatHang();
	
	@Query(value = "select * from dondathang where madonhang not in (select madonhang from quanlynhanvien)",nativeQuery = true)
	List<DonDatHang> TimHoaDonChuaCoNhanVien();
	
	@Query(value = "select * from dondathang where madonhang = ?1",nativeQuery = true)
	DonDatHang TimDonHangTheoMaDonHang(int madh);
	
}
