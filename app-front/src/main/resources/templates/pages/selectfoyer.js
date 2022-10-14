function getFoyer() {
    $.ajax({
        url: 'http://172.31.249.161:8081',
        headers: {
            'Content-Type': 'application/json'
        },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            data.map(habitation => {
                $("list-habitation")
                    .append($('<tr>')
                        .append($('<li>').text(foyer.street_number))

                    );
            })
        },
        error: function () {
            alert("Error occurred while trying to retrieve users data, please try again")
        }
    });
}