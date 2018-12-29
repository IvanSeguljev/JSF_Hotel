-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 29, 2018 at 06:09 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SegiResort`
--

-- --------------------------------------------------------

--
-- Table structure for table `Hotel`
--

CREATE TABLE `Hotel` (
  `id` int(10) UNSIGNED NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `slika` text NOT NULL,
  `opis` text NOT NULL,
  `menadzer_id` int(11) NOT NULL,
  `adresa` text NOT NULL,
  `telefon` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Hotel`
--

INSERT INTO `Hotel` (`id`, `naziv`, `slika`, `opis`, `menadzer_id`, `adresa`, `telefon`) VALUES
(10, 'Hilton Beograd', '2018-12-29T11:04:25.426Zhilton.jpg', 'Hotel Hilton Belgrade nudi krovni bar i restoran sa pogledom na grad, a nalazi se na peša?koj udaljenosti od Trga Slavija i Hrama Sv. Save. Living Well Health Club nudi spa i velnes sadržaje, a gostima su na raspolaganju termalno kupatilo, sauna i tursko kupatilo.\r\n\r\nProstrane sobe poseduju klima-ure?aj, sto i flat-screen TV sa kablovskim kanalima. Svaka smeštajna jedinica nudi kuvalo i frižider, dok pojedine obuhvataju i ?ajnu kuhinju. Sopstvena kupatila sadrže tuš (bez kabine) i fen za kosu. Besplatan toaletni pribor je obezbe?en.\r\n\r\nPoslovni i sadržaji za sastanke dostupni su u hotelu. Gosti imaju mogu?nost da dožive srpsku kuhinju u restoranu Dva Kralja (Two Kings) ili da uživaju u azijskoj fjužn (fusion) hrani. Uz pogled na Beograd, gosti Skaj Laundža (Sky Lounge) mogu ispijati koktele. Pored toga, rum servis je na raspolaganju 24 sata dnevno.\r\n\r\nMuzeji, galerije, restorani i raznovrsne prodavnice nalaze se u okolini. Trg republike udaljen je 1,5 km, dok je Kalemegdan udaljen 2 km od hotela Hilton Belgrade.\r\n\r\nAerodrom Nikola Tesla nalazi se na 19 km od hotela Belgrade Hilton. Usluga aerodromskog prevoza je dostupna uz doplatu. Hotel poseduje parking garažu, a dostupna je i usluga iznajmljivanja automobila. \r\n\r\nPrema nezavisnim recenzijama, naši gosti obožavaju ovaj deo destinacije Beograd.\r\n\r\nParovima se posebno svidela lokacija - ocenili su je sa 9,4 za boravak udvoje.', 31, 'Kralja Milana 35   , Vracar, 11000 Beograd, Srbija', '064111111111'),
(11, 'Hotel Novi Sad', '2018-12-29T11:09:02.541ZnoviSad.jpg', 'Hotel Novi Sad se nalazi na idealnoj lokaciji u blizini glavne autobuske i železni?ke stanice, nedaleko od centra grada, Novosadskog sajma i Sportskog i poslovnog centra Vojvodina.\r\n\r\nIza?ite i obi?ite Petrovaradinsku tvr?avu i druge zanimljive atrakcije.\r\n\r\nUživajte u ukusnim specijalitetima nacionalne i internacionalne kuhinje, pra?enim velikim izborom pi?a i koktela. Obroci se služe u restoranu, koji tako?e ima i terasu i može primiti do 250 ljudi. On predstavlja odli?no mesto za ven?anja, prijeme i koktele.\r\n\r\nZapo?nite dan u aperitiv baru, gde možete pijuckati omiljeno pi?e dok surfujete po internetu ili ?itate novine.\r\n\r\nU hotelu Novi Sad na raspolaganju vam je profesionalno opremljena sala za seminare koja prima do 60 ljudi. \r\n\r\nParovima se posebno svidela lokacija - ocenili su je sa 8,5 za boravak udvoje.', 39, 'Bulevar Jase Tomica 1, 21000 Novi Sad, Srbija', '064141414011'),
(12, 'Hotel Srbija', '2018-12-29T11:10:41.002Zsrbija.jpg', 'Hotel Srbija se nalazi u 18-spratnoj zgradi pored živopisnog parka, u mirnom i ugodnom delu Beograda. Poseduje restoran na poslednjem spratu, odakle se pruža panoramski pogled na grad.\r\n\r\nSobe su dobro opremljene i pogodne za sve vrste gostiju, bilo da ste u Beogradu zbog posla ili zadovoljsta. Smeštajne jedinice uklju?uju besplatan pristup internetu.\r\n\r\nU prizemlju hotela Srbija nalazi se jedan restoran koji služi internacionalnu kuhinju. U ostala 4 objekta služe se doru?ak na bazi švedskog stola i grupe jela. Mogu?e je tako?e organizovati razli?ite vrste doga?aja.\r\n\r\nUkoliko planirate da organizujete ven?anje, seminar, konferenciju ili bilo koji drugi doga?aj, na raspolaganju vam je dobro opremljena sala za sastanke. Ovaj smeštajni objekat poseduje i besplatan privatni parking. \r\n\r\nDestinacija Voždovac je odli?an izbor za putnike koje zanimaju šoping, razgledanje i doma?a hrana.\r\n\r\nParovima se posebno svidela lokacija - ocenili su je sa 8,0 za boravak udvoje.', 40, 'Ustanicka 127c, Voždovac, 11000 Beograd, Srbija', '064222333543');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik_iznajmio`
--

CREATE TABLE `korisnik_iznajmio` (
  `id` int(11) NOT NULL,
  `korisnik_id` int(11) DEFAULT NULL,
  `soba_id` int(11) DEFAULT NULL,
  `cena` float NOT NULL,
  `poeni` int(11) NOT NULL,
  `ostaje_dana` int(11) NOT NULL,
  `komentar` varchar(255) DEFAULT NULL,
  `datum_iznajmljeno` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hotel` varchar(255) DEFAULT NULL,
  `status` enum('Rezervisano','Stigao','Otisao') NOT NULL DEFAULT 'Rezervisano'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik_iznajmio`
--

INSERT INTO `korisnik_iznajmio` (`id`, `korisnik_id`, `soba_id`, `cena`, `poeni`, `ostaje_dana`, `komentar`, `datum_iznajmljeno`, `hotel`, `status`) VALUES
(17, 41, 9, 3500, 35, 7, '', '2018-12-29 11:24:33', 'Hilton Beograd', 'Otisao'),
(18, 41, 9, 3500, 35, 7, '', '2018-12-29 11:24:52', 'Hilton Beograd', 'Otisao'),
(19, 41, 12, 1000, 10, 2, 'Awlergican sam na prasinu, molim vas dobro ocistite sobu', '2018-12-29 11:25:26', 'Hilton Beograd', 'Stigao'),
(20, 42, 11, 6000, 60, 3, '', '2018-12-29 11:26:09', 'Hilton Beograd', 'Stigao'),
(21, 42, 14, 2800, 28, 7, '', '2018-12-29 11:26:23', 'Hotel Novi Sad', 'Stigao'),
(22, 42, 15, 5600, 56, 7, '', '2018-12-29 11:26:35', 'Hotel Novi Sad', 'Rezervisano'),
(23, 42, 11, 12000, 120, 6, '', '2018-12-29 11:26:44', 'Hilton Beograd', 'Otisao'),
(24, 43, 12, 3000, 30, 6, '', '2018-12-29 11:27:06', 'Hilton Beograd', 'Rezervisano'),
(25, 43, 10, 7000, 70, 10, '', '2018-12-29 11:27:21', 'Hilton Beograd', 'Otisao'),
(26, 43, 9, 7000, 70, 14, 'Neka nas ceka sampanjac', '2018-12-29 11:27:45', 'Hilton Beograd', 'Stigao'),
(27, 44, 16, 8000, 80, 10, '', '2018-12-29 11:28:16', 'Hotel Srbija', 'Stigao');

-- --------------------------------------------------------

--
-- Table structure for table `soba_tip`
--

CREATE TABLE `soba_tip` (
  `id` int(11) NOT NULL,
  `hotel_id` int(10) UNSIGNED NOT NULL,
  `kreveti` int(11) NOT NULL,
  `slika` varchar(255) NOT NULL,
  `broj_soba` int(11) NOT NULL,
  `broj_slobodnih` int(11) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `cena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `soba_tip`
--

INSERT INTO `soba_tip` (`id`, `hotel_id`, `kreveti`, `slika`, `broj_soba`, `broj_slobodnih`, `opis`, `cena`) VALUES
(9, 10, 2, '2018-12-29T11:11:33.350Zhr1.jpeg', 30, 29, 'Dvokrevetna soba sa bracnim krevetom', 500),
(10, 10, 3, '2018-12-29T11:12:08.500Zhr2.jpeg', 10, 10, 'trokrevetna soba sa bracnim krevetom', 700),
(11, 10, 5, '2018-12-29T11:12:41.098Zhr4.jpeg', 5, 4, 'Petokrevetna soba', 2000),
(12, 10, 2, '2018-12-29T11:13:16.292Zhr4.jpeg', 10, 8, 'dvokrevetna soba sa odvojenim krevetima', 500),
(14, 11, 2, '2018-12-29T11:19:50.012Zhr4.jpeg', 20, 19, 'Dvokrevetna soba sa bracnim krevetom', 400),
(15, 11, 4, '2018-12-29T11:20:27.001Zhr3.jpeg', 5, 4, 'Cetvorokrevetna soba sa bracnim krevetom', 800),
(16, 12, 3, '2018-12-29T11:21:31.611Zhr4.jpeg', 10, 9, 'Trokrevetna soba', 800),
(17, 12, 2, '2018-12-29T11:22:55.283Zhr1.jpeg', 16, 16, 'Dvokrevetna soba', 700),
(18, 12, 2, '2018-12-29T11:22:35.336Zhr2.jpeg', 5, 5, 'Soba sa bracnim krevetom, dvokrevetna', 400);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('Klijent','Menadzer','Administrator') NOT NULL,
  `poeni` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `username`, `password`, `email`, `role`, `poeni`) VALUES
(30, 'admin', 'pTvXji9YqUf07xD8TixuiQXfs9kGqCgcrqDRHj9ZWkg=$Ao1M4KQhpx9CIWKvcYnazGQHqhYH7ECI0L+Sp7uTqd4=', 'admin@mail.com', 'Administrator', 55),
(31, 'menadzer', 'Xi1RPl+JJBQs2SyklmQrpD7kg4Ba5E/wuBNX8bznXqg=$1MnEXN2CvAEr4gUztg3P+Rq42v41/TakvMe+qWC2f58=', 'menadzer@mail.com', 'Menadzer', 0),
(32, 'klijent', 'QVpttykASwbSf0pIuwa0FyIkAlsX+OyDLm1QhaQN3cc=$e6+CsDIfMkUvF/5OkKmB3CVnfWV0zROABLlxzpJtzb8=', 'klijent@mail.com', 'Klijent', 0),
(39, 'menadzer1', 'hI96NGo2wHA92fMyMpOJBQI5pJ/u+X78lArRdAVzWIQ=$Ars7t8oC59b83wTfQiS58cw6GBWfZRQHKUC1vW6DP74=', 'menadzer1@mail.com', 'Menadzer', 0),
(40, 'menadzer2', 'FcBwZtGHZverB7B6/QrKyb8a/GcIhYzLu0VjZwvBL2Y=$4r37xqjfU1kxknxBPRpeYH3lkjnzlk6ZeWff0/82Ong=', 'menadzer2@mail.com', 'Menadzer', 0),
(41, 'ivan', 'sXuhiXWs2UuYDGrwIYhI2tqKSl2VmzpXWsl7mJP+eAo=$rVspjG2EOAJa6UnCGfKwYBahsvaFJfUh/RjF06GjMic=', 'ivan.seguljev@yahoo.com', 'Klijent', 80),
(42, 'Pera', 'qgUBhrQQkP+Nyn7mm0IDrfuQyj7bZaHlw0XJZvkBdsk=$yh9Buq7x/4t0F8UZvrIiPY9FealthZO8Rk6ZjT7Hjdk=', 'Pera@mail.com', 'Klijent', 264),
(43, 'mika', 'RzCXiFW1uJtko24epQwVvk05Gw2KSefJmV0vxm+8eV4=$OoGRqlpGHYDP/mz70Xv30ICEsQriBo/XA8VhFl4/o4g=', 'mika@mail.com', 'Klijent', 170),
(44, 'zika', 'W7lGtqFYmVo42sIWESQt8wgz7yF/VYiuJ/u+t0BEjHI=$aybNRpgatLj8wnSgkwzaOIIDSeyHvuqTCsAiSHEMKNo=', 'zika@mail.com', 'Klijent', 80);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Hotel`
--
ALTER TABLE `Hotel`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `menadzer_id` (`menadzer_id`),
  ADD UNIQUE KEY `naziv` (`naziv`);

--
-- Indexes for table `korisnik_iznajmio`
--
ALTER TABLE `korisnik_iznajmio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `korisnik_id` (`korisnik_id`),
  ADD KEY `soba_id` (`soba_id`),
  ADD KEY `hotel` (`hotel`);

--
-- Indexes for table `soba_tip`
--
ALTER TABLE `soba_tip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Hotel`
--
ALTER TABLE `Hotel`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `korisnik_iznajmio`
--
ALTER TABLE `korisnik_iznajmio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `soba_tip`
--
ALTER TABLE `soba_tip`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Hotel`
--
ALTER TABLE `Hotel`
  ADD CONSTRAINT `Hotel_ibfk_1` FOREIGN KEY (`menadzer_id`) REFERENCES `User` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `korisnik_iznajmio`
--
ALTER TABLE `korisnik_iznajmio`
  ADD CONSTRAINT `korisnik_iznajmio_ibfk_1` FOREIGN KEY (`korisnik_id`) REFERENCES `User` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `korisnik_iznajmio_ibfk_2` FOREIGN KEY (`soba_id`) REFERENCES `soba_tip` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `korisnik_iznajmio_ibfk_3` FOREIGN KEY (`hotel`) REFERENCES `Hotel` (`naziv`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `soba_tip`
--
ALTER TABLE `soba_tip`
  ADD CONSTRAINT `soba_tip_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
