package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaInfoDestinatario;
import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaInfoEmitente;
import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaProcessada;
import com.fincatto.documentofiscal.utils.DFPersister;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.BeanProperty;
import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class GeradorGnreXMLService {

    public static String gerarXMLGnreString(String xmlNotaOriginal){
        try {
            final NFNotaProcessada notaProcessada = new DFPersister().read(NFNotaProcessada.class, xmlNotaOriginal);
            LoteGuia novoLote = new LoteGuia();
            Gnre gnre = gerarGuia(notaProcessada);
            List<Guias> guias = new ArrayList<>();
            guias.add(new Guias(gnre));
            novoLote.setGuias(guias);

            if(GnreXMLValidator.validaGnreLote(novoLote.toString())){
                return novoLote.toString();
            }
            return null;
            //gerarXML(novoLote, caminhoArquuivo02);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String lerArquivoXMLComoString(String caminhoArquivo) throws Exception {
        File arquivo = new File(caminhoArquivo);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(arquivo);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

        return stringWriter.toString();
    }

    public static void gerarXML(LoteGuia loteGuia, String caminho) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LoteGuia.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File(caminho);
            marshaller.marshal(loteGuia, file);

            System.out.println("Arquivo XML criado com sucesso!");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
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
        ArrayList<ItemGnre> itemGnreList = gerarItensGNRE_RJ(nfNotaProcessada);
        gnre.setItemGnreList(itemGnreList);
        return gnre;
    }

    private static ArrayList<ItemGnre> gerarItensGNRE_RJ(NFNotaProcessada nfNotaProcessada) {
        ArrayList<ItemGnre> itensGnre = new ArrayList<>();
        ItemGnre itemGnre = new ItemGnre();
        itemGnre.setReceita(ReceitaEnum._100102.getCodigo());
        itemGnre.setProduto(ProdutoEnum._89.getCodigo());
        if(nfNotaProcessada.getNota().getInfo().getDestinatario().getEndereco().getUf().equalsIgnoreCase("RJ")){
            DocumentoOrigem documentoOrigem = new DocumentoOrigem();
            documentoOrigem.setIdentificador("24");
            documentoOrigem.setDocumentoOrigem(nfNotaProcessada.getNota().getInfo().getChaveAcesso());
            itemGnre.setDocumentoOrigem(documentoOrigem);
        } else {
            DocumentoOrigem documentoOrigem = new DocumentoOrigem();
            documentoOrigem.setIdentificador("10");
            documentoOrigem.setDocumentoOrigem(nfNotaProcessada.getNota().getInfo().getIdentificacao().getNumeroNota());
            itemGnre.setDocumentoOrigem(documentoOrigem);
        }
        ZonedDateTime data = nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada() !=null ? nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada(): nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraEmissao();
        itemGnre.setDataVencimento(calendarToStringWeb(zonedDateTimeToCalendar(data)));
        BigDecimal valorIcmsFCP = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSFundoCombatePobreza());
        BigDecimal valorIcmsPartilhaDestino = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSPartilhaDestinatario());
        List<Valor> valores = new ArrayList<>();
        Valor valor11 = new Valor();
        valor11.setValor(String.valueOf(arredondarComDuasCasas(valorIcmsPartilhaDestino.doubleValue())));
        valor11.setIdentificador("11");
        valores.add(valor11);
        if(valorIcmsFCP.compareTo(BigDecimal.ZERO) > 0){
            Valor valor12 = new Valor();
            valor12.setValor(String.valueOf(arredondarComDuasCasas(valorIcmsFCP.doubleValue())));
            valor12.setIdentificador("12");
            valores.add(valor12);
        }
        itemGnre.setValor(valores);
        itemGnre.setContribuinteDestinatario(getDestinatario(nfNotaProcessada.getNota().getInfo().getDestinatario()));
        itemGnre.setCamposExtras(gerarCamposExtras(nfNotaProcessada));
        itensGnre.add(itemGnre);
        return itensGnre;
    }

    private static ArrayList<ItemGnre> gerarItensGNRE_AC(NFNotaProcessada nfNotaProcessada) {
        ArrayList<ItemGnre> itensGnre = new ArrayList<>();
        ItemGnre itemGnre = new ItemGnre();

        DocumentoOrigem documentoOrigem = new DocumentoOrigem();
        documentoOrigem.setIdentificador("10");
        documentoOrigem.setDocumentoOrigem(nfNotaProcessada.getNota().getInfo().getIdentificacao().getNumeroNota());
        itemGnre.setDocumentoOrigem(documentoOrigem);

        ZonedDateTime data = nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada() !=null ? nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraSaidaOuEntrada(): nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraEmissao();
        itemGnre.setDataVencimento(calendarToStringWeb(zonedDateTimeToCalendar(data)));
        BigDecimal valorIcmsFCP = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSFundoCombatePobreza());
        BigDecimal valorIcmsPartilhaDestino = converterEmBigDecimal(nfNotaProcessada.getNota().getInfo().getTotal().getIcmsTotal().getValorICMSPartilhaDestinatario());
        List<Valor> valores = new ArrayList<>();
        Valor valor11 = new Valor();
        valor11.setValor(String.valueOf(arredondarComDuasCasas(valorIcmsPartilhaDestino.doubleValue())));
        valor11.setIdentificador("11");
        valores.add(valor11);
        if(valorIcmsFCP.compareTo(BigDecimal.ZERO) > 0){
            Valor valor12 = new Valor();
            valor12.setValor(String.valueOf(arredondarComDuasCasas(valorIcmsFCP.doubleValue())));
            valor12.setIdentificador("12");
            valores.add(valor12);
        }
        itemGnre.setValor(valores);
        itemGnre.setContribuinteDestinatario(getDestinatario(nfNotaProcessada.getNota().getInfo().getDestinatario()));
        itemGnre.setCamposExtras(gerarCamposExtras(nfNotaProcessada));
        itensGnre.add(itemGnre);
        return itensGnre;
    }

    private static ArrayList<CampoExtra> gerarCamposExtras(NFNotaProcessada nfNotaProcessada) {
        ArrayList<CampoExtra> campoExtras = new ArrayList<>();
        CampoExtra campo01 = new CampoExtra();
        campo01.setCodigo("117");
        ZonedDateTime data = nfNotaProcessada.getNota().getInfo().getIdentificacao().getDataHoraEmissao();
        campo01.setValor(calendarToStringWeb(zonedDateTimeToCalendar(data)));
        campoExtras.add(campo01);
        CampoExtra campo02 = new CampoExtra();
        campo02.setCodigo("118");
        campo02.setValor("Gerado via sistema");
        campoExtras.add(campo02);
        return campoExtras;
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
        contribuinte.setUf(emitente.getEndereco().getUf());
        contribuinte.setIdentificaoContribuite(new IdentificaoContribuite(emitente.getCnpj(), null));
        return contribuinte;
    }

    private static Contribuinte getDestinatario(NFNotaInfoDestinatario destinatario) {
        Contribuinte contribuinte = new Contribuinte();
        contribuinte.setMunicipio(removeFirstTwoCharacters(destinatario.getEndereco().getCodigoMunicipio()));
        contribuinte.setRazaoSocial(destinatario.getRazaoSocial());
        if(Objects.nonNull(destinatario.getCnpj())) {
            contribuinte.setIdentificaoContribuite(new IdentificaoContribuite(destinatario.getCnpj(), null));
        } else {
            contribuinte.setIdentificaoContribuite(new IdentificaoContribuite(null, destinatario.getCpf()));
        }
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
