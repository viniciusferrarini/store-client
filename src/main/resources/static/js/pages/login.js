var login = new Vue({
    el: '#login',
    data: {
        user: {
            name: "",
            email: "",
            cpfCnpj: "",
            password: ""
        },
        isValidCpfCnpj: false,
        isValidPassword: false,
        cmfPassword: "",
        forgotPasswordEmail: ""
    },
    methods: {

        newUser: function () {
            event.preventDefault();
            var self = this;
            self.user.cpfCnpj = $('#cpfCnpj').val();
            util.httpPostJson("/register/newUser", self.user).then(function (data) {
                toastr.success("Seu cadastro foi realizado com sucesso!", "Sucesso");
            }, function (error) {
                toastr.error(error.responseJSON.message, "Erro!");
            });
        },

        checkCpfCnpj: function () {
            var cpfCnpj = $('#cpfCnpj').val();
            if(cpfCnpj !== "") {
                if (valida_cpf_cnpj(cpfCnpj)) {
                    this.isValidCpfCnpj = true;
                } else {
                    this.isValidCpfCnpj = false;
                    toastr.error("CPF ou CNPJ inválido!", "Erro!");
                }
            }
        },

        confirmPassword: function () {
            if((this.user.password !== "" && this.user.password !== undefined) && (this.cmfPassword !== "" && this.cmfPassword !== undefined)){
                if (this.user.password === this.cmfPassword) {
                    this.isValidPassword = true;
                } else {
                    this.isValidPassword = false;
                    toastr.error("As senhas não são iguais, verifique!", "Erro");
                }
            }
        },

        forgotPassword: function () {
            event.preventDefault();
            util.httpGet("/login/forgotPassword?email=" + this.forgotPasswordEmail).then(function (data) {
                if(data){
                    toastr.success("Email encaminhado com sucesso!", "Sucesso!");
                }else{
                    toastr.error("Email não cadastrado, verifique o e-mail informado!", "Erro!");
                }
            });
        }
    }

});