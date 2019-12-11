<!DOCTYPE html>
<html
		base="http://localhost:63342/semantic-web-project-web-app/city.html"
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

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<script src="https://maps.google.com/maps/api/js?key=AIzaSyDMy2HfZYaVHg2uuz9rVG1uYOyy-7wilNs" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/rdf.js"></script>
</head>

<body onload="getCityInfo()" id="body">
    <?php echo '<input type="hidden" id="cityName" value="'.$_GET['city'].'">'; ?>

	<div class="container-flex">
		<div id="rooms">
			<table id="table">
				<thead id="tableHead">
				</thead>

				<tbody id="tableBody">
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>