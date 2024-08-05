
CREATE OR REPLACE FUNCTION generate_customer_code() RETURNS TRIGGER AS $$
BEGIN
    NEW.code := 'KHND' || LPAD(nextval('customer_code_seq')::TEXT, 5, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_customer
    BEFORE INSERT ON ecotechgroup_erp.customer
    FOR EACH ROW
EXECUTE FUNCTION generate_customer_code();

