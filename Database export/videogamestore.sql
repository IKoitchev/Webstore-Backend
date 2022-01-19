-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2022 at 03:30 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `videogamestore`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `price` double NOT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `author` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id`, `name`, `description`, `price`, `genre`, `url`, `author`) VALUES
(1, 'Among us', 'Among Us is a computer game developed by the company InnerSloth and released in 2018, described as an \"online multiplayer social deduction game\"', 4, 'social deduction game', '/images/amongus.jpeg', 'admin'),
(2, 'NBA 2020', 'NBA 2K22 puts the entire basketball universe in your hands. Anyone, anywhere can hoop in NBA 2K22', 60, 'eSports', '/images/nba.jpg', 'admin'),
(3, 'Phasmophobia', 'Phasmophobia is a 4 player online co-op psychological horror. Paranormal activity is on the rise and it’s up to you and your team to use all the ghost hunting equipment at your disposal in order to gather as much evidence as you can', 11.59, 'horror', '/images/phasm.jpg', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `message`, `timestamp`, `user_name`) VALUES
(2, 'hi', '2022-01-11 11:10:26', 'jon'),
(3, 'hello', '2022-01-11 11:12:47', 'jon1'),
(4, 'hello', '2022-01-13 11:23:12', 'jon');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_finished` tinyint(1) NOT NULL,
  `purchase_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `order_finished`, `purchase_date`) VALUES
(1, 2, 1, '2022-01-04'),
(2, 2, 0, NULL),
(3, 4, 0, NULL),
(4, 1, 1, '2022-01-11'),
(5, 1, 1, '2022-01-13'),
(6, 0, 0, NULL),
(7, 0, 0, NULL),
(8, 0, 0, NULL),
(9, 0, 0, NULL),
(10, 0, 0, NULL),
(11, 5, 1, '2022-01-11'),
(15, 5, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`order_id`, `game_id`, `id`) VALUES
(1, 1, 1),
(2, 2, 2),
(4, 1, 8),
(3, 1, 10),
(3, 2, 11),
(6, 2, 13),
(7, 1, 15),
(8, 2, 16),
(9, 3, 17),
(10, 3, 18),
(11, 1, 21),
(5, 2, 31);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `date_posted` date DEFAULT NULL,
  `game_id` int(11) NOT NULL,
  `author` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `text`, `date_posted`, `game_id`, `author`) VALUES
(4, 'Great game! Enjoyed playing with friends!', '2022-01-10', 1, 'jon2'),
(5, 'This game is way too scary', '2022-01-12', 3, 'jon2'),
(6, '', NULL, 2, 'jon2'),
(7, 'Nice game', '2022-01-13', 2, 'jon');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_CUSTOMER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`) VALUES
(1, 'email@gmail.com', '$2a$10$WJA.x0KDf5jnEw3aN3Vv2ehWaqyJD3Juqnj/xtXEe3T7pZo6Y.qwe', 'jon'),
(2, 'ivan@gmail.com', '$2a$10$DQd0GWASZqDCtgkQTYf6buFNSBNL7Si.Mgl35MFBJME9QWAtY4PV2', 'ivan'),
(3, 'ivan1@gmail.com', '$2a$10$9PsPzvE0YLziScyXqSPUk.I6lo/iZKNHdRFfJuaKoklgeDunbWYGS', 'ivan1'),
(4, 'jon1@gmail.com', '$2a$10$rY43lTSVPsoOJ4Uh4vMOZ.XrYKRfXMtvgt8mw69zJBxjv7COokYt.', 'jon1'),
(5, 'therealjon@gmail.com', '$2a$10$gRU34ko2VG.wjXxwnPRGDOhzWFlx37qq5DhuvCR3sb6PIolciQJwm', 'jon2');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(3, 1),
(4, 1),
(5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh24gu2mhloifdd4w33g1l05wm` (`game_id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKh24gu2mhloifdd4w33g1l05wm` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
