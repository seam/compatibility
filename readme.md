Seam Compatibility 
==================

This module is a collection of various tests to validate Java EE 6 container
compliance. They focus on problem areas that have, at one time or another,
compromised the portability of Seam 3.

Running in JBoss AS (Managed)
-----------------------------

Run the tests using:

    mvn test -Ditest-container=jbossas-managed

Running in JBoss AS (Remote)
----------------------------

Start the JBoss server using:

    $JBOSSAS_HOME/bin/run.sh
  
Then run the tests using:

    mvn test -Ditest-container=jbossas-remote

Running in Glassfish
--------------------

Start the Glassfish server using: 

    $GLASSFISH_HOME/bin/asadmin start-domain domain1

Then run the tests using:

    mvn test -Ditest-container=glassfish-remote
