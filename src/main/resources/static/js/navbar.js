new Vue({
    el: '#navbar',
    data: {
        navList: []
    },
    methods: {
        init: function () {
            var self = this;
            util.httpGet("/navbar").then(function (data) {
                self.navList = data;
            });
        }
    },
    mounted: function () {
        this.init();
    }
});
