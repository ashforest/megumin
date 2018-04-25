CREATE DATABASE IF NOT EXISTS store_db;

USE store_db;

CREATE TABLE users (id INT AUTO_INCREMENT,
                    user VARCHAR(100),
                    surname VARCHAR(100),
                    login VARCHAR(100),
                    password VARCHAR(100),
                    role VARCHAR(80),
  PRIMARY KEY(id));

CREATE TABLE profiles (id INT AUTO_INCREMENT,
                       telephone VARCHAR(80),
                       gender VARCHAR(80),
                       email VARCHAR(80),
                       city VARCHAR(100),
                       street VARCHAR(100),
                       house VARCHAR(100),
                       flat VARCHAR(80),
                       user_id INT,
  FOREIGN KEY(user_id) REFERENCES users(id),
  PRIMARY KEY(id));

CREATE TABLE order_details (id INT AUTO_INCREMENT,
                            date_of_reception TIMESTAMP,
                            closing_date TIMESTAMP,
  PRIMARY KEY(id));

CREATE TABLE orders (id INT AUTO_INCREMENT,
                     owner_id INT,
                     status VARCHAR(150),
                     detail_id INT,
  PRIMARY KEY(id),
  FOREIGN KEY (owner_id) REFERENCES users(id),
  FOREIGN KEY(detail_id) REFERENCES order_details(id));


CREATE TABLE categories (id INT AUTO_INCREMENT, name VARCHAR(100), PRIMARY KEY(id));

CREATE TABLE details (id INT AUTO_INCREMENT, name VARCHAR(100), PRIMARY KEY(id));

CREATE TABLE category_detail (category_id INT,
                              detail_id INT,
  FOREIGN KEY(category_id) REFERENCES categories (id),
  FOREIGN KEY(detail_id) REFERENCES details (id));

CREATE TABLE products (id INT AUTO_INCREMENT,
                       name VARCHAR(300),
                       description TEXT,
                       mark DOUBLE,
                       price DOUBLE,
                       image VARCHAR(100),
                       category_id INT,
                       version INT,
  FOREIGN KEY(category_id) REFERENCES categories (id),
  PRIMARY KEY(id));

CREATE TABLE characteristics (id INT AUTO_INCREMENT,
                              product_id INT,
                              detail_id INT,
                              value VARCHAR(100),
  PRIMARY KEY(id),
  FOREIGN KEY(product_id) REFERENCES products(id),
  FOREIGN KEY(detail_id) REFERENCES details(id));

CREATE TABLE order_content (id INT AUTO_INCREMENT,
                            order_id INT,
                            product_id INT,
                            amount INT,
  PRIMARY KEY(id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY(product_id) REFERENCES products(id));

CREATE TABLE reviews (id INT AUTO_INCREMENT,
                      user_id INT NOT NULL,
                      product_id INT NOT NULL,
                      date TIMESTAMP,
                      content TEXT,
  PRIMARY KEY(id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(product_id) REFERENCES products(id));

CREATE TABLE carts (id INT AUTO_INCREMENT,
                    user_id INT,
                    product_id INT,
                    amount INT,
  PRIMARY KEY(id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(product_id) REFERENCES products(id));

INSERT INTO categories (name) VALUE ('Мобильные телефоны');
INSERT INTO categories (name) VALUE ('Телевизоры');
INSERT INTO categories (name) VALUE ('Ноутбуки');

INSERT INTO details (name) VALUES ('Год выпуска');
INSERT INTO details (name) VALUES ('Страна производитель');
INSERT INTO details (name) VALUES ('Операционная система');
INSERT INTO details (name) VALUES ('Цена');
INSERT INTO details (name) VALUES ('Производитель');
INSERT INTO details (name) VALUES ('Процессор');

INSERT INTO category_detail (category_id, detail_id) VALUES (1, 1);
INSERT INTO category_detail (category_id, detail_id) VALUES (1, 2);
INSERT INTO category_detail (category_id, detail_id) VALUES (1, 3);
INSERT INTO category_detail (category_id, detail_id) VALUES (1, 4);
INSERT INTO category_detail (category_id, detail_id) VALUES (1, 5);

INSERT INTO category_detail (category_id, detail_id) VALUES (2, 1);
INSERT INTO category_detail (category_id, detail_id) VALUES (2, 2);
INSERT INTO category_detail (category_id, detail_id) VALUES (2, 4);
INSERT INTO category_detail (category_id, detail_id) VALUES (2, 5);

INSERT INTO category_detail (category_id, detail_id) VALUES (3, 1);
INSERT INTO category_detail (category_id, detail_id) VALUES (3, 2);
INSERT INTO category_detail (category_id, detail_id) VALUES (3, 4);
INSERT INTO category_detail (category_id, detail_id) VALUES (3, 5);
INSERT INTO category_detail (category_id, detail_id) VALUES (3, 6);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Xiaomi Redmi 4A Gold',
  'Android, экран 5" IPS (720x1280), ОЗУ 2 ГБ, флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 4100 мАч, 2 SIM, цвет черный',
  8.0,
  150,
  1,
  '1.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Apple iPhone 7 32GB Black',
  'Apple iOS, экран 4.7" IPS (750x1334), ОЗУ 2 ГБ, флэш-память 32 ГБ, камера 12 Мп, аккумулятор 1960 мАч, 1 SIM, цвет черный',
  8.0,
  500,
  1,
  '2.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Samsung Galaxy S8',
  'Android, экран 5.8" AMOLED (1440x2960), ОЗУ 4 ГБ, флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM, цвет черный',
  8.0,
  800,
  1,
  '3.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Apple iPhone 6',
  'Apple iOS, экран 4.7" IPS (750x1334), ОЗУ 1 ГБ, флэш-память 16 ГБ, камера 8 Мп, аккумулятор 1810 мАч, 1 SIM, цвет темно-серый',
  8.0,
  499,
  1,
  '4.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Huawei P8 lite 2017',
  'Android, экран 5.2" IPS (1080x1920), ОЗУ 3 ГБ, флэш-память 16 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM, цвет черный',
  8.0,
  230,
  1,
  '5.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Xiaomi Redmi Note 4 3GB/32GB',
  'Android, экран 5.5" IPS (1080x1920), ОЗУ 3 ГБ, флэш-память 32 ГБ, карты памяти, камера 13 Мп, аккумулятор 4100 мАч, 2 SIM, цвет темно-серый',
  8.0,
  155,
  1,
  '6.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Samsung Galaxy S7',
  'Android, экран 5.5" AMOLED (1440x2560), ОЗУ 4 ГБ, флэш-память 32 ГБ, карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 1 SIM, цвет черный',
  8.0,
  550,
  1,
  '7.jpeg'
);

INSERT INTO products (name, description, mark, price, category_id, image)
VALUES (
  'Huawei P9 Lite',
  'Android, экран 5.2" IPS (1080x1920), ОЗУ 2 ГБ, флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 3000 мАч, 2 SIM, цвет черный',
  8.0,
  199,
  1,
  '8.jpeg'
);

INSERT INTO characteristics (product_id, detail_id, value) VALUES (1, 1, '2016');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (1, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (1, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (1, 4, '150');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (1, 5, 'Xiaomi');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (2, 1, '2016');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (2, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (2, 3, 'Apple iOS');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (2, 4, '500');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (2, 5, 'Apple');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (3, 1, '2017');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (3, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (3, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (3, 4, '800');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (3, 5, 'Samsung');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (4, 1, '2014');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (4, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (4, 3, 'Apple iOS');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (4, 4, '499');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (4, 5, 'Apple');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (5, 1, '2017');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (5, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (5, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (5, 4, '230');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (5, 5, 'Huawei');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (6, 1, '2017');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (6, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (6, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (6, 4, '155');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (6, 5, 'Xiaomi');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (7, 1, '2016');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (7, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (7, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (7, 4, '550');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (7, 5, 'Samsung');

INSERT INTO characteristics (product_id, detail_id, value) VALUES (8, 1, '2017');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (8, 2, 'Китай');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (8, 3, 'Android');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (8, 4, '199');
INSERT INTO characteristics (product_id, detail_id, value) VALUES (8, 5, 'Huawei');