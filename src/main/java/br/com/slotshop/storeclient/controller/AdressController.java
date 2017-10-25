package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.UserAdress;
import br.com.slotshop.server.service.CrudService;
import br.com.slotshop.server.service.UserAdressService;
import br.com.slotshop.server.service.UserService;
import br.com.slotshop.storeclient.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("userAdress")
public class AdressController extends RestCrudController<UserAdress, Long> {

    @Autowired private UserService userService;

    @Autowired private UserAdressService userAdressService;

    @Override
    protected CrudService<UserAdress, Long> getService() {
        return userAdressService;
    }

    @GetMapping("/loggedUserAdress")
    public @ResponseBody List<UserAdress> getLoggedUserAdress(){
        return userAdressService.findByUser(userService.getLoggedUser());
    }

    @PostMapping("/saveUserAdress")
    public @ResponseBody UserAdress saveUserAdress(@Valid @RequestBody UserAdress userAdress){
        userAdress.setUser(userService.getLoggedUser());
        return userAdressService.save(userAdress);
    }

    @GetMapping("/userAdressByCartZipCode")
    public @ResponseBody UserAdress getUserAdressByCartZipCode(HttpSession httpSession){
        Cart cart = (Cart) httpSession.getAttribute("cart");
        if(cart != null) {
            return userAdressService.getUserAdressByUserAndCartZipCode(cart.getZipCode());
        }
        return null;
    }

    @PostMapping("/removeUserAdress")
    public @ResponseBody void removeUserAdress(@RequestBody Long id){
        try {
            userAdressService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
