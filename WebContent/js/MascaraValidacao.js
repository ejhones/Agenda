// JavaScript Document
//adiciona mascara de data
function MascaraData(dtaNasc){
        if(mascaraInteiro(dtaNasc)==false){
                event.returnValue = false;
        }
        return formataCampo(dtaNasc, '00/00/0000', event);
}

//adiciona mascara ao telefone
function MascaraTelefone(telefone){
        if(mascaraInteiro(telefone)==false){
                event.returnValue = false;
        }
        return formataCampo(telefone, '(00) 0000-0000', event);
}

//valida telefone
function ValidaTelefone(telefone){
        exp = /\(\d{2}\)\ \d{4}\-\d{4}/
        if(!exp.test(telefone.value))
                alert('Numero de Telefone Invalido!');
}

//valida data
function ValidaData(dtaNasc){
        exp = /\d{2}\/\d{2}\/\d{4}/
        if(!exp.test(dtaNasc.value))
                alert('Data Invalida!');
}

//valida numero inteiro com mascara
function mascaraInteiro(){
        if (event.keyCode < 48 || event.keyCode > 57){
                event.returnValue = false;
                return false;
        }
        return true;
}

//formata de forma generica os campos
function formataCampo(campo, Mascara, evento) {
        var boleanoMascara;

        var Digitato = evento.keyCode;
        exp = /\-|\.|\/|\(|\)| /g
        campoSoNumeros = campo.value.toString().replace( exp, "" );

        var posicaoCampo = 0;
        var NovoValorCampo="";
        var TamanhoMascara = campoSoNumeros.length;;

        if (Digitato != 8) { 
        	// backspace
                for(i=0; i<= TamanhoMascara; i++) {
                        boleanoMascara  = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
                                                                || (Mascara.charAt(i) == "/"))
                        boleanoMascara  = boleanoMascara || ((Mascara.charAt(i) == "(")
                                                                || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
                        if (boleanoMascara) {
                                NovoValorCampo += Mascara.charAt(i);
                                  TamanhoMascara++;
                        }else {
                                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                                posicaoCampo++;
                          }
                  }
                campo.value = NovoValorCampo;
                  return true;
        }else {
                return true;
        }
}
