CREATE DATABASE Fatec
GO
USE Fatec

CREATE TABLE disciplina(
cod_disciplina			CHAR(8)			NOT NULL,
nome					VARCHAR(50)		NOT NULL,
sigla					CHAR(7)			NOT NULL,
turno					CHAR(5)			NOT NULL,
num_aulas				INT				NOT NULL,
PRIMARY KEY(cod_disciplina),
);

CREATE TABLE avaliacao(
cod_avaliacao	INT		IDENTITY(1,1)	NOT NULL,
tipo			VARCHAR(20)				NOT NULL,
PRIMARY KEY(cod_avaliacao),
);

CREATE TABLE aluno(
ra		CHAR(13)		NOT NULL,
nome	VARCHAR(50)		NOT NULL,
PRIMARY KEY(ra),
);
SELECT * FROM notas

CREATE TABLE falta(
ra				CHAR(13)	NOT NULL,
cod_disciplina	CHAR(8)		NOT NULL,
data_falta		CHAR(10)		NOT NULL,
presenca		CHAR(4)			NOT NULL,
PRIMARY KEY(ra,cod_disciplina,data_falta),
FOREIGN KEY(cod_disciplina) REFERENCES disciplina(cod_disciplina),
FOREIGN KEY(ra) REFERENCES aluno(ra),
);

CREATE TABLE notas(
ra				CHAR(13)	NOT NULL,
cod_disciplina	CHAR(8)		NOT NULL,
cod_avaliacao	INT			NOT NULL,
nota			INT			NOT NULL,
PRIMARY KEY(ra,cod_disciplina,cod_avaliacao),
FOREIGN KEY(cod_disciplina) REFERENCES disciplina(cod_disciplina),
FOREIGN KEY(ra) REFERENCES aluno(ra),
FOREIGN KEY(cod_avaliacao) REFERENCES avaliacao(cod_avaliacao),
);
SELECT * FROM avaliacao
SELECT al.ra, al.nome, n1.nota  FROM notas n1 INNER JOIN aluno al ON n1.ra = al.ra INNER JOIN disciplina dis ON n1.cod_disciplina = dis.cod_disciplina
--UDF para as notas da turma
CREATE FUNCTION udf_notas_alunos()
RETURNS @tabela TABLE(
	ra	CHAR(13),
	nome_aluno VARCHAR(50),
	nome_disciplina VARCHAR(50),
	tipo_avaliacao VARCHAR(20),
	nota INT
)
AS
BEGIN
	DECLARE @ra_aluno CHAR(13)
	DECLARE @cod_disciplina CHAR(8)
	DECLARE @nome_disciplina VARCHAR(50)
	DECLARE @cod_avaliacao INT
	DECLARE @tipo_avaliacao VARCHAR(20)
	DECLARE @nome_aluno VARCHAR(13)
	DECLARE @nota INT

	DECLARE cursor_notas CURSOR FOR SELECT ra, cod_disciplina, cod_avaliacao FROM notas
	OPEN cursor_notas
	
	FETCH NEXT FROM cursor_notas INTO @ra_aluno, @cod_disciplina, @cod_avaliacao
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			SET @nome_aluno = (SELECT nome FROM aluno WHERE ra = @ra_aluno)
			SET @tipo_avaliacao = (SELECT tipo FROM avaliacao WHERE cod_avaliacao = @cod_avaliacao)
			SET @nome_disciplina = (SELECT nome FROM disciplina WHERE cod_disciplina = @cod_disciplina)
			SET @nota = (SELECT nota FROM notas WHERE ra = @ra_aluno AND cod_avaliacao = @cod_avaliacao AND cod_disciplina = @cod_disciplina)
			INSERT INTO @tabela (ra,nome_aluno,nome_disciplina,tipo_avaliacao,nota) VALUES(@ra_aluno,@nome_aluno,@nome_disciplina,@tipo_avaliacao,@nota)
			FETCH NEXT FROM cursor_notas INTO @ra_aluno, @cod_disciplina, @cod_avaliacao
		END
	CLOSE cursor_notas
	DEALLOCATE cursor_notas
	
	RETURN
END
SELECT al.ra, al.nome AS nome_avaliacao, dis.nome AS nome_disciplina, av.tipo, n.nota FROM aluno al, disciplina dis, avaliacao av, notas n
INSERT INTO notas(ra,cod_disciplina,cod_avaliacao,nota) VALUES('1110481812005','5005-220',5,8)
SELECT * FROM udf_notas_alunos()
--UDF para faltas da turma
CREATE FUNCTION udf_faltas_alunos()
RETURNS @tabela TABLE(
	ra	CHAR(13),
	nome_aluno	VARCHAR(50),
	nome_disciplina VARCHAR(50),
	data_falta CHAR (10),
	falta CHAR(4)
)
AS
BEGIN
	DECLARE @ra_aluno CHAR(13)
	DECLARE @nome_aluno VARCHAR(50)
	DECLARE @cod_disciplina CHAR(8)
	DECLARE @nome_disciplina VARCHAR(50)
	DECLARE @data_falta CHAR(10)
	DECLARE @falta CHAR(4)

	DECLARE cursor_faltas CURSOR FOR SELECT ra, cod_disciplina, data_falta FROM falta
	OPEN cursor_faltas
	
	FETCH NEXT FROM cursor_faltas INTO @ra_aluno, @cod_disciplina, @data_falta
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			INSERT INTO @tabela (ra,nome_aluno,nome_disciplina,data_falta,falta) SELECT al.ra, al.nome, dis.nome, fa.data_falta, fa.presenca 
			FROM falta fa INNER JOIN aluno al ON fa.ra = al.ra INNER JOIN disciplina dis ON fa.cod_disciplina = dis.cod_disciplina
			WHERE fa.ra = @ra_aluno
			FETCH NEXT FROM cursor_faltas INTO @ra_aluno, @cod_disciplina, @data_falta
		END
	CLOSE cursor_faltas
	DEALLOCATE cursor_faltas
	
	RETURN
END

SELECT ra, nome_aluno, nome_disciplina, data_falta, falta FROM udf_faltas_alunos()