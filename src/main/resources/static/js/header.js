var header = new Vue({
    el: '#header',
    data: {
        navList: [],
        totalAmount: 0,
        user: {}
    },
    methods: {

        getAmountCart: function () {
            var self = this;
            util.httpGet("/cart/amountTotalCart").then(function (data) {
                self.totalAmount = data;
            });
        },

        getNavbar: function () {
            var self = this;
            util.httpGet("/navbar").then(function (data) {
                self.navList = data;
            });
        },

        getLoggedUser: function () {
            var self = this;
            util.httpGet("/user").then(function (data) {
               self.user = data;
            });
        },

        addProduct: function (product, amount) {

            if(amount <= product.amount) {
                var cartProduct = {
                    id: new Date().getTime(),
                    product: product,
                    amount: amount
                };

                var url = "/cart";

                var self = this;
                util.httpPostJson(url, cartProduct).then(function (data) {
                    setTimeout(function () {
                        location.pathname = "/cart";
                    }, 300);
                });
            }else{
                $('#alert-amount').show('fast');
            }
        },

        validUser: function () {
            return this.user.id !== undefined && this.user.id !== "" && this.user.id !== null;
        }

    },
    mounted: function () {
        this.getNavbar();
        this.getAmountCart();
        this.getLoggedUser();
    }
});