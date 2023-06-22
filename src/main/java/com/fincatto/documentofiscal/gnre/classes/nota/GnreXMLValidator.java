package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.validadores.DFXMLValidador;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;

public final class GnreXMLValidator {

    public static boolean validaGnreLote(final String arquivoXML) throws Exception {
        return GnreXMLValidator.validaGnre(arquivoXML, "lote_gnre_v2.00.xsd");
    }

    public static boolean validaGnre(final String xml, final String xsd) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = GnreXMLValidator.class.getClassLoader().getResource(String.format("schemas/PL_GNRE/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }
}
