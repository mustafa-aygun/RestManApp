drop database hms;
CREATE USER IF NOT EXISTS 'cng443user'@'localhost' IDENTIFIED BY '1234';
CREATE DATABASE IF NOT EXISTS `hms`;
GRANT ALL PRIVILEGES ON `hms`.* TO 'cng443user'@'localhost';

CREATE TABLE IF NOT EXISTS `hms`.`person` (
    `id` int(11) DEFAULT '0',
    `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
    `date_of_birth` varchar(64) DEFAULT NULL,
     `gender` varchar(2) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `hms`.`customer`(
    `customer_id` int(11) DEFAULT '0', 
    `registrationDate` varchar(20) DEFAULT NULL,
    `creditCardDetails` varchar(64) DEFAULT NULL,
    CONSTRAINT fk_person_id FOREIGN KEY (customer_id) REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS `hms`.`booking`(
    `booking_id` int(11) DEFAULT '0', 
    `customer_id` int(11) DEFAULT '0', 
    `bookingDate` varchar(20) DEFAULT NULL,
    PRIMARY KEY(`booking_id`),
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE IF NOT EXISTS `hms`.`order`(
    `order_id` int(11) DEFAULT '0', 
    `customer_id` int(11) DEFAULT '0', 
    `orderDate` varchar(20) DEFAULT NULL,
    `orderDetails` varchar(64) DEFAULT NULL,
    `extraNotes` varchar(64) DEFAULT NULL,
    PRIMARY KEY(`order_id`),
    CONSTRAINT fk_customerorder_id FOREIGN KEY (order_id) REFERENCES customer(customer_id)
);

CREATE TABLE IF NOT EXISTS `hms`.`inRestOrder`(
    `inRest_order_id` int(11) DEFAULT '0', 
    `inRest_booking_id` int(11) DEFAULT '0', 
    `tableNumber` int(11) DEFAULT '0', 
    CONSTRAINT fk_order_id FOREIGN KEY (inRest_order_id) REFERENCES order(order_id),
    CONSTRAINT fk_orderbooking_id FOREIGN KEY (inRest_booking_id) REFERENCES booking(booking_id)
);