```sql
create database transport;
create table cars (
id bigint auto_increment primary key not null,
brand varchar(255) not null,
model varchar(255) not null,
year bigint not null,
city_of_manufacture varchar(255) not null,
engineer_name varchar(255) not null
);
INSERT INTO cars (brand, model, year, city_of_manufacture, engineer_name) VALUES
('Toyota', 'Camry', 2021, 'Toyota City', 'Hiroshi Kawamura'),
('Honda', 'Civic', 2019, 'Suzuka', 'Masahiro Maeda'),
('Ford', 'Mustang', 2020, 'Detroit', 'Jim Hackett'),
('Chevrolet', 'Impala', 2018, 'Oshawa', 'Mary Barra'),
('BMW', 'X5', 2022, 'Spartanburg', 'Klaus Fröhlich'),
('Audi', 'A6', 2021, 'Ingolstadt', 'Markus Duesmann'),
('Mercedes-Benz', 'C-Class', 2020, 'Bremen', 'Ola Källenius'),
('Hyundai', 'Elantra', 2019, 'Ulsan', 'Lee Byung-tae'),
('Tesla', 'Model 3', 2021, 'Fremont', 'Elon Musk'),
('Nissan', 'Altima', 2018, 'Yokohama', 'Makoto Uchida');
```