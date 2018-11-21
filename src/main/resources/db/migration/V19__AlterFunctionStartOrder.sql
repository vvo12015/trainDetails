DROP FUNCTION public.start_order(order_id bigint, train_id bigint);

CREATE OR REPLACE FUNCTION public.start_order(order_id bigint, train_id bigint)
  RETURNS boolean AS
$BODY$
    BEGIN
        UPDATE orders
        SET active_date = now()
        WHERE id = order_id;
        UPDATE orders
        SET state_id = 2
        WHERE id = order_id;
        DELETE FROM orders
        WHERE (id != order_id) AND (orders.train_id = train_id);
        RETURN true;
    END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

