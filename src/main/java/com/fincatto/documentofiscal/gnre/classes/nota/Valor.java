package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Valor {

    @Attribute(name = "tipo")
    private String identificador;

    @Element(name = "valor")
    private String valor;
}
