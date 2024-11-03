SELECT "Creando base de datos...";
CREATE DATABASE shm;
USE shm;

SELECT "Creando tablas de SHM..";
CREATE TABLE `Paciente` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `genero` enum('masculino','femenino','otro') NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL
);

CREATE TABLE `Tratamiento` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `fecha_inicio` date NOT NULL
);

CREATE TABLE `Dosis` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_tratamiento` int NOT NULL,
  `id_medicamento` int NOT NULL,
  `dias_duracion` int NOT NULL,
  `cantidad` varchar(50) NOT NULL,
  `metodo_administracion` enum('oral','parenteral','tópica','respiratoria','rectal') NOT NULL,
  `nota` varchar(200)
);

CREATE TABLE `Recordatoria` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_dosis` int NOT NULL,
  `hora` time NOT NULL
);

CREATE TABLE `Medicamento` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL
);

CREATE TABLE `Alergia` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL
);

CREATE TABLE `Enfermedad` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL
);

CREATE TABLE `Cirugia` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL
);

CREATE TABLE `Vacuna` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL
);

CREATE TABLE `AntecedenteMedicamento` (
  `id_paciente` int NOT NULL,
  `id_medicamento` int NOT NULL
);

CREATE TABLE `AntecedenteAlergia` (
  `id_paciente` int NOT NULL,
  `id_alergia` int NOT NULL
);

CREATE TABLE `AntecedenteEnfermedad` (
  `id_paciente` int NOT NULL,
  `id_enfermedad` int NOT NULL
);

CREATE TABLE `AntecedenteCirugia` (
  `id_paciente` int,
  `id_cirugia` int,
  `fecha_aplicacion` date
);

CREATE TABLE `AntecedenteVacuna` (
  `id_paciente` int,
  `id_vacuna` int,
  `fecha_aplicacion` date
);

ALTER TABLE `Tratamiento` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `Dosis` ADD FOREIGN KEY (`id_tratamiento`) REFERENCES `Tratamiento` (`id`);
ALTER TABLE `Dosis` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `Medicamento` (`id`);
ALTER TABLE `Recordatoria` ADD FOREIGN KEY (`id_dosis`) REFERENCES `Dosis` (`id`);
ALTER TABLE `AntecedenteMedicamento` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `AntecedenteMedicamento` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `Medicamento` (`id`);
ALTER TABLE `AntecedenteAlergia` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `AntecedenteAlergia` ADD FOREIGN KEY (`id_alergia`) REFERENCES `Alergia` (`id`);
ALTER TABLE `AntecedenteEnfermedad` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `AntecedenteEnfermedad` ADD FOREIGN KEY (`id_enfermedad`) REFERENCES `Enfermedad` (`id`);
ALTER TABLE `AntecedenteCirugia` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `AntecedenteCirugia` ADD FOREIGN KEY (`id_cirugia`) REFERENCES `Cirugia` (`id`);
ALTER TABLE `AntecedenteVacuna` ADD FOREIGN KEY (`id_paciente`) REFERENCES `Paciente` (`id`);
ALTER TABLE `AntecedenteVacuna` ADD FOREIGN KEY (`id_vacuna`) REFERENCES `Vacuna` (`id`);


SELECT "Creando usuario con acceso a la base de datos...";
CREATE USER IF NOT EXISTS 'admin_shm'@'localhost' IDENTIFIED BY 'ods3';
GRANT INSERT, SELECT, EXECUTE, UPDATE, DELETE ON shm.* TO 'admin_shm'@'localhost';
