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

};