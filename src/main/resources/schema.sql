CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  country VARCHAR(255),
  phone VARCHAR(255)
);

CREATE TABLE flights (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  flight_number VARCHAR(100),
  from_airport VARCHAR(100),
  to_airport VARCHAR(100),
  departure_time DATETIME,
  arrival_time DATETIME,
  airline VARCHAR(100),
  direct BOOLEAN,
  base_price DOUBLE,
  taxes DOUBLE,
  seat_class VARCHAR(50)
);

CREATE TABLE bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  reference_code VARCHAR(255) UNIQUE NOT NULL,
  user_id BIGINT,
  flight_id BIGINT,
  created_at DATETIME,
  status VARCHAR(50),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (flight_id) REFERENCES flights(id)
);
