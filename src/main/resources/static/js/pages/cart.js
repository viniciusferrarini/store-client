var cart = new Vue({
    el: '#cart',
    data: {
        amount: 1,
        product: {}
    },
    methods: {
        getProduct: function () {
            var self = this;
            util.httpGet("/1").then(function (data) {
                self.product = data;
            });
        },

        getProductPicture: function (picture) {
            return "http://localhost:7990/gallery/picture/" + picture;
        },

        getClass: function(key) {
            return key === 0 ? 'item active' : 'item';
        },
    },
    mounted: function () {
        this.getProduct();
    }
});