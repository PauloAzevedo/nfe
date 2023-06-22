package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

public class DocumentoOrigem {

    @Attribute(name = "tipo")
    private String identificador;

    @Text
    private String documentoOrigem;

    public DocumentoOrigem() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDocumentoOrigem() {
        return documentoOrigem;
    }

    public void setDocumentoOrigem(String documentoOrigem) {
        this.documentoOrigem = documentoOrigem;
    }
}
