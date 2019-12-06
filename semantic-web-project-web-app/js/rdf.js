function getAllCities() {
	var query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>" +
                "SELECT ?label WHERE {" +
                "  ?city a dbo:city ." +
                "  ?city rdfs:label ?label ." +
                "} ";
	
	var responseUrl = "http://localhost:3030/bike_station_db" + "?query=" + encodeURIComponent(query) + "&output=json";
	console.log(responseUrl);

	$.getJSON(responseUrl, function(jsonData) {
    	/* Parse json file */
    	var bindings = jsonData.results.bindings;

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

    $.getJSON(responseUrl, function(jsonData) {
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
            var bikeStationLocationValue = document.createTextNode(bindings[i].lat.value + " " + bindings[i].long.value);
            bikeStationLocation.appendChild(bikeStationLocationValue);
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
            var bikeStationPaiementValue = document.createTextNode(bindings[i].cardPaiement.value);
            bikeStationPaiement.appendChild(bikeStationPaiementValue);
            bikeStation.appendChild(bikeStationPaiement);

            table.appendChild(bikeStation);
        }
    });
}