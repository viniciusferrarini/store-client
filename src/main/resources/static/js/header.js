new Vue({
    el: '#header',
    data: {
        navList: []
    },
    methods: {
        init: function () {
            var self = this;
            util.httpGet("/navbar").then(function (data) {
                self.navList = JSON.parse(data);
            });
        }
    },
    mounted: function () {
        this.init();
    }
});