# microservice_shop_final

# Стек технологий
- Java
- Maven
- MongoDB
- Spring Boot
- Lombok
- Mapstruct
- Apache Kafka
- Spring Cloud
- Eureka
- Zipkin
  
  # Основные возможности
  Rest API, для обеспечения работы магазина(упрощенный вид).

  Есть возможность создавать , удалять продукты. Также есть возможность редактирования основной информации о продукте. Реализована возможность учета количества продуктов на складе и проверки на наличие. Также можно просматривать какие продукты есть на складе и в каком количестве

  Также присутствует API для регистрации, удаления пользователя  и просмотра, изменения основный информации.

  Пользователь может добавлять продукты в корзину(+проверяется есть ли достаточное количество продукта на складе) и удалять из нее.

  И в конце пользователь может сделать заказ. 

  # Архитектура
  - configuration-modulee - модуль, использующий Spring Cloud Config Server для запуска конфигурационного сервера в режиме(конфигурация бд и порты).
  - eureka-module - модуль, который использует Spring Cloud Netflix Eureka в качестве встроенного сервера обнаружения.
  - ApiGateway-module - модуль, который использует Spring Cloud API gateway  в качестве прокси в архитектуре. Прокси анализирует запрос, перенаправляет его к нужному микросервису и возвращает ответ обратно.
  - ProductService - микросервис, который отвечает за создание, удаление, получение из бд , редактировние, проверку на наличие продуктов в магазине.
  - userModule - микросервис, который отвечает за создание, удаление, получение из бд , редактировние аккаунтов покупателей.
  - orderModule - микросервис, который отвечает за создание, получение из бд заказов пользователей.
  - cartModule - микросервис, который отвечает за добавление и удаление продуктов  из корзины(добавлять и удалять можно по одной штуке и по несколько).

  # Описание запросов
  ## User Module
  
  ### Получение всех пользователей
  -GET http://localhost:8080/user/findall
  
  ### Получение пользователя по Id
  -GET http://localhost:8080/user/find/{userId}
  {
          "userId": "653ec75fb090df0e439cf620"
  }

   ### Удаление  пользователя по Id
  -DELETE http://localhost:8080/user/{userId}
  {
          "userId": "653ec75fb090df0e439cf620"
  }

  ### Изменения основной информации пользователя по Id
  -PUT http://localhost:8080/user/{userId}
  {
         "firstName": "Jim"
  }

  ### Создание пользователя 
  -POST http://localhost:8080/user/save
  {
        "firstName": "Nick",
        "lastName": "Bond",
        "phone": "88005553535",
        "email": "nick@mail.ru",
        "address": {
            "street": "street",
            "city": "LA",
            "zip": "22123213"
        },
        "dateOfBirth": "2000-01-01"
  }

  

## Product Module
 ### Получение всех продуктов на складе
  -GET http://localhost:8080/products
  
  ### Получение продукта по Id
  -GET http://localhost:8080/products/{productNumber}
  {
          "productNumber": "653ec75fb090df0e439cf620"
  }

   ### Удаление  продукта по Id
  -DELETE http://localhost:8080/products/{productNumber}
  {
          "productNumber": "653ec75fb090df0e439cf620"
  }

  ### Изменения основной информации продукта по Id
  -PUT http://localhost:8080/products/{productNumber}
  {
         "productName": "Apple"
  }

  ### Создание продукта 
  -POST http://localhost:8080/products
  {
        "productName": "Scsdfvfdcscs",
        "productPrice": 1,
        "productDescription": "Csdvdcscs",
        "manufacturer": "CScsdfvfdcscs",
        "productNumInStock": 1,
        "dateOfCreate": "2000-02-02",
        "dateOfExpiration": "2000-02-02"
  }

  ### Проверка количества продукта 
  -GET http://localhost:8080/{productNumber}/isInStock
   {
          "productNumber": "653ec75fb090df0e439cf620"
   }



  ## Order Module
 ### Получение списка всех заказов 
  -GET http://localhost:8080/order/findAll

  ### Создание заказа 
  -POST http://localhost:8080/order
[
    {
        "productName": "apple",
        "productPrice": 10,
        "productDescription": "fruit",
        "manufacturer": "russia",
        "productNumInStock": 1000,
        "dateOfCreate": "2023-08-08",
        "dateOfExpiration": "2023-09-09"
    },
    {
        "productName": "tomato",
        "productPrice": 15,
        "productDescription": "vegitable",
        "manufacturer": "ukrain",
        "productNumInStock": 15000,
        "dateOfCreate": "2000-07-07",
        "dateOfExpiration": "2000-27-07"
    }
    ]


  ## Cart Module
  
 ### Добавление одной единицы продукта в корзину по Id пользователя
  -POST http://localhost:8080/cart/addCartForACustomer/{customerId}
            "customerId": "653ec75fb090df0e439cf620"
      {
        "productId": "653ec68459a6476f3fec64e0",
        "productName": "Scsdfvfdcscs",
        "productPrice": 1,
        "productDescription": "Csdvdcscs",
        "manufacturer": "CScsdfvfdcscs",
        "productNumInStock": 1,
        "dateOfCreate": "2000-02-02",
        "dateOfExpiration": "2000-02-02"
    }

    
 ### Добавление нескольких единиц продукта в корзину по Id пользователя
  -POST http://localhost:8080/cart/addProductToCartWithQuantity/{customerId}/quantity/{quantity}
            "customerId": "653ec75fb090df0e439cf620"
            "quantity":20
      {
        "productId": "653ec68459a6476f3fec64e0",
        "productName": "Scsdfvfdcscs",
        "productPrice": 1,
        "productDescription": "Csdvdcscs",
        "manufacturer": "CScsdfvfdcscs",
        "productNumInStock": 1,
        "dateOfCreate": "2000-02-02",
        "dateOfExpiration": "2000-02-02"
    }

### Удаление одной единицы продукта из корзины по Id пользователя
  -DELETE http://localhost:8080/cart/removeProductFromCart/{customerId}/product/{productId}
            "customerId": "653ec75fb090df0e439cf620"
            "productId": "653ec68459a6476f3fec64e0",
    
 ### Удаление нескольких единиц продукта из корзину по Id пользователя
  -DELETE http://localhost:8080/cart/removeProductFromCartWithQuantity/{customerId}/product/{productId}/quantity/{quantity}
            "customerId": "653ec75fb090df0e439cf620"
            "quantity":20

  ### Проверка корзины по Id пользователя
  -POST http://localhost:8080/cart/checkout/{customerId}
              "customerId": "653ec75fb090df0e439cf620"
