package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.utils.DFPersister;
import jakarta.xml.bind.annotation.XmlElement;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


public class ItemGnre {

    @Element(name = "receita", required = false)
    private String receita;
    @Element(name = "documentoOrigem", required = false)
    private DocumentoOrigem documentoOrigem;

    @Element(name = "produto", required = false)
    private String produto;

    @Element(name = "dataVencimento")
    private String dataVencimento;

    @ElementList(entry = "valor", inline = true)
    private List<Valor> valor;

    @Element(name = "convenio", required = false)
    private String convenio;

    @Element(name = "contribuinteDestinatario")
    private Contribuinte contribuinteDestinatario;

    @ElementList(entry = "campoExtra",name = "camposExtras", type = CampoExtra.class)
    private ArrayList<CampoExtra> camposExtras;



    public ItemGnre() {
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public DocumentoOrigem getDocumentoOrigem() {
        return documentoOrigem;
    }

    public void setDocumentoOrigem(DocumentoOrigem documentoOrigem) {
        this.documentoOrigem = documentoOrigem;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public List<Valor> getValor() {
        return valor;
    }

    public void setValor(List<Valor> valor) {
        this.valor = valor;
    }

    public Contribuinte getContribuinteDestinatario() {
        return contribuinteDestinatario;
    }

    public void setContribuinteDestinatario(Contribuinte contribuinteDestinatario) {
        this.contribuinteDestinatario = contribuinteDestinatario;
    }

    public List<CampoExtra> getCamposExtras() {
        return camposExtras;
    }

    public void setCamposExtras(ArrayList<CampoExtra> camposExtras) {
        this.camposExtras = camposExtras;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
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
