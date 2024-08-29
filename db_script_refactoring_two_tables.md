## Создайте таблицу engineers:

```sql
CREATE TABLE engineers
(
id          BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
first_name  VARCHAR(255) NOT NULL,
last_name   VARCHAR(255) NOT NULL,
description TEXT
);
```
## Заполните таблицу engineers уникальными именами:

```sql
INSERT INTO engineers (first_name, last_name)
SELECT DISTINCT
SUBSTRING_INDEX(engineer_name, ' ', 1) AS first_name,
SUBSTRING_INDEX(engineer_name, ' ', -1) AS last_name
FROM cars;
```

## Добавьте колонку engineer_id в таблицу cars:
```sql
ALTER TABLE cars
ADD COLUMN engineer_id BIGINT;
```

## Обновите таблицу cars:

```sql
-- Отключение безопасного режима
SET SQL_SAFE_UPDATES = 0;

-- Ваш запрос
UPDATE cars
    JOIN engineers ON CONCAT(engineers.first_name, ' ', engineers.last_name) = cars.engineer_name
    SET cars.engineer_id = engineers.id;

-- Включение безопасного режима обратно
SET SQL_SAFE_UPDATES = 1;
```

## Удалите старую колонку engineer_name, если она больше не нужна:
```sql
ALTER TABLE cars
DROP COLUMN engineer_name;
```

## Если невозможно удалить инженера:

```sql
ALTER TABLE cars DROP FOREIGN KEY fk_engineer;

ALTER TABLE cars
ADD CONSTRAINT fk_engineer FOREIGN KEY (engineer_id) REFERENCES engineers(id) ON DELETE CASCADE;
```
