-- 
--
CREATE SCHEMA poi_db;
--
CREATE TABLE points_of_interest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location POINT NOT NULL SRID 4326,
    request_count INT DEFAULT 0,
    SPATIAL INDEX(location)
);
--
INSERT INTO points_of_interest (name, location, request_count) VALUES 
('London', ST_GeomFromText('POINT(51.5074 -0.1278)', 4326), 0),
('Berlin', ST_GeomFromText('POINT(52.5200 13.4050)', 4326), 0),
('Madrid', ST_GeomFromText('POINT(40.4168 -3.7038)', 4326), 0),
('Rome', ST_GeomFromText('POINT(41.9028 12.4964)', 4326), 0),
('Paris', ST_GeomFromText('POINT(48.8566 2.3522)', 4326), 0),
('Bucharest', ST_GeomFromText('POINT(44.4268 26.1025)', 4326), 0),
('Vienna', ST_GeomFromText('POINT(48.2082 16.3738)', 4326), 0),
('Hamburg', ST_GeomFromText('POINT(53.5511 9.9937)', 4326), 0),
('Warsaw', ST_GeomFromText('POINT(52.2297 21.0122)', 4326), 0),
('Budapest', ST_GeomFromText('POINT(47.4979 19.0402)', 4326), 0),
('Barcelona', ST_GeomFromText('POINT(41.3851 2.1734)', 4326), 0),
('Milan', ST_GeomFromText('POINT(45.4642 9.1900)', 4326), 0),
('Prague', ST_GeomFromText('POINT(50.0755 14.4378)', 4326), 0),
('Sofia', ST_GeomFromText('POINT(42.6977 23.3219)', 4326), 0),
('Brussels', ST_GeomFromText('POINT(50.8503 4.3517)', 4326), 0),
('Amsterdam', ST_GeomFromText('POINT(52.3676 4.9041)', 4326), 0),
('Lisbon', ST_GeomFromText('POINT(38.7223 -9.1393)', 4326), 0),
('Copenhagen', ST_GeomFromText('POINT(55.6761 12.5683)', 4326), 0),
('Stockholm', ST_GeomFromText('POINT(59.3293 18.0686)', 4326), 0),
('Oslo', ST_GeomFromText('POINT(59.9139 10.7522)', 4326), 0),
('Helsinki', ST_GeomFromText('POINT(60.1695 24.9354)', 4326), 0),
('Athens', ST_GeomFromText('POINT(37.9838 23.7275)', 4326), 0),
('Tallinn', ST_GeomFromText('POINT(59.4372 24.7536)', 4326), 0),
('Riga', ST_GeomFromText('POINT(56.9496 24.1052)', 4326), 0),
('Vilnius', ST_GeomFromText('POINT(54.6872 25.2797)', 4326), 0),
('Belfast', ST_GeomFromText('POINT(54.5973 -5.9301)', 4326), 0),
('Zagreb', ST_GeomFromText('POINT(45.8150 15.9819)', 4326), 0),
('Bratislava', ST_GeomFromText('POINT(48.1482 17.1067)', 4326), 0),
('Ljubljana', ST_GeomFromText('POINT(46.0569 14.5051)', 4326), 0),
('Sarajevo', ST_GeomFromText('POINT(43.8486 18.3564)', 4326), 0),
('Skopje', ST_GeomFromText('POINT(41.9973 21.4280)', 4326), 0);

-- 
INSERT INTO points_of_interest (name, location, request_count) 
VALUES ('Crete', ST_GeomFromText('POINT(35.2401 24.8093)', 4326), 0);
--
INSERT INTO points_of_interest (name, location, request_count) 
VALUES ('Kreuzberg', ST_GeomFromText('POINT(52.4940 13.4280)', 4326), 0);
--
INSERT INTO points_of_interest (name, location, request_count) 
VALUES ('Ronda', ST_GeomFromText('POINT(36.7402 -5.1624)', 4326), 0);
--

