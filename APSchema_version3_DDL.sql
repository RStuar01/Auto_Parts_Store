-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema auto_parts_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auto_parts_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auto_parts_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `auto_parts_schema` ;

-- -----------------------------------------------------
-- Table `auto_parts_schema`.`contact_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`contact_info` (
  `contact_info_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `phone_number` VARCHAR(45) NOT NULL COMMENT '',
  `cell_phone_number` VARCHAR(45) NOT NULL COMMENT '',
  `email_address` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`contact_info_id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`Address` (
  `address_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `street_address` VARCHAR(45) NOT NULL COMMENT '',
  `city` VARCHAR(45) NOT NULL COMMENT '',
  `state` VARCHAR(45) NOT NULL COMMENT '',
  `zip_code` VARCHAR(45) NOT NULL COMMENT '',
  `unit_number` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`address_id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`customer_id`)  COMMENT '',
  INDEX `fk_customer_contact_info1_idx` (`contact_info_contact_info_id` ASC)  COMMENT '',
  INDEX `fk_customer_Address1_idx` (`Address_address_id` ASC)  COMMENT '',
  CONSTRAINT `fk_customer_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `auto_parts_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `auto_parts_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`company` (
  `company_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `company_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`company_id`)  COMMENT '',
  INDEX `fk_company_Address1_idx` (`Address_address_id` ASC)  COMMENT '',
  INDEX `fk_company_contact_info1_idx` (`contact_info_contact_info_id` ASC)  COMMENT '',
  CONSTRAINT `fk_company_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `auto_parts_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `auto_parts_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`supplier` (
  `supplier_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  `company_company_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`supplier_id`)  COMMENT '',
  INDEX `fk_supplier_contact_info1_idx` (`contact_info_contact_info_id` ASC)  COMMENT '',
  INDEX `fk_supplier_Address1_idx` (`Address_address_id` ASC)  COMMENT '',
  INDEX `fk_supplier_company1_idx` (`company_company_id` ASC)  COMMENT '',
  CONSTRAINT `fk_supplier_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `auto_parts_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `auto_parts_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier_company1`
    FOREIGN KEY (`company_company_id`)
    REFERENCES `auto_parts_schema`.`company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `contact_info_contact_info_id` INT NOT NULL COMMENT '',
  `Address_address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`employee_id`)  COMMENT '',
  INDEX `fk_employee_contact_info1_idx` (`contact_info_contact_info_id` ASC)  COMMENT '',
  INDEX `fk_employee_Address1_idx` (`Address_address_id` ASC)  COMMENT '',
  CONSTRAINT `fk_employee_contact_info1`
    FOREIGN KEY (`contact_info_contact_info_id`)
    REFERENCES `auto_parts_schema`.`contact_info` (`contact_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_Address1`
    FOREIGN KEY (`Address_address_id`)
    REFERENCES `auto_parts_schema`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`invoice` (
  `invoice_number` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `time` VARCHAR(45) NOT NULL COMMENT '',
  `customer_customer_id` INT NOT NULL COMMENT '',
  `employee_employee_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_number`)  COMMENT '',
  INDEX `fk_invoice_customer1_idx` (`customer_customer_id` ASC)  COMMENT '',
  INDEX `fk_invoice_employee1_idx` (`employee_employee_id` ASC)  COMMENT '',
  CONSTRAINT `fk_invoice_customer1`
    FOREIGN KEY (`customer_customer_id`)
    REFERENCES `auto_parts_schema`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_employee1`
    FOREIGN KEY (`employee_employee_id`)
    REFERENCES `auto_parts_schema`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`product` (
  `product` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `description` VARCHAR(45) NOT NULL COMMENT '',
  `year_minimum` VARCHAR(45) NOT NULL COMMENT '',
  `year_maximum` VARCHAR(45) NOT NULL COMMENT '',
  `make` VARCHAR(45) NOT NULL COMMENT '',
  `model` VARCHAR(45) NOT NULL COMMENT '',
  `supplier_price` VARCHAR(45) NOT NULL COMMENT '',
  `sell_price` VARCHAR(45) NOT NULL COMMENT '',
  `core_charge` VARCHAR(45) NOT NULL COMMENT '',
  `compatibility_number` VARCHAR(45) NOT NULL COMMENT '',
  `company_company_id` INT NOT NULL COMMENT '',
  `min_quantity_in_stock` VARCHAR(45) NOT NULL COMMENT '',
  `max_quantity_in_stock` VARCHAR(45) NOT NULL COMMENT '',
  `warehouse_location` VARCHAR(45) NOT NULL COMMENT '',
  `quantity_in_stock` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`product`)  COMMENT '',
  INDEX `fk_product_company1_idx` (`company_company_id` ASC)  COMMENT '',
  CONSTRAINT `fk_product_company1`
    FOREIGN KEY (`company_company_id`)
    REFERENCES `auto_parts_schema`.`company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`invoice_line_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`invoice_line_item` (
  `invoice_line_number` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `invoice_invoice_number` INT NOT NULL COMMENT '',
  `quantity_purchased` VARCHAR(45) NOT NULL COMMENT '',
  `product_product` INT NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_line_number`)  COMMENT '',
  INDEX `fk_invoice_line_item_invoice1_idx` (`invoice_invoice_number` ASC)  COMMENT '',
  INDEX `fk_invoice_line_item_product1_idx` (`product_product` ASC)  COMMENT '',
  CONSTRAINT `fk_invoice_line_item_invoice1`
    FOREIGN KEY (`invoice_invoice_number`)
    REFERENCES `auto_parts_schema`.`invoice` (`invoice_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_line_item_product1`
    FOREIGN KEY (`product_product`)
    REFERENCES `auto_parts_schema`.`product` (`product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`accounting_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`accounting_sales` (
  `accounting_sales_record_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `sold_quantity` VARCHAR(45) NULL COMMENT '',
  `product_product` INT NOT NULL COMMENT '',
  `dollar_value` VARCHAR(45) NOT NULL COMMENT '',
  `sales_tax_acquired` VARCHAR(45) NULL COMMENT '',
  `invoice_line_item_invoice_line_number` INT NOT NULL COMMENT '',
  PRIMARY KEY (`accounting_sales_record_id`)  COMMENT '',
  INDEX `fk_accounting_product1_idx` (`product_product` ASC)  COMMENT '',
  INDEX `fk_accounting_invoice_line_item1_idx` (`invoice_line_item_invoice_line_number` ASC)  COMMENT '',
  CONSTRAINT `fk_accounting_product1`
    FOREIGN KEY (`product_product`)
    REFERENCES `auto_parts_schema`.`product` (`product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounting_invoice_line_item1`
    FOREIGN KEY (`invoice_line_item_invoice_line_number`)
    REFERENCES `auto_parts_schema`.`invoice_line_item` (`invoice_line_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_parts_schema`.`accounting_purchases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auto_parts_schema`.`accounting_purchases` (
  `accounting_purchases_record_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `purchases_quantity` VARCHAR(45) NOT NULL COMMENT '',
  `dollar_value` VARCHAR(45) NULL COMMENT '',
  `product_product` INT NOT NULL COMMENT '',
  PRIMARY KEY (`accounting_purchases_record_id`)  COMMENT '',
  INDEX `fk_accounting_purchases_product1_idx` (`product_product` ASC)  COMMENT '',
  CONSTRAINT `fk_accounting_purchases_product1`
    FOREIGN KEY (`product_product`)
    REFERENCES `auto_parts_schema`.`product` (`product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
