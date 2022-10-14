function getFoyer() {
    $.ajax({
        url: 'http://localhost:8081/foyer',
        headers: {
            'Content-Type': 'application/json'
        },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            data.map(foyer => {
                $("#table-foyer").find('tbody')
                    .append($('<tr>')
                       .append($('<td>').text(foyer.id_foyer))
                       .append($('<td>').text(foyer.name_foyer))

                    );
            })
        },
        error: function () {
            alert("Error occurred while trying to retrieve consommation data, please try again")
        }
    });
}