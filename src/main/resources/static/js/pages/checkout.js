var checkout = new Vue({

    el: '#checkout',
    data: {
        cart: {},
        adressList: [],
        adress: {},
        selectedAdress: {},
        priceTime: [],
        zipCode: "",
        parcel: [],
        cartDisabled: true
    },
    methods: {

        getCart: function () {
            var self = this;
            util.httpGet("/cart/getCart").then(function (data) {
                self.cart = data;
                setTimeout(function () {
                    self.getParcel();
                }, 300);
            });
        },

        getUserAdressByCartZipCode: function () {
            var self = this;
            util.httpGet("/userAdress/userAdressByCartZipCode").then(function (data) {
               self.selectedAdress = data;
            });
        },

        getUserAdress: function () {
            var self = this;
            util.httpGet("/userAdress/loggedUserAdress").then(function (data) {
                self.adressList = data;
            });
        },

        getProductPicture: function (picture) {
            return "http://localhost:7990/gallery/picture/" + picture;
        }, 
        
        newAdress: function () {
            this.adress = {
                id: "",
                city: "",
                state: "",
                district: "",
                street: ""
            };
            $('#adressModal').modal('show');
        },

        saveAdress: function () {
            var self = this;
            util.httpPostJson("/userAdress/saveUserAdress", self.adress).then(function (data) {
               var msg = "";
               if(self.adress.id === "") {
                   msg = "Endereço cadastrado com sucesso!";
                   self.adressList.push(data);
               }else{
                   msg = "Endereço atualizado com sucesso!";
               }
               toastr.success(msg, "Sucesso!");
                $('#adressModal').modal('hide');
            }, function (error) {
                console.log(error);
                if(error.status === 400){
                    var errors = error.responseJSON.errors;
                    for (var i = 0; i < errors.length; i++) {
                        var obj = errors[i];
                        toastr.warning(obj.defaultMessage, "Atenção!");
                    }
                }
            });
        },

        editAdress: function () {
            this.adress = this.selectedAdress;
            $('#adressModal').modal('show');
        },

        removeAdress: function (adress) {
            var self = this;
            util.httpPostJson("/userAdress/removeUserAdress", adress).then(function () {
                var index = self.adressList.indexOf(adress);
                if(index !== -1){
                    self.adressList.splice(index, 1);
                }
                toastr.success("Endereço removido com sucesso!", "Sucesso!");
            });
        },

        getCityByZipCode: function (zipCode) {
            var self = this;
            util.httpGet("http://api.postmon.com.br/v1/cep/" + this.adress.zipCode).then(function (data) {
                self.adress.city = data.cidade;
                self.adress.state = data.estado;
                if(data.bairro !== undefined){
                    self.adress.district = data.bairro;
                }
                if(data.logradouro !== undefined){
                    self.adress.street = data.logradouro;
                }
            })
        },

        checkPriceAndTime: function (zipCode) {
            this.zipCode = zipCode;
            if(zipCode !== undefined && zipCode !== null && zipCode.length > 0) {
                var self = this;
                util.httpGet("/cart/calculatePriceTime/" + zipCode).then(function (data) {
                    var service = data.servicos.cservico[0];
                    if(service.erro === "0"){
                        self.priceTime = data.servicos;
                        setTimeout(function () {
                            $('#priceTimeModal').modal('show');
                        }, 300);
                    }else{
                        $('#zipCode')
                        toastr.error(service.msgErro, "Erro!");
                    }
                });
            }else{
                toastr.warning("Informe o CEP e tente novamente!", "Atenção");
            }
        },

        changeFreight: function () {
            var self = this;
            self.cart.zipCode = self.zipCode;
            util.httpPostJson("/cart/changeFreight" , self.cart).then(function (data) {
                self.cart = data;
                toastr.success("Frete e endereço de entrega atualizados com sucesso!", "Sucesso!");
                $('#priceTimeModal').modal('hide');
                setTimeout(function () {
                    self.getParcel();
                }, 300);
            });
        },

        getParcel: function () {
            var parcelTemp = [];
            for (var i = 1; i <= 12; i++) {
                var obj = {
                    parcel: i,
                    description: i + "x de R$" + util.numeroParaMoeda(this.cart.totalCart / i) + " sem juros"
                }
                parcelTemp.push(obj);
            }
            this.parcel = parcelTemp;
        },

        checkPayment: function () {
            if(this.cart.payment === "TICKET"){
                this.cartDisabled = true;
            }else if (this.cart.payment === "CREDITCARD"){
                this.cartDisabled = false;
            }
            this.changePaymentForm();
        },

        changePaymentForm: function () {
            var self = this;
            util.httpPostJson("/cart/changePayment", this.cart).then(function (data) {
                self.cart = data;
            });
        }
    },

    mounted: function () {
        this.getCart();
        this.getUserAdressByCartZipCode();
        this.getUserAdress();
    }

});