-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 24 Ağu 2021, 15:14:54
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `student_management_system`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lecture`
--

CREATE TABLE `lecture` (
  `lecture_name` varchar(20) DEFAULT NULL,
  `lecture_code` varchar(6) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `lecture`
--

INSERT INTO `lecture` (`lecture_name`, `lecture_code`, `edit_time`) VALUES
('English', 'ENG302', '2021-08-24 09:27:12'),
('Geography', 'GEO951', '2021-08-24 12:13:51'),
('Informatik', 'INF202', '2021-08-24 12:14:45'),
('Mathematik', 'MAT123', '2021-08-24 09:25:51'),
('Physik', 'PHY234', '2021-08-24 09:29:31');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lecture_and_student`
--

CREATE TABLE `lecture_and_student` (
  `student_name` varchar(20) NOT NULL,
  `student_surname` varchar(20) NOT NULL,
  `student_no` int(7) NOT NULL,
  `lecture_code` varchar(6) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `lecture_and_student`
--

INSERT INTO `lecture_and_student` (`student_name`, `student_surname`, `student_no`, `lecture_code`, `edit_time`) VALUES
('Student', 'STUDENT', 4567891, 'MAT123', '2021-08-24 12:15:27'),
('Student', 'STUDENT', 4567891, 'GEO951', '2021-08-24 12:15:42'),
('Beststudent', 'EVER', 1234567, 'INF202', '2021-08-24 12:26:52'),
('Beststudent', 'EVER', 1234567, 'PHY234', '2021-08-24 12:27:08');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lecture_and_teacher`
--

CREATE TABLE `lecture_and_teacher` (
  `teacher_name` varchar(20) NOT NULL,
  `teacher_surname` varchar(20) NOT NULL,
  `teacher_no` bigint(11) NOT NULL,
  `lecture_code` varchar(6) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `lecture_and_teacher`
--

INSERT INTO `lecture_and_teacher` (`teacher_name`, `teacher_surname`, `teacher_no`, `lecture_code`, `edit_time`) VALUES
('Mike', 'TYSON', 74859612378, 'MAT123', '2021-08-24 12:03:41'),
('Myteacher', 'İSBEST', 12345678911, 'GEO951', '2021-08-24 12:16:25'),
('Myteacher', 'İSBEST', 12345678911, 'PHY234', '2021-08-24 12:20:12'),
('Bigbrother', 'İSWATCHİNGYOU', 48592613777, 'ENG302', '2021-08-24 12:20:41'),
('Another', 'PERSON', 14225698799, 'INF202', '2021-08-24 12:21:31');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `student`
--

CREATE TABLE `student` (
  `student_name` varchar(20) NOT NULL,
  `student_surname` varchar(20) NOT NULL,
  `student_no` int(7) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `student`
--

INSERT INTO `student` (`student_name`, `student_surname`, `student_no`, `edit_time`) VALUES
('Beststudent', 'EVER', 1234567, '2021-08-24 12:26:30'),
('Mystudent', 'İSBEST', 4567890, '2021-08-24 09:23:07'),
('Student', 'STUDENT', 4567891, '2021-08-24 09:22:33'),
('Jackie', 'CHAN', 4895263, '2021-08-24 12:12:21'),
('Tom', 'MURPHY', 5847693, '2021-08-24 12:12:11');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teacher`
--

CREATE TABLE `teacher` (
  `teacher_name` varchar(20) NOT NULL,
  `teacher_surname` varchar(20) NOT NULL,
  `teacher_no` bigint(11) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `teacher`
--

INSERT INTO `teacher` (`teacher_name`, `teacher_surname`, `teacher_no`, `edit_time`) VALUES
('Myteacher', 'İSBEST', 12345678911, '2021-08-24 09:24:59'),
('Another', 'PERSON', 14225698799, '2021-08-24 12:01:01'),
('Another', 'TEACHER', 47586912345, '2021-08-24 09:24:37'),
('Bigbrother', 'İSWATCHİNGYOU', 48592613777, '2021-08-24 12:12:42'),
('Mike', 'TYSON', 74859612378, '2021-08-24 10:53:07');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `lecture`
--
ALTER TABLE `lecture`
  ADD PRIMARY KEY (`lecture_code`);

--
-- Tablo için indeksler `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_no`);

--
-- Tablo için indeksler `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
