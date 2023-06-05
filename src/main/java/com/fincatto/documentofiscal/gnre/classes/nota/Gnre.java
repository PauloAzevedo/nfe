package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TDadosGNRE")
public class Gnre {

    @Element(name = "ufFavorecida")
    private String ufFavorecida;

    @Element(name = "tipoGnre")
    private String tipoGnre;

    @Element(name = "contribuinteEmitente")
    private ContribuinteEmitente contribuinteEmitente;


}
