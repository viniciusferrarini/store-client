var header = new Vue({
    el: '#header',
    data: {
        navList: [],
        cart: {
          totalAmount: 0
        }
    },
    methods: {

        getCart: function () {
            var self = this;
            util.httpGet("/cart/token/" + this.getCartToken()).then(function (data) {
                self.cart = data;
            });
        },

        getNavbar: function () {
            var self = this;
            util.httpGet("/navbar").then(function (data) {
                self.navList = data;
            });
        },

        addProduct: function (product, amount) {

            var cartProduct = {
                product: product,
                amount: amount
            };

            var url = "/cart";
            if (this.checkCartToken()) {
                url += "/token?token=" + this.getCartToken();
            }

            var self = this;
            util.httpPostJson(url, cartProduct).then(function (data) {
                if (!self.checkCartToken()) {
                    self.saveCartToken(data.token);
                }
                setTimeout(function () {
                    location.pathname = "/cart";
                }, 300);
            });

        },

        getCartToken: function () {
            if(localStorage.getItem("cartToken") !== null){
                return localStorage.getItem("cartToken");
            }
            return "";
        },

        checkCartToken: function () {
            return localStorage.getItem("cartToken") !== null;
        },

        saveCartToken: function (token) {
            localStorage.setItem("cartToken", token);
        },

        clearCart: function () {

        }

    },
    mounted: function () {
        this.getNavbar();
        if (this.checkCartToken()){
            this.getCart();
        }
    }
});