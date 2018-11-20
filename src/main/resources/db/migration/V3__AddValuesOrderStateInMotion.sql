UPDATE order_state
SET in_motion = 't'
WHERE id in (2, 3, 4);

UPDATE order_state
SET in_motion = 'f'
WHERE id in (1, 5, 6);