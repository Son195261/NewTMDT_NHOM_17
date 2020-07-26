package nhom17.thuongmaidientu.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.expr.NewArray;
import nhom17.thuongmaidientu.model.ChiTietDonHang;
import nhom17.thuongmaidientu.model.DonDatHang;
import nhom17.thuongmaidientu.model.KhachHang;
import nhom17.thuongmaidientu.model.LoaiSanPham;
import nhom17.thuongmaidientu.model.NhanVien;
import nhom17.thuongmaidientu.model.QuanTriVien;
import nhom17.thuongmaidientu.model.SanPham;
import nhom17.thuongmaidientu.model.UuDai;
import nhom17.thuongmaidientu.repository.ChiTietDonHangRepository;
import nhom17.thuongmaidientu.repository.DanhGiaRepository;
import nhom17.thuongmaidientu.repository.DonDatHangRepository;
import nhom17.thuongmaidientu.repository.KhachHangRepository;
import nhom17.thuongmaidientu.repository.LoaiSanPhamRepository;
import nhom17.thuongmaidientu.repository.NhaCungCapRepository;
import nhom17.thuongmaidientu.repository.NhanVienRepository;
import nhom17.thuongmaidientu.repository.QuanLyNhanVienRepository;
import nhom17.thuongmaidientu.repository.QuanLyUuDaiRepository;
import nhom17.thuongmaidientu.repository.QuanTriVienRepository;
import nhom17.thuongmaidientu.repository.SanPhamRepository;
import nhom17.thuongmaidientu.repository.UuDaiRepository;
import nhom17.thuongmaidientu.service.SendEmail;

@Controller
public class ControllerClass {
	KhachHang tkkh;
	QuanTriVien quanTriVien;
	NhanVien nhanVien;
	private JavaMailSender javaMailSender;
	
	int type = 0; //0 la khach hang, 1 la nhan vien, 2 la quan tri vien
	int dathang = 0;
	List<SanPham> giohang = new ArrayList<SanPham>();
	List<Integer> listMaSanPham = new ArrayList<Integer>();
	float thanhtien = 0;
	float tongtien = 0;
	boolean tangsanpham = false;
	int soluong_hienco = 1;
	Map<Integer,Integer> map_sp_soluong = new HashMap<Integer, Integer>();
	int ma_hoadon = 0;
	int maxacnhan;
	
	@Autowired
	KhachHangRepository khachHangRepository;
	
	@Autowired
	SanPhamRepository sanPhamRepository;
	
	@Autowired
	DonDatHangRepository donDatHangRepository;
	
	@Autowired
	ChiTietDonHangRepository chiTietDonHangRepository;
	
	@Autowired
	DanhGiaRepository danhGiaRepository;
	
	@Autowired
	QuanTriVienRepository quanTriVienRepository;
	
	@Autowired
	UuDaiRepository uuDaiRepository;
	
	@Autowired
	QuanLyUuDaiRepository quanLyUuDaiRepository;
	
	@Autowired
	LoaiSanPhamRepository loaiSanPhamRepository;
	
	@Autowired
	NhaCungCapRepository nhaCungCapRepository;
	
	@Autowired
	NhanVienRepository nhanVienRepository;
	
	@Autowired
	QuanLyNhanVienRepository quanLyNhanVienRepository;
	
	@Autowired
	private SendEmail sendEmail;
	
	@RequestMapping("/chuadangnhap")
	public String ChuaDangNhap()
	{
		return "ErrorSession";
	}
	
	@RequestMapping("/trangchu")
	public String GetTrangChu(Model modelSanpham,Model model)
	{
		modelSanpham.addAttribute("sanpham", sanPhamRepository.findAll());
		if (type==0) {
			model.addAttribute("dx", "khachhang");
		}
		else if (type == 1)
		{
			model.addAttribute("dx", "nhanvien");
		}
		else if (type == 2) {
			model.addAttribute("dx", "quantrivien");
		}
		return "TrangChu";
	}
	
	@RequestMapping("/trangchu/dangkythongtin")
	public String GetDangKy(Model model)
	{
		model.addAttribute("khachhang",new KhachHang());
		return "DangKyThongTin";
	}
	
	//dang ky khach hang
	@RequestMapping("/trangchu/dangkythongtin/save")
	public String save(@Valid KhachHang khachhang,RedirectAttributes redirect) {			
		khachHangRepository.save(khachhang);
		redirect.addFlashAttribute("success","Đăng ký thành công");
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/trangdangnhapkhachhang")
	public String TrangDangNhapKH(Model model)
	{
		model.addAttribute("khachhang", new KhachHang());
		return "TrangDangNhapKhachHang";
	}
	
	@RequestMapping("/trangchu/trangdangnhapquantrivien")
	public String TrangDangNhapQuanTriVien(Model model)
	{
		model.addAttribute("quantrivien", new QuanTriVien());
		return "TrangDangNhapQuanTriVien";
	}
	
	@RequestMapping("/trangchu/trangdangnhapnhanvien")
	public String TrangDangNhapNhanVien(Model model)
	{
		model.addAttribute("nhanvien", new NhanVien());
		return "TrangDangNhapNhanVien";
	}
	
	@RequestMapping("/trangchu/trangquanlytaikhoan/trangcapnhat")
	public String GetTrangCapNhatTT(Model model,HttpSession session,RedirectAttributes redirect)
	{	
		if(tkkh!=null)
		{
			@SuppressWarnings("deprecation")
			int id = (int) session.getValue(tkkh.getEmail());		
			KhachHang kHang = khachHangRepository.findById(id).get();	
			model.addAttribute("khachhang",kHang);
			return "SuaDoiThongTin";
		}
		return "ErrorSession";
	}
	
	@RequestMapping("/trangchu/xulydangnhapkhachhang")
	public String XuLyDangNhapKH(@Valid KhachHang khachHang,RedirectAttributes redirect,HttpSession session,Model model,Model modelSanpham)
	{
		//System.out.println(khachHang.getEmail());
		tkkh = khachHangRepository.findKhachHang(khachHang.getEmail(),khachHang.getMatkhau());
		if(tkkh!=null)
		{
			type = 0;
			session.setAttribute(tkkh.getEmail(), tkkh.getMakh());
			model.addAttribute("dx", "dangxuat");
			redirect.addFlashAttribute("success","Đăng nhập thành công");
			return "redirect:/trangchu";
		}
		redirect.addFlashAttribute("success","Đăng nhập thất bại");
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/xulydangnhapquantrivien")
	public String XuLyDangNhapQTV(@Valid QuanTriVien quanTriVien1,RedirectAttributes redirect,Model model)
	{
		quanTriVien = quanTriVienRepository.findQuanTriVien(quanTriVien1.getTen(), quanTriVien1.getMatkhau());
		if (quanTriVien!=null) {
			type = 2;
			model.addAttribute("dx", "dangxuat");
			redirect.addFlashAttribute("success","Đăng nhập thành công");
			return "redirect:/trangchu";
		}
		redirect.addFlashAttribute("success","Đăng nhập thất bại");
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/xulydangnhapnhanvien")
	public String XuLyDangNhapNhanVien(@Valid NhanVien nhanVien1,RedirectAttributes redirect,Model model)
	{
		//nhanVien1 = quanTriVienRepository.findQuanTriVien(quanTriVien1.getTen(), quanTriVien1.getMatkhau());
		nhanVien = nhanVienRepository.TimNhanVienDeDangNhap(nhanVien1.getSodienthoai(), nhanVien1.getMatkhau());
		if (nhanVien!=null) {
			type = 1;
			model.addAttribute("dx", "dangxuat");
			redirect.addFlashAttribute("success","Đăng nhập thành công");
			return "redirect:/trangchu";
		}
		//System.out.println("Không có ai");
		redirect.addFlashAttribute("success","Đăng nhập thất bại");
		return "redirect:/trangchu";
	}
	
	//dang xuat
	@RequestMapping("/trangchu/xulydangxuat")
	public String DangXuat(Model model,HttpSession session,Model modelSanpham)
	{
		session.removeAttribute(tkkh.getEmail());	
		modelSanpham.addAttribute("sanpham", sanPhamRepository.findAll());
		model.addAttribute("dx", "");
		thanhtien = 0;
		giohang.clear();
		listMaSanPham.clear();
		tongtien = 0;
		map_sp_soluong.clear();
		if (tkkh!=null)
		{
			tkkh =  null;
		}
		if(quanTriVien!=null)
		{
			quanTriVien = null;
		}
		if(nhanVien!=null)
		{
			nhanVien = null;
		}
		return "trangchu";
	}
	
	//cap nhat thong tin
	@RequestMapping("/trangchu/trangquanlytaikhoan/suathongtin")
	public String Update(@Valid KhachHang kh1,BindingResult result,RedirectAttributes redirect,HttpSession session) {	
		@SuppressWarnings("deprecation")
		int id = (int) session.getValue(tkkh.getEmail());		
		khachHangRepository.UpdateTTKhachHang(kh1.getSodienthoai(), kh1.getDiachi(), id);
		
		redirect.addFlashAttribute("success","Cập nhật thành công");
		return "redirect:/trangchu/trangquanlytaikhoan";
	}
	
	@RequestMapping("/trangchu/chitietsanpham/{idsanpham}")
	public String HienThiChiTiet(@PathVariable int idsanpham,Model modelSanpham,Model modelDanhgia,Model modelIdSanpham,Model modelSanPhamLienQuan)
	{
		SanPham sanPham = sanPhamRepository.findSanPhamById(idsanpham);
		//DanhGia danhGia = danhGiaRepository.findDanhGia(idsanpham);
		
		modelSanpham.addAttribute("chitietsp", sanPham);
		modelDanhgia.addAttribute("danhgiasanpham", danhGiaRepository.findDanhGia(idsanpham));
		
		modelIdSanpham.addAttribute("idsp", idsanpham);
		modelSanPhamLienQuan.addAttribute("sanphamlienquan", sanPhamRepository.findSanPhamByLoai(sanPham.getloaisanpham().getMaloaisanpham()));
		return "ChiTietSanPham";
	}
	
	@RequestMapping("/trangchu/chitietsanpham/binhluan/{idsanpham}")
	public String LuuBinhLuan(@PathVariable int idsanpham,@RequestParam String binhluan,RedirectAttributes redirect,HttpSession session)
	{
		if(tkkh!=null)
		{
			System.out.println(binhluan);
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			@SuppressWarnings("deprecation")
			int makh = (int) session.getValue(tkkh.getEmail());
			danhGiaRepository.InsertDanhGia(date, idsanpham, makh, binhluan);
			
			return "redirect:/trangchu/chitietsanpham/{idsanpham}";
		}
		return "ErrorSession";
	}

	// xu ly dat gio hang
	@RequestMapping("/trangchu/xulydatgiohang/{idsanpham}")
	public String XuLyDatHang(@PathVariable int idsanpham,RedirectAttributes redirect)
	{
		SanPham sp_hd = sanPhamRepository.findSanPhamById(idsanpham);	
		
		if(!listMaSanPham.contains(sp_hd.getMasanpham()))
		{
			listMaSanPham.add(sp_hd.getMasanpham());
			giohang.add(sp_hd);
			map_sp_soluong.put(sp_hd.getMasanpham(), 1);
		}
		else {
			System.out.println("Sản phẩm đã nằm trong giỏ");
		}		
		
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/giohang")
	public String TrangHienthiHoaDon(Model modelSanpham,Model modelTien,Model sp_slModel)
	{
		thanhtien = 0;
		modelSanpham.addAttribute("sanphamvuadat", giohang);
		for (int i = 0; i < giohang.size(); i++) {
			thanhtien+=giohang.get(i).getGiaca();
		}
		if(tangsanpham)
		{
			modelTien.addAttribute("tongtienform", tongtien);
			tangsanpham = false;
		}
		else {
			modelTien.addAttribute("tongtienform", thanhtien);	
		}
		sp_slModel.addAttribute("spandsl", map_sp_soluong);
		return "TrangGioHang";
	}
	
	@RequestMapping("/trangchu/giohang/xoa/{idsanpham}")
	public String XoaSanphamkhoiHoaDon(@PathVariable int idsanpham,RedirectAttributes redirect)
	{
		SanPham sp_hd = sanPhamRepository.findSanPhamById(idsanpham);
		if(listMaSanPham.contains(sp_hd.getMasanpham()))
		{
			for (int i = 0; i < giohang.size(); i++) {
				if(giohang.get(i).getMasanpham() == sp_hd.getMasanpham())
				{
					giohang.remove(i);
					map_sp_soluong.remove(sp_hd.getMasanpham());
				}
			}
		}
		return "redirect:/trangchu/giohang";
	}
	
	@RequestMapping("/trangchu/giohang/tangsoluong/{idsanpham}")
	public String tangsoluong(@PathVariable int idsanpham,@RequestParam String soluong,RedirectAttributes redirect,Model model)
	{
		//tinh tong tien
		tongtien = 0;
		for (int i = 0; i < giohang.size(); i++) {
			tongtien+=giohang.get(i).getGiaca();
		}
		
		int sl = Integer.parseInt(soluong);
		soluong_hienco = sl;
		
		SanPham sp = sanPhamRepository.findSanPhamById(idsanpham);
		tongtien+=(sl-1)*sp.getGiaca();
		tangsanpham = true;
		map_sp_soluong.put(sp.getMasanpham(), soluong_hienco);
		
		return "redirect:/trangchu/giohang";
	}
	
	@RequestMapping("/trangchu/giohang/mua")
	public String mua(RedirectAttributes redirect,HttpSession session)
	{
		if(tkkh!=null)
		{
			@SuppressWarnings("deprecation")
			int makh = (int) session.getValue(tkkh.getEmail());
			
			int madonhang = 0;
			
			if(ma_hoadon == 0)
			{
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				donDatHangRepository.insertHoaDon(makh,date,0);			
				madonhang = donDatHangRepository.TraVeMaHoaDon();
			}
			else {
				madonhang = donDatHangRepository.TraVeMaHoaDonCu(ma_hoadon);
			}
			
			for(Map.Entry<Integer, Integer> entry : map_sp_soluong.entrySet())
			{
				//System.out.println(entry.getKey() + " = " + entry.getValue());
				chiTietDonHangRepository.insertChiTietHoaDon(madonhang, entry.getKey(), entry.getValue());
				sanPhamRepository.CapNhatSoLuong(entry.getValue(), entry.getKey());
			}			
					
			map_sp_soluong.clear();
			listMaSanPham.clear();
			giohang.clear();
			tangsanpham = false;
			tongtien = 0;
			thanhtien = 0;
			ma_hoadon = 0;
			
			return "redirect:/trangchu";
		}
		return "ErrorSession";
	}
	
	@RequestMapping("/trangchu/quanlyhoadon")
	public String TrangQuanLyDonHang(Model modelKhachHang,Model modelHoaDon,RedirectAttributes redirect)
	{
		if(tkkh!=null)
		{
			modelKhachHang.addAttribute("tenkhachhang", tkkh.getTenkh());
			int makh = (int) tkkh.getMakh();
			List<DonDatHang> listHoaDon = donDatHangRepository.GetDanhsachHoaDon(makh);		
			modelHoaDon.addAttribute("danhsachhoadon", listHoaDon);
			return "QuanLyHoaDon";
		}
		
		redirect.addFlashAttribute("success","Yêu cầu đăng nhập");	
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/quanlyhoadon/chitiethoadon/{mahoadon}")
	public String TrangChiTietHoaDon(@PathVariable int mahoadon, Model modelSanpham,Model modelKhachhang,Model modelHoadon,Model modelTamtinh,Model modelType)
	{
		ma_hoadon = mahoadon;
		
		modelKhachhang.addAttribute("ttkhachhang", tkkh);	
		List<ChiTietDonHang> ctdh = chiTietDonHangRepository.findSanPhamHoaDon(mahoadon);	
		modelSanpham.addAttribute("chitietsanpham", ctdh);	
		modelHoadon.addAttribute("mahd", mahoadon);	
		float tamtinh = 0;
		for(int i = 0;i < ctdh.size();i++)
		{
			tamtinh+=ctdh.get(i).getSanPham_chitietDatHang().getGiaca() * ctdh.get(i).getSoluong();
		}
		modelTamtinh.addAttribute("tamtinhtien", tamtinh);		
		
		return "ChiTietHoaDon";
	}
	
	@RequestMapping("/trangchu/quanlyhoadon/chitiethoadon/themsanpham")
	public String ThemSanPhamHoaDon(RedirectAttributes redirect)
	{	
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/quanlyhoadon/xoahoadon/{mahoadon}")
	public String XoaHoaDon(@PathVariable int mahoadon, RedirectAttributes redirect)
	{
		donDatHangRepository.XoaHoaDon(mahoadon);
		redirect.addFlashAttribute("success","Xóa hóa đơn thành công");	
		return "redirect:/trangchu/quanlyhoadon";
	}
	
	@RequestMapping("/trangchu/trangquanlytaikhoan")
	public String TrangQuanLyTaiKhoan(Model modelKhachHang,Model modelNhanvien,Model xacnhan,RedirectAttributes redirect)
	{
		if(tkkh!=null)
		{
			modelKhachHang.addAttribute("kh", khachHangRepository.findKhachHangbyid(tkkh.getMakh()));
			xacnhan.addAttribute("xacnhan", "kh");
			//redirect.addFlashAttribute("success","Đăng nhập thành công");
			return "TrangQuanLyTaiKhoan";
		}
		if(nhanVien!=null)
		{
			modelNhanvien.addAttribute("nv", nhanVienRepository.TimNhanVienTheoMaNhanVien(nhanVien.getManv()));
			xacnhan.addAttribute("xacnhan", "nv");
			//redirect.addFlashAttribute("success","Đăng nhập thành công");
			return "TrangQuanLyTaiKhoan";
		}
		redirect.addFlashAttribute("success","Yêu cầu đăng nhập");	
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/trangquanlytaikhoan/trangthaydoimatkhau")
	public String trangMatKhau(Model model)
	{
		return "TrangThayDoiMatKhau";
	}
	
	@RequestMapping("/trangchu/trangquanlytaikhoan/xulydoimatkhau")
	public String Dieukhienthaydoimatkhau(@RequestParam String mkcu,@RequestParam String mkmoi,@RequestParam String mkxacnhan,RedirectAttributes redirect)
	{
		if(tkkh!=null)
		{
			if(mkcu.compareTo(tkkh.getMatkhau()) == 0)
			{
				if(mkmoi.compareTo(mkxacnhan) == 0)
				{
					khachHangRepository.Thaydoimatkhau(mkmoi, tkkh.getMakh());
					//return "redirect:/trangchu/trangquanlytaikhoan";
				}
				else {
					redirect.addFlashAttribute("success","Cập nhật thất bại");	
					return "/trangchu/trangquanlytaikhoan/trangthaydoimatkhau";
				}
			}		
		}
		if(nhanVien!=null)
		{
			if(mkcu.compareTo(tkkh.getMatkhau()) == 0)
			{
				if(mkmoi.compareTo(mkxacnhan) == 0)
				{
					nhanVienRepository.Thaydoimatkhau(nhanVien.getManv(), mkmoi);
					//return "redirect:/trangchu/trangquanlytaikhoan";
				}
				else {
					redirect.addFlashAttribute("success","Cập nhật thất bại");	
					return "/trangchu/trangquanlytaikhoan/trangthaydoimatkhau";
				}
			}	
		}
		
		return "redirect:/trangchu/trangquanlytaikhoan";
	}	
	
	@RequestMapping("/trangchu/trangquanlytaikhoan/xoataikhoan")
	public String DieuKhienXoaTaiKhoan()
	{	
		if(tkkh!=null)
		{
			int makh = tkkh.getMakh();
			tkkh = null;
			khachHangRepository.XoaTaiKhoan(makh);
		}
		
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/xemuudai")
	public String TrangXemUuDai(Model modelUuDai)
	{
		modelUuDai.addAttribute("danhsachuudaiht", uuDaiRepository.findAllUuDai());
		return "TrangXemUuDai";
	}
	
	@RequestMapping("/trangchu/xemuudai/xemuudaicanhan")
	public String XemUuDaiCaNhan(Model modelKhachHang,Model modelUuDai)
	{
		if(tkkh!=null)
		{
			modelKhachHang.addAttribute("tenkh", tkkh.getTenkh());
			modelKhachHang.addAttribute("danhsachuudai", quanLyUuDaiRepository.findAllUudai_KH(tkkh.getMakh()));
			return "TrangXemUudaiCaNhan";	
		}
		return "ErrorSession";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/xemuudaihethong")
	public String XemUuDaiHeThong(Model modelUuDai)
	{	
		modelUuDai.addAttribute("danhsachuudaiht", uuDaiRepository.findAllUuDai());
		return "TrangXemUudaiHeThong";			
	}
	
	@RequestMapping("/trangchu/quanlysanpham")
	public String TrangQuanLySanPham(Model modelSanpham,RedirectAttributes redirect)
	{
		if(quanTriVien!=null)
		{
			modelSanpham.addAttribute("sanpham", sanPhamRepository.findAll());	
			return "TrangQuanLySanPham";
		}
		redirect.addFlashAttribute("success","Yêu cầu đăng nhập");	
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/quanlysanpham/suathongtinsanpham/{idsanpham}")
	public String ChuyenTrangSuaSanPham(@PathVariable int idsanpham,Model modelSanPham,Model modelMasanpham)
	{
		modelSanPham.addAttribute("sanpham", sanPhamRepository.findSanPhamById(idsanpham));
		modelMasanpham.addAttribute("idsanpham", idsanpham);
		return "TrangSuaThongTinSanPham";
	}
	
	@RequestMapping("/trangchu/quanlysanpham/xulysuathongtinsanpham/{idsanpham}")
	public String DieuKhienSuaSanPham(@PathVariable int idsanpham,@RequestParam String tensanpham,@RequestParam String giaca)
	{
		float giamoi = Float.parseFloat(giaca);
		sanPhamRepository.CapNhatThongTinSanPham(tensanpham, giamoi, idsanpham);
		return "redirect:/trangchu/quanlysanpham";
	}
	
	@RequestMapping("/trangchu/quanlysanpham/themmoisanpham")
	public String TrangThemSanPham(Model modelLoaiSanPham,Model modelNhacungcap)
	{
		modelLoaiSanPham.addAttribute("loaisanpham", loaiSanPhamRepository.TimTatCaLoaiSanPham());
		modelNhacungcap.addAttribute("nhacungcap", nhaCungCapRepository.TimTatCaNhaCungCap());
		return "TrangThemMoiSanPham";
	}
	
	@RequestMapping("/trangchu/quanlysanpham/themmoisanpham/xulythemsanpham")
	public String DieuKhienThemSanPham(@RequestParam String tensanpham,@RequestParam String ngaysanxuat,@RequestParam String hansudung,
			@RequestParam String noidung,@RequestParam String giaca,@RequestParam String soluong,@RequestParam String maloaisanpham,
			@RequestParam String manhacungcap,@RequestParam String hinhanh,RedirectAttributes redirect) throws ParseException
	{
		SanPham sanPham = new SanPham();
		String pathString = "/image/";
		if(Integer.parseInt(maloaisanpham) == 1)
		{
			
		}
		else if (Integer.parseInt(maloaisanpham) == 2) {
			pathString+="dientuvamoto/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 3) {
			pathString+="thoitrangnu/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 4) {
			
		}
		else if (Integer.parseInt(maloaisanpham) == 5) {
			pathString+="dientuvamoto/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 6) {
			
		}
		else if (Integer.parseInt(maloaisanpham) == 7) {
			pathString+="giadinhvacuocsong/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 8) {
			pathString+="thoitrangnu/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 9) {
			pathString+="thoitrangnam/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 10) {
			pathString+="thoitrangnam/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 11) {
			pathString+="thethaodulich/";
			pathString+=hinhanh;
		}
		else if (Integer.parseInt(maloaisanpham) == 12) {
			pathString+="dientuvamoto/";
			pathString+=hinhanh;
		}
		
		sanPham.setTensanpham(tensanpham);
		
		Date ngaysx = new SimpleDateFormat("yyyy-MM-dd").parse(ngaysanxuat);
		sanPham.setNgaysanxuat(ngaysx);
		
		Date hansd = new SimpleDateFormat("yyyy-MM-dd").parse(hansudung);
		sanPham.setNgaysanxuat(hansd);
		
		sanPham.setNoidung(noidung);
		sanPham.setGiaca(Float.parseFloat(giaca));
		sanPham.setHinhanh(pathString);
		sanPham.setSoluong(Integer.parseInt(soluong));
		sanPham.setloaisanpham(loaiSanPhamRepository.TimLoaiSanPham(Integer.parseInt(maloaisanpham)));
		sanPham.setManhacc(nhaCungCapRepository.TimNhaCungCap(Integer.parseInt(manhacungcap)));
		
		sanPhamRepository.save(sanPham);
		redirect.addFlashAttribute("success","Thêm mới thành công");
		return "redirect:/trangchu/quanlysanpham";
	}
	
	@RequestMapping("/trangchu/quanlysanpham/xoasanpham/{idsanpham}")
	public String DieuKhienXoaSanPham(@PathVariable int idsanpham,RedirectAttributes redirect)
	{
		sanPhamRepository.XoaSanPham(idsanpham);
		return "redirect:/trangchu/quanlysanpham";
	}
	
	@RequestMapping("/trangchu/quanlyuudai")
	public String TrangQuanLyUuDai(Model modelUuDai,Model modelQuanTriVien,RedirectAttributes redirect)
	{
		if(quanTriVien!=null)
		{
			modelQuanTriVien.addAttribute("tenquantrivien", quanTriVien.getTen());
			modelUuDai.addAttribute("danhsachuudai", uuDaiRepository.findAllUuDai());	
			return "TrangQuanLyUuDai";
		}
		redirect.addFlashAttribute("success", "Yêu cầu đăng nhập!!!");
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/suathongtinuudai/{mauudai}")
	public String TrangSuaThongTinUuDai(@PathVariable int mauudai,RedirectAttributes redirect,Model modelUudai)
	{		
		modelUudai.addAttribute("ctuudai", uuDaiRepository.TimKiemUuDaiTheoMa(mauudai));
		return "TrangSuaThongTinUuDai";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/xulysuauudai/{mauudai}")
	public String DieuKhienSuaUuDai(@PathVariable int mauudai,@RequestParam String tenuudai,@RequestParam String sotiengiam)
	{
		uuDaiRepository.CapNhatUuDai(mauudai, tenuudai, Float.parseFloat(sotiengiam));
		return "redirect:/trangchu/quanlyuudai";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/xoathongtinuudai/{mauudai}")
	public String DieuKhienXoaUuDai(@PathVariable int mauudai,RedirectAttributes redirect)
	{
		uuDaiRepository.XoaUuDai(mauudai);
		return "redirect:/trangchu/quanlyuudai";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/themuudai")
	public String TrangThemUuDai(Model model)
	{
		model.addAttribute("uudai",new UuDai());
		return "TrangThemMoiUuDai";
	}
	
	@RequestMapping("/trangchu/quanlyuudai/xulythemuudai")
	public String DieuKhienThemUuDai(@Valid UuDai uuDai)
	{
		uuDaiRepository.save(uuDai);
		return "redirect:/trangchu/quanlyuudai";
	}
	
	@RequestMapping("/trangchu/quanlykhachhang")
	public String TrangQuanLyKhachHang(Model modelKhachhang,Model modelQuantrivien,RedirectAttributes redirect)
	{
		if(quanTriVien!=null)
		{
			modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
			modelKhachhang.addAttribute("khachhang", khachHangRepository.TimTatCaKhachHang());
			return "TrangQuanLyKhachHang";
		}
		redirect.addFlashAttribute("success", "Yêu cầu đăng nhập");
		return "redirect:/trangchu";
	}
	
	@RequestMapping("/trangchu/quanlykhachhang/xemhoatdongkhachhang/{makh}")
	public String XemHoatDongKhachHang(@PathVariable int makh,Model modelKhachhang,Model modelChitietdonhang,Model modelQuantrivien)
	{
		modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
		modelKhachhang.addAttribute("makhachhang", makh);
		
		List<DonDatHang> danhsachmahoadon = donDatHangRepository.TimMaHoaDonTheoMaKH(makh);
		
		List<ChiTietDonHang> dsChiTietDonHangs = new ArrayList<ChiTietDonHang>();
		
		for(int i = 0;i < danhsachmahoadon.size();i++)
		{
			dsChiTietDonHangs.addAll(chiTietDonHangRepository.findSanPhamHoaDon(danhsachmahoadon.get(i).getMadonhang()));
		}
		
		modelChitietdonhang.addAttribute("chitietdonhang", dsChiTietDonHangs);	
		return "TrangXemHoatDongKhachHang";
	}
	
	@RequestMapping("/trangchu/quanlykhachhang/xemhoatdongkhachhang/themuudai/{makh}")
	public String TrangPhanPhoiUuDai(@PathVariable int makh,Model modelUudai,Model modelKhachhang,Model modelQuantrivien)
	{
		modelKhachhang.addAttribute("makhachhang", makh);
		modelUudai.addAttribute("danhsachuudai", uuDaiRepository.findAllUuDai());
		modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
		return "TrangPhanPhoiUuDai";
	}
	
	@RequestMapping("/trangchu/quanlykhachhang/xemhoatdongkhachhang/xulythemuudai/{makh}")
	public String DieuKhienPhanPhoiUuDai(@PathVariable int makh,@RequestParam String mauudai,@RequestParam String ngaybatdau,@RequestParam String ngayketthuc) throws ParseException
	{
		Date ngaybd = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybatdau);
		Date ngaykt = new SimpleDateFormat("yyyy-MM-dd").parse(ngayketthuc);
		
		quanLyUuDaiRepository.ChenPhanPhoiUuDai(makh, Integer.parseInt(mauudai), ngaybd, ngaykt);
		return "redirect:/trangchu/quanlykhachhang";
	}
		
	@RequestMapping("/trangchu/quanlynhanvien")
	public String TrangQuanLyNhanVien(Model modelQuantrivien,Model modelNhanvien,RedirectAttributes redirect)
	{
		if(quanTriVien!=null)
		{
			modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
			modelNhanvien.addAttribute("nhanvien", nhanVienRepository.TimTatCaNhanVien());
			return "TrangQuanLyNhanVien";
		}
		redirect.addFlashAttribute("success", "Yêu cầu đăng nhập");
		return "ErrorSession";	
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/xemthongtinnhanvien/{manv}")
	public String XemThongTinNhanVien(@PathVariable int manv,Model modelNhanvien,Model modelQuantrivien,Model modelQuanlynhanvien)
	{
		modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
		modelNhanvien.addAttribute("manhanvien", manv);
		modelQuanlynhanvien.addAttribute("danhsachdonhang", quanLyNhanVienRepository.DanhSachDonHangCuaNhanVien(manv));
		
		return "TrangXemThongTinNhanVien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/suathongtinnhanvien/{manv}")
	public String TrangSuaThongTinNhanVien(@PathVariable int manv,Model modelMaNhanvien,Model modelQuantrivien,Model modelNhanVien)
	{
		modelQuantrivien.addAttribute("tenquantrivien", quanTriVien.getTen());
		modelMaNhanvien.addAttribute("manhanvien", manv);
		modelNhanVien.addAttribute("nhanvien", nhanVienRepository.TimNhanVienTheoMaNhanVien(manv));
		return "TrangSuaThongTinNhanVien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/suathongtinnhanvien/suahesoluong/{manv}")
	public String DieuKhienSuaNhanVien(@PathVariable int manv,@RequestParam String hesoluong)
	{
		nhanVienRepository.CapNhatNhanVien(manv, Float.parseFloat(hesoluong));
		return "redirect:/trangchu/quanlynhanvien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/xoanhanvien/{manv}")
	public String DieuKhienXoaNhanVien(@PathVariable int manv)
	{
		nhanVienRepository.XoaNhanVien(manv);
		return "redirect:/trangchu/quanlynhanvien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/themmoinhanvien")
	public String TrangThemMoiNhanVien(Model modelNhanVien)
	{
		modelNhanVien.addAttribute("nhanvien", new NhanVien());
		return "TrangThemMoiNhanVien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/themmoinhanvien/luuthongtin")
	public String XuLyThemNhanVien(@Valid NhanVien nhanvien,RedirectAttributes redirect) {			
		nhanVienRepository.save(nhanvien);
		redirect.addFlashAttribute("success","thêm mới thành công");
		return "redirect:/trangchu/quanlynhanvien";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/phanphoidonhang/{manv}")
	public String TrangPhanPhoiDonHang(@PathVariable int manv,Model modelManhanvien,Model modelMadonhang,Model modelDonhang)
	{
		modelManhanvien.addAttribute("manhanvien", manv);
		modelMadonhang.addAttribute("madonhangchuaconv", donDatHangRepository.TimHoaDonChuaCoNhanVien());
		modelDonhang.addAttribute("dondathang", donDatHangRepository.LayTatCaDonDatHang());
		return "TrangPhanPhoiDonHang";
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/phanphoidonhang/xulyphanphoidonhang/{manv}")
	public String DieuKhienPhanPhoiDonHang(@PathVariable int manv,@RequestParam String madonhang,@RequestParam String ngaytiepnhan) throws ParseException
	{
		Date ngaytn = new SimpleDateFormat("yyyy-MM-dd").parse(ngaytiepnhan);
		
		quanLyNhanVienRepository.ThemQuanLyNhanVien(manv, Integer.parseInt(madonhang), ngaytn);
		
		return "redirect:/trangchu/quanlynhanvien";
	}
	
	@RequestMapping("/trangchu/trangquanlyvanchuyen")
	public String TrangQuanLyVanChuyen(Model modelDonhang,Model modelNhanvien,RedirectAttributes redirect)
	{
		if(nhanVien!=null)
		{
			modelNhanvien.addAttribute("tennhanvien", nhanVien.getTennv());
			modelDonhang.addAttribute("danhsachdonhang", quanLyNhanVienRepository.DanhSachDonHangCuaNhanVien(nhanVien.getManv()));
			return "TrangQuanLyVanChuyen";
		}
		redirect.addFlashAttribute("success", "Yêu cầu đăng nhập!!!");
		return "ErrorSession";
	}
	
	@RequestMapping("/trangchu/trangquanlyvanchuyen/trangxemthongtindonhang/{madh}")
	public String TrangXemThongTinDonHang(@PathVariable int madh,Model modelMadh,Model modelDonhang,Model modelChitietdonhang)
	{
		modelMadh.addAttribute("madonhang", madh);
		modelDonhang.addAttribute("donhang", donDatHangRepository.TimDonHangTheoMaDonHang(madh));
		modelChitietdonhang.addAttribute("chitietdonhang", chiTietDonHangRepository.findSanPhamHoaDon(madh));
		return "TrangXemThongTinDonHang";
	}
	
	@RequestMapping("/trangchu/trangnhapemail")
	public String TrangNhapEmail()
	{
		return "FormNhapEmail";
	}
	
	@RequestMapping("/trangchu/trangresetmatkhau")
	public String TrangResetMatKhau(@RequestParam String email)
	{	
		Random random = new Random();
		maxacnhan = random.nextInt(10000);
		
		try {
			sendEmail.sendNotification(email, maxacnhan);
		} catch (MailException e) {
			// TODO: handle exception
		}
		
		return "TrangResetMatKhau";
	}
	
	@RequestMapping("/trangchu/trangresetmatkhau/xulyresetmatkhau")
	public String XuLyResetMatKhau(@RequestParam String email,@RequestParam String mkmoi,@RequestParam String maxn)
	{
		if(Integer.parseInt(maxn) == maxacnhan)
		{
			khachHangRepository.Thaydoimatkhau1(mkmoi, email);
		}
		return "redirect:/trangchu/trangdangnhapkhachhang";
	}
	
	@RequestMapping("/trangchu/trangtimkiemsanpham")
	public String DieuKhienTimKiemSanPham(@RequestParam String tensanpham,Model model,RedirectAttributes redirect)
	{
		if(checkxausanphamdauvao(tensanpham))
		{
			model.addAttribute("sanpham",sanPhamRepository.findByNameContaining(tensanpham));
			return "TrangTimKiemSanPham";	
		}
		model.addAttribute("khongcosanpham", 0);
		return "TrangTimKiemSanPham";
	}
	
	boolean checkxausanphamdauvao(String tensanpham)
	{
		if(tensanpham.equals(""))
		{
			return false;
		}
		return true;
	}
	
	@RequestMapping("/trangchu/quanlynhanvien/timkiemnhanvien")
	public String DieuKhienTimKiemNhanVien(@RequestParam String tennhanvien,Model model,RedirectAttributes redirect)
	{
		if(checkxaunhanviendauvao(tennhanvien))
		{
			model.addAttribute("nhanvien",nhanVienRepository.findByNameContaining(tennhanvien));
			return "TrangTimKiemNhanVien";
		}
		model.addAttribute("khongconhanvien",0);
		return "TrangTimKiemNhanVien";
	}
	
	boolean checkxaunhanviendauvao(String tennhanvien)
	{
		if(tennhanvien.equals(""))
		{
			return false;
		}
		return true;
	}
	
	@RequestMapping("/trangchu/quanlyuudai/timkiemuudai")
	public String DieuKhienTimKiemUuDai(@RequestParam String tenuudai,Model model,RedirectAttributes redirect)
	{
		if(checkxaunhanviendauvao(tenuudai))
		{
			model.addAttribute("danhsachuudai",uuDaiRepository.findByNameContaining(tenuudai));
			return "TrangTimKiemUuDai";
		}
		model.addAttribute("khongcouudai",0);
		return "TrangTimKiemUuDai";
	}
	
	boolean checkxauuudaidauvao(String tenuudai)
	{
		if(tenuudai.equals(""))
		{
			return false;
		}
		return true;
	}
	
	@RequestMapping("/trangchu/quanlykhachhang/timkiemkhachhang")
	public String DieuKhienTimKiemKhachHang(@RequestParam String tenkhachhang,Model model,RedirectAttributes redirect)
	{
		if(checkxaukhachhangdauvao(tenkhachhang))
		{
			model.addAttribute("khachhang",khachHangRepository.findByNameContaining(tenkhachhang));
			return "TrangTimKiemKhachHang";
		}
		model.addAttribute("khongcouudai",0);
		return "TrangTimKiemKhachHang";
	}
	
	boolean checkxaukhachhangdauvao(String tenkhachhang)
	{
		if(tenkhachhang.equals(""))
		{
			return false;
		}
		return true;
	}
	
}
