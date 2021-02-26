1- mvn clean package
2- C:\ijworkspace\demo>docker build -t spring-boot-docker .
3- C:\ijworkspace\demo>docker run -d -p 8080:8080 --name demo spring-boot-docker