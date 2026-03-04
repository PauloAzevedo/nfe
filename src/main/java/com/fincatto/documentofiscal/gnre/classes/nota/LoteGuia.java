package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.utils.DFPersister;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.List;

@Root(name = "TLote_GNRE")
@Namespace(reference = "http://www.gnre.pe.gov.br")
public class LoteGuia {
    @Attribute(name = "versao")
    private String versao = "2.00";

    @ElementList(entry = "guias", inline = true)
    private List<Guias> guias;

    public LoteGuia() {
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public List<Guias> getGuias() {
        return guias;
    }

    public void setGuias(List<Guias> guias) {
        this.guias = guias;
    }

    @Override
    public String toString() {
        final Persister persister = new DFPersister();
        try (StringWriter writer = new StringWriter()) {
            persister.write(this, writer);
            return writer.toString();
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
