package com.fincatto.documentofiscal.gnre.classes.nota;

import com.fincatto.documentofiscal.nfe400.classes.nota.NFNotaInfoEmitente;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

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

    @ElementList(entry = "itensGNRE", inline = true)
    private List<ItemGnre> itemGnreList;

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

    public void setItemGnreList(List<ItemGnre> itemGnreList) {
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


}
