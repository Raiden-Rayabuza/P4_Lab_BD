<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="StyleSheets/index.css" media="screen">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;700&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="StyleSheets/normalize.css" media="screen">
<title>SIGA 2.0</title>
</head>
<body>
<header id="navbar">
<a href="index.jsp"><img src="Imgs/logo_fatec.png" alt="Logo fatec zona leste"></a>
</header>
<main>
	<!-- skills inicio -->
    <section id="skills-section">
      <div class="container">
        <div class="skills-container">
          <img src="Imgs/notas_icon.png" alt="Nota de aluno">
          <h3>Notas</h3>
          <p>Aplicar as notas das avaliações</p>
          <a href="gerenciarNotas.jsp"><button>Gerenciar Notas</button></a>
        </div>
        <div class="skills-container">
          <img src="Imgs/faltas_icon.png" alt="Aluno com presença confirmada">
          <h3>Faltas</h3>
           <p>Aplicar as faltas dos alunos</p>
           <a href="gerenciarFaltas.jsp"><button>Gerenciar Faltas</button></a>
        </div>
      </div>
    </section>
    <!-- skills fim -->
</main>
<footer>
</footer>
</body>
</html>