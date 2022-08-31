# Daily_planner

Description:
---
```
Приложение использует Spring Boot и движок шаблонов Thymeleaf.
Данные хранятся в базе Postgres, обмен проходит с помощью query-запросов и организует фильтрацию,
сортировку по значениям, регулируемую с помощью элементов управления в пользовательском интерфейсе.
Приложение развернуто в docker.
```  
screenshots:
---
 ![2022-07-05_20-56-46](https://user-images.githubusercontent.com/63203877/177387719-9c0cabb9-9ec7-4cff-8607-1a2a3c3e55fa.png)

Запуск .jar:
---
```
java -jar .\docker\java\Daily_planner-0.0.1-SNAPSHOT.jar
```
Docker:
---
```  
cd docker\ 
docker compose up
```

