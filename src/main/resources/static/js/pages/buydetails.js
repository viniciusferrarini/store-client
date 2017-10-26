var buyDetails = new Vue({
    el: '#buyDetails',
    data: {
        trackingCode: "",
        tracking: {
            historico: []
        }
    },
    methods: {
        getFreightDetails: function () {
            var self = this;
            util.httpGet("http://api.postmon.com.br/v1/rastreio/ect/" + self.trackingCode).then(function (data) {
                self.tracking = data;
                setTimeout(function () {
                    $('#trackingModal').modal('show');
                }, 300);
            });
        }
    }
});