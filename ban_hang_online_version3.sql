create database NHOM17_BANHANGONLINE_V3
go

create table quantrivien 
(
	ten varchar(20) not null primary key,
	matkhau varchar(10) not null
)
go

create table nhacungcap 
(
	manhacc int not null primary key identity(1,1),
	tennhacc nvarchar(30) not null,
	sodienthoai varchar(11) not null,
	email varchar(20) not null,
	diachi nvarchar(40) null
)
go

create table loaisanpham
(
	maloaisanpham int not null primary key identity(1,1),
	tenloaisanpham nvarchar(30) not null
)
go

create table sanpham
(
	masanpham int not null primary key identity(1,1),
	tensanpham nvarchar(100) not null,
	ngaysanxuat date not null,
	hansudung date null,
	noidung nvarchar(100) null,
	giaca float,
	hinhanh nvarchar(200),
	soluong int not null,
	maloaisanpham int,
	constraint fk_maloaisanpham foreign key(maloaisanpham)
	references loaisanpham(maloaisanpham),
	manhacc int,
	constraint fk_manhacc foreign key(manhacc)
	references nhacungcap(manhacc)
)
go

create table nhanvien
(
	manv int not null primary key identity(1,1),
	matkhau varchar(11) not null,
	tennv nvarchar(20) not null,
	ngaysinh date null,
	sodienthoai varchar(11) not null,
	diachi nvarchar(30) null,
	hesoluong float
)
go

create table quanlynhanvien
(
	maquanlynhanvien int not null primary key identity(1,1),
	manv int not null,
	constraint fk_nhanvien_quanlynhanvien foreign key(manv)
	references nhanvien(manv) on delete cascade,
	madonhang int not null,
	constraint fk_donhang_quanlynhanvien foreign key(madonhang)
	references dondathang(madonhang) on delete cascade,
	ngaytiepnhan date not null
)
go

create table khachhang
(
	makh int not null primary key identity(1,1),
	tenkh nvarchar(25) not null,
	sodienthoai varchar(11) not null,
	diachi nvarchar(30) not null,
	email varchar(25) not null,
	matkhau varchar(11) not null
)
go

create table danhgia
(
	madanhgia int not null primary key identity(1,1),
	ngaydanhgia date not null,
	masanpham int not null,
	constraint fk_masanpham_danhgia foreign key(masanpham)
	references sanpham(masanpham) on delete cascade,
	makh int not null,
	constraint fk_makh_danhgia foreign key(makh)
	references khachhang(makh) on delete cascade,
	binhluan nvarchar(100) null
)
go

create table dondathang 
(
	madonhang int not null primary key identity(1,1),
	makh int,
	constraint fk_makh_dondathang foreign key(makh)
	references khachhang(makh) on delete cascade,
	ngaydathang date not null,
	trangthaidonhang int not null,
)
go

create table chitietdonhang
(
	maquanlyhoadon int not null primary key identity(1,1),
	madonhang int not null,
	constraint fk_madonhang_chitietdonhang foreign key(madonhang)
	references dondathang(madonhang) on delete cascade,
	masanpham int not null,
	constraint fk_masanpham_chitietdonhang foreign key(masanpham)
	references sanpham(masanpham),
	soluong int not null
)
go

create table uudai
(
	mauudai int not null primary key identity(1,1),
	tenuudai nvarchar(20) not null,
	sotiengiam float
)	
go

create table quanlyuudai
(
	maquanlyuudai int not null primary key identity(1,1),
	makh int not null,
	constraint fk_makh_uudai foreign key(makh)
	references khachhang(makh) on delete cascade,
	mauudai int not null,
	constraint fk_mauudai_uudai foreign key(mauudai)
	references uudai(mauudai) on delete cascade,
	ngaybatdau date not null,
	ngayketthuc date not null
)
go

INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Lê Hoàng Nam', N'0999999999', N'Hà Nội', N'namlh@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Nguyễn Thị Huế', N'0988888888', N'Thanh Hóa', N'huent@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Đào Công Minh', N'0977777777', N'Ninh Bình', N'minhdc@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Trần Bảo Ngọc', N'0966666666', N'Hồ Chí Minh', N'ngoctb@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Đinh Hà My', N'0955555555', N'Nghệ An', N'mydh@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Văn Thị Thu Hà', N'0944444444', N'Huế', N'thuha@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Đỗ Văn An', N'0933333333', N'Cà Mau', N'vanan@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Hoàng Thu Trà', N'0922222222', N'Thái Bình', N'thutra@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Bùi Hồng Loan', N'0911111111', N'Thái Nguyên', N'hongloan@gmail.com', N'123456')
INSERT [dbo].[khachhang] ([tenkh], [sodienthoai], [diachi], [email], [matkhau]) VALUES (N'Đặng Thảo Anh', N'0987654321', N'Hà Tĩnh', N'anhdt@gmail.com', N'123456')

INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Hàng mẹ, bé và Đồ chơi')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Phụ kiện điện tử')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Siêu thị tạp hóa')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Sức khỏe và làm đẹp')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Thiết bị điện tử')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'TV và Thiết bị điện gia dụng')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Hàng gia dụng & đời sống')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Thời trang nữ')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Thời trang nam')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Phụ kiện thời trang')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Thời thao và du lịch')
INSERT [dbo].[loaisanpham] ([tenloaisanpham]) VALUES (N'Ôtô, xe máy, thiết bị định vị')

INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Cửa hàng mẹ và bé', N'0983527511', N'mebe@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Phụ kiện điện tử', N'0936253748', N'dientu@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Siêu thị tạp hóa', N'0973647484', N'taphoa@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Làm đẹp', N'0763648444', N'skld@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Thiết bị gia dụng', N'0983654744', N'giadung@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'H&M', N'0983527513', N'h&m@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Sunhouse', N'0936253143', N'sunhouseu@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Zara', N'0973647467', N'zara@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Clio', N'0763648333', N'clio@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Vinfast', N'0983654455', N'vinfast@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'H&M', N'0983527513', N'h&m@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'GoldSun', N'0936253143', N'goldsun@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Luminarc', N'0973647467', N'Luminarc@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'FIvestar', N'0763648333', N'FIvestar@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'cinifa', N'0983654455', N'canifa@gmail.com', NULL)
INSERT [dbo].[nhacungcap] ([tennhacc], [sodienthoai], [email], [diachi]) VALUES (N'Yamaha', N'0763648333', N'yamaha@gmail.com', NULL)

INSERT [dbo].[nhanvien] ([matkhau], [tennv], [ngaysinh], [sodienthoai], [diachi], [hesoluong]) VALUES ('123456', N'Phạm Gia Khánh', CAST(N'1992-01-01' AS Date), N'0932456782', N'Hà Nội', 2.1)
INSERT [dbo].[nhanvien] ([matkhau], [tennv], [ngaysinh], [sodienthoai], [diachi], [hesoluong]) VALUES ('123456', N'Nguyễn Thị Quyên', CAST(N'1998-03-31' AS Date), N'0826474758', N'Hòa Bình', 2.2)
INSERT [dbo].[nhanvien] ([matkhau], [tennv], [ngaysinh], [sodienthoai], [diachi], [hesoluong]) VALUES ('123456', N'Nguyễn Đức Thịnh', CAST(N'1995-02-14' AS Date), N'0947561848', N'Vĩnh Phúc', 2.3)
INSERT [dbo].[nhanvien] ([matkhau], [tennv], [ngaysinh], [sodienthoai], [diachi], [hesoluong]) VALUES ('123456', N'Nguyễn Thu Trang', CAST(N'1997-08-09' AS Date), N'0925478294', N'Quảng Bình', 2)

INSERT [dbo].[quantrivien] ([ten], [matkhau]) VALUES (N'admin1', N'admin1')
INSERT [dbo].[quantrivien] ([ten], [matkhau]) VALUES (N'admin2', N'admin2')

INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'KH mới', 20000)
INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'sinh nhật cửa hàng', 50000)
INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'ngày tết', 100000)
INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'kỉ niệm mùng 8-3', 45000)
INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'ngày 30-5/1-5', 67000)
INSERT [dbo].[uudai] ([tenuudai], [sotiengiam]) VALUES (N'Ngày 20-10', 12000)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Camera hành trình Xiaomi 70Mai Pro',CAST(N'2020-01-01' AS Date),null,N'Kèm thẻ nhớ 64GB class 10',1250000,N'/image/dientuvamoto/CameraHTKemBoNho.png',10,2,2)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Camera hành trình Xiaomi 70Mai Pro',CAST(N'2020-01-10' AS Date),null,null,1000000,N'/image/dientuvamoto/CameraHTKhongBoNho.png',20,2,2)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Máy lọc không khí ô tô',CAST(N'2020-01-1' AS Date),CAST(N'2022-04-10' AS Date),N'màng lọc Formaldehyde PM2.5',365000,N'/image/dientuvamoto/MaylockhongkhiFormaldehydePM2.5.png',200,7,3)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Máy xông tinh dầu',CAST(N'2020-01-1' AS Date),CAST(N'2023-01-10' AS Date),N'Cho ô tô (chống say)',550000,N'/image/dientuvamoto/Mayxongtinhdauoto.png',432,7,3)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Module GPS Cho Camera Hành Trình XIAOMI 70mai Pro',CAST(N'2020-01-1' AS Date),null,N'Mô đun cảnh báo va chạm trên camera 70mai Pro',2550000,N'/image/dientuvamoto/ModuleGPSCameraHT.png',42,2,2)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Thiết bị Lọc Không Khí Làm Mát Xe',CAST(N'2020-01-1' AS Date),null,N'khuếch tán tinh dầu và hương liệu khô',556000,N'/image/dientuvamoto/Thietbilockhongkhi.png',52,5,2)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Xe máy Wave RSX căm thắng đĩa (Đỏ)',CAST(N'2020-01-1' AS Date),null,null,35000000,N'/image/dientuvamoto/WaveRSXcamthangdia(do).png',45,12,16)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Xe máy Wave RSX căm thắng đĩa (Xanh)',CAST(N'2020-01-1' AS Date),null,null,34000000,N'/image/dientuvamoto/WaveRSXcamthangdia(xanh).png',35,12,16)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Xe tay côn Honda Winner thể thao 2016 - Xanh Trăng',CAST(N'2020-01-1' AS Date),null,null,27500000,N'/image/dientuvamoto/XetayconHondaWinnerthethao2016.png',39,12,16)	

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Xe Yamaha NVX 125 Deluxe 2019 _ Đen',CAST(N'2020-01-1' AS Date),null,null,47500000,N'/image/dientuvamoto/XeYamahaNVX125Deluxe2019den.png',24,12,10)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Xe Yamaha NVX 125 Deluxe 2019 _ Đỏ',CAST(N'2020-01-1' AS Date),null,null,27500000,N'/image/dientuvamoto/XeYamahaNVX125Deluxe2019do.png',25,12,10)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bình Nước Thủy Tinh Luminarc Arc 1L G2635',CAST(N'2019-11-30' AS Date),null,null,40000,N'\image\giadinhvacuocsong\Bình Nước Thủy Tinh Luminarc Arc 1L G2635.png',250,7,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bình nước thủy tinh Luminarc Octime G2665 1.6 lít',CAST(N'2020-01-25' AS Date),null,null,60000,N'\image\giadinhvacuocsong\Bình nước thủy tinh Luminarc Octime G2665 1.6 lít.png',250,7,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'BÌNH ĐỰNG NƯỚC HELLO MASTER nhiều MÀU SẮC 420ML',CAST(N'2020-01-25' AS Date),null,null,55000,N'\image\giadinhvacuocsong\BÌNH ĐỰNG NƯỚC HELLO MASTER nhiều MÀU SẮC 420ML.png',370,7,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bộ 2 nồi thủy tinh Luminarc 1L 2L – LAVI6P0 (Nâu)',CAST(N'2020-01-25' AS Date),null,null,220000,N'\image\giadinhvacuocsong\Bộ 2 nồi thủy tinh Luminarc 1L 2L – LAVI6P0 (Nâu).png',170,7,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bộ 2 nồi thủy tinh Luminarc Amberline 1.5L, 3.5L LAVI6P3 - Sản xuất Pháp',CAST(N'2020-01-3' AS Date),null,null,670000,N'\image\giadinhvacuocsong\Bộ 2 nồi thủy tinh Luminarc Amberline 1.5L, 3.5L LAVI6P3 - Sản xuất Pháp.png',170,7,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bộ nồi inox 5 đáy SUNHOUSE SH787',CAST(N'2020-01-25' AS Date),null,null,325000,N'\image\giadinhvacuocsong\Bộ nồi inox 5 đáy SUNHOUSE SH787.png',174,7,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'bộ 3 nồi inox sun house',CAST(N'2019-06-25' AS Date),CAST(N'2026-06-25' AS Date),null,425000,N'\image\giadinhvacuocsong\bộ 3 nồi inox sun house.png',174,7,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo inox chống dính',CAST(N'2019-06-25' AS Date),null,null,725000,N'\image\giadinhvacuocsong\chảo inox chống dính.png',64,7,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo chống dính 3 lớp fivestar',CAST(N'2019-06-25' AS Date),null,null,425000,N'\image\giadinhvacuocsong\chảo chống dính 3 lớp fivestar.png',60,7,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo chống dính sunhouse',CAST(N'2020-02-25' AS Date),null,null,685000,N'\image\giadinhvacuocsong\chảo chống dính sunhouse.png',65,7,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo đá chống dính sunhouse',CAST(N'2020-02-25' AS Date),null,null,585000,N'\image\giadinhvacuocsong\chảo đá chống dính sunhouse.png',46,7,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo đúc siêu bền vanda 19cm sunhouse',CAST(N'2020-03-01' AS Date),null,null,785000,N'\image\giadinhvacuocsong\chảo đúc siêu bền vanda 19cm sunhouse.png',36,7,7)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'chảo sâu lòng 24cm sunhouse',CAST(N'2020-03-01' AS Date),null,null,785000,N'\image\giadinhvacuocsong\chảo sâu lòng 24cm sunhouse.png',36,7,7)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Combo 2 nồi Thủy tinh Trianon 2,5L 3,5L + Bộ 6 ly thủy tinh Islande 310ml+ bộ 2 sạn gỗ Xoan',CAST(N'2020-03-01' AS Date),null,null,785000,N'\image\giadinhvacuocsong\Combo 2 nồi Thủy tinh Trianon 2,5L 3,5L + Bộ 6 ly thủy tinh Islande 310ml+ bộ 2 sạn gỗ Xoan.png',36,7,7)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'combo 3 nồi inox fivestar',CAST(N'2020-02-01' AS Date),null,null,435000,N'\image\giadinhvacuocsong\combo 3 nồi inox fivestar.png',36,7,14)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Nồi thủy tinh 1L Luminarc C6317 (Nâu)',CAST(N'2019-10-01' AS Date),null,null,355000,N'\image\giadinhvacuocsong\Nồi thủy tinh 1L Luminarc C6317 (Nâu).png',38,7,13)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Nồi thủy tinh Luminarc Vitro Ikumi 1.0 lít',CAST(N'2019-10-01' AS Date),null,null,315000,N'\image\giadinhvacuocsong\Nồi thủy tinh Luminarc Vitro Ikumi 1.0 lít.png',38,7,13)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'nồi fivestar',CAST(N'2019-10-01' AS Date),null,null,125000,N'\image\giadinhvacuocsong\nồi fivestar.PNG',58,7,14)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'nồi inox 3day fivestar',CAST(N'2019-12-11' AS Date),null,null,425000,N'\image\giadinhvacuocsong\nồi inox 3day fivestar.png',18,7,14)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'nồi kho thịt cá',CAST(N'2019-12-11' AS Date),null,null,225000,N'\image\giadinhvacuocsong\nồi kho thịt cá.png',180,7,14)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'adidas Áo thun thể thao nam AFC TEE EH5709',CAST(N'2020-1-11' AS Date),null,null,235000,N'\image\thethaodulich\adidas Áo thun thể thao nam AFC TEE EH5709.png',180,9,11)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Adidas Áo thun thể thao nam CLUB 3STR TE DP2875',CAST(N'2020-1-11' AS Date),null,null,235000,N'\image\thethaodulich\Adidas Áo thun thể thao nam CLUB 3STR TE DP2875.png',180,9,11)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Adidas Áo thun thể thao nam CLUB 3STR TE DU0859',CAST(N'2020-1-11' AS Date),null,null,265000,N'\image\thethaodulich\Adidas Áo thun thể thao nam CLUB 3STR TE DU0859.png',240,9,11)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'adidas Áo thun thể thao nam M GRFX LNR T 3 EI4599',CAST(N'2020-1-11' AS Date),null,null,165000,N'\image\thethaodulich\adidas Áo thun thể thao nam M GRFX LNR T 3 EI4599.png',240,9,11)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'adidas Giày chạy bộ adidas skyrocket B43698',CAST(N'2020-2-11' AS Date),null,null,125000,N'\image\thethaodulich\adidas Giày chạy bộ adidas skyrocket B43698.png',240,11,11)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'adidas Giày thể thao chạy bộ nam DURAMO 9 EE8029',CAST(N'2020-2-11' AS Date),null,null,115000,N'\image\thethaodulich\adidas Giày thể thao chạy bộ nam DURAMO 9 EE8029.png',340,11,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'adidas Giày thể thao chạy bộ nam ENERGYFALCON EE9844',CAST(N'2020-2-11' AS Date),null,null,215000,N'\image\thethaodulich\adidas Giày thể thao chạy bộ nam ENERGYFALCON EE9844.png',320,11,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo Panel 3-Stripes longsleeve tee',CAST(N'2020-2-11' AS Date),null,null,145000,N'\image\thethaodulich\Áo Panel 3-Stripes longsleeve tee.png',120,11,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Ba Lô LAPTOP cao cấp LAZA BL282 (Đen)',CAST(N'2020-3-21' AS Date),null,null,345000,N'\image\thethaodulich\Ba Lô LAPTOP cao cấp LAZA BL282 (Đen).png',120,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Balo Laptop Hàn Quốc LAZA BL419 - Chính Hãng Phân Phối',CAST(N'2020-3-21' AS Date),null,null,385000,N'\image\thethaodulich\Balo Laptop Hàn Quốc LAZA BL419 - Chính Hãng Phân Phối.png',120,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Balo Laptop Thời Trang LAZA BL358 - Chính Hãng Phân Phối',CAST(N'2020-3-21' AS Date),null,null,415000,N'\image\thethaodulich\Balo Laptop Thời Trang LAZA BL358 - Chính Hãng Phân Phối.png',120,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Balo laptop thời trang LAZA BL452 - Chính Hãng Phân Phối',CAST(N'2020-3-21' AS Date),null,null,315700,N'\image\thethaodulich\Balo laptop thời trang LAZA BL452 - Chính Hãng Phân Phối.png',150,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Balo nam Hàn Quốc LAZA BL416 - Chính Hãng Phân Phối',CAST(N'2020-1-21' AS Date),null,null,305000,N'\image\thethaodulich\Balo nam Hàn Quốc LAZA BL416 - Chính Hãng Phân Phối.png',50,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Bộ Thể Thao Hè Dành Cho Nam HOT 2020',CAST(N'2020-1-21' AS Date),null,null,305000,N'\image\thethaodulich\Bộ Thể Thao Hè Dành Cho Nam HOT 2020.png',50,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Duramo 9 Shoes B96578',CAST(N'2020-1-21' AS Date),null,null,205000,N'\image\thethaodulich\Duramo 9 Shoes B96578.png',58,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày Adidas',CAST(N'2020-1-21' AS Date),null,null,175000,N'\image\thethaodulich\Giày Adidas.png',98,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày chạy Nam Anta',CAST(N'2020-1-21' AS Date),null,null,175000,N'\image\thethaodulich\Giày chạy Nam Anta.png',98,11,8)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày chạy nam nữ Anta',CAST(N'2020-1-11' AS Date),null,null,195000,N'\image\thethaodulich\Giày chạy nam nữ Anta.png',98,11,8)

-- thoi trang nam
INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp',CAST(N'2020-1-11' AS Date),null,null,95000,N'\image\thoitrangnam\Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp.png',98,9,9)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp1',CAST(N'2020-1-11' AS Date),null,null,99000,N'\image\thoitrangnam\Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp1.png',48,9,9)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp2',CAST(N'2020-1-11' AS Date),null,null,99000,N'\image\thoitrangnam\Áo sơ mi nam trắng Kojiba dài tay Hàn Quốc vải lụa thái chống nhăn cao cấp2.png',48,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo Thun Nam Cá Sấu Có Cổ Đẹp, Áo phông nam Chấm Bi Cao Cấp',CAST(N'2020-2-3' AS Date),null,null,109000,N'\image\thoitrangnam\Áo Thun Nam Cá Sấu Có Cổ Đẹp, Áo phông nam Chấm Bi Cao Cấp.png',145,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo thun nam cổ bẻ hàng xuất dư , Áo phông nam có cổ chất cotton đẹp',CAST(N'2020-2-5' AS Date),null,null,119000,N'\image\thoitrangnam\Áo thun nam cổ bẻ hàng xuất dư , Áo phông nam có cổ chất cotton đẹp.png',156,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo thun nam cổ trụ cao cấp giá rẻ ASALA - king01',CAST(N'2020-2-5' AS Date),null,null,89000,N'\image\thoitrangnam\Áo thun nam cổ trụ cao cấp giá rẻ ASALA - king01.png',116,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo thun nam cổ trụ thanh lich ASALA - king01',CAST(N'2020-2-5' AS Date),null,null,79000,N'\image\thoitrangnam\Áo thun nam cổ trụ thanh lich ASALA - king01.png',86,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo Thun Nam Trơn Có Cổ Kojiba Form Slim Chất Liệu Cotton Thun Cao Cấp Không Bai Nhão',CAST(N'2020-2-5' AS Date),null,null,135000,N'\image\thoitrangnam\Áo Thun Nam Trơn Có Cổ Kojiba Form Slim Chất Liệu Cotton Thun Cao Cấp Không Bai Nhão.png',86,9,3)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng Hồ Nữ Halei Mặt Vuông Dây Kim Loại Vàng Chính Hãng',CAST(N'2020-3-1' AS Date),null,null,335000,N'\image\thoitrangnam\Đồng Hồ Nữ Halei Mặt Vuông Dây Kim Loại Vàng Chính Hãng.png',26,9,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày Sneaker Nam X 2k19 Jet Navy DSMH02200XNH (Xanh Nhớt)',CAST(N'2020-3-1' AS Date),null,null,375000,N'\image\thoitrangnam\Giày Sneaker Nam X 2k19 Jet Navy DSMH02200XNH (Xanh Nhớt).png',96,9,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày sneaker thể thao thời trang nam X 2019 - Summer 2k19 BKL DSMH01000DEN (Đen)',CAST(N'2020-3-1' AS Date),null,N'Tặng tất',275000,N'\image\thoitrangnam\Giày sneaker thể thao thời trang nam X 2019 - Summer 2k19 BKL DSMH01000DEN (Đen) + Tặng tất.png',83,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày Sneaker thời trang nam X Summer 2K19 ADVENTURE COLLECTION - Thunder Black DSMH01100DEN (Đen) ',CAST(N'2020-3-1' AS Date),null,null,295000,N'\image\thoitrangnam\Giày Sneaker thời trang nam X Summer 2K19 ADVENTURE COLLECTION - Thunder Black DSMH01100DEN (Đen) .png',144,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Giày Thể Thao Nam Bitis Hunter Core - Black Line DSMH02900TRG',CAST(N'2020-3-1' AS Date),null,null,295000,N'\image\thoitrangnam\Giày Thể Thao Nam Bitis Hunter Core - Black Line DSMH02900TRG.png',144,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'LEVIS QUẦN JEANS NAM DÀI ',CAST(N'2020-3-1' AS Date),null,null,145000,N'\image\thoitrangnam\LEVIS QUẦN JEANS NAM DÀI .png',197,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần jean nam baggy - 2019',CAST(N'2019-11-1' AS Date),null,null,185000,N'\image\thoitrangnam\Quần jean nam baggy - 2019.png',207,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần jean nam đen trơn form chất cực đẹp - body (rách gối)',CAST(N'2020-1-7' AS Date),null,null,285000,N'\image\thoitrangnam\Quần jean nam đen trơn form chất cực đẹp - body (rách gối).png',320,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần jeans nam levis 501 Original Fit jeans ',CAST(N'2020-1-7' AS Date),null,null,185000,N'\image\thoitrangnam\Quần jeans nam levi 501 Original Fit jeans .png',122,9,6)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần jeans nam levis 501 Original Fit jeans hàng hiệu',CAST(N'2020-1-7' AS Date),null,null,85000,N'\image\thoitrangnam\Quần jeans nam levis 501 Original Fit jeans hàng hiệu.png',122,9,6)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần jeans nam levi 551 hàng hiệu',CAST(N'2020-2-8' AS Date),null,null,87000,N'\image\thoitrangnam\Quần jeans nam levi 551 hàng hiệu.png',182,9,6)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Sơ mi mềm mịn, không nhăn KINGMAN đẳng cấp',CAST(N'2020-2-8' AS Date),null,null,127000,N'\image\thoitrangnam\Sơ mi mềm mịn, không nhăn KINGMAN đẳng cấp.png',82,9,6)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo thun cộc tay TENTINO thun lạnh co dãn 4 chiều',CAST(N'2020-2-8' AS Date),null,null,97000,N'\image\thoitrangnu\Áo thun cộc tay TENTINO thun lạnh co dãn 4 chiều.png',52,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Áo thun form rộng Womyn Maybi',CAST(N'2020-2-13' AS Date),null,null,103000,N'\image\thoitrangnu\Áo thun form rộng Womyn Maybi.png',52,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ÁO THUN NỮ LEVIS 29663-0004',CAST(N'2020-2-14' AS Date),null,null,103000,N'\image\thoitrangnu\ÁO THUN NỮ LEVIS 29663-0004.png',72,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ÁO THUN NỮ LEVIS SS REGULAR',CAST(N'2020-2-14' AS Date),null,null,113000,N'\image\thoitrangnu\ÁO THUN NỮ LEVIS SS REGULAR.png',72,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ÁO THUN POLO NỮ LEVIS SS REGULAR',CAST(N'2020-2-14' AS Date),null,null,121000,N'\image\thoitrangnu\ÁO THUN POLO NỮ LEVIS SS REGULAR.png',29,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồ Bộ Áo Thun Nữ + Quần Đùi Sọt Thun Nữ Thể Thao, Ở Nhà, Đồ Ngủ Thời Trang Hàn Quốc Mới',CAST(N'2020-3-14' AS Date),null,null,161000,N'\image\thoitrangnu\Đồ Bộ Áo Thun Nữ + Quần Đùi Sọt Thun Nữ Thể Thao, Ở Nhà, Đồ Ngủ Thời Trang Hàn Quốc Mới.png',108,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'LEVIS ÁO THUN NỮ 17369-0833',CAST(N'2020-1-14' AS Date),null,null,151000,N'\image\thoitrangnu\LEVIS ÁO THUN NỮ 17369-0833.png',108,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'LEVIS ÁO THUN NỮ TAY NGẮN 52599-0024',CAST(N'2020-3-4' AS Date),null,null,191000,N'\image\thoitrangnu\LEVIS ÁO THUN NỮ TAY NGẮN 52599-0024.png',108,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'LEVIS QUẦN JEANS NỮ NGẮN 16888-0000',CAST(N'2020-3-4' AS Date),null,null,61000,N'\image\thoitrangnu\LEVIS QUẦN JEANS NỮ NGẮN 16888-0000.png',106,8,1)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'LEVIS QUẦN KHAKI NỮ DÀI NA',CAST(N'2020-3-4' AS Date),null,null,81000,N'\image\thoitrangnu\LEVIS QUẦN KHAKI NỮ DÀI NA.png',106,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'OUDIANYUN Đầm voan quá gối',CAST(N'2020-2-1' AS Date),null,null,181000,N'\image\thoitrangnu\OUDIANYUN Đầm voan quá gối.png',16,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Quần Jean Họa Tiết ZARA Skinny chính hãng',CAST(N'2020-2-1' AS Date),null,null,121000,N'\image\thoitrangnu\Quần Jean Họa Tiết ZARA Skinny chính hãng.png',16,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'QUẦN JEANS NỮ LEVIS',CAST(N'2020-2-1' AS Date),null,null,111000,N'\image\thoitrangnu\QUẦN JEANS NỮ LEVIS.png',76,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'QUẦN JEANS NỮ LEVIS1',CAST(N'2020-2-1' AS Date),null,null,117000,N'\image\thoitrangnu\QUẦN JEANS NỮ LEVIS1.png',196,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ZANZEA Đầm Nữ Form Rộng Không Tay Phong Cách Hàn Quốc',CAST(N'2019-12-1' AS Date),null,null,127000,N'\image\thoitrangnu\ZANZEA Đầm Nữ Form Rộng Không Tay Phong Cách Hàn Quốc.png',206,3,15)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ZANZEA Đầm Nữ Form Rộng Không Tay Phong Cách Hàn Quốc 1',CAST(N'2019-12-1' AS Date),null,null,157000,N'\image\thoitrangnu\ZANZEA Đầm Nữ Form Rộng Không Tay Phong Cách Hàn Quốc1.png',276,3,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ZANZEA Long Top Solid Blouse Shirt Dress Women Long Sleeve Pockets Buttons Dress2',CAST(N'2019-12-1' AS Date),null,null,157000,N'\image\thoitrangnu\ZANZEA Long Top Solid Blouse Shirt Dress Women Long Sleeve Pockets Buttons Dress2.png',276,3,12)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng Hồ Cặp Đôi Nam Nữ Halei Mặt Đen Dây Da Kim Loại Chính Hãng',CAST(N'2019-12-1' AS Date),null,null,357000,N'\image\thoitrangphukien\Đồng Hồ Cặp Đôi Nam Nữ Halei Mặt Đen Dây Da Kim Loại Chính Hãng.png',236,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng hồ nữ dây da JULIUS JU972',CAST(N'2019-12-1' AS Date),null,null,437000,N'\image\thoitrangphukien\Đồng hồ nữ dây da JULIUS JU972.png',136,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng Hồ Nữ Halei Chính hãng Chống Nước, Chống Xước',CAST(N'2019-12-1' AS Date),null,null,507000,N'\image\thoitrangphukien\Đồng Hồ Nữ Halei Chính hãng Chống Nước, Chống Xước.png',36,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng Hồ Nữ JA-1133 Julius Hàn Quốc dây da mặt kẻ caro',CAST(N'2020-3-16' AS Date),null,null,409000,N'\image\thoitrangphukien\Đồng Hồ Nữ JA-1133 Julius Hàn Quốc dây da mặt kẻ caro.png',31,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ĐỒNG HỒ NỮ JA-426LF JU1052 JULIUS HÀN QUỐC DÂY THÉP (VÀNG HỒNG)',CAST(N'2020-3-16' AS Date),null,null,609000,N'\image\thoitrangphukien\ĐỒNG HỒ NỮ JA-426LF JU1052 JULIUS HÀN QUỐC DÂY THÉP (VÀNG HỒNG).png',71,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'ĐỒNG HỒ NỮ SKMEI 1083, DÂY DA CAO CẤP, KIỂU DÁNG SANG TRỌNG',CAST(N'2020-3-16' AS Date),null,null,509000,N'\image\thoitrangphukien\ĐỒNG HỒ NỮ SKMEI 1083, DÂY DA CAO CẤP, KIỂU DÁNG SANG TRỌNG.png',78,10,4)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Đồng hồ nữ SKMEI 1397 chính hãng dây da cao cấp SK1397',CAST(N'2020-1-16' AS Date),null,null,229000,N'\image\thoitrangphukien\Đồng hồ nữ SKMEI 1397 chính hãng dây da cao cấp SK1397.png',28,10,5)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'SKMEI Women Fashion Watches Simple Quartz Watch Leather Waterproof Casual Wristwatches Female Clock',CAST(N'2020-1-16' AS Date),null,null,269000,N'\image\thoitrangphukien\SKMEI Women Fashion Watches Simple Quartz Watch Leather Waterproof Casual Wristwatches Female Clock.png',28,10,5)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Vòng tay nữ dạng lắc xích thiết kế cao cấp bền màu Orin V2142',CAST(N'2020-1-16' AS Date),null,null,369000,N'\image\thoitrangphukien\Vòng tay nữ dạng lắc xích thiết kế cao cấp bền màu Orin V2142.png',52,10,5)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Vòng tay nữ thời trang dạng lắc bi cao cấp thiết kế 2 màu Orin V2072',CAST(N'2020-2-6' AS Date),null,null,153000,N'\image\thoitrangphukien\Vòng tay nữ thời trang dạng lắc bi cao cấp thiết kế 2 màu Orin V2072.png',52,10,5)

INSERT [dbo].[sanpham] ([tensanpham], [ngaysanxuat], [hansudung], [noidung], [giaca], [hinhanh], [soluong], [maloaisanpham], [manhacc])
VALUES (N'Vòng tay nữ, lắc tay pha lê nữ thiết kế thời thượng Orin V2183',CAST(N'2020-2-6' AS Date),null,null,653000,N'\image\thoitrangphukien\Vòng tay nữ, lắc tay pha lê nữ thiết kế thời thượng Orin V2183.png',49,10,5)




