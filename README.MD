# Task
The challenge is about integrating with a third-party data provider (Robinhood in this case).
As the market data team, we are responsible for all data provided in Midas related to
financial instruments and this requires us to integrate with many different data providers. We
want you to experience this process closely.
You are given an SQL file that contains `instrument` table creation and seed data. You can
find this file here: https://gist.github.com/ahmetuysal/431cab9bdf6f6f68c75b389522879d4b

Let’s dive in!

## Part I: Market Sync
You will use the Robinhood markets endpoint to retrieve and save all markets to the
database.
GET https://api.robinhood.com/markets
- [x] Market table creation: columns should be id, code (mic field in Robinhood response),
  symbol (acronym field in Robinhood response), name, country, and website
- [x] REST endpoint to trigger market sync (retrieving from the provider and updating the
  database)
  
## Part II: Instrument Sync
  You will use the Robinhood instruments endpoint to retrieve and enhance instruments in the
  database.
  GET https://api.robinhood.com/instruments/?symbol=AAPL

- [x] Initially, the instrument table only contained id and symbol columns. You are
  expected to alter the instrument table to add new columns: name, custom_name
  (simple_name field in Robinhood response), and market_id (you will have to find the
  corresponding market record by interpreting the market field in Robinhood response)
- [x] REST endpoint to trigger instrument sync for all symbols in the database (retrieving
  from the provider and updating the database)

## Part III: Instrument Retrieve
You are expected to provide two REST endpoints to
- [ ] Retrieving all instruments
- [x] Retrieving instrument by given symbol

## Technical Requirements
- [x] You should use Java 17 and Spring Boot 
- [x] You can use any relational database (PostgreSQL is preferred)
- [ ] Your submission should be runnable without any modifications
- [ ] Your code should be adequately tested (unit & integration)
- [ ] Bonus: Containerize your application with Docker
- [ ] Bonus: Cache implementation for retrieving stock by the symbol.