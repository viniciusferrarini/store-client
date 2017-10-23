var buy = new Vue({
    el: '#buy',
    data: {
        buyList: [],
    },
    methods: {
        getBuys: function () {
            var self = this;
            util.httpGet("/buy/findAll").then(function (data) {
                self.buyList = data;
            });
        }
    },
    mounted: function () {
        this.getBuys();
    }
});