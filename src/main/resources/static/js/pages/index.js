new Vue({
    el: '#index',
    data: {
        productList: []
    },
    methods: {
        getProduct: function () {
            var self = this;
            util.httpGet("/findFirst10").then(function (data) {
                var page = JSON.parse(data);
                self.productList = page.content;
            });
        },

        getProductPicture: function (product) {
            return "http://localhost:7990/gallery/picture/" + product.gallery[0].picture;
        }
    },
    mounted: function () {
        this.getProduct();
    }
});


