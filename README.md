Guice Repo Addressbook
============

Inspired by tomivirkki/cdiutils-addressbook

    This application is a slightly modified version of the sample application cdiutils-addressbook 
(https://github.com/tomivirkki/cdiutils-addressbook.git). Addon cdiutils-1.2.0 was replaced by guice vaadin mvp addon 
(http://code.google.com/p/guice-vaadin-mvp/). Spring data jpa (bridged with guice by 
https://code.google.com/p/guice-repository/) was used for persistence.
    Application can be deployed to servlet container such as tomcat 7 (because it supports Servlet 3.0
specification). For tomcat 7 with last versions you need to remember that procedure of loading of jdbc
drivers was changed (see http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html#DriverManager, 
the service provider mechanism and memory leaks) So, to use old mechanism you need configure server.xml this way: 
<Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" driverManagerProtection="false"/>
The main idea of this sample is to show how the MVP paradigm can be used in the java web stack with guice 
dependency injection.
