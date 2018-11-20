DELETE FROM orders;

ALTER TABLE orders
DROP COLUMN waiting_deadline,
DROP COLUMN deadline1,
DROP COLUMN deadline2,
ADD COLUMN waiting_deadline bigint,
ADD COLUMN deadline1 bigint,
ADD COLUMN deadline2 bigint;
