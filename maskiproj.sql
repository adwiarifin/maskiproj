-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `detail_transaksi`;
CREATE TABLE `detail_transaksi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_transaksi` int(10) unsigned NOT NULL,
  `material` varchar(50) NOT NULL,
  `panjang` double NOT NULL,
  `lebar` double NOT NULL,
  `harga` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_transaksi` (`id_transaksi`),
  CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `detail_transaksi` (`id`, `id_transaksi`, `material`, `panjang`, `lebar`, `harga`) VALUES
(1,	1,	'Busa 2 mm',	100,	100,	0.67),
(2,	1,	'Suede',	100,	100,	0.77);

DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `formula` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `material` (`id`, `name`, `formula`) VALUES
(1,	'Vinyl Biasa',	13000),
(2,	'Furing 75 gr',	17000),
(3,	'Suede',	13000),
(4,	'Beludru',	13000),
(5,	'K 100 kuning',	5080),
(6,	'K 30 kuning',	5080),
(7,	'K 20 Abu',	5080),
(8,	'K 30 Abu',	5080),
(9,	'Busa Abu 4cm',	20000),
(10,	'Busa Kuning 4 cm',	20000),
(11,	'Busa 8 mm',	15000),
(12,	'Busa Ijo 4 cm',	20000),
(13,	'Busa 10 mm',	20000),
(14,	'Spon ati',	26400),
(15,	'Busa 2 mm',	15000),
(16,	'Triplek',	29768),
(17,	'Enceng Polos 35',	3500),
(18,	'Enceng Polos 70',	7000),
(19,	'Mendong Polos 35',	3500),
(20,	'Mendong Polos 70',	7000),
(21,	'Mika',	12000);

DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tanggal` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `pemesan` varchar(100) NOT NULL,
  `jenis_pesanan` varchar(100) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `transaksi` (`id`, `tanggal`, `pemesan`, `jenis_pesanan`, `total`) VALUES
(1,	'2018-03-01 06:42:46',	'Adwi Arifin',	'Pesanan Biasa',	1.44);

-- 2018-02-28 23:47:19
