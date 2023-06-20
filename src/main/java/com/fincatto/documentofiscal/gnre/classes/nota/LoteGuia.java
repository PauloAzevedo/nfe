package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "TLote_GNRE")
@Namespace(reference = "http://www.gnre.pe.gov.br")
public class LoteGuia {
    @Attribute(name = "versao")
    private String versao = "2.00";

    @ElementList(entry = "guias", inline = true)
    private List<Gnre> guias;

    public LoteGuia() {
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public List<Gnre> getGuias() {
        return guias;
    }

    public void setGuias(List<Gnre> guias) {
        this.guias = guias;
    }
}
