/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.24-MariaDB : Database - db_posyandu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_posyandu` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_posyandu`;

/*Table structure for table `akun` */


/*Table structure for table `ibu` */

/*Table structure for table `kader` */

DROP TABLE IF EXISTS `kader`;

CREATE TABLE `kader` (
  `nik_kader` varchar(30) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `jenis_kelamin` enum('LAKI-LAKI','PEREMPUAN') DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') DEFAULT NULL,
  PRIMARY KEY (`nik_kader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `ibu`;

CREATE TABLE `ibu` (
  `nik_ibu` varchar(30) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nama_suami` varchar(255) DEFAULT NULL,
  `status` enum('HAMIL','KB','BELUM DITENTUKAN') DEFAULT NULL,
  `tanggal_lahir` varchar(30) DEFAULT NULL,
  `gol_darah` enum('A','B','AB','O') DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nik_ibu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `akun`;

CREATE TABLE `akun` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) DEFAULT NULL,
  `level` enum('ADMIN','KADER','PESERTA') DEFAULT NULL,
  `nik_kader` varchar(30) DEFAULT NULL,
  `nik_ibu` varchar(30) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `id_kader` (`nik_kader`),
  KEY `id_ibu` (`nik_ibu`),
  CONSTRAINT `akun_ibfk_1` FOREIGN KEY (`nik_kader`) REFERENCES `kader` (`nik_kader`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `akun_ibfk_2` FOREIGN KEY (`nik_ibu`) REFERENCES `ibu` (`nik_ibu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `riwayat_ibu` */

DROP TABLE IF EXISTS `riwayat_ibu`;

CREATE TABLE `riwayat_ibu` (
  `id_riwayat` int(10) NOT NULL AUTO_INCREMENT,
  `nik_ibu` varchar(30) DEFAULT NULL,
  `metode_kb` varchar(255) DEFAULT NULL,
  `komplikasi_kb` enum('YA','TIDAK') DEFAULT NULL,
  `kegagalan_kb` enum('YA','TIDAK') DEFAULT NULL,
  `hamil_ke` int(3) DEFAULT NULL,
  `tanggal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_riwayat`),
  KEY `id_ibu` (`nik_ibu`),
  CONSTRAINT `riwayat_ibu_ibfk_1` FOREIGN KEY (`nik_ibu`) REFERENCES `ibu` (`nik_ibu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `anak`;

CREATE TABLE `anak` (
  `id_anak` int(10) NOT NULL AUTO_INCREMENT,
  `nik_ibu` varchar(30) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `kategori` enum('BAYI','BALITA') DEFAULT NULL,
  `bb_lahir` int(5) DEFAULT NULL,
  `tb_lahir` int(5) DEFAULT NULL,
  `jenis_kelamin` enum('LAKI-LAKI','PEREMPUAN') DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `tempat_lahir` date DEFAULT NULL,
  PRIMARY KEY (`id_anak`),
  KEY `id_ibu` (`nik_ibu`),
  CONSTRAINT `anak_ibfk_1` FOREIGN KEY (`nik_ibu`) REFERENCES `ibu` (`nik_ibu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `anak` */




/*Table structure for table `riwayat_anak` */

DROP TABLE IF EXISTS `riwayat_anak`;

CREATE TABLE `riwayat_anak` (
  `id_riwayat` int(10) NOT NULL AUTO_INCREMENT,
  `id_anak` int(10) DEFAULT NULL,
  `nama_sg` varchar(255) DEFAULT NULL,
  `vitamin` enum('BIRU','MERAH') DEFAULT NULL,
  `penolong_kelahiran` varchar(255) DEFAULT NULL,
  `riwayat_kelahiran` enum('SPONTAN','OPERASI') DEFAULT NULL,
  `mp_asi` varchar(255) DEFAULT NULL,
  `riwayat_penyakit` varchar(255) DEFAULT NULL,
  `berat_badan` int(5) DEFAULT NULL,
  `tinggi_badan` int(5) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  PRIMARY KEY (`id_riwayat`),
  KEY `id_anak` (`id_anak`),
  CONSTRAINT `riwayat_anak_ibfk_1` FOREIGN KEY (`id_anak`) REFERENCES `anak` (`id_anak`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `riwayat_anak` */


DROP TABLE IF EXISTS `imunisasi`;

CREATE TABLE `imunisasi` (
  `id_imunisasi` int(10) NOT NULL AUTO_INCREMENT,
  `id_riwayat` int(10) DEFAULT NULL,
  `jenis` enum('HEPATITIS B','POLIO','BCG','CAMPAK RUBELLA','DPT-HB-HiB') DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  PRIMARY KEY (`id_imunisasi`),
  KEY `id_riwayat` (`id_riwayat`),
  CONSTRAINT `imunisasi_ibfk_1` FOREIGN KEY (`id_riwayat`) REFERENCES `riwayat_anak` (`id_riwayat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `imunisasi` */




DROP TABLE IF EXISTS `kegiatan`;

CREATE TABLE `kegiatan` (
  `id_kegiatan` int(10) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,
  `deskripsi` varchar(500) DEFAULT NULL,
  `tanggal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_kegiatan`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS `pendataan`;

CREATE TABLE `pendataan` (
  `id_pendataan` int(10) NOT NULL AUTO_INCREMENT,
  `nik_kader` varchar(30) DEFAULT NULL,
  `nik_ibu` varchar(30) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  PRIMARY KEY (`id_pendataan`),
  KEY `id_kader` (`nik_kader`),
  KEY `id_ibu` (`nik_ibu`),
  CONSTRAINT `pendataan_ibfk_1` FOREIGN KEY (`nik_ibu`) REFERENCES `ibu` (`nik_ibu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pendataan_ibfk_2` FOREIGN KEY (`nik_kader`) REFERENCES `kader` (`nik_kader`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `pendataan` */




