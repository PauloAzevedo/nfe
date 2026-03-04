package com.fincatto.documentofiscal.gnre.classes.nota;

import org.apache.commons.lang3.StringUtils;

public enum ReceitaEnum {

    _100013("100013", "ICMS Comunicação"),
    _100021("100021", "ICMS Energia Elétrica"),
    _100030("_100030", "ICMS Transporte"),
    _100048("100048", "ICMS Substituição Tributária por Apuração"),
    _100056("100056", "ICMS Importação"),
    _100080("100080", "ICMS Recolhimentos Especiais"),
    _100099("100099", "ICMS Substituição Tributária por Operação"),
    _100102("100102", "ICMS Consumidor Final Não Contribuinte Outra UF por Operação"),
    _100110("100110", "ICMS Consumidor Final Não Contribuinte Outra UF por Apuração");
    private final String codigo;
    private final String descricao;

    ReceitaEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }



    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ReceitaEnum valueOfCodigo(final String codigo) {
        for (final ReceitaEnum receita : ReceitaEnum.values()) {
            if (receita.getCodigo().equalsIgnoreCase(StringUtils.trim(codigo))) {
                return receita;
            }
        }
        throw new IllegalArgumentException(String.format("Nao mapeado", codigo));
    }
}
