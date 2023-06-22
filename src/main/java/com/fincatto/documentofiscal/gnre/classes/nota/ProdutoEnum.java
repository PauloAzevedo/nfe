package com.fincatto.documentofiscal.gnre.classes.nota;

import org.apache.commons.lang3.StringUtils;

public enum ProdutoEnum {

    _86("86","Bebidas"),
    _6("6","Cigarros e produtos derivados do fumo"),
    _7("7","Cimento"),
    _87("87","Energia Elétrica - Recolhimento Consumidores Livres"),
    _16("16","Marketing Porta-a-Porta"),
    _18("18","Materiais de Construção, Acabamentos, Bricolagens ou Adornos"),
    _19("19","Materiais de Limpeza"),
    _89("89","Outros"),
    _90("90","Peças, Partes e Acessórios para Veículos Automotores"),
    _68("68","Produtos alimentícios"),
    _23("23","Produtos Farmacêuticos"),
    _27("27","Tintas, Vernizes e outras mercadorias da indústria química");

    private final String codigo;
    private final String descricao;

    ProdutoEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ProdutoEnum valueOfCodigo(final String codigo) {
        for (final ProdutoEnum produto : ProdutoEnum.values()) {
            if (produto.getCodigo().equalsIgnoreCase(StringUtils.trim(codigo))) {
                return produto;
            }
        }
        throw new IllegalArgumentException(String.format("Nao mapeado", codigo));
    }
}
