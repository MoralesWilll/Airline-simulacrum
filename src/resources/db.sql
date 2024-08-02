-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Airline` DEFAULT CHARACTER SET utf8mb3 ;
USE `Airline` ;

-- -----------------------------------------------------
-- Table `Airline`.`plane`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`plane` (
  `idplane` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(225) NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`idplane`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Airline`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`flight` (
  `idflight` INT NOT NULL AUTO_INCREMENT,
  `destiny` VARCHAR(225) NOT NULL,
  `departureDate` DATE NOT NULL,
  `departureTime` TIME NOT NULL,
  `idplane` INT NOT NULL,
  PRIMARY KEY (`idflight`),
  INDEX `idplane_idx` (`idplane` ASC) VISIBLE,
  CONSTRAINT `idplane`
    FOREIGN KEY (`idplane`)
    REFERENCES `Airline`.`plane` (`idplane`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Airline`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`passenger` (
  `idpassenger` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `lastName` VARCHAR(225) NOT NULL,
  `dni` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`idpassenger`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Airline`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`reservation` (
  `idreservation` INT NOT NULL AUTO_INCREMENT,
  `idpassenger` INT NOT NULL,
  `idflight` INT NOT NULL,
  `reservationDate` DATE NOT NULL,
  `seat` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`idreservation`),
  INDEX `idflight_idx` (`idflight` ASC) VISIBLE,
  INDEX `idpassenger_idx` (`idpassenger` ASC) VISIBLE,
  CONSTRAINT `idflight`
    FOREIGN KEY (`idflight`)
    REFERENCES `Airline`.`flight` (`idflight`),
  CONSTRAINT `idpassenger`
    FOREIGN KEY (`idpassenger`)
    REFERENCES `Airline`.`passenger` (`idpassenger`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
