-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 07 mars 2023 à 22:55
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `thniti`
--

-- --------------------------------------------------------

--
-- Structure de la table `conversation`
--

CREATE TABLE `conversation` (
  `id` int(11) NOT NULL,
  `idexpediteur` int(11) NOT NULL,
  `idrecipient` int(11) NOT NULL,
  `pseudo1` varchar(50) DEFAULT NULL,
  `pseudo2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `conversation`
--

INSERT INTO `conversation` (`id`, `idexpediteur`, `idrecipient`, `pseudo1`, `pseudo2`) VALUES
(1, 1, 2, 'meriam', 'mohamed');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(10) UNSIGNED NOT NULL,
  `date` datetime DEFAULT NULL,
  `etat` varchar(20) DEFAULT NULL,
  `type_m` varchar(20) NOT NULL,
  `contenu` varchar(600) NOT NULL,
  `idconversation` int(11) DEFAULT NULL,
  `idexpediteur` int(11) NOT NULL,
  `idrecipient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `date`, `etat`, `type_m`, `contenu`, `idconversation`, `idexpediteur`, `idrecipient`) VALUES
(10, '2023-02-21 20:21:12', 'ENVOYE', 'TEXTE', 'yo', 1, 1, 2),
(13, '2023-02-21 22:24:25', 'ENVOYE', 'TEXTE', 'salut', 1, 1, 2),
(16, '2023-02-22 02:06:01', 'ENVOYE', 'TEXTE', 'salut', 1, 1, 2),
(22, '2023-02-22 10:20:20', 'ENVOYE', 'TEXTE', 'allo', 1, 1, 2),
(26, '2023-03-04 15:05:56', 'ENVOYE', 'TEXTE', 'salutvv', 1, 1, 2),
(31, '2023-03-06 23:43:19', 'ENVOYE', 'TEXTE', 'heyB', 1, 1, 2),
(35, '2023-03-07 20:48:28', 'ENVOYE', 'TEXTE', 'send message', 1, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `messagerie`
--

CREATE TABLE `messagerie` (
  `id_M` int(11) NOT NULL,
  `contenuM` varchar(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `messagerie`
--

INSERT INTO `messagerie` (`id_M`, `contenuM`, `etat`, `user`) VALUES
(7, 'test', 'msg lu', 1),
(8, 'mljkj', 'msg non lu', 1),
(9, 'nn', 'msg non u', 1),
(10, 'jh', 'msg lu', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_R` int(11) NOT NULL,
  `TypeR` varchar(255) CHARACTER SET latin1 NOT NULL,
  `DescriptionR` varchar(255) CHARACTER SET latin1 NOT NULL,
  `Objet` varchar(255) CHARACTER SET latin1 NOT NULL,
  `DateR` date NOT NULL,
  `etat` varchar(255) CHARACTER SET latin1 NOT NULL,
  `id_u` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_R`, `TypeR`, `DescriptionR`, `Objet`, `DateR`, `etat`, `id_u`) VALUES
(69, 'retard', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhh', 'reclamation', '2023-03-02', 'traité', 1),
(70, 'rec', 'mm', 'mm', '2023-03-02', 'non traité', 1),
(71, 'tttttttt', 'tt', 'tttttttt', '2023-03-02', 'non traité', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nom_u` varchar(255) NOT NULL,
  `prenom_u` varchar(255) NOT NULL,
  `email_u` varchar(255) NOT NULL,
  `tel_u` varchar(255) NOT NULL,
  `role_u` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `nom_u`, `prenom_u`, `email_u`, `tel_u`, `role_u`) VALUES
(1, 'fff', 'ff', 'odij', '444', 'ff'),
(2, '11111111', 'jjjjjjjj', '1452378', 'malek.benabbes1@esprit.tn', 'jjjjjjj');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `pseudo` varchar(30) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `dateNaissance` date DEFAULT NULL,
  `sexe` varchar(10) NOT NULL,
  `numTel` bigint(15) DEFAULT NULL,
  `etat` varchar(30) NOT NULL,
  `URLPhotoProfil` varchar(250) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `email`, `nom`, `prenom`, `pseudo`, `mdp`, `role`, `dateNaissance`, `sexe`, `numTel`, `etat`, `URLPhotoProfil`, `salt`, `token`) VALUES
(1, '', 'meriam', 'bensaid', 'meriam', 'aabbcc', '', '1999-02-13', 'm', NULL, '', NULL, NULL, NULL),
(2, '', 'hamouda', 'benromdhana', 'hromdhana', 'aabbcc', '', '1999-02-13', 'm', NULL, '', NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idexpediteur` (`idexpediteur`),
  ADD KEY `idrecipient` (`idrecipient`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idconversation` (`idconversation`),
  ADD KEY `fk_idexpediteur` (`idexpediteur`),
  ADD KEY `fk_idrecipient` (`idrecipient`);

--
-- Index pour la table `messagerie`
--
ALTER TABLE `messagerie`
  ADD PRIMARY KEY (`id_M`),
  ADD KEY `user` (`user`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_R`),
  ADD KEY `id` (`id_u`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `messagerie`
--
ALTER TABLE `messagerie`
  MODIFY `id_M` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_R` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `messagerie`
--
ALTER TABLE `messagerie`
  ADD CONSTRAINT `messagerie_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_u`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
