
package br.com.slotshop.storeclient.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicosType", namespace = "http://tempuri.org/", propOrder = {
    "cServico"
})
public class ServicosType {

    @XmlElement(namespace = "http://tempuri.org/")
    private List<CServicoType> cServico;

    public List<CServicoType> getCServico() {
        if (cServico == null) {
            cServico = new ArrayList<CServicoType>();
        }
        return this.cServico;
    }

}
