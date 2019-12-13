# semantic-web-project
Authors: 
<ul>
  <li>
		<a href="https://www.linkedin.com/in/anas-el-ghaoui-690326115/">EL GHAOUI ANASS</a>
	</li>
	<li>
		<a href="https://www.linkedin.com/in/walid-ouchtiti/">OUCHTITI Walid</a>
	</li>
</ul>
<br>

## Main objective

  - Make a Web application for finding available bicycles, using open data from the bicycle sharing systems of many cities.
  - Make it extensible to new cities as easily as possible.
  - Extend the application to provide availability of other smart city resources, such as parking places, carsharing services, etc.
  - Link the data with other data sources, such as information on the public transport systems or touristic data.

## Pedagogical objectives

  - Do a little software development, using Semantic Web programming frameworks
  - Setup and interact with an RDF database
  - Exploit multiple sources of heterogeneous data
  - Present information online with rich metadata

## How to run the code
### 1) Requirements
To run this code you will need an Apache HTTP server and the Apache Jena Fuseki engine.

### 2) Steps

**2-1- Create the triplestore** <br/>
First step is to create the triplestore, with the name: **"bike_station_db"** (Apache Jena Fuseki server must be running on port 3030), or create it with a custom name and change the url to the triplestore in the interface **"Constants**" in the Java project folder: [https://github.com/Anass-ELGHAOUI/semantic-web-project/tree/master/src/main/java](https://github.com/Anass-ELGHAOUI/semantic-web-project/tree/master/src/main/java)

**2-2- Run the Java code** <br/>
Before doing any of the remaining steps download the repository content and put it in the HTTP apache directory.
Second step is to run the Java code through the **"Main.java"** class. This Java program parses the given open data urls and loads it to the triplestore.

**2-3- Access the wep application** <br/>
The web application which is located in the folder: [https://github.com/Anass-ELGHAOUI/semantic-web-project/tree/master/semantic-web-project-web-app](https://github.com/Anass-ELGHAOUI/semantic-web-project/tree/master/semantic-web-project-web-app) can be accessed through the HTTP Apache server.

### 3) Additional information
On the web application you have two possible use cases:
**3-1- Access data on the web application** <br/>
You can check the pre-added data from the Java application, and also the data added by users.

**3-2- Add data to the web application** <br/>
In the front page of the web application, you have a form that you'll have to complete if you want to add a city.
Two file formats are supported to add data: XML and CSV.

**3-3- RDFa** <br/>
The page which displays information about the bike stations of a certain city has RDFa in it. You can get the RDFa content by copying the page code from the console (not by accessing the source code as the page is dynamically generated using JavaScript) and parsing it using the RDFa distiller: [https://www.w3.org/2012/pyRdfa/#distill_by_input](https://www.w3.org/2012/pyRdfa/#distill_by_input)
