-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema frogger
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `frogger`;
CREATE SCHEMA IF NOT EXISTS `frogger` DEFAULT CHARACTER SET utf8mb3;
SHOW WARNINGS;
USE `frogger`;

-- -----------------------------------------------------
-- Table `frogger`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frogger`.`roles`;
CREATE TABLE IF NOT EXISTS `frogger`.`roles` (
  `rol_id` INT NOT NULL AUTO_INCREMENT,
  `rol_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rol_id`),
  UNIQUE INDEX `rol_name_UNIQUE` (`rol_name` ASC)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `frogger`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frogger`.`users`;
CREATE TABLE IF NOT EXISTS `frogger`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `Roles_rol_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `Username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_users_roles_idx` (`Roles_rol_id` ASC),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`Roles_rol_id`)
    REFERENCES `frogger`.`roles` (`rol_id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `frogger`.`user_follows_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frogger`.`user_follows_user`;
CREATE TABLE IF NOT EXISTS `frogger`.`user_follows_user` (
  `user_who_follows` INT NOT NULL,
  `user_to_follow` INT NOT NULL,
  PRIMARY KEY (`user_who_follows`, `user_to_follow`),
  INDEX `fk_users_has_users_users1_idx` (`user_who_follows` ASC),
  INDEX `fk_users_has_users_users2_idx` (`user_to_follow` ASC),
  CONSTRAINT `fk_users_has_users_users1`
    FOREIGN KEY (`user_who_follows`)
    REFERENCES `frogger`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_users_users2`
    FOREIGN KEY (`user_to_follow`)
    REFERENCES `frogger`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `frogger`.`record_scores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frogger`.`record_scores`;
CREATE TABLE IF NOT EXISTS `frogger`.`record_scores` (
  `record_id` INT NOT NULL AUTO_INCREMENT,
  `record_scorescol` TIME NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`record_id`),
  INDEX `fk_record_scores_users1_idx` (`users_user_id` ASC),
  CONSTRAINT `fk_record_scores_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `frogger`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

