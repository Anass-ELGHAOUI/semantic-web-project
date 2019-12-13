<!DOCTYPE html>
<html
	base="http://localhost:63342/semantic-web-project-web-app/index.html"
	lang="en"
	prefix="ex: http://example.org/
			rdfs: http://www.w3.org/2000/01/rdf-schema#
			geo: http://www.w3.org/2003/01/geo/wgs84_pos#
			xsd: http://www.w3.org/2001/XMLSchema#
			dbo: http://dbpedia.org/ontology/
			dbr: http://dbpedia.org/page/"
>
<head>
	<meta charset="UTF-8">
	<title>Bike stations</title>

	<link rel="stylesheet" type="text/css" href="css/index.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDMy2HfZYaVHg2uuz9rVG1uYOyy-7wilNs&libraries=places"></script>
	<script type="text/javascript" src="js/rdf.js"></script>
    <script>
        google.maps.event.addDomListener(window, 'load', initializeCountry);
        google.maps.event.addDomListener(window, 'load', initializeCity);
    </script>
</head>

<body onload="getAllCountries()">
	<div id="getDataIndex"></div>

	<hr/>

	<div id="setDataIndex">
		<h1>Upload new data</h1>

		<form id="userForm" method="GET" action="upload.php">
			<label>Country: </label>
			<input type="text" id="country" name="country" required>

			<label>City: </label>
			<input type="text" id="city" name="city" required>

			<br/><br/>

			<input type="submit" value="Submit"/>
		</form>
	</div>
</body>

</html>

