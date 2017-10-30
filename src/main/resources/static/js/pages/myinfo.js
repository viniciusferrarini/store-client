var myinfo = new Vue({
    el: '#myinfo',
    data: {
        user: {},
        password: "",
        cmfPassword: "",
        isValidCpfCnpj: false,
        isValidPassword: false
    },
    methods: {
        getInfo: function () {
            var self = this;
            util.httpGet("/myinfo/getInfo").then(function (data) {
                self.user = data;
                setTimeout(function () {
                    self.checkCpfCnpj()
                }, 300);
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
            if((this.password !== "" && this.password !== undefined) && (this.cmfPassword !== "" && this.cmfPassword !== undefined)){
                if (this.password === this.cmfPassword) {
                    this.isValidPassword = true;
                } else {
                    this.isValidPassword = false;
                    toastr.error("As senhas não são iguais, verifique!", "Erro");
                }
            }
        },

        savePassword: function () {
            event.preventDefault();
            var self = this;
            util.httpGet("/myinfo/changePassword?password="+this.password).then(function (data) {
               self.password = "";
               self.cmfPassword = "";
               self.isValidPassword = false;
               toastr.success("Sua senha foi alterada com sucesso!", "Sucesso!");
            });
        }

    },

    mounted: function () {
        this.getInfo();
    }

});