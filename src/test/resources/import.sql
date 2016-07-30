



-- Insert Generos musicais
insert into genero_musical ( id, denominacao )  values  (1,'Symphonic Power Metal');
insert into genero_musical ( id, denominacao )  values  (2,'Gothic Metal');
insert into genero_musical ( id, denominacao )  values  (3,'Melodic Death');
insert into genero_musical ( id, denominacao )  values  (4,'Power Metal');
insert into genero_musical ( id, denominacao )  values  (5,'Heavy');
insert into genero_musical ( id, denominacao )  values  (6,'MPB');
insert into genero_musical ( id, denominacao )  values  (7,'Rock');
insert into genero_musical ( id, denominacao )  values  (8,'Death Metal');


-- insert gravadoras
insert into gravadora ( id, nome ) values (1,'Independete');
insert into gravadora ( id, nome ) values (2,'EMI');
insert into gravadora ( id, nome ) values (3,'Warner');
insert into gravadora ( id, nome ) values (4,'BMG');
insert into gravadora ( id, nome ) values (5,'Spinefarm Records');
insert into gravadora ( id, nome ) values (6,'Akeloo/Avispa');
insert into gravadora ( id, nome ) values (7,'Nuclear Blast');
insert into gravadora ( id, nome ) values (8,'Universal');

-- Insert Bandas

insert into grupo_musical (id , denominacao, ano_inicio, ano_fim ) values (1, 'Nightwish', 1996, null );
insert into grupo_musical (id , denominacao, ano_inicio, ano_fim ) values (2, 'Avantasia', 2000, null );
insert into grupo_musical (id , denominacao, ano_inicio, ano_fim ) values (3, 'Lunatica', 1998, null );
insert into grupo_musical (id , denominacao, ano_inicio, ano_fim ) values (4, 'Abatu', 2004, 2007 );
insert into grupo_musical (id , denominacao, ano_inicio, ano_fim ) values (5, 'Russell Allens Atomic Soul', 2004, null );



-- insert generos

insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (1, 1 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (4, 1 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (8, 4 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (1, 2 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (1, 3 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (2, 3 );
insert into grupo_genero_musical (id_genero_musical, id_grupo_musical ) values (5, 5 );




INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (1, 'Caetano Veloso', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (2, 'Roberto Carlos', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (3, 'Luan Santana', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (4, 'Gilberto Gil', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (5, 'Ivete Zangalo', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (6, 'Fábio Junior', NOW(), NULL);

-- artistas nightwish
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (7, 'Tarja Turunen', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (8, 'Emppu Vourinen', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (9, 'Tuomas Holopainen', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (10, 'Jukka Nevalainen', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (11, 'Marco Hietala', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (12, 'Floor Jansen', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (17, 'Anette Olzon', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (18, 'Andrea Dätwyler', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (19,  'Emilio MG Barrantes', NOW(), NULL);
INSERT INTO ARTISTA (ID, NOME_ARTISTA, DATA_CADASTRO, ULTIMA_ATUALIZACAO) VALUES (20, 'Ronny Wolf', NOW(), NULL);



---


insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (1, 1,'M','1988-09-06', 'Caetano Emanuel Viana Teles Veloso é um músico, produtor, arranjador e escritor brasileiro.');
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (2, 2 ,'M','1941-04-19','Roberto Carlos Braga é um cantor, empresário e compositor brasileiro.');
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (3, 3,'M', '1991-03-13', NULL);
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (4, 4,'M', '1942-06-26', NULL);
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (5, 5,'F', '1972-03-27', 'Ivete Maria Dias de Sangalo Cady é uma cantora, compositora, instrumentista, atriz, apresentadora, dubladora, produtora, empresária e escritora brasileira');
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (6, 6,'M', '1953-11-21', 'Fábio Júnior, nome artístico de Fábio Corrêa Ayrosa Galvão, é um cantor, compositor e ator brasileiro. Atuou em diversas telenovelas, quase todas da Rede Globo');

-- artistas nightwish
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (7,7 ,'F', '1977-08-17', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (8,8 ,'M', '1977-08-17', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (9, 9,'M', '1966-01-14', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (10,10,'M', '1978-04-21', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (11, 11,'M', '1977-08-17', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (12, 12,'F', '1981-02-21', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (17, 17,'F', '1971-06-21', null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (18,18 ,'F', null, null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (19, 19, 'M', null, null );
insert into ArtistaDetalhe (id, id_artista, sexo, DATA_NASCIMENTO, biografia) VALUES (20, 20, 'M', '1971-06-21', null);


update ArtistaDetalhe set version = 0;

insert into atuacao( id, denominacao ) values (1, 'Bass');
insert into atuacao( id, denominacao ) values (2, 'Keyboards');
insert into atuacao( id, denominacao ) values (3, 'Drums');
insert into atuacao( id, denominacao ) values (4, 'Vocals');
insert into atuacao( id, denominacao ) values (5, 'Guitars');



-- atuações

-- niggtwish
insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (1,9,1, 2, '1996-01-01', null);
insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (2,9,1, 4, '1996-01-01', '2001-01-01');

insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (3,8,1, 1, '1996-01-01', '1997-01-01');
insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (4,8,5, 1, '1996-01-01', null);

insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (5,7,1, 4, '1996-01-01', '2005-01-01');

insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (6,8,1, 4, '2007-01-01', '2012-01-01');

insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (7,18,3, 4, '2013-01-01', null);
insert into artista_atuacao( id , id_artista, id_banda, id_atuacao, inicio, fim ) values (8,19,3, 4, '2001-01-01', null);

-- CD

insert into album (id, ano, titulo, id_gravadora, id_grupo_musical ) values (1, 2004, 'Fall of Divinity', 1, 4);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (2, 2005, 'Shub-Niggurath', 1, 4);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (3, 2005, 'Cast into Oblivion', 1, 4);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (4, 2006, 'Ritual Brutality', 1, 4);

insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (5, 2000, 'The Metal Opera', 2, 2);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (6, 2002, 'The Metal Opera Pt. II', 2, 2);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (7, 2007, 'Lost In Space', 3, 2);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (8, 2010, 'Angel of Babylon', 3, 2);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (9, 2016, 'Ghostlights', 3, 2);

insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (10, 2006, 'The Edge of Infinity', 4, 3);
insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (11, 2009, 'New Shores', 4, 3);


insert into album (id, ano, titulo, id_gravadora, id_grupo_musical  ) values (12, 2005, 'Russell Allens Atomic Soul', 4, 5);


-- Musicas
 insert into musica (id, numero, titulo, duracao, id_album ) values ( 1, 1, 'Introduction' , 2, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 2, 2, 'The Edge of Infinity' , 4, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 3, 3, 'Sons of the Wind' , 5, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 4, 4, 'Who You Are' , 4, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 5, 5, 'Out!', 3, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 6, 6, 'Song for You' , 5, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 7, 7, 'Together', 3 ,10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 8, 8, 'The Power of Love', 5 , 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 9, 9, 'Words Unleashed' , 7, 10);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 10, 10, 'EmOcean' , 5, 10);


insert into musica (id, numero, titulo, duracao, id_album ) values ( 11, 1, 'Two Dreamers' , 4, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 12, 2, 'How Did It Come to This', 5 , 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 13, 3, 'The Incredibles', 3, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 14, 4, 'My Hardest Walk' , 3, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 15, 5, 'Farewell My Love' ,3, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 16, 6, 'The Chosen Ones', 3,11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 17, 7, 'Heart of a Lion' , 4, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 18, 8, 'Into the Dissoncance' ,6, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 19, 9, 'Winds of Heaven' , 8, 11);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 20, 10, 'The Day the Falcon Dies' , 5, 11);


insert into musica (id, numero, titulo, duracao, id_album ) values ( 21, 1, 'Blackout' 	,04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 22, 2, 'Unjustified', 	03 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 23, 3, 'Voodoo Hand' ,	03 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 24, 4, 'Angel', 	05 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 25, 5, 'The Distance', 	04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 26, 6, 'Seasons of Insanity', 	04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 27, 7, 'Gaia', 	04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 28, 8, 'Loosin You', 	04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 29, 9, 'Saucey Jack', 	04 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 30, 10, 'We Will Fly', 	07 , 12);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 31, 11, 'Atomic Soul', 	03 , 12);

-- avantasia
insert into musica (id, numero, titulo, duracao, id_album ) values ( 32, 1, 'Prelude', 1, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 33, 2,  'Reach Out for the Light', 1, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 34, 3,  'Serpents in Paradise', 3, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 35, 4, 'Malleus Maleficarum', 4, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 36, 5,  'Breaking Away', 5, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 37, 6,  'Farewell', 6, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 38, 7,  'The Glory of Rome', 4, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 39, 8,  'In Nomine Patris', 7, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 40, 10,  'Avantasia', 4, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 41, 11, 'A New Dimension', 9, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 42, 12,  'Inside', 3, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 43, 13,  'Sign of the Cross', 5, 5);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 44, 14,  'The Tower', 12, 5);

insert into musica (id, numero, titulo, duracao, id_album ) values ( 45, 1, 'Lost in Space (radio version)' , 5, 7);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 46, 2, 'Lost in Space (radio version edit)' , 5, 7);
insert into musica (id, numero, titulo, duracao, id_album ) values ( 47, 3, 'Lost in Space (album version)' , 5, 7);


-- Review
insert into review (id, tipo_review, autor, data, descricao, pontuacao, id_album, id_grupo_musical) values (1, 1, 'lucas', '2016-06-08', 'Review de uma Banda NightWish', NULL, NULL, 1);

insert into review (id, tipo_review, autor, data, descricao, pontuacao, id_album, id_grupo_musical) values (2, 2, 'rafael', '2016-02-08', 'Review de um CD  Lunatica', 86, 10, NULL);

ALTER SEQUENCE atuacao_seq RESTART WITH 1000;
ALTER SEQUENCE musica_seq RESTART WITH 1000;
ALTER SEQUENCE artista_seq RESTART WITH 1000;
ALTER SEQUENCE genero_seq RESTART WITH 1000;
ALTER SEQUENCE grupo_musical_seq RESTART WITH 1000;
ALTER SEQUENCE gravadora_seq RESTART WITH 1000;
ALTER SEQUENCE musica_seq RESTART WITH 1000;
ALTER SEQUENCE artista_atuacao_seq RESTART WITH 1000;

ALTER SEQUENCE review_seq RESTART WITH 1000;

ALTER SEQUENCE artista_detalhe_seq RESTART WITH 1000;
