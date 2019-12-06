function getAllCountries() {
    var query = "PREFIX dbo: <http://dbpedia.org/ontology/>" +
                 "SELECT DISTINCT ?country WHERE {" +
                        "?city a dbo:city ." +
                        "?city dbo:country ?country ." +
                 "} ";

    var responseUrl = "http://localhost:3030/bike_station_db" + "?query=" + encodeURIComponent(query) + "&output=json";

    $.getJSON(responseUrl, function(jsonData) {
        /* Parse json file */
        var bindings = jsonData.results.bindings;

        if (bindings.length > 0) {
            /* Create html list */
            var list = document.createElement('select');
            list.setAttribute("id", "countriesList");
            list.setAttribute("onchange", "getSelectedCountry()");
            list.innerHTML += '<option selected> Select a country </option>';
            document.getElementById("getData").appendChild(list);

            for (var i = 0; i < bindings.length; i++) {
                var countries = document.getElementById('countriesList');
                var country = document.createElement('option');
                country.setAttribute("id", bindings[i].country.value);
                country.setAttribute("name", bindings[i].country.value);
                var countryName = document.createTextNode(bindings[i].country.value);

                country.appendChild(countryName);
                countries.appendChild(country);
            }
        }
        else {
            var message = document.createElement('h1');
            message.setAttribute("id", "message")
            message.appendChild(document.createTextNode("There's no data in the triplestore"));
            document.getElementById("getData").appendChild(message);
        }
    });
}

function getAllCities(country) {
	var query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
    "PREFIX dbo: <http://dbpedia.org/ontology/>" +
    "SELECT ?label WHERE {" +
    "  ?city a dbo:city ." +
    "  ?city rdfs:label ?label ." +
    "  ?city dbo:country \"" + country + "\" ." +
    "} ";

    var responseUrl = "http://localhost:3030/bike_station_db" + "?query=" + encodeURIComponent(query) + "&output=json";

    $.getJSON(responseUrl, function(jsonData) {
    	/* Parse json file */
    	var bindings = jsonData.results.bindings;

        /* Create html list */
        var list = document.createElement('select');
        list.setAttribute("id", "citiesList");
        list.setAttribute("onchange", "getSelectedCity()");
        list.innerHTML += '<option selected> Select a city </option>';
        document.getElementById("getData").appendChild(list);

        for (var i = 0; i < bindings.length; i++) {
            var cities = document.getElementById('citiesList');
            var city = document.createElement('option');
            city.setAttribute("id", bindings[i].label.value);
            city.setAttribute("name", bindings[i].label.value);
            var cityName = document.createTextNode(bindings[i].label.value);

            city.appendChild(cityName);
            cities.appendChild(city);
        }
    });
}

function getSelectedCountry() {
    var countries = document.getElementById('countriesList');
    var country = countries.options[countries.selectedIndex].id;
    getAllCities(country);
}

function getSelectedCity() {
	var cities = document.getElementById('citiesList');
	var city = cities.options[cities.selectedIndex].id;
	window.location.replace("city.html?id=" + city);
}

/* Taken from: https://html-online.com/articles/get-url-parameters-javascript/
Function that return url parameters */
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function getCityInfo() {
    var cityName = getUrlVars()["id"];

    if (cityName == null) {
        window.location.href = "index.html";
    }
    else {
        /* Table head */
        var tableHead = document.getElementById('tableHead');

        var tableTitle = document.createElement('tr');
        var tableTitleCell = document.createElement('th');
        tableTitleCell.setAttribute("colspan", 6);
        var tableTitleCellValue = document.createTextNode(cityName);
        tableTitleCell.appendChild(tableTitleCellValue);
        tableTitle.appendChild(tableTitleCell);
        tableHead.appendChild(tableTitle);

        var tableAttributes = document.createElement('tr');

        var tableAtt = document.createElement('td');
        var tableAttValue = document.createTextNode("Bike station Name");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableAtt = document.createElement('td');
        tableAttValue = document.createTextNode("Location");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableAtt = document.createElement('td');
        tableAttValue = document.createTextNode("Available bikes");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableAtt = document.createElement('td');
        tableAttValue = document.createTextNode("Free spots");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableAtt = document.createElement('td');
        tableAttValue = document.createTextNode("Total capacity");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableAtt = document.createElement('td');
        tableAttValue = document.createTextNode("Payment");
        tableAtt.appendChild(tableAttValue);
        tableAttributes.appendChild(tableAtt);

        tableHead.appendChild(tableAttributes);



        /* Table body */
        var query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            " SELECT DISTINCT ?label ?id ?lat ?long ?available ?free ?total ?cardPaiement WHERE {" +
            " ?city rdfs:label \"" + cityName + "\" ." +
            " ?city <http://www.example.com/bikeStations> ?bikeStation ." +
            " ?bikeStation rdfs:label ?label ." +
            " ?bikeStation <http://www.example.com/id> ?id ." +
            " ?bikeStation <http://www.w3.org/2003/01/geo/wgs84_pos#lat> ?lat ." +
            " ?bikeStation <http://www.w3.org/2003/01/geo/wgs84_pos#long> ?long ." +
            " ?bikeStation <http://www.example.com/available> ?available ." +
            " ?bikeStation <http://www.example.com/free> ?free ." +
            " ?bikeStation <http://www.example.com/total> ?total ." +
            " ?bikeStation <http://www.example.com/cardPaiement> ?cardPaiement ." +
            "} ";

        var responseUrl = "http://localhost:3030/bike_station_db" + "?query=" + encodeURIComponent(query) + "&output=json";
        console.log(responseUrl);

        $.getJSON(responseUrl, function (jsonData) {
            /* Parse json file */
            var bindings = jsonData.results.bindings;

            for (var i = 0; i < bindings.length; i++) {
                var table = document.getElementById('tableBody');

                var bikeStation = document.createElement('tr');

                /* Bike station name */
                var bikeStationName = document.createElement('td');
                var bikeStationNameValue = document.createTextNode(bindings[i].label.value);
                bikeStationName.appendChild(bikeStationNameValue);
                bikeStation.appendChild(bikeStationName);

                /* Bike station location */
                var bikeStationLocation = document.createElement('td');
                bikeStationLocation.setAttribute("id", "location");
                var mapTitle = cityName + " - " + bindings[i].label.value
                initMap(bindings[i].lat.value, bindings[i].long.value, bikeStationLocation, mapTitle);
                bikeStation.appendChild(bikeStationLocation);

                /* Available bikes */
                var bikeStationAvailable = document.createElement('td');
                var bikeStationAvailableValue = document.createTextNode(bindings[i].available.value);
                bikeStationAvailable.appendChild(bikeStationAvailableValue);
                bikeStation.appendChild(bikeStationAvailable);

                /* Free spots */
                var bikeStationFree = document.createElement('td');
                var bikeStationFreeValue = document.createTextNode(bindings[i].free.value);
                bikeStationFree.appendChild(bikeStationFreeValue);
                bikeStation.appendChild(bikeStationFree);

                /* Total capacity */
                var bikeStationTotal = document.createElement('td');
                var bikeStationTotalValue = document.createTextNode(bindings[i].total.value);
                bikeStationTotal.appendChild(bikeStationTotalValue);
                bikeStation.appendChild(bikeStationTotal);

                /* Paiement method */
                var bikeStationPaiement = document.createElement('td');
                if (bindings[i].cardPaiement.value === "1") {
                    var bikeStationPaiementValue = document.createTextNode("Card accepted");
                }
                else {
                    var bikeStationPaiementValue = document.createTextNode("Card not accepted");
                }
                bikeStationPaiement.appendChild(bikeStationPaiementValue);
                bikeStation.appendChild(bikeStationPaiement);

                table.appendChild(bikeStation);
            }
        });
    }
}


/* Taken from: https://nouvelle-techno.fr/actualites/2017/12/06/pas-a-pas-inserer-une-carte-google-maps-avec-lapi-google-maps-javascript
*  Function to show the map using google maps api */
function initMap(lat, lon, id, mapTitle) {
    var map = new google.maps.Map(id, {
        center: new google.maps.LatLng(lat, lon),
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: true,
        scrollwheel: false,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
        },
        navigationControl: true,
        navigationControlOptions: {
            style: google.maps.NavigationControlStyle.ZOOM_PAN
        }
    });

    var latLon = new google.maps.LatLng(parseFloat(lat),parseFloat(lon));

    var marker = new google.maps.Marker({
        position: latLon,
        title: mapTitle,
        map: map
    });
    marker.setMap(map);
}