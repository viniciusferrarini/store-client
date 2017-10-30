var cart = new Vue({
    el: '#cart',
    data: {
        cart: {},
        zipCode: "",
        priceTime: []
    },
    methods: {

        getCart: function () {
            var self = this;
            util.httpGet("/cart/getCart").then(function (data) {
                self.cart = data;
            });
        },

        removeToCart: function (cartProduct) {
            var self = this;
            util.httpPostJson("/cart/removeToCart", cartProduct).then(function (data) {
                self.cart = data;
                header.totalAmount = data.totalAmount > 0 ? data.totalAmount : 0;
            });
        },

        getProductPicture: function (picture) {
            return "http://130.211.158.13:7990/gallery/picture/" + picture;
        },

        getClass: function(key) {
            return key === 0 ? 'item active' : 'item';
        },

        checkList: function (list) {
            return list !== undefined && list !== null && list.length > 0;
        },

        checkPriceAndTime: function () {
            if(this.zipCode !== undefined && this.zipCode !== null && this.zipCode.length > 0) {
                var self = this;
                util.httpGet("/cart/calculatePriceTime/" + this.zipCode).then(function (data) {
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
            });
        },

        changeAmount: function (cartProduct) {
            if (cartProduct.amount > 0) {
                var self = this;
                util.httpPostJson("/cart/changeAmount", self.cart).then(function (data) {
                    self.cart = data;
                });
            } else {
                toastr.warning("Seu produto foi removido do carrinho, devido a quantidade informada ser 0!", "Produto removido do carrinho!")
                this.removeToCart(cartProduct);
            }
        }

    },
    mounted: function () {
        this.getCart();
    }
});