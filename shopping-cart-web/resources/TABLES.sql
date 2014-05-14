SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `shoppingcart` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_role` (
  `key_role` INT(11) NOT NULL,
  `fld_roletype` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `fld_roletype_UNIQUE` (`fld_roletype` ASC),
  PRIMARY KEY (`key_role`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_user` (
  `key_user` INT(11) NOT NULL AUTO_INCREMENT,
  `key_role` INT(11) NOT NULL,
  `fld_username` VARCHAR(45) NOT NULL,
  `fld_password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`key_user`),
  UNIQUE INDEX `key_user_UNIQUE` (`key_user` ASC),
  UNIQUE INDEX `fld_username_UNIQUE` (`fld_username` ASC),
  INDEX `fk_tbl_user_tbl_role1_idx` (`key_role` ASC),
  CONSTRAINT `fk_tbl_user_tbl_role1`
    FOREIGN KEY (`key_role`)
    REFERENCES `shoppingcart`.`tbl_role` (`key_role`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_general_cs;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_category` (
  `key_category` INT(11) NOT NULL AUTO_INCREMENT,
  `fld_category_name` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `fld_category_name_UNIQUE` (`fld_category_name` ASC),
  PRIMARY KEY (`key_category`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_product` (
  `key_product` INT(11) NOT NULL AUTO_INCREMENT,
  `key_category` INT(11) NOT NULL,
  `fld_product_name` VARCHAR(45) NOT NULL,
  `fld_inventory_qty` INT(11) NOT NULL DEFAULT 0,
  `fld_unit_price` DECIMAL NOT NULL,
  `fld_product_image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`key_product`),
  INDEX `fk_tbl_product_tbl_category1_idx` (`key_category` ASC),
  CONSTRAINT `fk_tbl_product_tbl_category1`
    FOREIGN KEY (`key_category`)
    REFERENCES `shoppingcart`.`tbl_category` (`key_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_order` (
  `key_order` INT(11) NOT NULL AUTO_INCREMENT,
  `key_user` INT(11) NULL DEFAULT NULL,
  `fld_amount` DECIMAL NULL DEFAULT NULL,
  `fld_status` VARCHAR(5) NOT NULL DEFAULT 0,
  PRIMARY KEY (`key_order`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `shoppingcart`.`tbl_order_detail` (
  `key_order_detail` INT(11) NOT NULL,
  `key_order` INT(11) NOT NULL,
  `key_product` INT(11) NULL DEFAULT NULL,
  `fld_quantity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`key_order_detail`),
  INDEX `fk_tbl_order_detail_tbl_order1_idx` (`key_order` ASC),
  INDEX `fk_tbl_order_detail_tbl_product1_idx` (`key_product` ASC),
  CONSTRAINT `fk_tbl_order_detail_tbl_order1`
    FOREIGN KEY (`key_order`)
    REFERENCES `shoppingcart`.`tbl_order` (`key_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_order_detail_tbl_product1`
    FOREIGN KEY (`key_product`)
    REFERENCES `shoppingcart`.`tbl_product` (`key_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
