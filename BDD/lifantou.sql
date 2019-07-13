-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  sam. 13 juil. 2019 à 23:31
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `lifantou`
--

-- --------------------------------------------------------

--
-- Structure de la table `acces_app`
--

DROP TABLE IF EXISTS `acces_app`;
CREATE TABLE IF NOT EXISTS `acces_app` (
  `id_acces_app` bigint(20) NOT NULL AUTO_INCREMENT,
  `actived` int(11) NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_role` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_acces_app`),
  KEY `FKo5be2ikeeix002onvttx66x9n` (`id_role`),
  KEY `FKhgbnn4ljc42e2vhqkvvtx7oc5` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `acces_app`
--

INSERT INTO `acces_app` (`id_acces_app`, `actived`, `password`, `token`, `username`, `id_role`, `id_user`) VALUES
(2, 1, '$2a$10$DzaXT0gECxvmjyzV07X48OPDP38lVpYUyxsqlzLL8YC49zWwascbO', '273cd5f7-00c5-47f5-bbd3-a9dac04d2c93', 'Admin1@admin', 1, 2),
(3, 1, '$2a$10$HrGXBc.MVycREF2M8gNmbufmypcbhcWY4M7tvywJvJfmVeiMZFpVO', '88e8063d-5a9c-45b0-afca-edfca7b1363e', 'Producteur Test', 3, 5),
(4, 1, '$2a$10$sZMH0g0tEN25nf5v2pHP9ujcXs1rOLv/zE2UlESiK5sbP30jYZWB.', 'aac59b36-4317-40ca-8349-029adff9c21b', 'Ecole Primaire Seydou Sall', 2, 7),
(5, 1, '$2a$10$s8AnM6oMLqhCLwVzF2B9euGL7etCa3KRE0N7CiUlmzUkzfBq6BbIW', 'c0ec5ca7-3508-42db-b7d7-f52761e39421', 'ONG un repas pour tous', 4, 8),
(6, 2, '$2a$10$oAWWzsTYVTtHFqQUHGeicOu97cmxUxY.owLrw8WtWcqr.2ylWZwEW', '6afad4fd-e4fb-482c-9c33-e89bfbeab3cc', 'samba', 3, 9),
(7, 1, '$2a$10$hpHFnLCDPUu5SeL/Wy3xkO5EB1wU0QUF8lqNhuM6QQGtRr3beYvFK', 'f70ea638-4805-4ea0-82b5-271e76007751', 'Malainy Sagna', 2, 10),
(8, 1, '$2a$10$.ofBz8vOpW7pfrwKfNx2UutAuB9hfbDcbrwqUFcZxowlSEGdFT0Gi', '9cb1807d-d2d8-4c8d-9667-1513fa88f954', 'Ousmane', 3, 11),
(9, 1, '$2a$10$Zj7Tq/Cm5keRy6gkb.qJBONCepbWk7oPYiBdpDKbibwPESitKmK4m', '1edbc749-3d1f-4e6d-8631-5859a19a7486', 'Ndao baba ', 3, 12),
(10, 2, '$2a$10$TYTO8.QEb0/A/73pGlRAa.r5TVJAzARNid0HPfgqg7EWQFwBpEofW', 'c01147e4-8d0c-44b7-8e40-d72baba4c41e', 'Fatou', 4, 13),
(11, 1, '$2a$10$7yoaNJU8PgQFQUXF2aATEO9nJaxov3u0oEcoMerP0TzKD5fo6j.Za', '4eaf8cd1-ca62-4a7c-a856-4c97a8b318a0', 'Ciré Abdoul Ba', 3, 14),
(12, 1, '$2a$10$aPx.ahPEvgvPQutoPxzV7u1TAjJMsXCu4bmhMz4bC1GQ9lxVm1iaa', '68873fb5-0fae-4a92-a00f-24a917e2a3ac', 'mbengueseynabou', 2, 15),
(13, 0, '$2a$10$Lsc.97aPl2AuvcFoijCib./EKmRactbt5FY7cdVQVcRrzjJ9T/dA6', 'dfe0fc6a-9ddf-49a7-a717-bfea27ecb367', 'mbodj', 4, 16),
(14, 0, '$2a$10$/nUne5bdy8w36BSXLbUuLenyw1oVnVBVreDi9S7mqq1pNtCMP8DT6', 'cf5fd1e3-6613-47c1-b44f-584150514a79', 'Mad', 4, 17),
(15, 1, '$2a$10$IiZJNiJf7F8jbcx4SUi4f.m5f3sEwxqyqys6oRJvU7uo03Tvs/QDi', '0db81c12-eb2d-4175-8a92-afa34aa1e11e', 'boure sarr', 2, 18),
(16, 1, '$2a$10$oTv.XpgwArAzjjCNfzr/QOtTbY.xf.2SG/A9z9DwOnGBRwOP1egVu', '88ead72d-104b-4f09-9ada-8c9ac6fcb74f', 'Khady Ngom KAMARA', 2, 19),
(17, 1, '$2a$10$CaFgd0qGSb7PfDPhyFJgIuypkiXysHe275cZPjLFiEjzS7scm.O36', '9fffddf9-322e-4c44-b9e6-5b4fc9dd7ed2', 'Aïcha DJIBA', 3, 20),
(18, 1, '$2a$10$MW1q7CiWqkFY36CnvxuWD.XM7epN.VuPEeQ65pP8KKvl0voxmg33a', '2ca7f449-0a1d-4e1c-ae38-bc0ede8527ab', 'Abdoulaye KANDE', 3, 21),
(19, 1, '$2a$10$W9vP.aP5HMjqcK2wpBKNWuvbZ0JBXJvsLqgOnNDEMPaoWQ2kpAN3i', 'a4421a73-a13d-4a22-8a3e-fb0fd32d73d8', 'KANDE Abdoulaye', 3, 22),
(20, 1, '$2a$10$egV8uoEsVIOHluRWwOaUaOXEDM5plDr0kCQfcOFsIvBwS0CpQxuli', 'd3a5003a-c070-42ef-8c93-42730d7869bd', 'BaOumar', 2, 23),
(21, 1, '$2a$10$VkaOLOU2O5CGMd.8WJAEveBRpWahkSWPFFZKTnqqrCO7FepBwCr8O', 'ce27baa5-08d4-4a15-b378-6c3d6208d4c8', 'Écoleunautremonde', 2, 24),
(22, 0, '$2a$10$5JeXP/hfkUsQyrZu6GPGH.A6Txc4A/In7Y3nboufCuzP3ndoPe2Lm', '5b312d0e-3154-4bf7-a5e0-d40a8e301a75', 'Khadim diouf', 4, 25),
(23, 1, '$2a$10$/6NP42nas9v5WvAsyV0yruJ2i3kjsTWq9YPcKaP7NVzZ2EEBk4yaO', 'e02d6fde-7bd0-4d03-8c57-67c0fc35ff07', 'Fall Mame Abdou', 3, 26),
(24, 1, '$2a$10$zHvHYsqNhZ48jJeL2MPfLuitC4evkGsKhqIYG9tFQnHmQw9GUvooW', 'c15ea630-dad8-4cc3-ac63-5b9b8f09f642', 'Bop', 3, 27),
(25, 1, '$2a$10$DzaXT0gECxvmjyzV07X48OPDP38lVpYUyxsqlzLL8YC49zWwascbO', '8dc98a15-dd30-4c79-910f-a3084f3c7091', 'ousmanendiaye352@gmail.com', 3, 28);

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id_cart` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_livraison` date DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `ecole_id` bigint(20) DEFAULT NULL,
  `produit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_cart`),
  KEY `FKce9govmvvdg0p95romgkg8yyd` (`ecole_id`),
  KEY `FKo1ndp90pyq4fbcppi0b42bxn4` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `choix_producteur`
--

DROP TABLE IF EXISTS `choix_producteur`;
CREATE TABLE IF NOT EXISTS `choix_producteur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `etat_paiement` bit(1) NOT NULL,
  `quantite` int(11) DEFAULT NULL,
  `ligne_commande_id` bigint(20) DEFAULT NULL,
  `produit_producteur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xd0voshc0biutpsl16vbwtu4` (`ligne_commande_id`),
  KEY `FKjx0ad4b4ux8oy7g94fk2iicxc` (`produit_producteur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commandes_produits`
--

DROP TABLE IF EXISTS `commandes_produits`;
CREATE TABLE IF NOT EXISTS `commandes_produits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_cmd` date DEFAULT NULL,
  `date_livraison` date DEFAULT NULL,
  `etat_cmd` int(11) DEFAULT NULL,
  `etat_paiement` bit(1) NOT NULL,
  `montant_total` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ecole_id` bigint(20) DEFAULT NULL,
  `produit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmva3u7crt2c3vn1tqkch668it` (`ecole_id`),
  KEY `FKa6s42a475tek1qc9bvg7uve7w` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donations`
--

DROP TABLE IF EXISTS `donations`;
CREATE TABLE IF NOT EXISTS `donations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `montant` double NOT NULL,
  `nom_complet` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd2p196clbvqgbemy05ndspwu` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `donations`
--

INSERT INTO `donations` (`id`, `date`, `description`, `montant`, `nom_complet`, `token`, `user_id`) VALUES
(1, '2019-02-12', 'test', 5000, 'test', 'SiMs5JBS7cxRTG7vYzfa', 3),
(2, '2019-02-12', 'Dons', 200, 'Thiam Aminata ', '0lnAVy3blreFOgpp7txf', 4),
(3, '2019-02-12', 'Ecole', 500, 'Safy', 'dteRj3zrbRBSHjU7YKBO', 6),
(4, '2019-03-22', 'DON', 10000, 'Awa Thiam', 'oWiNMXgTZx8rT7CdXHGd', 5);

-- --------------------------------------------------------

--
-- Structure de la table `paiments`
--

DROP TABLE IF EXISTS `paiments`;
CREATE TABLE IF NOT EXISTS `paiments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_paiement` datetime DEFAULT NULL,
  `montant_verse` double NOT NULL,
  `ligne_cmd_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtkgpawja58y4mpum511s309vr` (`ligne_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `partenaire_ecoles`
--

DROP TABLE IF EXISTS `partenaire_ecoles`;
CREATE TABLE IF NOT EXISTS `partenaire_ecoles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_partenariat` date DEFAULT NULL,
  `ecole_id` bigint(20) NOT NULL,
  `partenaire_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33oquur6wjs2ee6ivxlj9w22d` (`ecole_id`),
  KEY `FK4i6ft82srctc4wk9vd007k2qi` (`partenaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `partenaire_ecoles`
--

INSERT INTO `partenaire_ecoles` (`id`, `date_partenariat`, `ecole_id`, `partenaire_id`) VALUES
(1, '2019-02-12', 7, 8);

-- --------------------------------------------------------

--
-- Structure de la table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE IF NOT EXISTS `password_reset_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `acces_app_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7b9xojbebdnjk1flcadwjrp9p` (`acces_app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `password_reset_token`
--

INSERT INTO `password_reset_token` (`id`, `expiry_date`, `token`, `acces_app_id`) VALUES
(4, '2019-03-11 23:03:15', '2cdbd326-be34-4814-a848-a96a6ba3d3e4', 4),
(5, '2019-04-21 13:36:16', 'a49d049f-535f-4a99-88dd-5aed7a7a46ea', 3),
(6, '2019-04-21 13:38:51', '9788ae7d-2aa3-45c2-bb07-dcafd79d07d8', 3),
(7, '2019-05-02 22:33:24', '0d681f70-2b11-4da9-9aaf-7ead3cdb47d4', 3),
(8, '2019-07-08 13:24:13', '19a9d092-8c36-4cb6-945d-3a8815871cc3', 21),
(9, '2019-07-08 13:26:41', 'eca2a19a-30a0-4662-8fdc-133fc63f6adf', 21);

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_produit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `original_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo_bytes` longblob,
  `prix_unitaire` int(11) DEFAULT NULL,
  `production_nat` int(11) DEFAULT NULL,
  `stock_global` int(11) DEFAULT NULL,
  `unite` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `nom_produit`, `original_name`, `photo_bytes`, `prix_unitaire`, `production_nat`, `stock_global`, `unite`) VALUES
INSERT INTO `produits` (`id`, `nom_produit`, `original_name`, `photo_bytes`, `prix_unitaire`, `production_nat`, `stock_global`, `unite`) VALUES
INSERT INTO `produits` (`id`, `nom_produit`, `original_name`, `photo_bytes`, `prix_unitaire`, `production_nat`, `stock_global`, `unite`) VALUES

-- --------------------------------------------------------

--
-- Structure de la table `produits_produit_producteur`
--

DROP TABLE IF EXISTS `produits_produit_producteur`;
CREATE TABLE IF NOT EXISTS `produits_produit_producteur` (
  `produit_id` bigint(20) NOT NULL,
  `produit_producteur_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qcowwwkuxcw7w2gctri6tqgnu` (`produit_producteur_id`),
  KEY `FKoe1dgw118vt0h3x25fal0ahnh` (`produit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit_producteur`
--

DROP TABLE IF EXISTS `produit_producteur`;
CREATE TABLE IF NOT EXISTS `produit_producteur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_production` date DEFAULT NULL,
  `quantite_stock` int(11) DEFAULT NULL,
  `producteur_id` bigint(20) DEFAULT NULL,
  `produit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKluhmeal9x7q6oyrvuo4npt73q` (`producteur_id`),
  KEY `FKet2l73cr3x17jp685604mxt86` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit_producteur`
--

INSERT INTO `produit_producteur` (`id`, `date_production`, `quantite_stock`, `producteur_id`, `produit_id`) VALUES
(15, '2019-05-30', 2000, 5, 8),
(18, '2019-05-04', 20000, 14, 15);

-- --------------------------------------------------------

--
-- Structure de la table `regions`
--

DROP TABLE IF EXISTS `regions`;
CREATE TABLE IF NOT EXISTS `regions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sigle` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zone_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjpwutes87ivuq616kc4xbt6p` (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `regions`
--

INSERT INTO `regions` (`id`, `nom_region`, `sigle`, `zone_id`) VALUES
(1, 'Dakar', 'DK', 1),
(2, 'Thies', 'TH', 1),
(3, 'ST-Louis', 'STL', 1),
(4, 'Louga', 'LG', 1),
(5, 'Matam', 'MT', 1),
(6, 'Kaolack', 'KL', 1),
(7, 'Kaffrine', 'KF', 1),
(8, 'Kedougou', 'KG', 1),
(9, 'Tambacounda', 'TC', 1),
(10, 'Zinguichor', 'ZG', 1),
(11, 'Sedhiou', 'SD', 1),
(12, 'Diourbel', 'DL', 1),
(13, 'Fatick', 'FK', 1),
(14, 'Kolda', 'KD', 1);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `type`) VALUES
(1, 'ADMIN'),
(2, 'ECOLE'),
(3, 'PRODUCTEUR'),
(4, 'PARTENAIRE');

-- --------------------------------------------------------

--
-- Structure de la table `terrains`
--

DROP TABLE IF EXISTS `terrains`;
CREATE TABLE IF NOT EXISTS `terrains` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `type_production` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `producteur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5nj5wy3hesf39c9fcsc3fwc8y` (`producteur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `type_user` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `identifiant` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `domaine_activite` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `etat` bit(1) DEFAULT NULL,
  `nom_structure` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `latitude_product` double DEFAULT NULL,
  `longitude_product` double DEFAULT NULL,
  `nom_product` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prenom_product` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activite_gest` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `budget_annuel` int(11) DEFAULT NULL,
  `departement` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `latitude_ecole` double DEFAULT NULL,
  `longitude_ecole` double DEFAULT NULL,
  `nb_eleve` int(11) DEFAULT NULL,
  `nom_ecole` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nom_gest` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prenom_gest` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4glh6r3osmm7gjoi5lf5imxx0` (`identifiant`),
  KEY `FK4muym4ujsr1xfh4qc3wsmmrhe` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`type_user`, `id`, `adresse`, `email`, `identifiant`, `tel`, `domaine_activite`, `etat`, `nom_structure`, `latitude_product`, `longitude_product`, `nom_product`, `prenom_product`, `activite_gest`, `budget_annuel`, `departement`, `latitude_ecole`, `longitude_ecole`, `nb_eleve`, `nom_ecole`, `nom_gest`, `prenom_gest`, `region_id`) VALUES
('User', 2, 'Bene tally', 'mouctardiop@gmail.com', NULL, '00221776666877', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('User', 3, 'test', 'test@test', NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('User', 4, 'Villa27/c cite soprim', 'aminatathiam0@gmail.com', NULL, '773337545', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('PROD', 5, 'Ndierba', 'wathiam@gmail.com', 'STL-PA-8027', '783749349', NULL, NULL, NULL, 0, 0, 'Point Focal ', ' Ziguinchor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('User', 6, '8526 sacrée  1', 'thiamsophie@gmail.com', NULL, '775513074', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ECOL', 7, 'Sedhiou', 'awa.thiam@lifantou.com', 'SD-RS-5710', '776445804', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'acheteur', 0, 'Sedhiou', 0, 0, 350, 'Ecole Primaire Seydou Sall', 'Diatta', 'Moustapha', 11),
('PART', 8, 'Dakar', 'contact@ong.com', 'DK-PE-7322', '783749349', 'ONG', b'0', 'ONG Un repas pour tous', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('PROD', 9, 'dakar', 'demba@gmail.com', 'DK-PA-5569', '77345672', NULL, NULL, NULL, 0, 0, 'DIALLO', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('ECOL', 10, 'Niamone', 'malainy.sagna@yahoo.com', 'ZG-RS-4064', '775012892', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'CEM NIAMONE 2', 'Sané', 'Yaya', 10),
('PROD', 11, 'Fimela', 'contact@lifantou.com', 'FK-PA-9271', '775615231', NULL, NULL, NULL, 0, 0, 'Mbodj', 'Ousmane', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
('PROD', 12, 'St Louis ', 'ndaobaba33@gmail.com', 'STL-PA-7859', '783873264', NULL, NULL, NULL, 0, 0, 'Ndaw', 'Baba', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('PART', 13, 'Nord foire', 'diawfatoudiaw@gmail.com', 'DK-PE-1480', '776148081', '', b'0', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('PROD', 14, 'Sacré Cœur 1 Villa 8524', 'ardo.samba@gmail.com', 'STL-PA-4021', '77 502 80 46', NULL, NULL, NULL, 0, 0, 'GIE Famille Abdoul Cire Ba', 'Cire Abdoul Ba', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('ECOL', 15, 'soprim', 'zeyynambengue@gmail.com', 'DK-RS-9845', '777142624', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'keur diarra', 'mbengue', 'zeyna', 1),
('PART', 16, 'loul sessene', 'ousmanembodji75@gmail.com', 'FK-PE-1362', '775615231', 'AGRICULTURE', b'0', 'ANCAR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
('PART', 17, 'Liberte 6', 'diambo97@gmail.com', 'DK-PE-466', '774248702', 'Agro-alimentaire ', b'0', 'We221', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('ECOL', 18, 'GRAND YOFF', 'bouresarr64@gmail.com', 'DK-RS-1771', '779593731', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'UVS', 'SARR', 'BOURE', 1),
('ECOL', 19, 'Hann Mariste II villa R153', 'cmoussacamara11@gmail.com', 'DK-RS-5469', '33 832 56 58 / 77 649 73 10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'Ecole Cheikh Moussa Camara', 'KAMARA', 'Khady Ngom', 1),
('PROD', 20, 'Kénia, lot numéro 308, Ziguinchor SENEGAL', 'aichadjiba@gmail.com', 'ZG-PA-8714', '776482927', NULL, NULL, NULL, 0, 0, 'DJIBA', 'Aïssatou ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10),
('PROD', 21, 'Ziguinchor', 'kandelaye2016@gmail.com', 'KD-PA-7221', '770168625', NULL, NULL, NULL, 0, 0, 'KANDE', 'Abdoulaye', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14),
('PROD', 22, 'Ziguinchor', 'kandelaye2016@gmail.com', 'DK-PA-6325', '770168625', NULL, NULL, NULL, 0, 0, 'KANDE', 'Abdoulaye', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('ECOL', 23, 'Ouakam Dakar ', 'oumarchamaba@gmail.com', 'DK-RS-224', '00221774181434', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'École Keur Massar Unité 4', 'Mr Dieng', '', 1),
('ECOL', 24, '313 Gibraltar ', 'fasegmor@gmail.com', 'DK-RS-6300', '338216276', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, 'Un autre monde ', 'Sonko', 'Amady', 1),
('PART', 25, 'Dakar TOUBA', 'safarkhadim@gmail.com', 'DK-PE-1581', '779854460', 'Agriculture', b'0', 'Safar', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('PROD', 26, 'Villa N 30 zac mbao', 'aziz.fall@live.fr', 'DK-PA-7752', '+221771747487', NULL, NULL, NULL, 0, 0, 'Fall', 'Mame Abdou', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('PROD', 27, 'touba mouride foundiougne', 'bopmp1975@yahoo.fr', 'FK-PA-344', '776558643', NULL, NULL, NULL, 0, 0, 'yédime', 'ndéné bop', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
('PROD', 28, 'Colobane, Dakar', 'ousmanendiaye352@gmail.com', 'DK-PA-7041', '775919686', NULL, NULL, NULL, 0, 0, 'Ndiaye', 'Ousmane', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `zones`
--

DROP TABLE IF EXISTS `zones`;
CREATE TABLE IF NOT EXISTS `zones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `zones`
--

INSERT INTO `zones` (`id`, `libelle`) VALUES
(1, 'Dakar-Thies-StLouis');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `acces_app`
--
ALTER TABLE `acces_app`
  ADD CONSTRAINT `FKhgbnn4ljc42e2vhqkvvtx7oc5` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKo5be2ikeeix002onvttx66x9n` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`);

--
-- Contraintes pour la table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKce9govmvvdg0p95romgkg8yyd` FOREIGN KEY (`ecole_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKo1ndp90pyq4fbcppi0b42bxn4` FOREIGN KEY (`produit_id`) REFERENCES `produits` (`id`);

--
-- Contraintes pour la table `choix_producteur`
--
ALTER TABLE `choix_producteur`
  ADD CONSTRAINT `FK3qq14d81d15l2qcfj0orob8oa` FOREIGN KEY (`id`) REFERENCES `commandes_produits` (`id`),
  ADD CONSTRAINT `FK7xd0voshc0biutpsl16vbwtu4` FOREIGN KEY (`ligne_commande_id`) REFERENCES `commandes_produits` (`id`),
  ADD CONSTRAINT `FKjx0ad4b4ux8oy7g94fk2iicxc` FOREIGN KEY (`produit_producteur_id`) REFERENCES `produit_producteur` (`id`);

--
-- Contraintes pour la table `commandes_produits`
--
ALTER TABLE `commandes_produits`
  ADD CONSTRAINT `FKa6s42a475tek1qc9bvg7uve7w` FOREIGN KEY (`produit_id`) REFERENCES `produits` (`id`),
  ADD CONSTRAINT `FKmva3u7crt2c3vn1tqkch668it` FOREIGN KEY (`ecole_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `donations`
--
ALTER TABLE `donations`
  ADD CONSTRAINT `FKd2p196clbvqgbemy05ndspwu` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `paiments`
--
ALTER TABLE `paiments`
  ADD CONSTRAINT `FKtkgpawja58y4mpum511s309vr` FOREIGN KEY (`ligne_cmd_id`) REFERENCES `commandes_produits` (`id`);

--
-- Contraintes pour la table `partenaire_ecoles`
--
ALTER TABLE `partenaire_ecoles`
  ADD CONSTRAINT `FK33oquur6wjs2ee6ivxlj9w22d` FOREIGN KEY (`ecole_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK4i6ft82srctc4wk9vd007k2qi` FOREIGN KEY (`partenaire_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `FK7b9xojbebdnjk1flcadwjrp9p` FOREIGN KEY (`acces_app_id`) REFERENCES `acces_app` (`id_acces_app`);

--
-- Contraintes pour la table `produits_produit_producteur`
--
ALTER TABLE `produits_produit_producteur`
  ADD CONSTRAINT `FKaljx85slo5st94xa9yo2wmkrd` FOREIGN KEY (`produit_producteur_id`) REFERENCES `produit_producteur` (`id`),
  ADD CONSTRAINT `FKoe1dgw118vt0h3x25fal0ahnh` FOREIGN KEY (`produit_id`) REFERENCES `produits` (`id`);

--
-- Contraintes pour la table `produit_producteur`
--
ALTER TABLE `produit_producteur`
  ADD CONSTRAINT `FKet2l73cr3x17jp685604mxt86` FOREIGN KEY (`produit_id`) REFERENCES `produits` (`id`),
  ADD CONSTRAINT `FKluhmeal9x7q6oyrvuo4npt73q` FOREIGN KEY (`producteur_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `regions`
--
ALTER TABLE `regions`
  ADD CONSTRAINT `FKhjpwutes87ivuq616kc4xbt6p` FOREIGN KEY (`zone_id`) REFERENCES `zones` (`id`);

--
-- Contraintes pour la table `terrains`
--
ALTER TABLE `terrains`
  ADD CONSTRAINT `FK5nj5wy3hesf39c9fcsc3fwc8y` FOREIGN KEY (`producteur_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK4muym4ujsr1xfh4qc3wsmmrhe` FOREIGN KEY (`region_id`) REFERENCES `regions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;