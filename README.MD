# JavaSpringBoot-StocksAndMarketsBackend
Application can be run via `docker-compose up -d`. This will populate the database (PostgreSQL) using the query
files in `init-scripts`. After running the containers you can use the following endpoints:

```
POST: localhost:8080/api/instrument/sync
POST: localhost:8080/api/market/sync
GET : localhost:8080/api/instrument/get/<SYMBOL>
GET : localhost:8080/api/instrument/getall
```
