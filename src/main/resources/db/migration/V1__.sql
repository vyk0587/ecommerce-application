-- Create User Table
CREATE TABLE user
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE
);

-- Create Profile Table
CREATE TABLE profile
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT UNIQUE,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    birth_date DATE,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- Create Address Table
CREATE TABLE address
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    street      VARCHAR(255),
    city        VARCHAR(100),
    state       VARCHAR(100),
    postal_code VARCHAR(20),
    country     VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- Create Order Table
CREATE TABLE `order`
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT,
    order_date DATETIME,
    status     VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- Create Product Table
CREATE TABLE product
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100),
    description TEXT,
    price       DECIMAL(10, 2)
);

-- Create OrderItems Table
CREATE TABLE order_items
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    order_id   INT,
    product_id INT,
    quantity   INT,
    price      DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES `order` (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

-- Create Category Table
CREATE TABLE category
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100),
    description TEXT
);

-- Create ProductCategory Table (Join Table for Many-to-Many Relationship)
CREATE TABLE product_category
(
    product_id  INT,
    category_id INT,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

-- Create Supplier Table
CREATE TABLE supplier
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100),
    contact_info TEXT
);

-- Create ProductSupplier Table (Join Table for Many-to-Many Relationship)
CREATE TABLE product_supplier
(
    product_id  INT,
    supplier_id INT,
    PRIMARY KEY (product_id, supplier_id),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (supplier_id) REFERENCES supplier (id) ON DELETE CASCADE
);

-- Create Review Table
CREATE TABLE review
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    product_id  INT,
    rating      INT CHECK (rating BETWEEN 1 AND 5),
    comment     TEXT,
    review_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

-- Create Wishlist Table
CREATE TABLE wishlist
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- Create WishlistItems Table
CREATE TABLE wishlist_items
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id INT,
    product_id  INT,
    FOREIGN KEY (wishlist_id) REFERENCES wishlist (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);
