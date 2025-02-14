set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/solar
set SPRING_DATASOURCE_USERNAME=njevcsawnings
set SPRING_DATASOURCE_PASSWORD=NJEvcsAwnings!2Root
set SPRING_CACHE_TYPE=simple
set USE_REDIS_CACHE=false
set SPRING_REDIS_HOST=localhost
set SPRING_REDIS_PORT=6379
call mvn clean install -Dmaven.test.skip=true
call java -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y -jar target\pvawnings-0.0.1-SNAPSHOT.jar -Xms1024m -Xmx2048m