package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.utils.DFPersister;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.List;

@Root(name = "guias", strict = false)
public class Guias {
    @Element(name = "TDadosGNRE")
    private Gnre guia;

    public Guias(Gnre guia) {
        this.guia = guia;
    }

    public Gnre getGuia() {
        return guia;
    }

    public void setGuia(Gnre guia) {
        this.guia = guia;
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
