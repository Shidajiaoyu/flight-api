-- 插入示例用户（密码需用 BCrypt 加密）
INSERT INTO users(email, password, first_name, last_name, country, phone)
VALUES('test@example.com', '$2a$10$DOWSDjGfUO6H9LlIfk9y0e9r5zzu.IntHblYj.u9xkoiSl6S3hwe', 'Test', 'User', 'USA', '1234567890');

-- 插入示例航班
INSERT INTO flights(flight_number, from_airport, to_airport, departure_time, arrival_time, airline, direct, base_price, taxes, seat_class)
VALUES ('FL123', 'New York', 'London', '2025-06-15 08:00:00', '2025-06-15 20:00:00', 'AirExample', true, 500.00, 50.00, 'Economy');
