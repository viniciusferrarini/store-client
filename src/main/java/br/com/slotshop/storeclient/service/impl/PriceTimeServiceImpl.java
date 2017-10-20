package br.com.slotshop.storeclient.service.impl;
import br.com.slotshop.storeclient.model.xml.CResultadoType;
import br.com.slotshop.storeclient.model.CourierParameters;
import br.com.slotshop.storeclient.service.PriceTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@Service
public class PriceTimeServiceImpl implements PriceTimeService {

    private static final String URL_WEBSERVICE  = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx/CalcPrecoPrazo";

    @Autowired private RestTemplate restTemplate;

    @Override
    public CResultadoType getPriceTime(String cepDestino) {

        String urlParameters = getUrlParameters(CourierParameters.builder()
                .nCdEmpresa("")
                .sDsSenha("")
                .nCdServico("04014, 04510")
                .sCepOrigem("85501-530")
                .sCepDestino(cepDestino)
                .nVlPeso("1")
                .nCdFormato(1)
                .nVlComprimento(30.0)
                .nVlAltura(15.0)
                .nVlLargura(15.0)
                .nVlDiametro(15.0)
                .sCdMaoPropria("N")
                .nVlValorDeclarado(0.0)
                .sCdAvisoRecebimento("N")
                .build());

        return restTemplate.getForObject(URL_WEBSERVICE + urlParameters, CResultadoType.class);

    }

    private String getUrlParameters(CourierParameters parametrosCorreios){
        StringBuilder url = new StringBuilder().append("?");
        Class clazz = parametrosCorreios.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                url.append(field.getName()).append("=").append(field.get(parametrosCorreios)).append("&");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return url.toString().substring(0, url.length() - 1);
    }

}
