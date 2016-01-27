<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script language="JavaScript" type="text/javascript" src="${raiz}/js/MascaraValidacao.js"></script>
	<title>Contato</title>
   	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>    
<body>
	<div class="container">
		<div class="well">
			<h1>${(pessoa.id==0)||(param.id==0)||(param.id==null)?'Inclus&atilde;o':'Altera&ccedil;&atilde;o'}	de Contato</h1>
        </div>
		<span class="label label-important">${erro}</span>		
		<form method="post" action="${raiz}/controller" name="form1">
			<input type="hidden" name="id" value="${pessoa.id==null?param.id:pessoa.id}" />
			<div class="form-group">
                 <label>Nome</label>
                 <input name="nome" value="${pessoa.nome==null?param.nome:pessoa.nome}" type="text" class="form-control" />
            </div>
            <div class="form-group">
                 <label>E-mail</label>
                 <input name="email" value="${pessoa.email==null?param.email:pessoa.email}" type="email" id="email" class="form-control" />
            </div>
            <div class="form-group">
                 <label>Telefone</label>
                 <input name="telefone" onKeyPress="MascaraTelefone(form1.telefone);" maxlength="14"  onBlur="ValidaTelefone(form1.telefone);" value="${pessoa.telefone==null?param.telefone:pessoa.telefone}" type="text" class="form-control" />
            </div>
            <div class="form-group">
                 <label>Data de Nascimento (DD/MM/AAAA)</label>
                 <input name="dtaNasc" id="dtaNasc" onKeyPress="MascaraData(form1.dtaNasc);"
 maxlength="10" onBlur= "ValidaDataform1.dtaNasc);" value="${pessoa.fmtDtaNasc==null?param.dtaNasc:pessoa.fmtDtaNasc}" type="text" class="form-control" />
            </div>
            <div class="form-group">
                 <label>Estado Civil</label>
                 <select name="estCivil">
					<c:forEach var="estadoCivil" items="${estadosCivis.valores}">
					<option ${(pessoa.estadoCivil==estadoCivil)||(param.estCivil==estadoCivil)?'selected':''}>${estadoCivil}</option>
					</c:forEach>
				</select>
           </div>
			<input type="submit" class="btn btn-success" name="acao" value="Salvar" />
			<input type="submit" class="btn btn-danger"  name="acao" value="Cancelar" />
	</form>
	</div>
</body>
</html>