$(document).ready(function () {
    $.getJSON("http://localhost:8080/complexes/load", function (json) {
        for (var i = 0; i < json.length; i++) {
            if (json[i].lat != null && json[i].lng != null) {
                var marker = L.marker([json[i].lat, json[i].lng]).addTo(mymap);
                marker.bindPopup("<b>"+ json[i].name + "</b><br>" + json[i].address)
            }
        }
    });

    $("#jsGrid").jsGrid({
        height: "500px",
        width: "100%",
        filtering: true,
        sorting: true,
        autoload: true,
        pageSize: 15,
        pageButtonCount: 5,

        controller: {
            loadData: function (filter) {
                var apartmentsList = [];
                jQuery.ajax({
                    contentType: 'application/json',
                    dataType: 'json',
                    processData: false,
                    type: 'POST',
                    url: 'apartments/load',
                    data: JSON.stringify(filter),
                    success: function (result) {
                        apartmentsList = result;
                    },
                    async: false
                });
                return apartmentsList;
            }
        },

        fields: [
            { name: "url",
                itemTemplate: function(value) {
                    return $("<a>").attr("href", value).text(value);
                },type: "text", width: 50 },
            { name: "houseType", type: "text", width: 50 },
            { name: "totalArea", type: "text", width: 50 },
            { name: "roomsArea", type: "text", width: 50 },
            { name: "livingArea", type: "text", width: 50 },
            { name: "price", type: "text", width: 50 },
            { name: "address", type: "text", width: 50 },
            { name: "complexName", type: "text", width: 50 }
        ]
    });
});