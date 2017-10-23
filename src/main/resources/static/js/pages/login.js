var login = new Vue({
    el: '#login',
    data: {
        user: {
            name: "",
            email: "",
            cpfCnpj: "",
            password: ""
        }
    },
    methods: {

        newUser: function () {
            event.preventDefault();
            var self = this;
            util.httpPostJson("/register/newUser", self.user).then(function (data) {
                location.pathname = "/buy";
            }, function (error) {
                toastr.error(error.responseJSON.message, "Erro!");
            });
        }

    }

});