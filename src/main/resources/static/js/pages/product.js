var product = new Vue({
    el: '#product',
    data: {
        amount: 1,
        product: {}
    },
    methods: {
        getProduct: function () {
            var self = this;
            util.httpGet("/product/" + productId).then(function (data) {
                self.product = data;
            });
        },

        getProductPicture: function (picture) {
            return "http://130.211.158.13:7990/gallery/picture/" + picture;
        },

        getClass: function(key) {
            return key === 0 ? 'item active' : 'item';
        }
    },
    mounted: function () {
        this.getProduct();
    }
});