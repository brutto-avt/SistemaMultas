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
  `cnh_numero` int(11) NOT NULL,
  `cnh_categoria` char(2) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `logradouro` varchar(200) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `complemento` varchar(80) NOT NULL,
  `uf` char(2) NOT NULL,
  `cidade` varchar(200) NOT NULL,
  `bairro` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.condutor: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `condutor` DISABLE KEYS */;
INSERT INTO `condutor` (`id`, `nome`, `nascimento`, `cnh_numero`, `cnh_categoria`, `cpf`, `cep`, `logradouro`, `numero`, `complemento`, `uf`, `cidade`, `bairro`) VALUES
	(1, 'Administrador', '2015-10-17', 0, '', '012.345.678-90', '99999-999', 'Sem Logradouro', '999', 'NA', 'PR', 'Ponta Grossa', 'Centro'),
	(2, 'Andre', '1988-12-05', 12312312, 'B', '066.274.679-17', '84010-300', 'R. Teste', '123', 'Ap 1', 'PR', 'Ponta Grossa', 'Centro'),
	(4, 'Teste', '1997-10-23', 123, 'B', '074.768.189-97', '85424-123', 'Ruad ash', '123', '2', 'PR', 'Ponta Grossa', 'centro');
/*!40000 ALTER TABLE `condutor` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.funcao
CREATE TABLE IF NOT EXISTS `funcao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `menu` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.funcao: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` (`id`, `nome`, `menu`) VALUES
	(1, 'Cadastro de veículos', 'jmiVeiculos'),
	(2, 'Cadastro de condutores', 'jmiCondutores'),
	(3, 'Cadastro de infrações', 'jmiInfracoes'),
	(4, 'Cadastro de taxas', 'jmiTaxas'),
	(5, 'Cadastro de usuários', 'jmiUsuarios'),
	(6, 'Meus dados', 'jmiConsultaCondutor'),
	(7, 'Meus veículos', 'jmiConsultaVeiculos'),
	(8, 'Minhas multas', 'jmiConsultaAutuacoes'),
	(9, 'Consulta detalhada', 'jmiConsultaDetalhada'),
	(10, 'Gestão de autuações', 'jmiGestaoAutuacoes'),
	(11, 'Transferir condutor', 'jmiTransferirCondutor');
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.infracao
CREATE TABLE IF NOT EXISTS `infracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artigo` varchar(20) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `gravidade` char(1) NOT NULL,
  `pontuacao` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.infracao: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `infracao` DISABLE KEYS */;
INSERT INTO `infracao` (`id`, `artigo`, `descricao`, `gravidade`, `pontuacao`, `valor`) VALUES
	(1, 'ART 162, I', 'Dirigir veículo s/ possuir CNH ou Permissão para Dirigir', 'S', 7, 574.62);
/*!40000 ALTER TABLE `infracao` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.multa
CREATE TABLE IF NOT EXISTS `multa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condutor_id` int(11) NOT NULL,
  `veiculo_id` int(11) DEFAULT NULL,
  `data_autuacao` datetime NOT NULL,
  `local_autuacao` varchar(200) NOT NULL,
  `data_vencimento` date NOT NULL,
  `data_pagamento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `multa_condutor_fk` (`condutor_id`),
  KEY `multa_veiculo_fk` (`veiculo_id`),
  CONSTRAINT `multa_condutor_fk` FOREIGN KEY (`condutor_id`) REFERENCES `condutor` (`id`),
  CONSTRAINT `multa_veiculo_fk` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.multa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` (`id`, `condutor_id`, `veiculo_id`, `data_autuacao`, `local_autuacao`, `data_vencimento`, `data_pagamento`) VALUES
	(1, 1, 1, '2014-11-10 23:44:49', 'Curitiba', '2015-10-09', NULL);
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.multa_infracao
CREATE TABLE IF NOT EXISTS `multa_infracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `multa_id` int(11) DEFAULT NULL,
  `infracao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `multa_infracao_multa_fk` (`multa_id`),
  KEY `multa_infracao_infracao_fk` (`infracao_id`),
  CONSTRAINT `multa_infracao_infracao_fk` FOREIGN KEY (`infracao_id`) REFERENCES `infracao` (`id`),
  CONSTRAINT `multa_infracao_multa_fk` FOREIGN KEY (`multa_id`) REFERENCES `multa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.multa_infracao: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `multa_infracao` DISABLE KEYS */;
INSERT INTO `multa_infracao` (`id`, `multa_id`, `infracao_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `multa_infracao` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.multa_taxa
CREATE TABLE IF NOT EXISTS `multa_taxa` (
  `id` int(11) NOT NULL,
  `multa_id` int(11) NOT NULL,
  `taxa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `multa_taxa_multa_fk` (`multa_id`),
  KEY `multa_taxa_taxa_fk` (`taxa_id`),
  CONSTRAINT `multa_taxa_multa_fk` FOREIGN KEY (`multa_id`) REFERENCES `multa` (`id`),
  CONSTRAINT `multa_taxa_taxa_fk` FOREIGN KEY (`taxa_id`) REFERENCES `taxa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.multa_taxa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `multa_taxa` DISABLE KEYS */;
INSERT INTO `multa_taxa` (`id`, `multa_id`, `taxa_id`) VALUES
	(0, 1, 1);
/*!40000 ALTER TABLE `multa_taxa` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.taxa
CREATE TABLE IF NOT EXISTS `taxa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  `valor` double NOT NULL,
  `tipo_valor` char(1) NOT NULL,
  `periodo` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.taxa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `taxa` DISABLE KEYS */;
INSERT INTO `taxa` (`id`, `descricao`, `valor`, `tipo_valor`, `periodo`) VALUES
	(1, 'Multa por atraso', 2, 'P', 'D');
/*!40000 ALTER TABLE `taxa` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condutor_id` int(11) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `tipo` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_condutor_fk` (`condutor_id`),
  CONSTRAINT `usuario_condutor_fk` FOREIGN KEY (`condutor_id`) REFERENCES `condutor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.usuario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `condutor_id`, `senha`, `tipo`) VALUES
	(1, 1, '21232f297a57a5a743894a0e4a801fc3', 'A'),
	(2, 2, 'c4ca4238a0b923820dcc509a6f75849b', 'N'),
	(3, 4, '202cb962ac59075b964b07152d234b70', 'N');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Copiando estrutura para tabela sistema_multas.usuario_funcao
CREATE TABLE IF NOT EXISTS `usuario_funcao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `funcao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_funcao_usuario_fk` (`usuario_id`),
  KEY `usuario_funcao_funcao_fk` (`funcao_id`),
  CONSTRAINT `usuario_funcao_funcao_fk` FOREIGN KEY (`funcao_id`) REFERENCES `funcao` (`id`),
  CONSTRAINT `usuario_funcao_usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.usuario_funcao: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario_funcao` DISABLE KEYS */;
INSERT INTO `usuario_funcao` (`id`, `usuario_id`, `funcao_id`) VALUES
	(1, 2, 2),
	(2, 2, 4),
	(3, 3, 4),
	(5, 3, 2);
/*!40000 ALTER TABLE `usuario_funcao` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistema_multas.veiculo: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` (`id`, `proprietario_id`, `placa`, `marca`, `modelo`, `ano`, `cor`) VALUES
	(1, 1, 'ABC-1234', 'Volkswagen', 'Saveiro 1.6', 2015, 'Vermelho');
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
