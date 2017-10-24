var header = new Vue({
    el: '#header',
    data: {
        navList: [],
        totalAmount: 0
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
        }

    },
    mounted: function () {
        this.getNavbar();
        this.getAmountCart();
    }
});