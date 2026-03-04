package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.utils.DFPersister;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;

@Root(name = "campoExtra")
public class CampoExtra {

    @Element(name = "codigo", required = false)
    private String codigo;

    @Element(name = "valor", required = false)
    private String valor;

    public CampoExtra() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
