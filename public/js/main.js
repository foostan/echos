(function () {
    function echo() {
        var message = $("#post-message").val();
        $.ajax({
            type: "POST",
            url: "/api/1.0/echos",
            data: {
                message: message,
                number: 10
            },
            success: function (data) {


                $("#result").children().remove();
                var $echo_result = $('<div class="panel panel-default"></div>')
                    .append('<div class="panel-heading">echo</div>')
                    .append('<table class="table"><tr><td>' + data.echo + '</td></tr></table>');

                var $echos_result_table = $('<table class="table"></table>');
                jQuery.each(data.echos, function () {
                    $echos_result_table.append("<tr><td>" + this + "</td></tr>");
                });

                var $echos_result = $('<div class="panel panel-default"></div>')
                    .append('<div class="panel-heading">echos</div>')
                    .append($echos_result_table);

                $("#result").append($echo_result);
                $("#result").append($echos_result);


            }
        });
    }

    $("#post-message").keydown(function (e) {
        if (e.keyCode == 13) {
            echo();
            return false;
        }
    });

    $("#post-btn").on("click", function () {
        echo();
    });
})();
