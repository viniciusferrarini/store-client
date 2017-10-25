var myinfo = new Vue({
    el: '#myinfo',
    data: {
        user: {}
    },
    methods: {
        getInfo: function () {
            var self = this;
            util.httpGet("/myinfo/getInfo").then(function (data) {
                self.user = data;
            });
        },

        saveInfo: function () {
            event.preventDefault();
            var self = this;
            self.user.cpfCnpj = $('#cpfCnpj').val();
            var parts = $('#birthday').val().split('/');
            self.user.birthday = new Date(parts[2],parts[1]-1, parts[0]);
            self.user.telephone = $('#telephone').val();
            util.httpPostJson("/myinfo", self.user).then(function (data) {
                toastr.success("Informações atualizadas com sucesso!", "Sucesso");
            }, function (error) {
                toastr.danger("Verifique os dados informados e tente novamente!", "Erro!")
            });
        }

    },

    mounted: function () {
        this.getInfo();
    }

});