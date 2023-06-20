package com.fincatto.documentofiscal.gnre.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "item")
public class ItemGnre {

    @Element(name = "receita")
    private String receita;
    @Element(name = "documentoOrigem")
    private DocumentoOrigem documentoOrigem;

    @Element(name = "produto")
    private String produto;

    @ElementList(entry = "valor", inline = true)
    private Valor valor;

    @Element(name = "contribuinteDestinatario")
    private Contribuinte contribuinteEmitente;

    @ElementList(entry = "camposExtra", inline = true)
    private List<CampoExtra> campoExtraList;
 }
