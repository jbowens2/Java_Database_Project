-- CREATED BY JIMMY BOWENS --
-- NOVEMBER 27, 2014 --
-- JAVA DATABASE PROJECT --

DROP SCHEMA IF EXISTS jbowen3db;
CREATE SCHEMA IF NOT EXISTS jbowen3db;
USE jbowen3db ;

DROP TABLE IF EXISTS VOLUNTERR;
CREATE TABLE VOLUNTEER (
  `VOLUNTEER_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `LASTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `PHONE` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`VOLUNTEER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS TROOP;
CREATE TABLE TROOP (
  `TROOP_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `ADDRESS` VARCHAR(255) NOT NULL,
  `CITY` VARCHAR(255) NULL DEFAULT NULL,
  `STATE` VARCHAR(45) NULL DEFAULT NULL,
  `ZIPCODE` INT(5) NOT NULL,
  `MANAGER` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TROOP_ID`),
  INDEX `FK_TROOP_TO_VOLUNTEER_idx` (`MANAGER` ASC),
  CONSTRAINT `FK_TROOP_TO_VOLUNTEER`
    FOREIGN KEY (`MANAGER`)
    REFERENCES VOLUNTEER (`VOLUNTEER_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE TROOP 
  ADD CONSTRAINT `FK-TROOP-VOLUNTEER`
  FOREIGN KEY (`MANAGER` )
  REFERENCES VOLUNTEER (`VOLUNTEER_ID` )
  ON DELETE CASCADE
  ON UPDATE CASCADE;

DROP TABLE IF EXISTS SCOUT;
CREATE TABLE SCOUT (
  `SCOUT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `LASTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `PHONE` VARCHAR(45) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(255) NULL DEFAULT NULL,
  `BIRTHDATE` VARCHAR(10) NULL DEFAULT NULL,
  `START_DATE` VARCHAR(10) NULL DEFAULT NULL,
  `PARENT` VARCHAR(255) NULL DEFAULT NULL,
  `ADDRESS` VARCHAR(255) NULL DEFAULT NULL,
  `CITY` VARCHAR(45) NULL DEFAULT NULL,
  `STATE` VARCHAR(45) NULL DEFAULT NULL,
  `ZIPCODE` VARCHAR(10) NULL DEFAULT NULL,
  `TROOP_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SCOUT_ID`),
  INDEX `FK_idx` (`TROOP_ID` ASC),
  CONSTRAINT `FK`
    FOREIGN KEY (`TROOP_ID`)
    REFERENCES TROOP (`TROOP_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER (
  `CUSTOMER_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` VARCHAR(255) NOT NULL,
  `LASTNAME` VARCHAR(255) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `SCOUT_ID` INT NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`),
  INDEX `FK_TO_SCOUT_idx` (`SCOUT_ID` ASC),
  CONSTRAINT `FK_TO_SCOUT`
    FOREIGN KEY (`SCOUT_ID`)
    REFERENCES SCOUT (`SCOUT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS SHIPMENT;
CREATE TABLE SHIPMENT (
  `SHIPMENT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DATE` DATE NOT NULL,
  `SCOUT_ID` INT(11) NOT NULL,
  `RECEIVED` INT(11) NOT NULL,
  PRIMARY KEY (`SHIPMENT_ID`),
  INDEX `FK_TO_SCOUT_idx` (`SCOUT_ID` ASC),
  CONSTRAINT `FK_SHIPMENT_TO_SCOUT`
    FOREIGN KEY (`SCOUT_ID`)
    REFERENCES SCOUT (`SCOUT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT (
  `PRODUCT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `DESCRIPTION` VARCHAR(512) NOT NULL,
  `PRICE` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS INVENTORY;
CREATE TABLE INVENTORY (
  `INVENTORY_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `QUANTITY` INT(11) NOT NULL,
  `PRODUCT_ID` INT(11) NOT NULL,
  `TROOP_ID` INT(11) NOT NULL,
  `SHIPMENT_ID` INT(11) NOT NULL,
  PRIMARY KEY (`INVENTORY_ID`),
  INDEX `FK_TO_SHIPMENT_idx` (`SHIPMENT_ID` ASC),
  INDEX `FK_INVENTORY_TO_TROOP_idx` (`TROOP_ID` ASC),
  INDEX `FK_INVENTORY_TO_PRODUCT_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `FK_INVENTORY_TO_SHIPMENT`
    FOREIGN KEY (`SHIPMENT_ID`)
    REFERENCES SHIPMENT (`SHIPMENT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_INVENTORY_TO_TROOP`
    FOREIGN KEY (`TROOP_ID`)
    REFERENCES TROOP (`TROOP_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_INVENTORY_TO_PRODUCT`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES PRODUCT (`PRODUCT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS MONEY;
CREATE TABLE MONEY (
  `TRANSACTION_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` DECIMAL(9,2) NOT NULL,
  `AMOUNT_RECEIVED` DECIMAL(9,2) NOT NULL,
  `DATE` DATE NOT NULL,
  `SCOUT_ID` INT(11) NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  INDEX `FK_MONEY_TO_SCOUT_idx` (`SCOUT_ID` ASC),
  CONSTRAINT `FK_MONEY_TO_SCOUT`
    FOREIGN KEY (`SCOUT_ID`)
    REFERENCES SCOUT (`SCOUT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS PRODUCT_ORDER;
CREATE TABLE PRODUCT_ORDER (
  `ORDER_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DATE` DATE NOT NULL,
  `SCOUT_ID` INT(11) NOT NULL,
  `CUSTOMER_ID` INT(11) NOT NULL,
  `PRODUCT_ID` INT(11) NOT NULL,
  `QUANTITY` INT(11) NOT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  INDEX `FK_idx` (`SCOUT_ID` ASC),
  INDEX `FK_TO_CUSTOMER_idx` (`CUSTOMER_ID` ASC),
  INDEX `FK_ORDER_TO_PRODUCT` (`PRODUCT_ID` ASC),
  CONSTRAINT `FK_ORDER_TO_SCOUT`
    FOREIGN KEY (`SCOUT_ID`)
    REFERENCES SCOUT (`SCOUT_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_TO_PRODUCT`
	FOREIGN KEY(`PRODUCT_ID`)
    REFERENCES PRODUCT(`PRODUCT_ID`),
  CONSTRAINT `FK_ORDER_TO_CUSTOMER`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES CUSTOMER (`CUSTOMER_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE SHIPMENT ADD ORDER_ID INT(11);

