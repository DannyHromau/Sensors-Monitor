
CREATE TABLE `roles` (
  `id` INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id`),
  `status` varchar(255) NOT NULL UNIQUE
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id`),
  `login` varchar(255) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `roles_id` INT NOT NULL,
  FOREIGN KEY (`roles_id`)  REFERENCES roles (`id`) ON DELETE CASCADE
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `sensors` (
  `id` INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id`),
  `name` varchar(30) NOT NULL UNIQUE,
  `model` varchar(15) NOT NULL,
  `range` varchar(8) DEFAULT NULL,
  `type` enum('Pressure', 'Voltage', 'Temperature', 'Humidity') DEFAULT NULL,
  `unit` enum('bar', 'voltage', '°С', '%') DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;