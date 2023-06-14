CREATE DATABASE Platina64Reviews;

USE Platina64Reviews;

CREATE TABLE Conta (
idconta BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
username VARCHAR (50) NOT NULL UNIQUE,
email VARCHAR(100) NOT NULL UNIQUE,
senha VARCHAR(25) NOT NULL,
PRIMARY KEY (idconta)
);

CREATE TABLE Jogo (
idjogo BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR (100) NOT NULL, 
descricao VARCHAR(510) NOT NULL, 
dataLancamento DATE NOT NULL, 
desenvolvedor VARCHAR(50) NOT NULL, 
genero VARCHAR (25) NOT NULL,
conquistas INT NOT NULL,
PRIMARY KEY (idjogo)
);

CREATE TABLE Review (
idreview BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nota SMALLINT NOT NULL,
analise VARCHAR(510),
idjogo BIGINT NOT NULL,
idconta BIGINT NOT NULL,
PRIMARY KEY (idreview),
FOREIGN KEY (idjogo) REFERENCES Jogo (idjogo),
FOREIGN KEY (idconta) REFERENCES Conta (idconta)
);

SELECT * FROM Jogo; SELECT * FROM Conta; SELECT * FROM Review;

INSERT INTO Jogo (idjogo, nome, descricao, dataLancamento, desenvolvedor, genero, conquistas)
VALUES (null, 'Dark Souls: Prepare to Die Edition', 'Dark Souls will be the most deeply challenging game you play this year. 
Can you live through a million deaths and earn your legacy?', '2012-08-24', 'FromSoftware', 'Action RPG', 41);

INSERT INTO Jogo (idjogo, nome, descricao, dataLancamento, desenvolvedor, genero, conquistas)
VALUES (null, 'Hollow Knight', 'Forge your own path in Hollow Knight! 
An epic action adventure through a vast ruined kingdom of insects and heroes. 
Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.', 
'2017-02-24', 'Team Cherry', 'Metroidvania Platformer', 63);

INSERT INTO Jogo (idjogo, nome, descricao, dataLancamento, desenvolvedor, genero, conquistas)
VALUES (null, 'Stardew Valley', 'You have inherited the old farm plot of your grandfather in Stardew Valley. 
Armed with hand-me-down tools and a few coins, you set out to begin your new life. 
Can you learn to live off the land and turn these overgrown fields into a thriving home?', 
'2016-02-26', 'ConcernedApe', 'Farming Simulator RPG', 40);

INSERT INTO Jogo (idjogo, nome, descricao, dataLancamento, desenvolvedor, genero, conquistas)
VALUES (null, 'The Elder Scrolls V: Skyrim', 'The Empire of Tamriel is on the edge. The High King of Skyrim has been murdered. Alliances form as claims to the throne are made. 
In the midst of this conflict, a far more dangerous, ancient evil is awakened. Dragons, long lost to the passages of the Elder Scrolls, have returned to Tamriel. 
The future of Skyrim, even the Empire itself, hangs in the balance as they wait for the prophesized Dragonborn to come; 
a hero born with the power of The Voice, and the only one who can stand amongst the dragons.', 
'2011-11-10', 'Bethesda Game Studios', 'Action RPG', 75);

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'PlayerTauzYoutube', 'playertauz@gmail.com', 'RapDoSonic');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'PedroPaintball', 'pedrozandona@gmail.com', 'SextaTeveRPG');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'ToruTacaFogo', 'torugaiadewano@gmail.com', 'Paçoca8');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'MickeyMouseGamer', 'disneyredragon@gmail.com', 'PlutoNaoPlanetario');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'CésinhaMaromba', 'cesarsegredos@gmail.com', 'GraçasADeus');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'NandaMoon', 'amandamedalista@gmail.com', 'PoesiaDeLiderança');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'ApollyonGayNerd', 'apollyonthedelta@gmail.com', 'FalandoIngles');

INSERT INTO Conta (idconta, username, email, senha) 
VALUES (null, 'PaolinhaSushi', 'poalaart@gmail.com', '2KMporHr');