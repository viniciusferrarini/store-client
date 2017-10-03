var header = new Vue({
    el: '#header',
    data: {
        navList: [],
        cart: {
            amount: 0,
            products: []
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
            var cart = this.cart;
            cart.amount += amount;
            var productIndex = cart.products.indexOf(product);
            if (productIndex !== -1){
                cart.products[productIndex].quantity += amount;
            }else{
                product.quantity = amount;
                cart.products.push(product);
            }
            this.saveCar()
        },

        getCar: function () {
            if(localStorage.getItem("cartStorage") !== null){
                this.cart = JSON.parse(localStorage.getItem("cartStorage"));
            }
        },

        saveCar: function () {
            localStorage.setItem("cartStorage", JSON.stringify(this.cart));
        },

        clearCar: function () {
            if(localStorage.getItem("cartStorage") !== null){
                localStorage.removeItem("cartStorage");
            }
        }

    },
    mounted: function () {
        this.getNavbar();
        this.getCar();
    }
});