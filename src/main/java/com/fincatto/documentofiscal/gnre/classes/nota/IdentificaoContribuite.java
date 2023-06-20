package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "identificacao")
public class IdentificaoContribuite {

    @Element(name = "CNPJ")
    private String cnpj;

    @Element(name = "CPF")
    private String cpf;

    public IdentificaoContribuite() {
    }

    public IdentificaoContribuite(String cnpj, String cpf) {
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
