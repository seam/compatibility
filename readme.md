Seam Compatibility 
==================

This module is a collection of various tests to validate container compliance.


Running in JBoss AS (Managed)
-----------------------------
mvn clean install -Ditest-container=jbossas-managed

Running in JBoss AS (Remote)
----------------------------
Start the JBoss server, then run:

  mvn clean install -Ditest-container=jbossas-remote

Running in Glassfish
--------------------
Start the Glassfish server using: 

  asadmin start-domain domain1

Then run:

  mvn clean install -Ditest-container=glassfish-remote
