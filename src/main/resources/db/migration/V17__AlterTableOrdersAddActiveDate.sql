ALTER TABLE orders
ADD COLUMN active_date timestamp without time zone;

CREATE OR REPLACE FUNCTION public.start_order(order_id bigint)
  RETURNS boolean AS
$BODY$
    BEGIN
        UPDATE orders
        SET active_date = now()
        WHERE id = order_id;
        UPDATE orders
        SET state_id = 2
        WHERE id = order_id;
        RETURN true;
    END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

