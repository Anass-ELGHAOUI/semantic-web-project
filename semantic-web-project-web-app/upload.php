<!DOCTYPE html>
<html
    base="http://localhost:63342/semantic-web-project-web-app/upload.html"
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
    <script type="text/javascript" src="js/rdf.js"></script>
    <script type="text/javascript" src="js/parsers.js"></script>
</head>

<body>
    <?php
        if (isset($_GET['city']) && isset($_GET['country'])) {
            echo '<input type="hidden" id="cityName" value="'.$_GET['city'].'">';
            echo '<input type="hidden" id="countryName" value="'.$_GET['country'].'">';

            if ($_GET['city'] == "Montpellier" && $_GET['country'] == "France" ||
                $_GET['city'] == "Strasbourg" && $_GET['country'] == "France" ||
                $_GET['city'] == "Lyon" && $_GET['country'] == "France") {

    ?>
        <script>cityAlreadyExists()</script>
    <?php
            }
        }
        else {
            header("Location: index.php");
        }
    ?>
    <form action="" method="POST">
        <div id="uploadData">
            <h1>Upload new data</h1>

            <br/><br/>
            <SELECT id="fileFormat" name="fileFormat" required>
                <OPTION selected value="">Choose a file format</OPTION>
                <OPTION value="json">Json</OPTION>
                <OPTION value="xml">XML</OPTION>
                <OPTION value="csv">CSV</OPTION>
            </SELECT>

            <br/><br/>

            <label>Content: </label>
            <textarea name="content" id="content" rows="10" cols="100" required></textarea>
            <!--            <input type="file" name="content" id="content">-->

            <br/><br/>

            <button type="button" onclick="parser()">Submit</button>
        </div>

        <div id="specifyAttributes">
            <hr>

            <h1>Define uploaded file's attributes</h1>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute1" name="attribute1" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning1" name="attributeMeaning1" type="text" value="Bike station name" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute2" name="attribute2" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning2" name="attributeMeaning2" type="text" value="Latitude" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute3" name="attribute3" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning3" name="attributeMeaning3" type="text" value="Longitude" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute4" name="attribute4" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning4" name="attributeMeaning4" type="text" value="Available bikes" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute5" name="attribute5" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning5" name="attributeMeaning5" type="text" value="Free spots" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute6" name="attribute6" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning6" name="attributeMeaning6" type="text" value="Total spots" disabled>

            <br/><br/>

            <label>Attribute: </label>
            <SELECT id="attribute7" name="attribute7" required>
                <OPTION selected value="">Choose an attribute</OPTION>
            </SELECT>
            <label>Meaning: </label>
            <input id="attributeMeaning7" name="attributeMeaning7" type="text" value="Payment method" disabled>

            <br/><br/>

            <input name = "submit" type="submit" value="Submit">
        </div>
    </form>

    <?php
    if (isset($_POST['submit'])) {
        if (isset($_POST['attribute1']) &&
            isset($_POST['attribute2']) &&
            isset($_POST['attribute3']) &&
            isset($_POST['attribute4']) &&
            isset($_POST['attribute5']) &&
            isset($_POST['attribute6']) &&
            isset($_POST['attribute7']) &&
            isset($_POST['delimiter']) &&
            isset($_POST['content']) &&
            isset($_POST['fileFormat'])) {
            /* CSV file */
            if ($_POST['fileFormat'] == "csv") {
                $csvData = $_POST['content'];

                $lines = explode(PHP_EOL, $csvData);
                $array = array();
                foreach ($lines as $line) {
                    $array[] = str_getcsv($line);
                }
    //                    print_r($array[0]);
    //                    echo 'ici ' . sizeof($array);

                /* Find attributes index following user choice */
                $attribute1 = 0;
                $attribute2 = 0;
                $attribute3 = 0;
                $attribute4 = 0;
                $attribute5 = 0;
                $attribute6 = 0;
                $attribute7 = 0;

                for ($j = 0; $j < sizeof($array[0]); $j++) {
                    if ($_POST['attribute1'] == $array[0][$j]) {
                        $attribute1 = $j;
                    }
                    if ($_POST['attribute2'] == $array[0][$j]) {
                        $attribute2 = $j;
                    }
                    if ($_POST['attribute3'] == $array[0][$j]) {
                        $attribute3 = $j;
                    }
                    if ($_POST['attribute4'] == $array[0][$j]) {
                        $attribute4 = $j;
                    }
                    if ($_POST['attribute5'] == $array[0][$j]) {
                        $attribute5 = $j;
                    }
                    if ($_POST['attribute6'] == $array[0][$j]) {
                        $attribute6 = $j;
                    }
                    if ($_POST['attribute7'] == $array[0][$j]) {
                        $attribute7 = $j;
                    }
                }
                /* Write parsing results in file */
//                $content = $array[0][$attribute1].",".$array[0][$attribute2].",".$array[0][$attribute3].",".$array[0][$attribute4].",".$array[0][$attribute5].",".$array[0][$attribute6].",".$array[0][$attribute7]."\n";

                for ($i = 1; $i < sizeof($array); $i++) {
                    $content .= $array[$i][$attribute1].",".$array[$i][$attribute2].",".$array[$i][$attribute3].",".$array[$i][$attribute4].",".$array[$i][$attribute5].",".$array[$i][$attribute6].",".$array[$i][$attribute7]."\n";
                }
                $path = $_SERVER['DOCUMENT_ROOT'].'/semantic-web-project/Manually-added-files/'. $_GET['country']."::".$_GET['city'] . '.txt';
                $fp = fopen( $path,"wb");
                fwrite($fp,$content);
                fclose($fp);
            }
        }
    }
    ?>

</body>
</html>