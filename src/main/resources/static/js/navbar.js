$( document ).ready(function() {

    util.httpGet("/navbar").then(function (data) {
        console.log(data);
    });

});