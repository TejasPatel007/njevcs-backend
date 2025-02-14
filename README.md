# Java 11

Install Java 11 and set required environment variables:

Windows : [step-by-step-guide-to-install-jdk-11-on-windows](https://www.studytonight.com/post/step-by-step-guide-to-install-jdk-11-on-windows)

Linux : [how-to-install-openjdk-on-ubuntu-20-04](https://www.linode.com/docs/guides/how-to-install-openjdk-on-ubuntu-20-04/)

# Apache Maven

Install Apache Maven and set required environment variables:

Windows : [how-to-install-maven-on-windows-39ff317e40cf](https://medium.com/@gauravshah97/how-to-install-maven-on-windows-39ff317e40cf)

Linux : [how-to-install-and-configure-apache-maven-on-ubuntu-18-04](https://www.liquidweb.com/blog/how-to-install-and-configure-apache-maven-on-ubuntu-18-04/)

# MySQL Server

Install MySQL Server:

Use 'root' for both when prompted for username and password while installing the MySQL server.

Windows : [how-to-install-mysql-workbench-on-windows](https://www.freecodecamp.org/news/how-to-install-mysql-workbench-on-windows/)

Linux : [how-to-install-mysql-workbench-on-ubuntu](https://www.geeksforgeeks.org/how-to-install-mysql-workbench-on-ubuntu/)

# Redis

Install Redis:

Windows : [install-redis-on-windows](https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/install-redis-on-windows/)

Linux : [install-redis-on-linux](https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/install-redis-on-linux/)

Install Redis Insight (GUI): [install-redis-insight](https://redis.io/insight/)

# Eclipse IDE

Install Eclipse IDE:

Windows : [how-to-download-and-install-eclipse-on-windows](https://www.geeksforgeeks.org/how-to-download-and-install-eclipse-on-windows/)

Linux : [eclipse-install-ubuntu](https://www.geeksforgeeks.org/eclipse-install-ubuntu/)

After the installation, open Eclipse IDE, select Window > Preferences, and open up the configuration for Java > Code Style > Formatter. Click on the button labeled Import. and select the file /pvawnings/src/main/resources/eclipse-java-google-style.xml, and click OK.

To reformat any file, press Ctrl+Shift+F while inside that file.

### Execute Commands for build and deployment of the application

Create JAR package:

```bash
mvn clean install -Dmaven.test.skip=true
```

Execute JAR to access the application:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/solar?createDatabaseIfNotExist=true
export SPRING_DATASOURCE_USERNAME=njevcsawnings
export SPRING_DATASOURCE_PASSWORD='NJEvcsAwnings!2Root'
export SPRING_CACHE_TYPE=simple
export USE_REDIS_CACHE=false
export SPRING_REDIS_HOST=localhost
export SPRING_REDIS_PORT=6379

java -jar pvawnings.jar --spring.datasource.url=${SPRING_DATASOURCE_URL} --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD} --spring.redis.host=${SPRING_REDIS_HOST} --spring.redis.port=${SPRING_REDIS_PORT} --spring.cache.type=${SPRING_CACHE_TYPE} --use.redis.cache=${USE_REDIS_CACHE}

java -jar target\pvawnings-0.0.1-SNAPSHOT.jar
```

### Access Swagger Documentation to test API end points 

Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

