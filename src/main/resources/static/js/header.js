var header = new Vue({
    el: '#header',
    data: {
        navList: [],
        cart: {
          amount: 0
        }
    },
    methods: {

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

            util.httpPostJson("/cart?id=" + this.getCartId(), cartProduct).then(function (data) {
                console.log(data);
            });
        },

        getCartId: function () {
            if(localStorage.getItem("cartId") !== null){
                return localStorage.getItem("cartId");
            }
            return null;
        },

        saveCart: function () {
            localStorage.setItem("cartId", this.cart);
        },

        clearCart: function () {

        }

    },
    mounted: function () {
        this.getNavbar();
        this.getCar();
    }
});