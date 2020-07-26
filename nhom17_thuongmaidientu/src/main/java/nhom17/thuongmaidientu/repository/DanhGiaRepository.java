package nhom17.thuongmaidientu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.DanhGia;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
	@Query(value = "select * from danhgia where masanpham = ?1",nativeQuery = true)
	List<DanhGia> findDanhGia(int masanpham);
	
	@Transactional
	@Modifying
	@Query(value = "insert into danhgia(ngaydanhgia,masanpham,makh,binhluan) values(?1,?2,?3,?4)",nativeQuery = true)
	void InsertDanhGia(Date ngaydanhgia,int masanpham,int makh,String binhluan);
}
