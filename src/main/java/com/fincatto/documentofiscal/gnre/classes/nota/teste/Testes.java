package com.fincatto.documentofiscal.gnre.classes.nota.teste;

import com.fincatto.documentofiscal.gnre.classes.nota.Contribuinte;
import com.fincatto.documentofiscal.gnre.classes.nota.Gnre;
import com.fincatto.documentofiscal.gnre.classes.nota.IdentificaoContribuite;
import com.fincatto.documentofiscal.gnre.classes.nota.LoteGuia;
import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaInfoEmitente;
import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaProcessada;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        LoteGuia novoLote = new LoteGuia();

       // novoLote.setGuias();
    }

    private static SimpleDateFormat dateFormatWeb = new SimpleDateFormat("yyyy-MM-dd");

    private static Gnre gerarGuia(final NFNotaProcessada nfNotaProcessada) {
        Gnre gnre = new Gnre();
        gnre.setTipoGnre("0");
        gnre.setUfFavorecida(nfNotaProcessada.getNota().getInfo().getDestinatario().getEndereco().getUf());
        gnre.setContribuinteEmitente(getEmitente(nfNotaProcessada.getNota().getInfo().getEmitente()));
        ZonedDateTime data = nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada() !=null ? nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada(): nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraEmissao();
        gnre.setDataPagamento(calendarToStringWeb(zonedDateTimeToCalendar(data)));
        gnre.setValorGNRE(verificarValorTotalGnre(nfNotaProcessada));
        //Parei nos itens
        return gnre;
    }

    public static String verificarValorTotalGnre(NFNotaProcessada nfNotaProcessada) {
        String valorTotal = "0.00";
        BigDecimal valorIcmsFCP = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSFundoCombatePobreza());
        BigDecimal valorIcmsPartilhaDestino = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSPartilhaDestinatario());
        BigDecimal valorCalculado = valorIcmsFCP.add(valorIcmsPartilhaDestino);
        valorTotal = String.valueOf(arredondarComDuasCasas(valorCalculado.doubleValue()));
        return valorTotal;
    }

    public static BigDecimal arredondarComDuasCasas(Double valor) {
        try {
            return new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
        } catch (Exception ex) {
            return new BigDecimal(0.0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public static BigDecimal converterEmBigDecimal(String valor){
        try {
            BigDecimal valorConvertido = BigDecimal.valueOf(Double.parseDouble(valor));
            return valorConvertido;
        } catch (Exception ex){
            return BigDecimal.ZERO;
        }
    }

    private static Contribuinte getEmitente(NFNotaInfoEmitente emitente) {
        Contribuinte contribuinte = new Contribuinte();
        contribuinte.setCep(emitente.getEndereco().getCep());
        StringBuilder endereco = new StringBuilder();
        endereco.append(emitente.getEndereco().getLogradouro());
        endereco.append(", ");
        endereco.append(emitente.getEndereco().getNumero());
        contribuinte.setEndereco(endereco.toString());
        contribuinte.setMunicipio(removeFirstTwoCharacters(emitente.getEndereco().getCodigoMunicipio()));
        contribuinte.setTelefone(emitente.getEndereco().getTelefone());
        contribuinte.setRazaoSocial(emitente.getRazaoSocial());
        contribuinte.setIdentificaoContribuite(new IdentificaoContribuite(emitente.getCnpj(), null));
        return contribuinte;
    }

    public static Calendar zonedDateTimeToCalendar(final ZonedDateTime zonedDateTime){
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(zonedDateTime.toInstant().toEpochMilli());
        return data;
    }

    public static String removeFirstTwoCharacters(String str) {
        if (str.length() < 2) {
            // Se a string tiver menos de 2 caracteres, retorna uma string vazia.
            return "";
        }
        // Remove os dois primeiros caracteres usando o método substring().
        return str.substring(2);
    }

    public static String calendarToStringWeb(Calendar cal) {
        if (cal != null) {
            return dateFormatWeb.format(cal.getTime());
        } else {
            return "";
        }
    }
}
