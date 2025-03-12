-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 06. Feb 2025 um 12:27
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `regamebuy`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `games`
--

CREATE TABLE `games` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `publisher` text NOT NULL,
  `platform` enum('NINTENDODS','WII','COMPUTER','PLAYSTATION4','XBOX','GAMECUBE','SWITCH') NOT NULL,
  `genre` text NOT NULL,
  `releaseYear` int(11) NOT NULL,
  `gameCondition` enum('NEW','USED','DAMAGED') NOT NULL,
  `price` double NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `games`
--

INSERT INTO `games` (`id`, `title`, `publisher`, `platform`, `genre`, `releaseYear`, `gameCondition`, `price`, `description`) VALUES
(1, 'The Legend of Zelda: Breath of the Wild', 'Nintendo', 'NINTENDODS', 'RPG', 2017, 'NEW', 59.99, 'Explore a vast open world filled with adventure and puzzles.'),
(2, 'The Witcher 3: Wild Hunt', 'CD Projekt', 'COMPUTER', 'RPG', 2015, 'NEW', 39.99, 'An epic story-driven RPG set in a visually stunning fantasy world.'),
(3, 'Grand Theft Auto V', 'Rockstar Games', 'COMPUTER', 'ACTION', 2013, 'USED', 19.99, 'Open-world action game with three main characters.'),
(4, 'Minecraft', 'Mojang', 'COMPUTER', 'SANDBOX', 2011, 'NEW', 22.99, 'Create, explore, and survive in a blocky open world.'),
(5, 'Dark Souls III', 'Bandai Namco', 'COMPUTER', 'RPG', 2016, 'USED', 29.99, 'A challenging action RPG in a dark fantasy universe.'),
(6, 'Elden Ring', 'FromSoftware', 'COMPUTER', 'RPG', 2022, 'NEW', 69.99, 'Open-world fantasy RPG from the creators of Dark Souls.'),
(7, 'Super Mario Odyssey', 'Nintendo', 'SWITCH', 'RPG', 2017, 'NEW', 59.99, 'Join Mario on a globetrotting adventure with his hat companion, Cappy.'),
(8, 'Red Dead Redemption 2', 'Rockstar Games', 'COMPUTER', 'RPG', 2018, 'NEW', 59.99, 'A western epic that follows the journey of Arthur Morgan.'),
(9, 'Hollow Knight', 'Team Cherry', 'SWITCH', 'ACTION', 2017, 'NEW', 14.99, 'A hand-drawn 2D Metroidvania in an insect kingdom.'),
(10, 'Portal 2', 'Valve', 'COMPUTER', 'PUZZLE', 2011, 'NEW', 9.99, 'Solve physics-based puzzles using portals.'),
(11, 'Half-Life 2', 'Valve', 'COMPUTER', 'FPS', 2004, 'USED', 4.99, 'Groundbreaking first-person shooter with an engaging story.'),
(12, 'The Last of Us Part II', 'Sony', 'COMPUTER', 'RPG', 2020, 'NEW', 59.99, 'A gripping narrative-driven action game.'),
(13, 'Stardew Valley', 'ConcernedApe', 'SWITCH', 'SIMULATION', 2016, 'NEW', 14.99, 'Manage your farm and build relationships in this relaxing simulation.'),
(14, 'Overwatch', 'Blizzard Entertainment', 'COMPUTER', 'SHOOTER', 2016, 'NEW', 39.99, 'Team-based shooter with unique heroes.'),
(17, 'Skyrim', 'Bethesda', 'COMPUTER', 'RPG', 2011, 'USED', 19.99, 'Explore a massive open world in this epic fantasy RPG.'),
(18, 'Animal Crossing: New Horizons', 'Nintendo', 'COMPUTER', 'SIMULATION', 2020, 'NEW', 59.99, 'Build and decorate your island paradise.'),
(19, 'Celeste', 'Maddy Makes Games', 'COMPUTER', 'SANDBOX', 2018, 'NEW', 19.99, 'A challenging and heartfelt 2D platformer.'),
(20, 'Fallout 4', 'Bethesda', 'COMPUTER', 'RPG', 2015, 'NEW', 29.99, 'Post-apocalyptic open-world RPG with base building.'),
(27, 'Half-Life 2', 'Valve', 'COMPUTER', 'FPS', 2004, 'NEW', 24.99, 'Groundbreaking first-person shooter with an engaging story.'),
(28, 'Portal 2', 'Valve', 'COMPUTER', 'PUZZLE', 2011, 'USED', 9.99, 'Solve physics-based puzzles using portals.'),
(29, 'The Legend of Zelda: Breath of the Wild', 'Nintendo', 'NINTENDODS', 'RPG', 2017, 'NEW', 59.99, 'Explore a vast open world filled with adventure and puzzles.');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `games`
--
ALTER TABLE `games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
