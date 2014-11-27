DROP SCHEMA IF EXISTS `GSCookieDB`;

CREATE SCHEMA IF NOT EXISTS `GSCookieDB` DEFAULT CHARACTER SET UTF8;

CREATE  TABLE `GSCookieDB`.`TROOP` (
  `TROOP_ID` INT NOT NULL AUTO_INCREMENT ,
  `ADDRESS` VARCHAR(255) NOT NULL,
  `CITY` VARCHAR(255) NULL ,
  `STATE` VARCHAR(45) NULL ,
  `MANAGER` INT NULL ,
  PRIMARY KEY (`TROOP_ID`));

CREATE  TABLE `GSCookieDB`.`VOLUNTEER` (
  `VOLUNTEER_ID` INT NOT NULL AUTO_INCREMENT ,
  `FIRSTNAME` VARCHAR(255) NULL ,
  `LASTNAME` VARCHAR(255) NULL ,
  `PHONE` VARCHAR(45) NULL ,
  `TROOP_ID` INT NULL ,
  `LEADER` INT NULL ,
  PRIMARY KEY (`VOLUNTEER_ID`) );
   
CREATE  TABLE `gscookiedb`.`SCOUT` (
  `SCOUT_ID` INT NOT NULL AUTO_INCREMENT ,
  `FIRSTNAME` VARCHAR(255) NULL ,
  `LASTNAME` VARCHAR(255) NULL ,
  `PHONE` VARCHAR(45) NULL ,
  `EMAIL` VARCHAR(255) NULL ,
  `BIRTHDATE` DATE NULL ,
  `START_DATE` DATE NULL ,
  `PARENT_FIRSTNAME` VARCHAR(255) NULL ,
  `PARENT_LASTNAME` VARCHAR(255) NULL ,
  `ADDRESS` VARCHAR(255) NULL ,
  `CITY` VARCHAR(45) NULL ,
  `STATE` VARCHAR(45) NULL ,
  `ZIPCODE` VARCHAR(10) NULL ,
  `TROOP_ID` INT NULL ,
  PRIMARY KEY (`SCOUT_ID`) );
   
CREATE  TABLE `gscookiedb`.`PRODUCT` (
  `PRODUCT_ID` INT NOT NULL AUTO_INCREMENT ,
  `PRODUCT_NAME` VARCHAR(255) NOT NULL ,
  `PRODUCT_DESCRIPTION` VARCHAR(512) NULL ,
  `RETAIL_PRICE` DECIMAL(9,2) NOT NULL ,
  PRIMARY KEY (`PRODUCT_ID`) );
  
CREATE  TABLE `gscookiedb`.`INVENTORY` (
  `INVENTORY_ID` INT NOT NULL AUTO_INCREMENT ,
  `QUANTITY` INT NOT NULL ,
  `PRODUCT_ID` INT NOT NULL ,
  `TROOP_ID` INT NOT NULL ,
  `SHIPMENT_ID` INT NULL ,
  PRIMARY KEY (`INVENTORY_ID`) );
  
CREATE  TABLE `gscookiedb`.`SHIPMENT` (
  `SHIPMENT_ID` INT NOT NULL AUTO_INCREMENT ,
  `DATE` DATE NOT NULL ,
  `SCOUT_ID` INT NOT NULL ,
  `RECEIVED` INT ,
  PRIMARY KEY (`SHIPMENT_ID`) );
  
CREATE  TABLE `gscookiedb`.`MONEY` (
  `TRANSACTION_ID` INT NOT NULL AUTO_INCREMENT ,
  `AMOUNT` DECIMAL(9,2) NOT NULL ,
  `DATE` DATE NOT NULL ,
  `SCOUT_ID` INT NOT NULL ,
  `TROOP_ID` INT NOT NULL ,
  PRIMARY KEY (`TRANSACTION_ID`) );

  CREATE  TABLE `gscookiedb`.`CUSTOMER` (
  `CUSTOMER_ID` INT NOT NULL AUTO_INCREMENT ,
  `FIRSTNAME` VARCHAR(255) NULL ,
  `LASTNAME` VARCHAR(255) NULL ,
  `PHONE` VARCHAR(45) NULL ,
  PRIMARY KEY (`CUSTOMER_ID`) );

CREATE  TABLE `gscookiedb`.`ORDER` (
  `ORDER_ID` INT NOT NULL AUTO_INCREMENT ,
  `DATE` DATE NOT NULL ,
  `SCOUT_ID` INT NOT NULL ,
  `CUSTOMER_ID` INT NOT NULL ,
  `STATUS` INT NULL ,
  PRIMARY KEY (`ORDER_ID`) );

 CREATE  TABLE `gscookiedb`.`PRODUCT_ORDER` (
  `PRODUCT_ID` INT NOT NULL ,
  `ORDER_ID` INT NOT NULL ,
  `QUANTITY` INT NOT NULL ,
  PRIMARY KEY (`PRODUCT_ID`, `ORDER_ID`) );
  
  
  
  
  
  
  
  
  
  
/*  
  
ALTER TABLE `gscookiedb`.`troop` 
  ADD CONSTRAINT `cookieman`
  FOREIGN KEY (`cookie_manager_id` )
  REFERENCES `gscookiedb`.`volunteer` (`volunteer_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `cookieman_idx` (`cookie_manager_id` ASC) ;

ALTER TABLE `gscookiedb`.`volunteer` 
  ADD CONSTRAINT `vtroop`
  FOREIGN KEY (`troop_id` )
  REFERENCES `gscookiedb`.`troop` (`troop_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `vtroop_idx` (`troop_id` ASC) ;

ALTER TABLE `gscookiedb`.`scout` 
  ADD CONSTRAINT `stroop`
  FOREIGN KEY (`troop_id` )
  REFERENCES `gscookiedb`.`troop` (`troop_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `stroop_idx` (`troop_id` ASC) ;

ALTER TABLE `gscookiedb`.`inventory` 
  ADD CONSTRAINT `iproduct`
  FOREIGN KEY (`product_id` )
  REFERENCES `gscookiedb`.`product` (`product_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `itroop`
  FOREIGN KEY (`troop_id` )
  REFERENCES `gscookiedb`.`troop` (`troop_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `ishipment`
  FOREIGN KEY (`shipment_id` )
  REFERENCES `gscookiedb`.`shipment` (`shipment_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `iproduct_idx` (`product_id` ASC) 
, ADD INDEX `itroop_idx` (`troop_id` ASC) 
, ADD INDEX `ishipment_idx` (`shipment_id` ASC) ;

ALTER TABLE `gscookiedb`.`shipment` 
  ADD CONSTRAINT `sscout`
  FOREIGN KEY (`scout_id` )
  REFERENCES `gscookiedb`.`scout` (`scout_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `sscout_idx` (`scout_id` ASC) ;

ALTER TABLE `gscookiedb`.`money` 
  ADD CONSTRAINT `mscout`
  FOREIGN KEY (`scout_id` )
  REFERENCES `gscookiedb`.`scout` (`scout_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `mtroop`
  FOREIGN KEY (`troop_id` )
  REFERENCES `gscookiedb`.`troop` (`troop_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `mscout_idx` (`scout_id` ASC) 
, ADD INDEX `mtroop_idx` (`troop_id` ASC) ;

ALTER TABLE `gscookiedb`.`product_order` 
  ADD CONSTRAINT `po_product`
  FOREIGN KEY (`product_id` )
  REFERENCES `gscookiedb`.`product` (`product_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `po_order`
  FOREIGN KEY (`order_id` )
  REFERENCES `gscookiedb`.`order` (`order_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `po_product_idx` (`product_id` ASC) 
, ADD INDEX `po_order_idx` (`order_id` ASC) ;

ALTER TABLE `gscookiedb`.`order` 
  ADD CONSTRAINT `oscout`
  FOREIGN KEY (`scout_id` )
  REFERENCES `gscookiedb`.`scout` (`scout_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `ocustomer`
  FOREIGN KEY (`customer_id` )
  REFERENCES `gscookiedb`.`customer` (`customer_id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `oscout_idx` (`scout_id` ASC) 
, ADD INDEX `ocustomer_idx` (`customer_id` ASC) ;



INSERT INTO `gscookiedb`.`product` (`product_name`,`product_description`,`retail_price`)
VALUES
("Thin Mints","Crispy chocolate wafers dipped in a mint chocolaty coating.",4),
("Caramel deLites / Samoas","Vanilla cookies coated in caramel, sprinkled with toasted coconut, and laced with chocolaty stripes",4),
("Peanut Butter Patties / Tagalongs","Crispy vanilla cookies layered with peanut butter and covered with a chocolaty coating",4),
("Shortbread / Trefoil","Traditional shortbread cookies",4),
("Do-si-dos / Peanut Butter Sandwich","Crunchy oatmeal sandwich cookie with creamy peanut butter filling",4),
("Cranberry Citrus Crisps","Crispy cookie, made with whole grain, full of tangy cranberry bits and zesty citrus flavor",4),
("Lemonades","Savory slices of shortbread with a refreshingly tangy lemon-flavored icing",4),
("Rah-Rah Raisins","Hearty oatmeal cookies with plump raisins and Greek yogurt-flavored chunks",4),
("Savannah Smiles","Crisp, zesty lemon wedge cookies dusted with powdered sugar",4),
("Thanks-A-Lot","Shortbread cookies dipped in rich fudge and topped with an embossed thank you message in one of 5 languages",4),
("Toffee-tastic","Rich, buttery cookies with sweet crunchy toffee bits",4),
("Trios","Chocolate Chips nestled in a gluten free peanut butter oatmeal cookie",4);

INSERT INTO `gscookiedb`.`troop`(`troop_number`,`city`,`state`,`cookie_manager_id`)
VALUES
("123","Baltimore","Maryland",null),
("456","White Marsh","Maryland",null),
("789","Towson","Maryland",null);

INSERT INTO `gscookiedb`.`volunteer`(`fname`,`lname`,`phone_num`,`troop_id`,`is_leader`)
VALUES
("Thad","Tenner","(822) 665-9278",1,1),
("Edyth","Eagle","(855) 545-7853",2,1),
("Jeff","Joerling","(811) 680-9046",3,1),
("Candie","Coty","(899) 577-3041",1,1),
("Ressie","Reisinger","(822) 166-4457",3,1),
("Katia","Keach","(811) 926-7706",2,1),
("Debera","Dresser","(844) 873-9190",1,0),
("Ardis","Abraham","(822) 771-7224",2,0),
("Nickie","Nading","(844) 809-2662",3,0),
("Joleen","Jacquemin","(833) 993-7901",1,0),
("Kizzy","Kellen","(822) 747-9486",2,0),
("Marna","Mattinson","(899) 679-8033",1,0),
("Louisa","Labbe","(811) 321-0936",3,0),
("Verlie","Vicini","(811) 296-2070",2,0),
("Russell","Rambin","(822) 277-6648",3,0),
("Arleen","Andrzejewski","(855) 271-2041",2,0),
("Randee","Reigle","(844) 724-7606",1,0),
("Sterling","Sin","(811) 021-0918",3,0),
("Glenna","Guerriero","(899) 958-5967",1,0),
("Demetria","Dorantes","(855) 394-4358",2,0);

UPDATE `gscookiedb`.`troop`
SET `cookie_manager_id` = 18
WHERE `troop_id` = 3;

UPDATE `gscookiedb`.`troop`
SET `cookie_manager_id` = 19
WHERE `troop_id` = 1;

UPDATE `gscookiedb`.`troop`
SET `cookie_manager_id` = 20
WHERE `troop_id` = 2;

INSERT INTO `gscookiedb`.`customer`(`fname`,`lname`,`phone_num`)
VALUES
("Jacquelin","Lieb","(855) 532-8721"),
("Rosena","Reina","(811) 247-5793"),
("Kaley","Contreras","(811) 644-0423"),
("Quinton","Ramsey","(833) 926-9352"),
("Waylon","Ellers","(811) 857-5784"),
("Merrilee","Mcguinness","(811) 225-0189"),
("Lashon","Corkery","(811) 349-7623"),
("Yon","Stearns","(833) 505-8588"),
("Mariella","Shipe","(844) 350-1451"),
("Maud","Kasel","(822) 768-9588"),
("Corey","Ridgell","(811) 275-8095"),
("Davida","Forester","(833) 904-5929"),
("Lillia","Crecelius","(855) 894-3451"),
("Evette","Grose","(855) 291-8796"),
("Paul","Maddocks","(899) 629-6142"),
("Echo","Jefferis","(899) 566-4958"),
("Broderick","Vong","(822) 520-0822"),
("Genoveva","Kuder","(899) 139-9359"),
("Emogene","Laroque","(811) 886-9390"),
("Dee","Vela","(811) 573-4312"),
("Agueda","Venema","(811) 439-3514"),
("Rosella","Yockey","(822) 536-8968"),
("Roger","Patchell","(822) 557-8487"),
("Celena","Greggs","(811) 504-8267"),
("Chas","Zynda","(833) 969-4841"),
("Tarsha","Faas","(844) 783-7515"),
("Leanora","Satterthwaite","(899) 729-1161"),
("Kamala","Mastin","(822) 665-9235"),
("Donna","Mortenson","(855) 957-8993"),
("Dinah","Plante","(822) 765-2703");

INSERT INTO `gscookiedb`.`scout`(`FIRSTNAME`,`LASTNAME`,`PHONE`,`EMAIL`,`BIRTHDATE`,`START_DATE`,`PARENT_FIRSTNAME`,`PARENT_LASTNAME`,`ADDRESS`,
`CITY`,`STATE`,`ZIPCODE`,`troop_id`)
VALUES
("Velda","Vogt","(822) 599-8043","l2y37rs@lo7j0p13xuv0.com","2003-01-18","2014-09-06","Odessa","Vogt","527 Hillcrest Driveÿ","Burke","VA","22015",1),
("Cher","Cookson","(899) 295-3824","oyvm9rl@a4ils6b.com","2002-01-05","2014-08-02","Rima","Cookson","510 Locust Lane","Smithtown","NY","11787",2),
("Lita","Leitch","(811) 075-3084","uz2q0y7@wprg-psf.com","2003-09-21","2014-08-17","Syreeta","Leitch","548 Henry Street","Monroe Township","NJ","8831",3),
("Ehtel","Edmonds","(855) 088-7866","2eagpa_ymssn6@47rskn.com","2002-05-12","2014-09-29","Joseph","Edmonds","372 Wood Street","Flemington","NJ","8822",1),
("Wanetta","Ware","(844) 652-7067","avwg@t-zy34.com","2002-09-26","2014-08-21","Damon","Ware","543 Route 100","Southfield","MI","48076",2),
("Wei","Weyer","(844) 537-3612","6ul6t-f@0pr3hni1xw52.com","2002-07-13","2014-08-28","Wan","Weyer","868 Sunset Avenueÿ","Severna Park","MD","21146",3),
("Angele","Apple","(822) 880-9642","m50mswdk__@drj8-gb9.com","2002-04-19","2014-09-28","Morgan","Apple","653 Sunset Avenue","Lake Villa","IL","60046",1),
("Esta","Elser","(822) 304-2716","268_utwnjh@1v0x7qh01.com","2003-01-02","2014-08-09","Corrin","Elser","222 Poplar Street","Lake Zurich","IL","60047",2),
("Michaele","Moroz","(844) 691-7590","6yh2@wo2u-jj4ehlx.com","2002-03-17","2014-08-28","Arlie","Moroz","935 Columbia Streetÿ","Greenfield","IN","46140",3),
("Corene","Campa","(899) 284-9966","9_or-amohldni@8-pwv4mf3v.com","2003-08-01","2014-08-15","Nona","Campa","533 Maple Lane","Winter Garden","FL","34787",1),
("Manuela","Michaelsen","(811) 467-1109","2x9mpvhn@9yk9-yfj2s.com","2003-03-29","2014-08-28","Roseanna","Michaelsen","801 7th Street","Upland","CA","91784",2),
("Regina","Rarick","(833) 147-9543","s0jc2whtg@wc7h0hvto9.com","2003-05-15","2014-08-15","Edmund","Rarick","835 Harrison Street","Bismarck","ND","58501",3),
("Sanora","Shand","(855) 349-8409","ipa9vlyb@4jtzsv.com","2003-02-11","2014-09-11","Valda","Shand","282 Meadow Street","Shirley","NY","11967",1),
("Leida","Lennox","(822) 836-8926","50jo_dw-@kgqze8.com","2002-09-03","2014-09-19","Almeda","Lennox","62 6th Street North","Calumet City","IL","60409",2),
("Hortencia","Hinckley","(844) 846-0455","l4h8c607cquw@a9x366f4.com","2002-07-26","2014-09-29","Carisa","Hinckley","932 Laurel Street","Chillicothe","OH","45601",3),
("Savannah","Schroeder","(833) 935-4796","4k9h_ae1npruktn@gmpv7-v5xny.com","2003-12-24","2014-09-09","Princess","Schroeder","444 Overlook Circle","Oklahoma City","OK","73112",1),
("Zulema","Zullo","(899) 469-8685","srji2fqzf@tmr94ahln.com","2002-07-29","2014-08-15","Helaine","Zullo","852 8th Avenue","Mount Holly","NJ","8060",2),
("Ardelle","Ayala","(844) 112-1496","5-v5uoq@7b8nsnw1c2.com","2003-10-20","2014-08-29","Loriann","Ayala","889 Clinton Street","Auburndale","FL","33823",3),
("Kimiko","Krajewski","(855) 423-9885","9zdn@v7n3h1e6sco.com","2002-08-10","2014-08-06","Marcelo","Krajewski","727 Holly Court","Bolingbrook","IL","60440",1),
("Tamekia","Teran","(822) 112-9090","42r6x7@l0qbagg2b5.com","2003-01-20","2014-08-04","Lashon","Teran","465 Highland Avenue","Port Charlotte","FL","33952",2);

INSERT INTO `gscookiedb`.`inventory`(`quantity`,`product_id`,`troop_id`,`shipment_id`)
VALUES
(331,1,1,null),
(474,2,1,null),
(376,3,1,null),
(381,4,1,null),
(214,5,1,null),
(383,6,1,null),
(315,7,1,null),
(362,8,1,null),
(236,9,1,null),
(361,10,1,null),
(465,11,1,null),
(409,12,1,null),
(498,1,2,null),
(307,2,2,null),
(415,3,2,null),
(406,4,2,null),
(200,5,2,null),
(120,6,2,null),
(443,7,2,null),
(352,8,2,null),
(131,9,2,null),
(105,10,2,null),
(310,11,2,null),
(111,12,2,null),
(349,1,3,null),
(238,2,3,null),
(493,3,3,null),
(403,4,3,null),
(232,5,3,null),
(136,6,3,null),
(489,7,3,null),
(373,8,3,null),
(443,9,3,null),
(236,10,3,null),
(475,11,3,null),
(474,12,3,null);
*/
