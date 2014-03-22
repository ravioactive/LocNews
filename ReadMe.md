                     RUNNING LOCATION SEARCH WEB APPLICATION




 
1. location search web applicaion code is in 'processor' folder.
2. SOLR cores for the project have their solrconfig,xml and schema.xml in the 'solr' folder.
3. Build the WAR file. Copy the WAR file and paste it into the APACHE TOMCAT webapp folder.
4. Deploy the app on your Apache Tomcat installtion by the usual methods.
5. Access the application by typing the URL 'http://localhost:8080/LocationSearch/search'
6. To create a new WAR File from the application run the pom.xml file in the LocationSearch code by typing the command mvn clean install -DskipTests
7. The Data files for the GeoNames location database can be downloaded from www.geonames.org. Just download, update their locations in each core's <core>-data-config.xml. Default is 'C:/solr-4.5.1/example/project3/<core>/info'.
8. Email me for any queries at: ravioactive@gmail.com
