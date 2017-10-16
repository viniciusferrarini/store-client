
package br.com.slotshop.storeclient.model.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _CResultado_QNAME = new QName("http://tempuri.org/", "cResultado");

    public ObjectFactory() { }

    public CResultadoType createCResultadoType() {
        return new CResultadoType();
    }

    public ServicosType createServicosType() {
        return new ServicosType();
    }

    public CServicoType createCServicoType() {
        return new CServicoType();
    }

    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cResultado")
    public JAXBElement<CResultadoType> createCResultado(CResultadoType value) {
        return new JAXBElement<CResultadoType>(_CResultado_QNAME, CResultadoType.class, null, value);
    }

}
