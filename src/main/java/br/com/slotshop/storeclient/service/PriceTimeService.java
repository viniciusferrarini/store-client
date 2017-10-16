package br.com.slotshop.storeclient.service;

import br.com.slotshop.storeclient.model.xml.CResultadoType;

public interface PriceTimeService {

    CResultadoType getPriceTime(String zipDestiny);

}
