Author: **Farrukh Mirza**  
Date: 13/07/2016  
Location: Dublin, Republic of Ireland  

Purpose
========

This service exposes a RESTful interface to create PDF documents from HTML/CSS/JSON.
This service is based on [Spring-Boot framework](http://projects.spring.io/spring-boot/) and Servlet 3.0 technology.
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
	
	
**NOTE:** Due to the nature of **LGPL** license, any modifications made to this service **MUST** be contributed back to the community.

Build
======

#### Pre-requisites

	1. JDK8
	2. Maven
	3. Git 

#### Compile and build
By Default the service is built for Apache Tomcat server <code>mvn clean install</code>.  
<code>deploy.bat</code> and <code>run.bat</code> can also be used to deploy the war file to Tomcat and run Tomcat server, assuming {CATALINA_HOME} is setup correctly. Linux versions of these scripts can be created very easily.

	1. The service can be explicitly built for WildFly <code>mvn clean install -Pwildfly</code>.  
	2. The service can be explicitly built for Apache Tomcat. Build using <code>mvn clean install -Ptomcat</code>.  

The main difference between the two profiles is the location of the log files.

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
	


