# Простейшая программа, показывающая совместную работу Java и MySQL
Программа предназначена только для образовательных целей, использование в коммерческих приложениях не рекомендуется. 

### Системные требования
* **MySQL**, скачивается здесь: [https://www.mysql.com/downloads/](https://www.mysql.com/downloads/)
* **Java** (11-й версии или выше), скачивается здесь: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
* **Git**, скачивается здесь: [https://git-scm.com/downloads](https://git-scm.com/downloads)

### Установка


- Склонируйте программу в нужную вам директорию:
```
git clone https://github.com/itlat-mysql/mysql-basics-java.git .
```
- Запустите MySQL сервер и создайте базу данных **example** cо всеми необходимыми данными - файл для создания базы данных (**db-example.sql**) находится в корневой директории данного приложения. 


- В директории **src/main/resources/** приложения создайте файл **application.properties** и заполните его параметрами для соединения с базой данных. В качестве примера следует использовать файл **src/main/resources/application.properties.example**:
```
db.url=jdbc:mysql://localhost:3306/example?characterEncoding=UTF8
db.user=root
db.password=secret
```
- Зайдите в коммандной строке в корневую директорию данного приложения и выполните данную комманду:
```
.\mvnw.cmd jetty:run
```
- Затем откройте свой браузер и перейдите по следующему адресу: 
```
http://localhost:8080