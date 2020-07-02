CREATE DATABASE ICheck;
USE ICheck;

CREATE TABLE user(
    username          VARCHAR(20),
    password     VARCHAR(30),
    email      VARCHAR(320),
    nombre          VARCHAR(30),
    Coord_lat       INT,
    Coord_Lon       INT,
    PRIMARY KEY(username)
    );
    
CREATE TABLE category(
    ID_category  TINYINT AUTO_INCREMENT,
    name_category   VARCHAR(30),
    PRIMARY KEY(ID_category)
    );
    
    
CREATE TABLE product(
    ID_product    VARCHAR(20),
    ID_category     TINYINT,
    description     VARCHAR(150),
    Days_of_life    SMALLINT,
    PRIMARY KEY(ID_product),
    CONSTRAINT fk_product_category FOREIGN KEY(ID_category) REFERENCES category(ID_category)
    );

CREATE TABLE favourites(
    username          VARCHAR(20),
    ID_product    VARCHAR(20),
    date_favourite      DATE,
    PRIMARY KEY(username, ID_product),
    CONSTRAINT fk_favourites_user FOREIGN KEY(username) REFERENCES user(username),
    CONSTRAINT fk_favourites_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
   
CREATE TABLE store(
    ID_store   TINYINT  AUTO_INCREMENT,
    name_store     VARCHAR(30),
    PRIMARY KEY(ID_store)
    );
    
    
CREATE TABLE branch_office(
    ID_branch_office SMALLINT  AUTO_INCREMENT,
    ID_store   TINYINT,
    name_branch_office  VARCHAR(50),
    Coord_lat       INT,
    Coord_Lon       INT,
    PRIMARY KEY(ID_branch_office),
    CONSTRAINT fk_branch_office_store FOREIGN KEY(ID_store) REFERENCES store(ID_store)
    );
    

CREATE TABLE cupboard(
    username VARCHAR(20),
    ID_product  VARCHAR(20),
    purchased_quantity TINYINT,
    spent TINYINT,
    date_of_expiry DATE,
    PRIMARY KEY(username,ID_product),
    CONSTRAINT fk_cuoboard_user FOREIGN KEY(username) REFERENCES user(username),
    CONSTRAINT fk_cuoboard_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
    
    

CREATE TABLE shopping_list(
    ID_shopping_list INT  AUTO_INCREMENT,
    username VARCHAR(20),
    list_name VARCHAR(50),
    feautured BOOLEAN,
    PRIMARY KEY(ID_shopping_list),
    CONSTRAINT fk_shopping_list_user FOREIGN KEY(username) REFERENCES user(username)
    );
    

CREATE TABLE list_for_product(
    ID_shopping_list INT,
    ID_product VARCHAR(20),
    quantity TINYINT,
    PRIMARY KEY(ID_shopping_list,ID_product),
    CONSTRAINT fk_list_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
    
    
CREATE TABLE purchase(
    ID_purchase INT  AUTO_INCREMENT,
    ID_shopping_list INT,
    ID_store TINYINT,
    PRIMARY KEY(ID_purchase),
    CONSTRAINT fk_purchase_store FOREIGN KEY(ID_store) REFERENCES store(ID_store)
    );
    
    
CREATE TABLE purchase_price(
    ID_purchase INT,
    ID_product VARCHAR(20),
    Iselling_price FLOAT,
    PRIMARY KEY(ID_purchase,ID_product),
    CONSTRAINT fk_purchaseprice_purchase FOREIGN KEY(ID_purchase) REFERENCES purchase(ID_purchase),
    CONSTRAINT fk_purchaseprice_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
 
 CREATE TABLE product_store_price(
    ID_store TINYINT,
    ID_product VARCHAR(20),
    base_price FLOAT,
    Iselling_price FLOAT,
    on_sale BOOLEAN,
    PRIMARY KEY(ID_store,ID_product),
    CONSTRAINT fk_storeprice_store FOREIGN KEY(ID_store) REFERENCES store(ID_store),
    CONSTRAINT fk_storeprice_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
    
 CREATE TABLE reminders(
    ID_reminders INT  AUTO_INCREMENT,
    ID_product VARCHAR(20),
    username VARCHAR(20),
    date_reminder DATE,
    PRIMARY KEY(ID_reminders),
    CONSTRAINT fk_reminders_user FOREIGN KEY(username) REFERENCES user(username),
    CONSTRAINT fk_reminders_product FOREIGN KEY(ID_product) REFERENCES product(ID_product)
    );
    
    
    
    
    
    
    
    
    
    --------------QUERYS--------------
    
    
    ---OBTENER TODOS LOS PRODUCTOS QUE ESTÁN POSIBLEMENTE EXPIRADOS---
SELECT product.description, cupboard.date_of_expiry  FROM cupboard
JOIN product ON product.ID_product= cupboard.ID_product
WHERE cupboard.date_of_expiry = CURDATE() AND cupboard.username= '{&user}';

SELECT * FROM employees
WHERE birth_date <= '1960-03-04';

