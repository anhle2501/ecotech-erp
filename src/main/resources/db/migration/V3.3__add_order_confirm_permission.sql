ALTER SEQUENCE permission_id_seq RESTART WITH 55;

INSERT INTO ecotechgroup_erp.permission(name, description)
SELECT 'order:confirm', 'Đặt hàng - duyệt đơn'
    WHERE NOT EXISTS (
    SELECT 1 FROM ecotechgroup_erp.permission WHERE name = 'order:confirm'
);