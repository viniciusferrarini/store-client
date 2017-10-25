var myAdress = new Vue({
    el: '#myAdress',
    data: {
        adressList: [],
        adress: {}
    },
    methods: {

        getAdressList: function () {
            var self = this;
            util.httpGet("/userAdress/loggedUserAdress").then(function (data) {
                self.adressList = data;
            });
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
            event.preventDefault();
            var self = this;
            self.adress.zipCode = $('#zipCode').val();
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

        editAdress: function (adress) {
            this.adress = adress;
            $('#adressModal').modal('show');
        },

        removeAdress: function (adress) {
            var self = this;
            util.httpPostJson("/userAdress/removeUserAdress", adress.id).then(function () {
                var index = self.adressList.indexOf(adress);
                if(index !== -1){
                    self.adressList.splice(index, 1);
                }
                toastr.success("Endereço removido com sucesso!", "Sucesso!");
            });
        },

        getCityByZipCode: function () {
            var self = this;
            util.httpGet("http://api.postmon.com.br/v1/cep/" + $('#zipCode').val()).then(function (data) {
                self.adress.zipCode = data.cep;
                self.adress.city = data.cidade;
                self.adress.state = data.estado;
                if(data.bairro !== undefined){
                    self.adress.district = data.bairro;
                }
                if(data.logradouro !== undefined){
                    self.adress.street = data.logradouro;
                }
            })
        }

    },

    mounted: function () {
        this.getAdressList();
    }

});