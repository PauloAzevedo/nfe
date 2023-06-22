package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaInfoEmitente;
import com.fincatto.documentofiscal.utils.DFPersister;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Root(name = "TDadosGNRE")
public class Gnre {

    @Attribute(name = "versao")
    private String versao = "2.00";
    @Element(name = "ufFavorecida")
    private String ufFavorecida;

    @Element(name = "tipoGnre")
    private String tipoGnre;

    @Element(name = "contribuinteEmitente")
    private Contribuinte contribuinteEmitente;

    @ElementList(entry = "item",name = "itensGNRE", type = ItemGnre.class)
    private ArrayList<ItemGnre> itemGnreList;

    @Element(name = "valorGNRE")
    private String valorGNRE;

    @Element(name = "dataPagamento")
    private String dataPagamento;

    public Gnre() {
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getUfFavorecida() {
        return ufFavorecida;
    }

    public void setUfFavorecida(String ufFavorecida) {
        this.ufFavorecida = ufFavorecida;
    }

    public String getTipoGnre() {
        return tipoGnre;
    }

    public void setTipoGnre(String tipoGnre) {
        this.tipoGnre = tipoGnre;
    }

    public Contribuinte getContribuinteEmitente() {
        return contribuinteEmitente;
    }

    public void setContribuinteEmitente(Contribuinte contribuinteEmitente) {
        this.contribuinteEmitente = contribuinteEmitente;
    }

    public List<ItemGnre> getItemGnreList() {
        return itemGnreList;
    }

    public void setItemGnreList(ArrayList<ItemGnre> itemGnreList) {
        this.itemGnreList = itemGnreList;
    }

    public String getValorGNRE() {
        return valorGNRE;
    }

    public void setValorGNRE(String valorGNRE) {
        this.valorGNRE = valorGNRE;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
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
