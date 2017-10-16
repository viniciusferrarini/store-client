
package br.com.slotshop.storeclient.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cServicoType", namespace = "http://tempuri.org/", propOrder = {
    "codigo",
    "valor",
    "prazoEntrega",
    "valorMaoPropria",
    "valorAvisoRecebimento",
    "valorValorDeclarado",
    "entregaDomiciliar",
    "entregaSabado",
    "erro",
    "msgErro",
    "valorSemAdicionais",
    "obsFim"
})
public class CServicoType {

    @XmlElement(name = "Codigo", namespace = "http://tempuri.org/", required = true)
    private String codigo;
    @XmlElement(name = "Valor", namespace = "http://tempuri.org/", required = true)
    private String valor;
    @XmlElement(name = "PrazoEntrega", namespace = "http://tempuri.org/", required = true)
    private String prazoEntrega;
    @XmlElement(name = "ValorMaoPropria", namespace = "http://tempuri.org/", required = true)
    private String valorMaoPropria;
    @XmlElement(name = "ValorAvisoRecebimento", namespace = "http://tempuri.org/", required = true)
    private String valorAvisoRecebimento;
    @XmlElement(name = "ValorValorDeclarado", namespace = "http://tempuri.org/", required = true)
    private String valorValorDeclarado;
    @XmlElement(name = "EntregaDomiciliar", namespace = "http://tempuri.org/", required = true)
    private String entregaDomiciliar;
    @XmlElement(name = "EntregaSabado", namespace = "http://tempuri.org/", required = true)
    private String entregaSabado;
    @XmlElement(name = "Erro", namespace = "http://tempuri.org/", required = true)
    private String erro;
    @XmlElement(name = "MsgErro", namespace = "http://tempuri.org/", required = true)
    private String msgErro;
    @XmlElement(name = "ValorSemAdicionais", namespace = "http://tempuri.org/", required = true)
    private String valorSemAdicionais;
    @XmlElement(namespace = "http://tempuri.org/", required = true)
    private String obsFim;

}
