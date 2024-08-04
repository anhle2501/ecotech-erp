-- Specify to check referential constraints
SET REFERENTIAL_INTEGRITY FALSE;

-- Insert data into the "user" table
INSERT INTO ecotechgroup_erp."user" (id, user_name, password, first_name, last_name, full_name, mobile_phone, description, enable, non_expired, non_lock, pw_non_expired) VALUES
                                                                                                                                                                              (47, 'nhutanh', '$2y$10$EmoXTx.AIF.gQxVOBfdUbe5cHZna719cfi3V6yDTGs5vhCF2Qt2AO', '', '', ' ', '', '', true, true, true, true),
                                                                                                                                                                              (57, 'manhtien', '$2a$10$KDKmkenFoSBM7S9.xouLH.VCFsH753NGg6nc8/YVgjZXYTDXejV2W', '', '', null, '', '', true, true, true, true),
                                                                                                                                                                              (44, 'admin', '$2y$10$9iT5h.fC/4ZTCDe1Fuo4T.icMMG9KtPCdQFf0YSJPHC/JP.JC5e7m', 'admin', 'admin', 'admin admin', 'admin', 'admin', true, true, true, true),
                                                                                                                                                                              (54, 'nhutanh1', '$2y$10$OwBFzqwe4uwvXQcRKVifQO4fBMrxPXE.FkAZdVzvJRfk1HFquSjsa', 'le', 'anh', null, '12345678', 'test user', true, true, true, true),
                                                                                                                                                                              (48, 'nhutanh11', '$2y$10$EmoXTx.AIF.gQxVOBfdUbe5cHZna719cfi3V6yDTGs5vhCF2Qt2AO', 'nhutanh11', 'nhutanh11', 'nhutanh11 nhutanh11', 'nhutanh11', 'nhutanh11', true, true, true, true);

-- Insert data into the "customer" table
INSERT INTO ecotechgroup_erp.customer (id, code, name, address, phone, tax_code, created_by, created_date, description, last_modified_by, last_modified_date, id_user_belong) VALUES
                                                                                                                                                                                  (3324, 'KHND00946', 'CÔNG TY TNHH MTV HIỆP HÒA', 'Thửa đất số 1312, tờ bản đồ số 7, tổ 07, ấp M', '939332997', '1500499526', 44, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                  (2986, 'KHND00607', 'ĐẠI LÝ HOÀNG PHÁT - Trương Văn Khôi', 'Ấp Tân Thành - Thanh Bình - Trảng Bom - Đồng ', NULL, NULL, 44, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                  (3299, 'KHND00921', 'VTNN MINH THÔNG', 'Xã Ea Sol, Huyện Ea H''leo, Tỉnh Đắk Lắk, Việt', '345147147', '8801990561-001', 44, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                  (2980, 'KHND00601', 'Phạm Phong Lai', 'Cần Đươc-Long An', NULL, NULL, 44, NULL, NULL, NULL, NULL, NULL);

-- Insert data into the "product" table
INSERT INTO ecotechgroup_erp.product (id, code, name, description, unit, last_modified_by, last_modified_date, created_date, created_by) VALUES
                                                                                                                                             (504, 'BVTV_BTP_Abamectin', 'Haihamec 3.6EC (Abamectin 3.6 % w/w EC)', '', 'Lít', 44, '2024-07-31 23:54:24.797734 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (516, 'BVTV_BTP_Lambda', 'Lambda-cyhalothrin 25g/l làm Perdana 2.5EC', '', 'Lít', 44, '2024-08-02 07:07:11.787798 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (506, 'BVTV_BTP_Abamectin-V', 'Haihamec 3.6EC (Abamectin 3.6w/w) - màu vàng trong', '', 'Lít', 44, '2024-08-03 02:37:37.288003 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (505, 'BVTV_BTP_Abamectin-54', 'Nguyên liệu bán thành phẩm ABA THAI 5.4EC (ABAMECTIN 54G/L EC )', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (507, 'BVTV_BTP_Alpha Cyperm', 'Alpha-Cypermethrin 50g/l EC- làm Pytax-S', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (508, 'BVTV_BTP_Astro', 'Astro 250SC (Azoxystrobin 250 g/L SC)', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (509, 'BVTV_BTP_Bạch tượng', 'Bạch Tượng 64EC (Emamectin benzoate 60g/l + Matrine 4g/l EC)', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (510, 'BVTV_BTP_Buxyzole', 'BUXYZOLE 240SC  (Azoxystrobin 80 g/l +Tebuconazole 160 g/l)', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44),
                                                                                                                                             (511, 'BVTV_BTP_Dimethoate', 'Dimethoate 400g/L làm Cova 40EC', 'TBVTV', 'Lít', 44, '2024-07-30 16:23:14.247342 +00:00', '2024-07-30 16:23:14.247342 +00:00', 44);

-- Insert data into the "payment_type" table
INSERT INTO ecotechgroup_erp.payment_type (id, name, description, debt_day, created_date, last_modified_date, created_by, last_modified_by) VALUES
    (20, 'Không nợ', '', 0, '2024-07-30 16:23:34.816379 +00:00', '2024-07-30 16:23:34.816379 +00:00', 44, 44);

-- Insert data into the "an_order" table
INSERT INTO ecotechgroup_erp.an_order (id, customer_id, description, create_at, payment_type_id, user_id, is_confirm, confirm_at, confirm_by, last_modified_date, last_modified_by, total_price) VALUES
                                                                                                                                                                                                     (255, 3324, '', '2024-08-03 02:37:24.058313 +00:00', 20, 44, false, NULL, NULL, '2024-08-03 02:37:24.058313 +00:00', 44, 0),
                                                                                                                                                                                                     (256, 3324, '', '2024-08-03 02:37:37.262029 +00:00', 20, 44, false, NULL, NULL, '2024-08-03 02:37:37.262029 +00:00', 44, 0);

-- Insert data into the "role" table
INSERT INTO ecotechgroup_erp.role (id, name, description) VALUES
                                                              (1, 'ROLE_USER', 'Quyền người dùng'),
                                                              (2, 'ROLE_ADMIN', 'Quyền admin');

-- Insert data into the "region" table
INSERT INTO ecotechgroup_erp.region (id, name, description) VALUES
                                                                (2, 'Mien Tay', 'Mien Tay'),
                                                                (1, 'Mien Dong 2 ', 'Mien Dong 29');

-- Insert data into the "order_product" table
INSERT INTO ecotechgroup_erp.order_product (id, order_id, product_id, price, total, quantity) VALUES
                                                                                                  (479, 255, 504, 0, 0, 0),
                                                                                                  (480, 256, 506, 0, 0, 0);

-- Insert data into the "region_user" table
INSERT INTO ecotechgroup_erp.region_user (region_id, user_id) VALUES
                                                                  (1, 44),
                                                                  (2, 44),
                                                                  (2, 47),
                                                                  (1, 47);

-- Insert data into the "role_permission" table
INSERT INTO ecotechgroup_erp.role_permission (role_id, permission_id) VALUES
                                                                          (2, 1),
                                                                          (2, 2),
                                                                          (1, 1),
                                                                          (1, 2);

-- Insert data into the "user_role" table
INSERT INTO ecotechgroup_erp.user_role (user_id_au, role_id) VALUES
                                                                 (48, 1),
                                                                 (57, 1),
                                                                 (47, 1),
                                                                 (44, 2);

SET REFERENTIAL_INTEGRITY TRUE;
