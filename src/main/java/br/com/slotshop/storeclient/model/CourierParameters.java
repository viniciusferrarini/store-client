package br.com.slotshop.storeclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierParameters {

    private String nCdEmpresa;

    private String sDsSenha;

    private String nCdServico;

    private String sCepOrigem;

    private String sCepDestino;

    private String nVlPeso;

    private Integer nCdFormato;

    private Double nVlComprimento;

    private Double nVlAltura;

    private Double nVlLargura;

    private Double nVlDiametro;

    private String sCdMaoPropria;

    private Double nVlValorDeclarado;

    private String sCdAvisoRecebimento;

}
