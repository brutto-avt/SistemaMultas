-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.32 - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para sistema_multas
CREATE DATABASE IF NOT EXISTS `sistema_multas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sistema_multas`;


-- Copiando estrutura para tabela sistema_multas.condutor
CREATE TABLE IF NOT EXISTS `condutor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `nascimento` date NOT NULL,
  `cnh_numero` varchar(20) NOT NULL,
  `cnh_categoria` char(2) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.endereco
CREATE TABLE IF NOT EXISTS `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condutor_id` int(11) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `logradouro` varchar(200) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `complemento` varchar(80) NOT NULL,
  `uf` char(2) NOT NULL,
  `cidade` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `endereco_condutor_fk` (`condutor_id`),
  CONSTRAINT `endereco_condutor_fk` FOREIGN KEY (`condutor_id`) REFERENCES `condutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.funcao
CREATE TABLE IF NOT EXISTS `funcao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `menu` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.infracao
CREATE TABLE IF NOT EXISTS `infracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artigo` varchar(20) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `gravidade` varchar(20) NOT NULL,
  `pontuacao` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.multa
CREATE TABLE IF NOT EXISTS `multa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condutor_id` int(11) NOT NULL,
  `protocolo` varchar(12) NOT NULL,
  `data_autuacao` datetime NOT NULL,
  `local_autuacao` varchar(200) NOT NULL,
  `data_vencimento` date NOT NULL,
  `data_pagamento` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `multa_condutor_fk` (`condutor_id`),
  CONSTRAINT `multa_condutor_fk` FOREIGN KEY (`condutor_id`) REFERENCES `condutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.multa_infracao
CREATE TABLE IF NOT EXISTS `multa_infracao` (
  `multa_id` int(11) NOT NULL,
  `infracao_id` int(11) NOT NULL,
  PRIMARY KEY (`multa_id`,`infracao_id`),
  KEY `multa_infracao_infracao_fk` (`infracao_id`),
  CONSTRAINT `multa_infracao_multa_fk` FOREIGN KEY (`multa_id`) REFERENCES `multa` (`id`),
  CONSTRAINT `multa_infracao_infracao_fk` FOREIGN KEY (`infracao_id`) REFERENCES `infracao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.multa_taxa
CREATE TABLE IF NOT EXISTS `multa_taxa` (
  `multa_id` int(11) NOT NULL,
  `taxa_id` int(11) NOT NULL,
  PRIMARY KEY (`multa_id`,`taxa_id`),
  KEY `multa_taxa_taxa_fk` (`taxa_id`),
  CONSTRAINT `multa_taxa_multa_fk` FOREIGN KEY (`multa_id`) REFERENCES `multa` (`id`),
  CONSTRAINT `multa_taxa_taxa_fk` FOREIGN KEY (`taxa_id`) REFERENCES `taxa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.taxa
CREATE TABLE IF NOT EXISTS `taxa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  `valor` double NOT NULL,
  `tipo_valor` char(1) NOT NULL,
  `periodo` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condutor_id` int(11) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `tipo` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_condutor_fk` (`condutor_id`),
  CONSTRAINT `usuario_condutor_fk` FOREIGN KEY (`condutor_id`) REFERENCES `condutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.usuario_funcao
CREATE TABLE IF NOT EXISTS `usuario_funcao` (
  `usuario_id` int(11) NOT NULL,
  `funcao_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`funcao_id`),
  KEY `usuario_funcao_funcao_fk` (`funcao_id`),
  CONSTRAINT `usuario_funcao_usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `usuario_funcao_funcao_fk` FOREIGN KEY (`funcao_id`) REFERENCES `funcao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela sistema_multas.veiculo
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proprietario_id` int(11) NOT NULL,
  `placa` char(8) NOT NULL,
  `marca` varchar(80) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `ano` int(11) NOT NULL,
  `cor` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `veiculo_proprietario_fk` (`proprietario_id`),
  CONSTRAINT `veiculo_proprietario_fk` FOREIGN KEY (`proprietario_id`) REFERENCES `condutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
