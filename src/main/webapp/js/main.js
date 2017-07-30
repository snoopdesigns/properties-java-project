var complexes = [];

$(document).ready(function() {

    $(document).ready(function () {
        $.getJSON("http://localhost:8080/complexes/load", function (json) {
            for (var i = 0; i < json.length; i++) {
                complexes.push(json[i]);
            }
            drawComplexesTable();
        });
    });

    $(document).ready(function () {
        $.getJSON("http://localhost:8080/apartments/load", function (json) {
            var tr;
            for (var i = 0; i < json.length; i++) {
                L.marker([json[i].lat, json[i].lng]).addTo(mymap);
            }
        });
    });

    function drawComplexesTable() {
        $("#jsGrid").jsGrid({
            width: "100%",
            height: "800px",
            filtering: true,
            sorting: true,
            paging: true,
            pageSize: 10,
            pageButtonCount: 5,
            data: complexes,
            fields: [
                { name: "id", type: "text", width: 150, autosearch: true },
                { name: "cianId", type: "number", width: 50, autosearch: true }
            ]
        });
    }
} );