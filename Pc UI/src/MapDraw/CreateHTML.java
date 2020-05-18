package MapDraw;

import Node_Package.NodeList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateHTML {
    //properties.

    //constructors.
    public CreateHTML()
    {

    }

    //methods.

    /**
     * Returns the first location of the NodeList to initialize the map.
     * Used to initialize the new HTML function in the createLatLng method.
     * @param ndList
     * @return String.
     */
    private String intializeCoordinate( NodeList ndList ){
        return "{lat: " + ndList.get( 0 ).getX() + "," + " lng: " + ndList.get( 0 ).getY() + "}," ;
    }

    /**
     * Creates the function which draws the path between the saved locations as a String.
     * Used to create the new HTML file in the overwriteFile method.
     * @param ndList
     * @return String.
     */
    private String createLatLng( NodeList ndList ){
        String holder = "{lat: " + ndList.get( 0 ).getX() + "," + " lng: " + ndList.get( 0 ).getY() + "}";

        for( int i = 0; i < ndList.size(); i++ ){
            holder += "," + "\n" + "{lat: " + ndList.get( i ).getX() + "," + " lng: " + ndList.get( i ).getY() + "}";
        }

        String initMap = "function initMap() {\n" +
                "  var map = new google.maps.Map(document.getElementById('map'), {\n" +
                "    zoom: 12,\n" +
                "    center: "+ this.intializeCoordinate( ndList ) + "\n" +
                "    mapTypeId: 'terrain'\n" +
                "  });\n" +
                "\n" +
                "  var flightPlanCoordinates = [\n" +
                        holder +
                "  ];\n" +
                "  var flightPath = new google.maps.Polyline({\n" +
                "    path: flightPlanCoordinates,\n" +
                "    geodesic: true,\n" +
                "    strokeColor: '#FF0000',\n" +
                "    strokeOpacity: 1.0,\n" +
                "    strokeWeight: 2\n" +
                "  });\n" +
                "\n" +
                "  flightPath.setMap(map);\n";

        return initMap;
    }

    /**
     * Sets the HTML file as the file with the given locations with the path drawn between them.
     * @param filePath
     * @param ndList
     */
    public void overwriteFile( String filePath, NodeList ndList ){
        String htmlPage = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <title>Route Optimizer Map</title>\n" +
                "    <style>\n" +
                "      /* Always set the map height explicitly to define the size of the div\n" +
                "       * element that contains the map. */\n" +
                "      #map {\n" +
                "        height: 100%;\n" +
                "        width: 74%;\n" +
                "      }\n" +
                "      /* Optional: Makes the sample page fill the window. */\n" +
                "      html, body {\n" +
                "        height: 100%;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "      #floating-panel {\n" +
                "        position: absolute;\n" +
                "        top: 10px;\n" +
                "        left: 25%;\n" +
                "        z-index: 5;\n" +
                "        background-color: #fff;\n" +
                "        padding: 5px;\n" +
                "        border: 1px solid #999;\n" +
                "        text-align: center;\n" +
                "        font-family: 'Roboto','sans-serif';\n" +
                "        line-height: 30px;\n" +
                "        padding-left: 10px;\n" +
                "      }\n" +
                "      #label {\n" +
                "        font-size: 15px;\n" +
                "        position: absolute;\n" +
                "        top: 15px; \n" +
                "        right: 125px;\n" +
                "      \tfloat: right;\n" +
                "      \twidth: 10em;\n" +
                "      \ttext-align: left;\n" +
                "        vertical-align: top;\n" +
                "      }\n" +
                "\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"floating-panel\">\n" +
                "      <input onclick=\"clearMarkers();\" type=button value=\"Hide Markers\">\n" +
                "      <input onclick=\"showMarkers();\" type=button value=\"Show All Markers\">\n" +
                "      <input onclick=\"deleteMarkers();\" type=button value=\"Delete Markers\">\n" +
                "      <input onclick=\"readFromFile();\" type=button value=\"read\">\n" +
                "\n" +
                "     \n" +
                "    </div>\n" +
                "    <div id=\"map\"></div>\n" +
                "    <label id = \"label\">\n" +
                "    <p>Points</p>\t\n" +
                "    </label>\n" +
                "    <p>Click on the map to add markers.</p>\n" +
                "    <script>\n" +
                "\n" +
                "      // In the following example, markers appear when the user clicks on the map.\n" +
                "      // The markers are stored in an array.\n" +
                "      // The user can then click an option to hide, show or delete the markers.\n" +
                "      var map;\n" +
                "      var markers = [];\n" +
                "      var labelIndex = 1;\n" +
                "      var string = \"\";\n" +
                "\n" +
                this.createLatLng( ndList ) +
                "\n" +
                "\n" +
                "        //Hopefully this works like a constructor.\n" +
                "        //I am hoping that as we read the data from txt File, Several Location objects will\n" +
                "        //be instantiazed with the given x (longtitude) & y (latitude) values.\n" +
                "        //these should be stored in an array of some sort and when drawing, the x & y values \n" +
                "        //will be used. HOPEFULLY.\n" +
                "        function Location( longitude, latitude ){\n" +
                "            this.longitude = longitude;\n" +
                "            this.latitude = latitude;\n" +
                "        }\n" +
                "\n" +
                "        // This event listener will call addMarker() when the map is clicked.\n" +
                "        map.addListener('click', function(event) {\n" +
                "          addMarker(event.latLng);\n" +
                "\n" +
                "          //console.log(labelIndex + getPosition());\n" +
                "\n" +
                "        });\n" +
                "\n" +
                "        \n" +
                "        \n" +
                "      }\n" +
                "\n" +
                "      // Adds a marker to the map and push to the array.\n" +
                "      function addMarker(location) {\n" +
                "        var marker = new google.maps.Marker({\n" +
                "          position: location,\n" +
                "          \n" +
                "          label: labelIndex.toString(),\n" +
                "          \n" +
                "          map: map,\n" +
                "          draggable: true\n" +
                "        });\n" +
                "        markers.push(marker);\n" +
                "        \n" +
                "        string += (marker.getPosition().lat() + \" \" + marker.getPosition().lng() + \"\\n\");\n" +
                "        //document.getElementById('Points').innerHTML = string;\n" +
                "        console.log(string);\n" +
                "        document.getElementById(\"label\").innerHTML = string;\n" +
                "       \n" +
                "        labelIndex++;\n" +
                "      }\n" +
                "\n" +
                "      // Sets the map on all markers in the array.\n" +
                "      function setMapOnAll(map) {\n" +
                "        for (var i = 0; i < markers.length; i++) {\n" +
                "          markers[i].setMap(map);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      // Removes the markers from the map, but keeps them in the array.\n" +
                "      function clearMarkers() {\n" +
                "        setMapOnAll(null);\n" +
                "      }\n" +
                "\n" +
                "      // Shows any markers currently in the array.\n" +
                "      function showMarkers() {\n" +
                "        setMapOnAll(map);\n" +
                "      }\n" +
                "\n" +
                "      // Deletes all markers in the array by removing references to them.\n" +
                "      function deleteMarkers() {\n" +
                "        clearMarkers();\n" +
                "        document.getElementById(\"label\").innerHTML = \"\";\n" +
                "        string = \"\";\n" +
                "        labelIndex = 1;\n" +
                "        markers = [];\n" +
                "      }\n" +
                "\n" +
                "      function readFromFile() {\n" +
                "        jQuery.get('/Users/canonal/Desktop/CS102 Group Project/CS102-Group-1F-Project/Route Opt Google Maps in Java Swing/HTML/result.txt', function(data) {\n" +
                "          var locArr = data.split(\",\");\n" +
                "          console.log(locArr);\n" +
                "          //optional stuff to do after success\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        \n" +
                "      }\n" +
                "\n" +
                "\n" +
                "      \n" +
                "\n" +
                "    </script>\n" +
                "    \n" +
                "    <script async defer\n" +
                "    src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBjo9_uBERd5oxWphvLurPH-zOFkW_tZig&callback=initMap\">\n" +
                "    </script>\n" +
                "  </body>";

        File file = new File( filePath );
        file.delete();

        File newFile = new File( filePath );

        try{
            FileWriter fileWriter = new FileWriter( newFile, false );
            fileWriter.write( htmlPage );
            fileWriter.close();
        }catch( IOException e ){
            e.printStackTrace();
        }


    }

    /**
     * Sets the HTML file to be the initial HTML file which we can select markers and no path is drawn.
     * @param filePath
     */
    public void returnToOgHTML( String filePath ){
        String htmlPage = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <title>Route Optimizer Map</title>\n" +
                "    <style>\n" +
                "      /* Always set the map height explicitly to define the size of the div\n" +
                "       * element that contains the map. */\n" +
                "      #map {\n" +
                "        height: 100%;\n" +
                "        width: 74%;\n" +
                "      }\n" +
                "      /* Optional: Makes the sample page fill the window. */\n" +
                "      html, body {\n" +
                "        height: 100%;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "      #floating-panel {\n" +
                "        position: absolute;\n" +
                "        top: 10px;\n" +
                "        left: 25%;\n" +
                "        z-index: 5;\n" +
                "        background-color: #fff;\n" +
                "        padding: 5px;\n" +
                "        border: 1px solid #999;\n" +
                "        text-align: center;\n" +
                "        font-family: 'Roboto','sans-serif';\n" +
                "        line-height: 30px;\n" +
                "        padding-left: 10px;\n" +
                "      }\n" +
                "      #label {\n" +
                "        font-size: 15px;\n" +
                "        position: absolute;\n" +
                "        top: 15px; \n" +
                "        right: 125px;\n" +
                "      \tfloat: right;\n" +
                "      \twidth: 10em;\n" +
                "      \ttext-align: left;\n" +
                "        vertical-align: top;\n" +
                "      }\n" +
                "\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"floating-panel\">\n" +
                "      <input onclick=\"clearMarkers();\" type=button value=\"Hide Markers\">\n" +
                "      <input onclick=\"showMarkers();\" type=button value=\"Show All Markers\">\n" +
                "      <input onclick=\"deleteMarkers();\" type=button value=\"Delete Markers\">\n" +
                "      <input onclick=\"readFromFile();\" type=button value=\"read\">\n" +
                "\n" +
                "     \n" +
                "    </div>\n" +
                "    <div id=\"map\"></div>\n" +
                "    <label id = \"label\">\n" +
                "    <p>Points</p>\t\n" +
                "    </label>\n" +
                "    <p>Click on the map to add markers.</p>\n" +
                "    <script>\n" +
                "\n" +
                "      // In the following example, markers appear when the user clicks on the map.\n" +
                "      // The markers are stored in an array.\n" +
                "      // The user can then click an option to hide, show or delete the markers.\n" +
                "      var map;\n" +
                "      var markers = [];\n" +
                "      var labelIndex = 1;\n" +
                "      var string = \"\";\n" +
                "\n" +
                "      function initMap() {\n" +
                "        var bilkent = {lat: 39.878040, lng: 32.748825};\n" +
                "\n" +
                "        map = new google.maps.Map(document.getElementById('map'), {\n" +
                "          zoom: 12,\n" +
                "          center: bilkent,\n" +
                "          mapTypeId: 'hybrid'\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "\n" +
                "        // This event listener will call addMarker() when the map is clicked.\n" +
                "        map.addListener('click', function(event) {\n" +
                "          addMarker(event.latLng);\n" +
                "\n" +
                "          //console.log(labelIndex + getPosition());\n" +
                "\n" +
                "        });\n" +
                "\n" +
                "      }\n" +
                "\n" +
                "      // Adds a marker to the map and push to the array.\n" +
                "      function addMarker(location) {\n" +
                "        var marker = new google.maps.Marker({\n" +
                "          position: location,\n" +
                "          \n" +
                "          label: labelIndex.toString(),\n" +
                "          \n" +
                "          map: map,\n" +
                "          draggable: true\n" +
                "        });\n" +
                "        markers.push(marker);\n" +
                "        \n" +
                "        string += (marker.getPosition().lat() + \" \" + marker.getPosition().lng() + \"\\n\");\n" +
                "        //document.getElementById('Points').innerHTML = string;\n" +
                "        console.log(string);\n" +
                "        document.getElementById(\"label\").innerHTML = string;\n" +
                "       \n" +
                "        labelIndex++;\n" +
                "      }\n" +
                "\n" +
                "      // Sets the map on all markers in the array.\n" +
                "      function setMapOnAll(map) {\n" +
                "        for (var i = 0; i < markers.length; i++) {\n" +
                "          markers[i].setMap(map);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      // Removes the markers from the map, but keeps them in the array.\n" +
                "      function clearMarkers() {\n" +
                "        setMapOnAll(null);\n" +
                "      }\n" +
                "\n" +
                "      // Shows any markers currently in the array.\n" +
                "      function showMarkers() {\n" +
                "        setMapOnAll(map);\n" +
                "      }\n" +
                "\n" +
                "      // Deletes all markers in the array by removing references to them.\n" +
                "      function deleteMarkers() {\n" +
                "        clearMarkers();\n" +
                "        document.getElementById(\"label\").innerHTML = \"\";\n" +
                "        string = \"\";\n" +
                "        labelIndex = 1;\n" +
                "        markers = [];\n" +
                "      }\n" +
                "\n" +
                "      function readFromFile() {\n" +
                "        jQuery.get('/Users/canonal/Desktop/CS102 Group Project/CS102-Group-1F-Project/Route Opt Google Maps in Java Swing/HTML/result.txt', function(data) {\n" +
                "          var locArr = data.split(\",\");\n" +
                "          console.log(locArr);\n" +
                "          //optional stuff to do after success\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        \n" +
                "      }\n" +
                "\n" +
                "\n" +
                "      \n" +
                "\n" +
                "    </script>\n" +
                "    \n" +
                "    <script async defer\n" +
                "    src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBjo9_uBERd5oxWphvLurPH-zOFkW_tZig&callback=initMap\">\n" +
                "    </script>\n" +
                "  </body>";

        File file = new File( filePath );
        file.delete();

        File newFile = new File( filePath );

        try{
            FileWriter fileWriter = new FileWriter( newFile, false );
            fileWriter.write( htmlPage );
            fileWriter.close();
        }catch( IOException e ){
            e.printStackTrace();
        }

    }


}