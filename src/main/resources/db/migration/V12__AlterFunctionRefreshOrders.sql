DROP FUNCTION refresh_orders();

CREATE OR REPLACE FUNCTION public.refresh_orders()
  RETURNS boolean AS
$BODY$
    BEGIN
        UPDATE orders
        SET state_id = 7
        WHERE orders.state_id = 1 AND orders.creation_date + (orders.waiting_deadline * interval '1 second') < now();
        UPDATE orders
        SET state_id = 3
        WHERE orders.state_id = 2 AND orders.creation_date + (orders.deadline1 * interval '1 second') < now();
        UPDATE orders
        SET state_id = 4
        WHERE orders.state_id = 3 AND orders.creation_date + (orders.deadline2 * interval '1 second') < now();
        DELETE FROM orders
        WHERE state_id = 7;
        RETURN true;
    END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;