--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE ecotechgroup_erp;




--
-- Drop roles
--

DROP ROLE softwaredev;


--
-- Roles
--

CREATE ROLE softwaredev;
ALTER ROLE softwaredev WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:MKQHEqAE6pkiEDK7lUCN5A==$OyZIIGFweP+Fm4eTnoJUaHLbz9W0VKrKpw48XU7nzJA=:qLTai0IbRe9d+YP52LlNdi3HxjOfiMvFtpMXmMUXr7k=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: softwaredev
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO softwaredev;

\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: softwaredev
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: softwaredev
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: softwaredev
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "ecotechgroup_erp" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ecotechgroup_erp; Type: DATABASE; Schema: -; Owner: softwaredev
--

CREATE DATABASE ecotechgroup_erp WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE ecotechgroup_erp OWNER TO softwaredev;

\connect ecotechgroup_erp

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ecotechgroup_erp; Type: DATABASE PROPERTIES; Schema: -; Owner: softwaredev
--

ALTER DATABASE ecotechgroup_erp SET search_path TO 'public', 'ecotechgroup_erp';


\connect ecotechgroup_erp

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ecotechgroup_erp; Type: SCHEMA; Schema: -; Owner: softwaredev
--

CREATE SCHEMA ecotechgroup_erp;


ALTER SCHEMA ecotechgroup_erp OWNER TO softwaredev;

--
-- Name: get_col_types(regclass, smallint[]); Type: FUNCTION; Schema: public; Owner: softwaredev
--

CREATE FUNCTION public.get_col_types(rel regclass, cols smallint[]) RETURNS text
    LANGUAGE sql
AS $$

select string_agg(typname, ', ' order by ordinality)

from pg_attribute a

         join pg_type t on t.oid = atttypid,

    unnest(cols) with ordinality

where attrelid = rel

  and attnum = unnest

$$;


ALTER FUNCTION public.get_col_types(rel regclass, cols smallint[]) OWNER TO softwaredev;

--
-- Name: is_null(anyelement); Type: FUNCTION; Schema: public; Owner: softwaredev
--

CREATE FUNCTION public.is_null(anyelement) RETURNS boolean
    LANGUAGE sql IMMUTABLE
AS $_$SELECT $1 IS NULL$_$;


ALTER FUNCTION public.is_null(anyelement) OWNER TO softwaredev;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: an_order; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.an_order (
                                           id bigint NOT NULL,
                                           customer_id bigint,
                                           description character varying(1000),
                                           create_at timestamp with time zone,
                                           payment_type_id bigint,
                                           total_price numeric DEFAULT '0'::numeric,
                                           user_id bigint NOT NULL,
                                           is_confirm boolean DEFAULT false,
                                           confirm_at timestamp with time zone,
                                           confirm_by bigint,
                                           last_modified_by integer,
                                           last_modified_date timestamp with time zone
);


ALTER TABLE ecotechgroup_erp.an_order OWNER TO softwaredev;

--
-- Name: an_order_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.an_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.an_order_id_seq OWNER TO softwaredev;

--
-- Name: an_order_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.an_order_id_seq OWNED BY ecotechgroup_erp.an_order.id;


--
-- Name: customer; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.customer (
                                           id bigint NOT NULL,
                                           code character varying(45),
                                           name character varying(100) NOT NULL,
                                           address character varying(200),
                                           phone character varying(10) NOT NULL,
                                           tax_code character varying(20),
                                           created_by bigint,
                                           created_date timestamp with time zone,
                                           description character varying(1000),
                                           last_modified_by bigint,
                                           last_modified_date timestamp with time zone
);


ALTER TABLE ecotechgroup_erp.customer OWNER TO softwaredev;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.customer_id_seq OWNER TO softwaredev;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.customer_id_seq OWNED BY ecotechgroup_erp.customer.id;


--
-- Name: order_product; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.order_product (
                                                id bigint NOT NULL,
                                                order_id bigint NOT NULL,
                                                product_id bigint NOT NULL,
                                                price bigint DEFAULT '0'::bigint,
                                                quantity bigint DEFAULT '0'::bigint,
                                                total bigint DEFAULT 0
);


ALTER TABLE ecotechgroup_erp.order_product OWNER TO softwaredev;

--
-- Name: order_product_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.order_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.order_product_id_seq OWNER TO softwaredev;

--
-- Name: order_product_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.order_product_id_seq OWNED BY ecotechgroup_erp.order_product.id;


--
-- Name: payment_type; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.payment_type (
                                               id bigint NOT NULL,
                                               name character varying(45),
                                               description character varying(1000),
                                               day integer DEFAULT 0,
                                               created_by integer,
                                               created_date timestamp with time zone,
                                               last_modified_by integer,
                                               last_modified_date timestamp with time zone
);


ALTER TABLE ecotechgroup_erp.payment_type OWNER TO softwaredev;

--
-- Name: payment_type_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.payment_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.payment_type_id_seq OWNER TO softwaredev;

--
-- Name: payment_type_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.payment_type_id_seq OWNED BY ecotechgroup_erp.payment_type.id;


--
-- Name: permission; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.permission (
                                             id bigint NOT NULL,
                                             name character varying(45) NOT NULL,
                                             description character varying(45)
);


ALTER TABLE ecotechgroup_erp.permission OWNER TO softwaredev;

--
-- Name: permission_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.permission_id_seq OWNER TO softwaredev;

--
-- Name: permission_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.permission_id_seq OWNED BY ecotechgroup_erp.permission.id;


--
-- Name: product; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.product (
                                          id bigint NOT NULL,
                                          code character varying(45) NOT NULL,
                                          name character varying(100) NOT NULL,
                                          description character varying(1000),
                                          quantity bigint,
                                          unit character varying(10),
                                          last_modified_by bigint,
                                          last_modified_date timestamp with time zone,
                                          created_date timestamp with time zone,
                                          created_by integer
);


ALTER TABLE ecotechgroup_erp.product OWNER TO softwaredev;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.product_id_seq OWNER TO softwaredev;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.product_id_seq OWNED BY ecotechgroup_erp.product.id;


--
-- Name: role; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.role (
                                       id bigint NOT NULL,
                                       name character varying(45) NOT NULL,
                                       description character varying(1000)
);


ALTER TABLE ecotechgroup_erp.role OWNER TO softwaredev;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.role_id_seq OWNER TO softwaredev;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.role_id_seq OWNED BY ecotechgroup_erp.role.id;


--
-- Name: role_permission; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.role_permission (
                                                  role_id bigint NOT NULL,
                                                  permission_id bigint NOT NULL
);


ALTER TABLE ecotechgroup_erp.role_permission OWNER TO softwaredev;

--
-- Name: user; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp."user" (
                                         id bigint NOT NULL,
                                         user_name character varying(45) NOT NULL,
                                         password character varying(1000) NOT NULL,
                                         first_name character varying(45),
                                         last_name character varying(45),
                                         full_name character varying(90),
                                         mobile_phone character varying(45),
                                         description character varying(1000),
                                         enable boolean DEFAULT true,
                                         non_expired boolean DEFAULT true,
                                         non_lock boolean DEFAULT true,
                                         pw_non_expired boolean DEFAULT true,
                                         access_token character varying
);


ALTER TABLE ecotechgroup_erp."user" OWNER TO softwaredev;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE SEQUENCE ecotechgroup_erp.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ecotechgroup_erp.user_id_seq OWNER TO softwaredev;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER SEQUENCE ecotechgroup_erp.user_id_seq OWNED BY ecotechgroup_erp."user".id;


--
-- Name: user_role; Type: TABLE; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE TABLE ecotechgroup_erp.user_role (
                                            user_id_au bigint NOT NULL,
                                            role_id bigint NOT NULL
);


ALTER TABLE ecotechgroup_erp.user_role OWNER TO softwaredev;

--
-- Name: an_order id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.an_order_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.customer ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.customer_id_seq'::regclass);


--
-- Name: order_product id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.order_product ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.order_product_id_seq'::regclass);


--
-- Name: payment_type id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.payment_type ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.payment_type_id_seq'::regclass);


--
-- Name: permission id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.permission ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.permission_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.product ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.role ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.role_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp."user" ALTER COLUMN id SET DEFAULT nextval('ecotechgroup_erp.user_id_seq'::regclass);


--
-- Data for Name: an_order; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.an_order (id, customer_id, description, create_at, payment_type_id, total_price, user_id, is_confirm, confirm_at, confirm_by, last_modified_by, last_modified_date) FROM stdin;
226	180		2023-08-06 17:28:12.48267+00	14	5	44	f	\N	\N	44	2023-08-06 17:28:22.012697+00
225	180		2023-08-06 17:27:05.607306+00	14	17	44	f	\N	\N	44	2023-08-06 17:28:33.85539+00
229	216		2024-01-07 16:48:34.516577+00	14	0	47	f	\N	\N	47	2024-01-07 16:48:34.516577+00
230	216		2024-01-07 16:50:59.34567+00	14	0	47	f	\N	\N	47	2024-01-07 16:50:59.34567+00
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.customer (id, code, name, address, phone, tax_code, created_by, created_date, description, last_modified_by, last_modified_date) FROM stdin;
180	1	Lê Nhựt Anh	ádá	4374749933	123456789	44	2023-08-06 17:09:12.969349+00	Khách hàng	44	2023-08-06 17:09:12.969349+00
185	testtemplate	test template	40/20 Lu Gia	0374749933	123456789	\N	\N	Khách hàng thường	\N	\N
186	test1	test1	40/20 Lu Gia	0374749933	123456789	44	\N	Khách hàng thường	\N	\N
212	2	2222222	2	2222222222	222222222	\N	\N	2	\N	\N
213	123	12323232	23213213	3213213123	23123123213	\N	\N	3123123	\N	\N
216	123321	321321321313	3213213213213	1231232133	21321321	47	2024-01-03 03:12:21.196365+00	12312312	47	2024-01-06 12:58:51.975484+00
217	212321	32132313	12312312312	1231231233	32131312312	47	2024-01-06 12:59:18.453655+00	123123123	47	2024-01-06 12:59:18.453655+00
\.


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.order_product (id, order_id, product_id, price, quantity, total) FROM stdin;
437	229	16	0	0	0
438	230	13	0	0	0
\.


--
-- Data for Name: payment_type; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.payment_type (id, name, description, day, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
14	3	3	0	44	2023-08-04 16:04:29.54029+00	44	2023-08-04 16:04:29.54029+00
\.


--
-- Data for Name: permission; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.permission (id, name, description) FROM stdin;
2	admin:create	admin-tạo
3	admin:update	admin-ghi
1	admin:read	admin-xem
4	admin:delete	admin-xóa
6	user:read	Chức năng đọc
7	user:create	Chức năng tạo mới
8	user:update	Chức năng cập nhật
9	user:delete	Chức năng xóa
10	user:upload	Chức năng tải lên
11	user:download	Chức năng tải xuống
12	user:report	Chức năng báo cáo
13	customer:read	Chức năng đọc
14	customer:create	Chức năng tạo mới
15	customer:update	Chức năng cập nhật
16	customer:delete	Chức năng xóa
17	customer:upload	Chức năng tải lên
18	customer:download	Chức năng tải xuống
19	customer:report	Chức năng báo cáo
20	product:read	Chức năng đọc
21	product:create	Chức năng tạo mới
22	product:update	Chức năng cập nhật
23	product:delete	Chức năng xóa
24	product:upload	Chức năng tải lên
25	product:download	Chức năng tải xuống
26	product:report	Chức năng báo cáo
27	payment-type:read	Chức năng đọc
28	payment-type:create	Chức năng tạo mới
29	payment-type:update	Chức năng cập nhật
30	payment-type:delete	Chức năng xóa
31	payment-type:upload	Chức năng tải lên
32	payment-type:download	Chức năng tải xuống
33	payment-type:report	Chức năng báo cáo
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.product (id, code, name, description, quantity, unit, last_modified_by, last_modified_date, created_date, created_by) FROM stdin;
13	2	22222		\N	2	48	2023-08-02 18:59:21.181333+00	2023-08-02 18:58:54.617334+00	44
17	6	66666	666	\N	6	44	2023-08-02 19:00:17.935335+00	2023-08-02 19:00:17.935335+00	44
15	4	44444		\N	4	48	2023-08-02 19:01:50.904333+00	2023-08-02 19:00:02.515332+00	44
14	3	33333		\N	3	44	2023-08-04 16:27:01.975423+00	2023-08-02 18:59:55.944334+00	44
16	5	55555		\N	55	47	2024-01-07 16:48:34.61729+00	2023-08-02 19:00:09.439332+00	44
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.role (id, name, description) FROM stdin;
1	ROLE_USER	Quyền người dùng
2	ROLE_ADMIN	Quyền admin
\.


--
-- Data for Name: role_permission; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.role_permission (role_id, permission_id) FROM stdin;
2	1
2	2
1	1
1	2
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp."user" (id, user_name, password, first_name, last_name, full_name, mobile_phone, description, enable, non_expired, non_lock, pw_non_expired, access_token) FROM stdin;
47	nhutanh	$2a$10$p4bIwWEpyadMF4hYQjd58ufK2EPGCUdvKlCLOn0OWSiZlgcEq66K6			 			t	t	t	t	\N
48	nhutanh11	$2a$10$TsOC3XNmSpepvFM/6iHWMOm8ApyDGi/9Ai3B7OjwzMCXzhpAp5gku	nhutanh11	nhutanh11	nhutanh11 nhutanh11	nhutanh11	nhutanh11	t	t	t	t	\N
44	admin	$2a$10$uy/N1I25sqqgo3JDO/q4wOfHrE933JBqpu7yTEVlo3p3DwThGwOqG	admin	admin	admin admin	admin	admin	t	t	t	t	\N
54	nhutanh1	123456789abc	le	anh	\N	12345678	test user	t	t	t	t	\N
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: ecotechgroup_erp; Owner: softwaredev
--

COPY ecotechgroup_erp.user_role (user_id_au, role_id) FROM stdin;
47	1
48	1
44	2
\.


--
-- Name: an_order_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.an_order_id_seq', 230, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.customer_id_seq', 217, true);


--
-- Name: order_product_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.order_product_id_seq', 438, true);


--
-- Name: payment_type_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.payment_type_id_seq', 19, true);


--
-- Name: permission_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.permission_id_seq', 33, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.product_id_seq', 25, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.role_id_seq', 2, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: ecotechgroup_erp; Owner: softwaredev
--

SELECT pg_catalog.setval('ecotechgroup_erp.user_id_seq', 55, true);


--
-- Name: an_order idx_16391_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT idx_16391_primary PRIMARY KEY (id);


--
-- Name: customer idx_16400_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.customer
    ADD CONSTRAINT idx_16400_primary PRIMARY KEY (id);


--
-- Name: order_product idx_16407_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.order_product
    ADD CONSTRAINT idx_16407_primary PRIMARY KEY (id);


--
-- Name: payment_type idx_16414_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.payment_type
    ADD CONSTRAINT idx_16414_primary PRIMARY KEY (id);


--
-- Name: permission idx_16422_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.permission
    ADD CONSTRAINT idx_16422_primary PRIMARY KEY (id);


--
-- Name: product idx_16427_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.product
    ADD CONSTRAINT idx_16427_primary PRIMARY KEY (id);


--
-- Name: role idx_16434_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.role
    ADD CONSTRAINT idx_16434_primary PRIMARY KEY (id);


--
-- Name: role_permission idx_16440_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.role_permission
    ADD CONSTRAINT idx_16440_primary PRIMARY KEY (role_id, permission_id);


--
-- Name: user idx_16444_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp."user"
    ADD CONSTRAINT idx_16444_primary PRIMARY KEY (id);


--
-- Name: user_role idx_16454_primary; Type: CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.user_role
    ADD CONSTRAINT idx_16454_primary PRIMARY KEY (user_id_au, role_id);


--
-- Name: idx_16422_name_unique; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX idx_16422_name_unique ON ecotechgroup_erp.permission USING btree (name);


--
-- Name: idx_16434_name_unique; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX idx_16434_name_unique ON ecotechgroup_erp.role USING btree (name);


--
-- Name: idx_16440_fk_privilege_id_idx; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE INDEX idx_16440_fk_privilege_id_idx ON ecotechgroup_erp.role_permission USING btree (permission_id);


--
-- Name: idx_16444_user_name_unique; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX idx_16444_user_name_unique ON ecotechgroup_erp."user" USING btree (user_name);


--
-- Name: product_code_uindex; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX product_code_uindex ON ecotechgroup_erp.product USING btree (code);


--
-- Name: product_name_uindex; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX product_name_uindex ON ecotechgroup_erp.product USING btree (name);


--
-- Name: product_unit_uindex; Type: INDEX; Schema: ecotechgroup_erp; Owner: softwaredev
--

CREATE UNIQUE INDEX product_unit_uindex ON ecotechgroup_erp.product USING btree (unit);


--
-- Name: an_order fk_confirm_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT fk_confirm_by FOREIGN KEY (confirm_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: customer fk_create_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.customer
    ADD CONSTRAINT fk_create_by FOREIGN KEY (created_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: payment_type fk_create_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.payment_type
    ADD CONSTRAINT fk_create_by FOREIGN KEY (created_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: product fk_create_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.product
    ADD CONSTRAINT fk_create_by FOREIGN KEY (created_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: an_order fk_customer_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES ecotechgroup_erp.customer(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: an_order fk_last_modified_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT fk_last_modified_by FOREIGN KEY (last_modified_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: payment_type fk_last_modified_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.payment_type
    ADD CONSTRAINT fk_last_modified_by FOREIGN KEY (last_modified_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: product fk_last_modified_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.product
    ADD CONSTRAINT fk_last_modified_by FOREIGN KEY (last_modified_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: customer fk_last_modified_by; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.customer
    ADD CONSTRAINT fk_last_modified_by FOREIGN KEY (last_modified_by) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: order_product fk_order_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.order_product
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES ecotechgroup_erp.an_order(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: an_order fk_payment_type_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT fk_payment_type_id FOREIGN KEY (payment_type_id) REFERENCES ecotechgroup_erp.payment_type(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: role_permission fk_privilege_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.role_permission
    ADD CONSTRAINT fk_privilege_id FOREIGN KEY (permission_id) REFERENCES ecotechgroup_erp.permission(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: order_product fk_product_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.order_product
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES ecotechgroup_erp.product(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: role_permission fk_role_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.role_permission
    ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES ecotechgroup_erp.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: an_order fk_user_id; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.an_order
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: user_role fk_user_id_au; Type: FK CONSTRAINT; Schema: ecotechgroup_erp; Owner: softwaredev
--

ALTER TABLE ONLY ecotechgroup_erp.user_role
    ADD CONSTRAINT fk_user_id_au FOREIGN KEY (user_id_au) REFERENCES ecotechgroup_erp."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: softwaredev
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO softwaredev;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: softwaredev
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

