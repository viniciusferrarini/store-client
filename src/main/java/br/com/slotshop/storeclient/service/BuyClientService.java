package br.com.slotshop.storeclient.service;

import br.com.slotshop.server.model.Buy;
import br.com.slotshop.storeclient.model.dto.Checkout;

import javax.servlet.http.HttpSession;

public interface BuyClientService {

    Buy buy(Checkout checkout, HttpSession httpSession);

}
