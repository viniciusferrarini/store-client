var forgotPassword = new Vue({
    el: '#forgotPassword',
    data: {
        password: "",
        cmfPassword: "",
        isValidPassword: false,
        isValidToken: true,
        token: ""
    },
    methods: {

        changePassword: function () {
            event.preventDefault()
            util.httpGet("/forgotPassword/changePassword?password=" + this.password + "&token=" + this.token).then(function (data) {
                toastr.success("Sua senha foi alterada com sucesso!", "Sucesso!");
                setTimeout(function () {
                    location.pathname("/login");
                }, 1000);
            }, function (error) {
                toastr.error("Ocorreu um erro ao alterar sua senha!", "Erro!");
            });
        },

        invalidToken: function () {
            toastr.error("O token informado não é válido! Tente recuperar sua senha novamente!", "Error");
            this.isValidToken = false;
        },

        confirmPassword: function () {
            if((this.password !== "" && this.password !== undefined) && (this.cmfPassword !== "" && this.cmfPassword !== undefined)){
                if (this.password === this.cmfPassword) {
                    this.isValidPassword = true;
                } else {
                    this.isValidPassword = false;
                    toastr.error("As senhas não são iguais, verifique!", "Erro");
                }
            }
        }
    }

});