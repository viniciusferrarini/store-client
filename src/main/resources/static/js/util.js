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
    }
};