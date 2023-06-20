package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "campoExtra")
public class CampoExtra {

    @Element(name = "codigo")
    private String codigo;

    @Element(name = "valor")
    private String valor;
}
