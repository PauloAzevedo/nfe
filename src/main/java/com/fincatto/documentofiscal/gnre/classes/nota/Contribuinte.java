package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

//@Root(name = "contribuinteEmitente")
public class Contribuinte {

    @Element(name = "identificacao")
    private IdentificaoContribuite identificaoContribuite;

    @Element(name = "razaoSocial")
    private String razaoSocial;

    @Element(name = "endereco")
    private String endereco;

    @Element(name = "municipio")
    private String municipio;

    @Element(name = "uf")
    private String uf;

    @Element(name = "cep")
    private String cep;

    @Element(name = "telefone")
    private String telefone;

    public Contribuinte() {
    }

    public IdentificaoContribuite getIdentificaoContribuite() {
        return identificaoContribuite;
    }

    public void setIdentificaoContribuite(IdentificaoContribuite identificaoContribuite) {
        this.identificaoContribuite = identificaoContribuite;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
