var util = {

    httpGet: function (url) {
        return $.ajax({
            url: url,
            type: 'GET',
            statusCode: {
                500: function () {

                }
            }
        });
    },

    httpPostJson: function (url, data) {
        return $.ajax({
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=UTF-8",
            type: 'POST',
            headers: {
                'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
            }
        });
    },

    numeroParaMoeda: function(n, c, d, t) {
        c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
        return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
    }

};