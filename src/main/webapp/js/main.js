var apartments = [];



$(document).ready(function() {

    $(document).ready(function () {
        $.getJSON("http://localhost:8080/apartments/load", function (json) {
            for (var i = 0; i < json.length; i++) {
                //L.marker([json[i].lat, json[i].lng]).addTo(mymap);
                apartments.push(json[i]);
            }
            drawComplexesTable();
        });
    });

    function drawComplexesTable() {
        $("#jsGrid").jsGrid({
            height: "90%",
            width: "100%",

            filtering: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,

            pageSize: 15,
            pageButtonCount: 5,

            deleteConfirm: "Do you really want to delete the client?",

            controller: {
                loadData: function (filter) {
                    return $.ajax({
                        type: "GET",
                        url: "/complexes/load",
                        data: filter,
                        dataType: "JSON"
                    });
                }
            },

            fields: [
                { name: "ID", type: "text", width: 150 },
                { name: "cianId", type: "number", width: 50 },
                { type: "control" }
            ]
        });
    }
} );