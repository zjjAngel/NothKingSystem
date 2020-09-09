FROM java:8
WORKDIR /usr/
ADD NorthKingSys-0.0.1-SNAPSHOT.jar  NorthKingSys-0.0.1-SNAPSHOT.jar
#RUN bash -c 'touch /NorthKingSys-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["-java","-jar","/NorthKingSys-0.0.1-SNAPSHOT.jar"]