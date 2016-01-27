<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Agenda de Contatos</title>
	<c:set var="raiz" value="${pageContext.request.contextPath}" />
	<c:set var="pessoas"  value="${pessoas}"/>
	<c:if test="${pessoas == null}">
		<meta http-equiv="REFRESH" content="1; URL=controller">
	</c:if>
	
	<script type="text/javascript">
		function criar_contato(){
			window.location.href="${raiz}/controller?acao=incluir"
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="well">
			<h1>Contatos</h1>
        </div>
        <c:set var="mensagem"  value="${mensagem}"/>
        <c:if test="${mensagem != null}">
			<div class="alert alert-success fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  				<strong>Successo!</strong> ${mensagem}
			</div>
		</c:if>
		<c:set var="erro"  value="${erro}"/>
        <c:if test="${erro != null}">
			<div class="alert alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  				<strong>Erro!</strong> ${erro}
			</div>
		</c:if>
        
        <span class="label label-important"></span>
	<p />
	<table id="gride" class="table table-striped" width="780">
		<thead class="grideCabeca">
			<tr>
				<th >Nome</th>
				<th >E-mail</th>
				<th >Telefone</th>
				<th >Nasc</th>
				<th >Estado Civil</th>
				<th >Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="linha" value="0" />
			<c:forEach var="pessoa" items="${pessoas}">
				
			    <c:set var="linha" value="${linha+1}" />
					<tr>
					<td>${pessoa.nome}</td>
					<td>${pessoa.email}</td>
					<td>${pessoa.telefone}</td>
					<td><fmt:formatDate value="${pessoa.dtaNasc}" /></td>
					<td>${pessoa.estadoCivil}</td>
					<td>
						<a href="${raiz}/controller?acao=alterar&id=${pessoa.id}"><img src="${raiz}/img/alterar.png" border="0" width="15" alt="Alterar pessoa"></a>&nbsp;
						<a href="${raiz}/controller?acao=excluir&id=${pessoa.id}"><img src="${raiz}/img/excluir.png" border="0" width="15" alt="Excluir pessoa"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p />
	<button type="button" class="btn btn-success" onclick="criar_contato()">Adicionar Contato</button>
	</div>
</body>
</html>