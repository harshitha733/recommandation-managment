-- Insert 'India'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'India', 'https://example.com/indian.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'India'
);

-- Insert 'United States'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'United States', 'https://example.com/usa.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'United States'
);

-- Insert 'Canada'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'Canada', 'https://example.com/canada.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'Canada'
);

-- Insert 'United Kingdom'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'United Kingdom', 'https://example.com/uk.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'United Kingdom'
);

-- Insert 'Australia'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'Australia', 'https://example.com/australia.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'Australia'
);

-- Insert 'Germany'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'Germany', 'https://example.com/germany.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'Germany'
);

-- Insert 'France'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'France', 'https://example.com/france.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'France'
);

-- Insert 'Japan'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'Japan', 'https://example.com/japan.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'Japan'
);

-- Insert 'China'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'China', 'https://example.com/china.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'China'
);

-- Insert 'Brazil'
INSERT INTO countries (country_name, image_url)
SELECT * FROM (SELECT 'Brazil', 'https://example.com/brazil.jpg') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM countries WHERE country_name = 'Brazil'
);



SELECT country_id INTO @IndiaId FROM countries WHERE country_name = 'India';
SELECT country_id INTO @USAId FROM countries WHERE country_name = 'United States';
SELECT country_id INTO @CanadaId FROM countries WHERE country_name = 'Canada';
SELECT country_id INTO @UKId FROM countries WHERE country_name = 'United Kingdom';
SELECT country_id INTO @AustraliaId FROM countries WHERE country_name = 'Australia';
SELECT country_id INTO @GermanyId FROM countries WHERE country_name = 'Germany';
SELECT country_id INTO @FranceId FROM countries WHERE country_name = 'France';
SELECT country_id INTO @JapanId FROM countries WHERE country_name = 'Japan';
SELECT country_id INTO @ChinaId FROM countries WHERE country_name = 'China';
SELECT country_id INTO @BrazilId FROM countries WHERE country_name = 'Brazil';

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Bavaria' AS state_name, 'https://example.com/bavaria.jpg' AS image_url, @GermanyId AS country_id UNION
               SELECT 'Saxony', 'https://example.com/saxony.jpg', @GermanyId UNION
               SELECT 'Baden-Württemberg', 'https://example.com/badenwurttemberg.jpg', @GermanyId UNION
               SELECT 'North Rhine-Westphalia', 'https://example.com/northrhinewestphalia.jpg', @GermanyId UNION
               SELECT 'Hesse', 'https://example.com/hesse.jpg', @GermanyId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Île-de-France' AS state_name, 'https://example.com/iledefrance.jpg' AS image_url, @FranceId AS country_id UNION
               SELECT 'Normandy', 'https://example.com/normandy.jpg', @FranceId UNION
               SELECT 'Brittany', 'https://example.com/brittany.jpg', @FranceId UNION
               SELECT 'Occitanie', 'https://example.com/occitanie.jpg', @FranceId UNION
               SELECT 'Provence-Alpes-Côte dAzur', 'https://example.com/provencealpescotedazur.jpg', @FranceId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Tokyo' AS state_name, 'https://example.com/tokyo.jpg' AS image_url, @JapanId AS country_id UNION
               SELECT 'Kyoto', 'https://example.com/kyoto.jpg', @JapanId UNION
               SELECT 'Osaka', 'https://example.com/osaka.jpg', @JapanId UNION
               SELECT 'Hokkaido', 'https://example.com/hokkaido.jpg', @JapanId UNION
               SELECT 'Okinawa', 'https://example.com/okinawa.jpg', @JapanId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Guangdong' AS state_name, 'https://example.com/guangdong.jpg' AS image_url, @ChinaId AS country_id UNION
               SELECT 'Shanghai', 'https://example.com/shanghai.jpg', @ChinaId UNION
               SELECT 'Beijing', 'https://example.com/beijing.jpg', @ChinaId UNION
               SELECT 'Sichuan', 'https://example.com/sichuan.jpg', @ChinaId UNION
               SELECT 'Hunan', 'https://example.com/hunan.jpg', @ChinaId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'São Paulo' AS state_name, 'https://example.com/saopaulo.jpg' AS image_url, @BrazilId AS country_id UNION
               SELECT 'Rio de Janeiro', 'https://example.com/rio.jpg', @BrazilId UNION
               SELECT 'Bahia', 'https://example.com/bahia.jpg', @BrazilId UNION
               SELECT 'Minas Gerais', 'https://example.com/minasgerais.jpg', @BrazilId UNION
               SELECT 'Paraná', 'https://example.com/parana.jpg', @BrazilId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Telangana' AS state_name, 'https://example.com/telangana.jpg' AS image_url, @IndiaId AS country_id UNION
               SELECT 'Karnataka', 'https://example.com/karnataka.jpg', @IndiaId UNION
               SELECT 'Maharashtra', 'https://example.com/maharashtra.jpg', @IndiaId UNION
               SELECT 'Tamil Nadu', 'https://example.com/tamilnadu.jpg', @IndiaId UNION
               SELECT 'Kerala', 'https://example.com/kerala.jpg', @IndiaId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'California' AS state_name, 'https://example.com/california.jpg' AS image_url, @USAId AS country_id UNION
               SELECT 'Texas', 'https://example.com/texas.jpg', @USAId UNION
               SELECT 'Florida', 'https://example.com/florida.jpg', @USAId UNION
               SELECT 'New York', 'https://example.com/newyork.jpg', @USAId UNION
               SELECT 'Illinois', 'https://example.com/illinois.jpg', @USAId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'Ontario' AS state_name, 'https://example.com/ontario.jpg' AS image_url, @CanadaId AS country_id UNION
               SELECT 'Quebec', 'https://example.com/quebec.jpg', @CanadaId UNION
               SELECT 'British Columbia', 'https://example.com/britishcolumbia.jpg', @CanadaId UNION
               SELECT 'Alberta', 'https://example.com/alberta.jpg', @CanadaId UNION
               SELECT 'Manitoba', 'https://example.com/manitoba.jpg', @CanadaId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);

INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'England' AS state_name, 'https://example.com/england.jpg' AS image_url, @UKId AS country_id UNION
               SELECT 'Scotland', 'https://example.com/scotland.jpg', @UKId UNION
               SELECT 'Wales', 'https://example.com/wales.jpg', @UKId UNION
               SELECT 'Northern Ireland', 'https://example.com/northernireland.jpg', @UKId UNION
               SELECT 'Cornwall', 'https://example.com/cornwall.jpg', @UKId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);
INSERT INTO states (state_name, image_url, country_id)
SELECT * FROM (SELECT 'New South Wales' AS state_name, 'https://example.com/newsouthwales.jpg' AS image_url, @AustraliaId AS country_id UNION
               SELECT 'Queensland', 'https://example.com/queensland.jpg', @AustraliaId UNION
               SELECT 'Victoria', 'https://example.com/victoria.jpg', @AustraliaId UNION
               SELECT 'Tasmania', 'https://example.com/tasmania.jpg', @AustraliaId UNION
               SELECT 'South Australia', 'https://example.com/southaustralia.jpg', @AustraliaId) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id
);