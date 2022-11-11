# Quotation Management

- Repository for carrying out the closed scope project to the first IDP project.

### Environment required for execution

- [Java](https://www.java.com/pt-BR/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)
- [Postman](https://www.postman.com/downloads/)
- [Eclipse](https://www.eclipse.org/downloads/)
- [Git](https://git-scm.com)

### 🚀 How to run

- Clone this repository:
```
$ https://github.com/mairaalvs/Quotation-Management.git
```

- Run the following commands on Docker:
```
docker network create inatel
```

```
docker container run --name mysql --network=inatel -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql
```

```
docker container run --name stockmanager --network=inatel -p 8080:8080 -d adautomendes/stock-manage
```

### 💻 How it works

The application developed, called Quotation Management, is a REST based application whose purpose is to store quotes from stock market. A user can register as many quotes as he wants for the same stock and a user can register quotes from different stocks. But, a stock can only be registered in Quotation Management if it is already registered in Stock Manager.

To register a new stock in Stock Manager:
#### POST http://localhost:8080/stock
```
{
    "id": "mglu3", 
    "description": "Magazine Luiza" 
}
```

To read all stocks from Stock Manager:
#### GET http://localhost:8080/stock

With the stock registered in the Stock Manager, we can start registering in Quotation Management.


To register a new stock in Quotation Management:
#### POST http://localhost:8081/stock
{
	"stockId": "mglu3",
	"quotesMap": {
		"2022-03-10": 15.40
	}
}

To read all stocks from Quotation Management:
#### GET http://localhost:8081/stock
	
To read only one stock in Quote Management:	
#### GET http://localhost:8081/stock/stockId
	
	