alter sequence hibernate_sequence start with 5000 increment by 1;

DELETE FROM ARTISTA;

INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (10000, 'Caetano Veloso','M','1988-09-06', 'Caetano Emanuel Viana Teles Veloso é um músico, produtor, arranjador e escritor brasileiro.');
INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (20000, 'Roberto Carlos','M','1941-04-19','Roberto Carlos Braga é um cantor, empresário e compositor brasileiro.');
INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (30000, 'Luan Santana','M', '1991-03-13', NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (40000, 'Gilberto Gil','M', '1942-06-26', NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (50000, 'Ivete Zangalo','F', '1972-03-27', 'Ivete Maria Dias de Sangalo Cady é uma cantora, compositora, instrumentista, atriz, apresentadora, dubladora, produtora, empresária e escritora brasileira');
INSERT INTO ARTISTA (ID, NOME_ARTISTA, SEXO, DATA_NASCIMENTO, BIOGRAFIA) VALUES (60000, 'Fábio Junior','M', '1953-11-21', 'Fábio Júnior, nome artístico de Fábio Corrêa Ayrosa Galvão, é um cantor, compositor e ator brasileiro. Atuou em diversas telenovelas, quase todas da Rede Globo');
