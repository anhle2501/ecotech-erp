-- Create the function if it doesn't exist
CREATE OR REPLACE FUNCTION generate_customer_code() RETURNS TRIGGER AS $$
BEGIN
    NEW.code := 'KHDN' || LPAD(nextval('customer_code_seq')::TEXT, 5, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Check if the trigger already exists before creating it
DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'before_insert_customer') THEN
            CREATE TRIGGER before_insert_customer
                BEFORE INSERT ON ecotechgroup_erp.customer
                FOR EACH ROW
            EXECUTE FUNCTION generate_customer_code();
        END IF;
    END;
$$;
