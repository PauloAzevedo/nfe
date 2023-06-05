package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "contribuinteEmitente")
public class ContribuinteEmitente {
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

}
