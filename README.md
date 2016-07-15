Author: **Farrukh Mirza**  
Date: 13/07/2016  
Location: Dublin, Republic of Ireland  

Purpose
========
HTML-PDF-Service is a Java [Spring Boot](http://projects.spring.io/spring-boot/) based microservice.  
It exposes a RESTful interface to convert well-formed HTML to PDF.  
The service only expects **HTML BODY contents** to be sent over in the service calls under html parameter (HEAD section will be included by default).  
The service allows the clients to optionally style the PDF output using CSS 2.1 style sheets.  
The service also allows the clients to optionally use HTML as a Template with JSON data.    

Even though there are many other similar projects available, the idea behind this microservice is to allow anyone to deploy it on existing Java based infrastructure within a public or private cloud environment.  
The service allows the users to quickly build HTML page with custom styling, try it out from the web console and then simply insert variables in the HTML body which will be replaced by JSON object or JSON array provided as a separate argument to the service.   
If a JSON array is provided with multiple JSON objects, then individual PDF outputs are merged together into a single PDF file and returned as such (try webconsole at  **{PROTOCOL}://{HOST:PORT}/html-pdf-service/**).  
In addition, this project also serves as a quick tutorial on building RESTful microservices and applications using Spring Boot in Java.  

This service requires Servlet 3.0 servlet container like Tomcat 8 or Wildfly 9+.  
This service uses:

1. [Flying Saucer Pdf library](https://github.com/flyingsaucerproject/flyingsaucer) to convert html and css documents (strings) into PDF.  
2. [Jackson](https://github.com/FasterXML/jackson) to handle html templating with Json data.
3. [Apache PDF Box](https://pdfbox.apache.org/) to merge multiple pdf files into one.
4. [Bootstrap](http://getbootstrap.com/) to style web page.   
5. [JQuery](https://jquery.com/) to test and control page behaviour.   
5. A few other pieces of javascript code were borrowed from different sources and converted into JQuery plugin. The original authors are acknowledged and credited in the respective files.   


Licenses
=========

**html-pdf-service** (this service) is provided under LGPL version 3 or later License.
Third party licenses are listed below.


1. FlyingSaucer is provided under LGPL License version 3 which in turn uses iText 2.1.7 under Mozilla Public License Version 1.1.
2. spring-boot is provided under APACHE License v2
3. apache-commons-lang3 is provided under APACHE License v2
4. pdfbox is provided under APACHE License v2
5. jackson is provided under APACHE License v2
	
	

Build
======

#### Pre-requisites

	1. JDK8
	2. Maven
	3. Git 

#### Compile, build and deploy
By Default the service is built for Apache Tomcat server <code>mvn clean install</code>.  
<code>deploy.bat</code> and <code>run.bat</code> can also be used to deploy the war file to Tomcat and run Tomcat server, assuming {CATALINA_HOME} is setup correctly. Linux versions of these scripts can be created very easily.

1. The service can be explicitly built for WildFly <code>mvn clean install -Pwildfly</code>.  
2. The service can be explicitly built for Apache Tomcat. Build using <code>mvn clean install -Ptomcat</code>.  
3. The service can be explicitly built for Embedded Tomcat Container. Build using <code>mvn clean install -Pstandalone</code>.  

The main difference between the two profiles is the location of the log files.

#### Standalone deployment

This microservice can be very easily deployed as a standalone executable by enabling embedded Tomcat.  

Simply build the project using standalone profile <code>mvn clean install -Pstandalone</code> and then run the resultant war <code>java -jar target\html-pdf-service.war</code>  

Now you can access the test page by pointing your browser to <code>http://localhost:8080/</code>  

**Note:** This, however, opens up the subject of port management in the production environment.

REST Endpoints
===============

Endpoint Types
--------------
There are two types of endpoints.  

	1. Actual Endpoints.  
	3. Management Endpoints (Spring Boot).  

#### Actual Endpoints

The service can be access from browser using <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/</code>. The main page serves as a testing area to try out different options.  

The actual service endpoints use HTTP POST and GET protocols with slight variations. They base endpoint is available at <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/service/convert/html/</code>  
The service can be invoked in two fashions:    
- The service can take the parameters in the **request body** by calling <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/service/convert/html/body</code>. In this case, the request body can contain a json object with html, css, json fields, where css and json fields are optional, e.g., <code> {"html": "< h1 >This is a title</ h1 > < p >This is body</ p >", "css": "h1{color:blue;}"} </code>. This only supports **POST** requests. 
- The service can take the parameters as **request parameters** by calling <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/service/convert/html/params</code>. In this case, the request parameters contain html, css, json fields, where css and json fields are optional, e.g., <code> html="< h1 >This is a title</ h1 > < p >This is body</ p >"&css="h1{color:blue;}" </code>.  

If the service response is desired as a byte stream, the service endpoint can be appended by <code>/byte</code>, e.g.,
- <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/service/convert/html/body/byte</code>
- <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/service/convert/html/params/byte</code>

#### Management Endpoints

These endpoints are provided by the spring-boot framework.  
They are generally available at <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/</code>

	1. <code>/health</code> shows general health of the application
	2. <code>/info</code> shows custom service info (Not in use)

Endpoints
--------------

Base Application URL: <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/</code> e.g., <code>http://localhost:8080/html-pdf-service</code>  
Base Service URL: <code>{PROTOCOL}://{HOST:PORT}/html-pdf-service/</code> e.g., <code>http://localhost:8080/html-pdf-service/service/convert/html</code>  

The service endpoints get appended to the base service endpoint url, e.g., <code>http://localhost:8080/html-pdf-service/service/convert/html/params</code>.

1. <code>/params</code> takes request parameters and responds with the file in the HTTP Servlet Response. 
2. <code>/params/byte</code> takes request parameters and will return a byte array of the generated PDF file. 
3. <code>/body</code> takes request body as json and responds with the file in the HTTP Servlet Response. 
4. <code>/body/byte</code> takes request body as json and will return a byte array of the generated PDF file. 


NOTES
======

	1. FlyingSaucer is able to pull images and place into PDF, however, the image url must be publicly accessible.
	

TESTED
======

Application Servers
---------------------

This service is tested on the following application servers:

1. Apache Tomcat version 8.0.28
2. WildFly 10.0.0.Final
3. Standalone deployment (Embedded Tomcat)  

Web Browsers
---------------------
This service is tested on the following web browsers:

1. FireFox 47.0
2. Google Chrome Version 51.0.2704.106 m
3. Internet Explorer 11
