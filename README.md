Mortgage PRoject

CREATE DATABASE `mortgage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_details` (
  `account_no_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  `balance` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `Created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_no_id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mortgage_details` (
  `mortgage_id` bigint NOT NULL AUTO_INCREMENT,
  `cusomer_id` bigint DEFAULT NULL,
  `account_no_id` bigint DEFAULT NULL,
  `mortgage_type` varchar(45) DEFAULT NULL,
  `porperty_cost` varchar(45) DEFAULT NULL,
  `mortgage_balance` bigint DEFAULT NULL,
  `mortgage_deposit_amount` bigint DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`mortgage_id`),
  KEY `customer_id_idx` (`cusomer_id`),
  KEY `account_no_id_idx` (`account_no_id`),
  CONSTRAINT `mortgage_account_no_id` FOREIGN KEY (`account_no_id`) REFERENCES `account_details` (`account_no_id`),
  CONSTRAINT `mortgage_customer_id` FOREIGN KEY (`cusomer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `transaction` (
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `from_account` bigint DEFAULT NULL,
  `to_account` bigint DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `transaction_status` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



