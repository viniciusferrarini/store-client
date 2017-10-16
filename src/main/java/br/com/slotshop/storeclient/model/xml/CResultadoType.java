
package br.com.slotshop.storeclient.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cResultadoType", namespace = "http://tempuri.org/", propOrder = {
    "servicos"
})
public class CResultadoType {

    @XmlElement(name = "Servicos", namespace = "http://tempuri.org/", required = true)
    private ServicosType servicos;

}
