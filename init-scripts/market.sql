CREATE TABLE market (
                        id BIGSERIAL PRIMARY KEY,
                        code VARCHAR(10) NOT NULL,
                        symbol VARCHAR(10) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        country VARCHAR(50) NOT NULL,
                        website VARCHAR(100)
);