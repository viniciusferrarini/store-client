var cart = new Vue({
    el: '#cart',
    data: {
        cart: []
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
            return "http://localhost:7990/gallery/picture/" + picture;
        },

        getClass: function(key) {
            return key === 0 ? 'item active' : 'item';
        },

        checkList: function (list) {
            return list !== undefined && list !== null && list.length > 0;
        }

    },
    mounted: function () {
        this.getCart();
    }
});