(function () {
    $("#post-btn").on("click", function () {
        var message = $("#post-message").val();

        $.ajax({
            type: "POST",
            url: "/api/1.0/echos",
            data: {
                message: message,
                number: 10
            },
            success: function (data) {
                $("#echo-result table").html("").append("<tr><td>" + data.echo + "</td></tr>");
                $("#echos-result table").html("");
                jQuery.each(data.echos, function() {
                    $("#echos-result table").append("<tr><td>" + this + "</td></tr>");
                });
            }
        });
    });
})();
