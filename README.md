## My Retail Product Service

###### Local Setup Prerequisite
  Java8
  MongoDB
  Install Lombok plugin and enable annotation processing
  
#### Project Build
     ./gradlew clean build

### Start local server
    ./gradlew clean bootRun

#### Health check 
      http://localhost:50502/actuator/health

#### Docker Build & docker Run
    
    docker build --tag myretail-product-service .
                  Or
    docker pull sravan008/myretail    
    docker run -d --name myretail-product-service --net=host -p 50502:50502 <container-id>
##### Running Mongo Container locally
      docker run --name some-mongo -d mongo:tag