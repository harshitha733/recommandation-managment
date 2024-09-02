-- Insert 'India'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'India', ' https://th.bing.com/th/id/OIP.OGsE70PeIHg8_ZHAWDQYcAHaE8?w=285&h=190&c=7&r=0&o=5&pid=1.7') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'India'

);


-- Insert 'United States'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'United States', ' https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkqH0rhgzF72AI5rDRpQ1BgCHDJ0QCIKq8tg&s') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'United States'

);



-- Insert 'Canada'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'Canada', ' https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMnmoRw62UAHbQy_DJe3dUpVRfhpkOw3113Q&s') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'Canada'

);



-- Insert 'United Kingdom'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'United Kingdom', ' https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScQzOy-2sy5fif2dJOwJNxD3jbvEHzDtIKjw&s') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'United Kingdom'

);



-- Insert 'Australia'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'Australia', ' https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLUtsxTN6AgAewDz06qEuSgidpdosKPeOaw&s') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'Australia'

);


-- Insert 'Germany'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'Germany', ' https://th.bing.com/th/id/OIP.XhJZt0QzInKhJ6Bhnv6IdgHaE7?w=261&h=180&c=7&r=0&o=5&pid=1.7') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'Germany'

);


-- Insert 'France'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'France', ' https://th.bing.com/th/id/OIP.x94I9d_aW4A7jyzYsTIE0AHaHa?w=162&h=180&c=7&r=0&o=5&pid=1.7') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'France'

);


-- Insert 'Japan'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'Japan', ' https://th.bing.com/th/id/OIP.EqngKqf1cBb9azRWCr0b-gHaE7?w=271&h=180&c=7&r=0&o=5&pid=1.7') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'Japan'

);


-- Insert 'China'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'China', 'https://th.bing.com/th?id=OSK.HERO9IuDwxd1BLKvKicKPMbrEgvVCfFQWrlbYrXlcejwcX8&w=472&h=280&c=13&rs=2&r=0&o=6&oif=webp&dpr=1.4&pid=SANGAM') AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM countries WHERE country_name = 'China'

);

-- Insert 'Brazil'

INSERT INTO countries (country_name, image_url)

SELECT * FROM (SELECT 'Brazil', 'https://th.bing.com/th?id=OIP.8KDxxLeJgJEQDSEqFt-VhAHaEK&w=333&h=187&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2') AS tmp

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

SELECT * FROM (SELECT 'Bavaria' AS state_name, 'https://th.bing.com/th?id=OSK.HERO5bzdwWXgzoW9fozvvD6rIiq9ZNpgRjW0FFChbYlWtiU&w=472&h=280&c=13&rs=2&r=0&o=6&oif=webp&dpr=1.4&pid=SANGAM' AS image_url, @GermanyId AS country_id UNION

               SELECT 'Saxony', 'https://th.bing.com/th?id=OIP.x8UcXiqGKKM1E2AIgoWWMwHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @GermanyId UNION

               SELECT 'Baden-Württemberg', 'https://th.bing.com/th?id=OIP.YlvY7cbMc_GbLZq_GC6g1AHaEo&w=316&h=197&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @GermanyId UNION

               SELECT 'North Rhine-Westphalia', 'https://th.bing.com/th?id=OSK.HEROHaZ-uiiS5m4BcUHIHiXbXhLmBN_kx75OZ76dDXwQnJM&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM', @GermanyId UNION

               SELECT 'Hesse', ' https://th.bing.com/th/id/OIP.vrpGqb0UTzHrJPRs0R4HfwHaFc?w=238&h=180&c=7&r=0&o=5&pid=1.7', @GermanyId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'Île-de-France' AS state_name, ' https://th.bing.com/th/id/OIP.ERX6sAYG5ISE94_bwanuigHaE8?w=252&h=180&c=7&r=0&o=5&dpr=1.4&pid=1.7 ' AS image_url, @FranceId AS country_id UNION

               SELECT 'Normandy', 'https://th.bing.com/th?id=OIP.ZyJPWV4BzoajGH9rk6ImLQHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @FranceId UNION

               SELECT 'Brittany', 'https://th.bing.com/th?id=OIP.M84YJmmuCjZHh2IpFOgM1wHaKi&w=209&h=298&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @FranceId UNION

               SELECT 'Occitanie', ' https://th.bing.com/th/id/OIP.ntvq_zYqe40K38mxPwPCcwHaFj?w=245&h=184&c=7&r=0&o=5&pid=1.7', @FranceId UNION

               SELECT 'Provence-Alpes-Côte dAzur', ' https://th.bing.com/th/id/OIP.MAba_EYOCjg4kCIA9XYakgHaDt?w=310&h=175&c=7&r=0&o=5&pid=1.7', @FranceId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'Tokyo' AS state_name, 'https://th.bing.com/th/id/OIP.kE7y3QwpQCTobP5VuJQksgHaEo?w=286&h=180&c=7&r=0&o=5&dpr=1.4&pid=1.7' AS image_url, @JapanId AS country_id UNION

               SELECT 'Kyoto', ' https://th.bing.com/th/id/OIP.gmlu9eeqgw6xE0vHNo4KzgHaE7?w=292&h=194&c=7&r=0&o=5&pid=1.7', @JapanId UNION

               SELECT 'Osaka', ' https://th.bing.com/th/id/OIP.yHB2HWiQpsxON9lNVTj2CgHaFj?w=212&h=180&c=7&r=0&o=5&pid=1.7', @JapanId UNION

               SELECT 'Hokkaido', ' https://th.bing.com/th/id/OIP.Hiz5mLMP3d7DxXO2cEyl1AHaE5?w=272&h=180&c=7&r=0&o=5&pid=1.7', @JapanId UNION

               SELECT 'Okinawa', ' https://res.cloudinary.com/jnto/image/upload/w_720,h_480,c_fill,f_auto,fl_lossy,q_60/v1/media/filer_public/09/3f/093f0899-7c33-40b5-a315-69f5ad7a6fd9/okinawa_sightseeing_castles_rpn5yq ', @JapanId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'Guangdong' AS state_name, 'http://ts3.mm.bing.net/th?id=OIP.TO1fjEe59ilo0W99nRcBtQHaE8&pid=15.1' AS image_url, @ChinaId AS country_id UNION

               SELECT 'Shanghai', ' https://th.bing.com/th/id/OIP.balmTlN93CI6WUjGYPF95wHaFj?w=229&h=180&c=7&r=0&o=5&pid=1.7', @ChinaId UNION

               SELECT 'Beijing', ' https://th.bing.com/th/id/OIP.QOHv2YBz4-qdWxy-xF7lZAHaFj?w=229&h=180&c=7&r=0&o=5&pid=1.7', @ChinaId UNION

               SELECT 'Sichuan', ' https://th.bing.com/th/id/OIP.gxMkDVed9u40fty4r_3yOwHaE0?w=267&h=180&c=7&r=0&o=5&pid=1.7', @ChinaId UNION

               SELECT 'Hunan', ' https://th.bing.com/th/id/OIP.qdBbWODweFNE9HbZ55YBygHaFj?w=256&h=192&c=7&r=0&o=5&pid=1.7', @ChinaId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'São Paulo' AS state_name, ' https://th.bing.com/th/id/OIP.pVvLOuOGfdtj3EWTopKFXAHaFa?w=248&h=181&c=7&r=0&o=5&pid=1.7' AS image_url, @BrazilId AS country_id UNION

               SELECT 'Rio de Janeiro', ' https://th.bing.com/th/id/OIP.3Uyv5qx_hU1fXUZW2VpkFAHaFu?w=216&h=180&c=7&r=0&o=5&pid=1.7', @BrazilId UNION

               SELECT 'Bahia', ' https://th.bing.com/th/id/OIP.4kKeiTscYo8BpY57zahD3wHaFj?w=252&h=189&c=7&r=0&o=5&pid=1.7', @BrazilId UNION

               SELECT 'Minas Gerais', ' https://th.bing.com/th/id/OIP.Ddm_KJaaI5rRtfkfG4E8AAHaE6?w=241&h=180&c=7&r=0&o=5&pid=1.7', @BrazilId UNION

               SELECT 'Paraná', ' https://th.bing.com/th/id/OIP.WemUu96MtwsJRjBPIdkdsAHaE9?w=258&h=180&c=7&r=0&o=5&pid=1.7', @BrazilId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'Telangana' AS state_name, ' https://xploreall.com/wp-content/uploads/2022/10/telangana-jpg.webp ' AS image_url, @IndiaId AS country_id UNION

               SELECT 'Karnataka', ' https://www.holidify.com/images/bgImages/MYSORE.jpg ', @IndiaId UNION

               SELECT 'Maharashtra', ' https://www.heritageindiaholidays.com/uploads/blog/1678972019fh0nw-historical-sites-in-maharashtra-700x576.jpg ', @IndiaId UNION

               SELECT 'Tamil Nadu', ' https://voiceofadventure.com/wp-content/uploads/2022/10/8603663088_618a4ea76c_b.jpg ', @IndiaId UNION

               SELECT 'Kerala', ' https://www.ekeralatourism.net/wp-content/uploads/2018/03/Alleppey.jpg ', @IndiaId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'California' AS state_name, ' https://th.bing.com/th/id/OIP.MyO0Z1ldpAF18Lmjg34pygHaEv?w=259&h=180&c=7&r=0&o=5&pid=1.7' AS image_url, @USAId AS country_id UNION

               SELECT 'Texas', ' https://th.bing.com/th/id/OIP.e_LV8MmsXs7Yjhfz3u1y_QAAAA?w=207&h=147&c=7&r=0&o=5&pid=1.7', @USAId UNION

               SELECT 'Florida', ' https://th.bing.com/th/id/OIP.4uxECUphwz3SIe5DUn4gEgHaEa?w=279&h=180&c=7&r=0&o=5&pid=1.7', @USAId UNION

               SELECT 'New York', ' https://th.bing.com/th/id/OIP.DhEkl-TTWn9088Oc33-ZnwHaEV?w=277&h=180&c=7&r=0&o=5&pid=1.7', @USAId UNION

               SELECT 'Illinois', ' https://th.bing.com/th/id/OIP.0TUrokxJyPDso7pOeVahwwHaFj?w=225&h=180&c=7&r=0&o=5&pid=1.7', @USAId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'Ontario' AS state_name, 'https://th.bing.com/th?id=OIP.VP2sLcl2NOvA8x1Cg2y0SAHaFj&w=288&h=216&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2' AS image_url, @CanadaId AS country_id UNION

               SELECT 'Quebec', ' https://th.bing.com/th?id=OIP.4ou97HMryfUWlfHGX-blsQHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2 ', @CanadaId UNION

               SELECT 'British Columbia', 'https://th.bing.com/th?id=OIP.st_srugi6nlXb8-WgkWajQHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @CanadaId UNION

               SELECT 'Alberta', 'https://th.bing.com/th?id=OIP.pvg8OxP1zi-H3QnJ2QZcIQHaFj&w=288&h=216&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @CanadaId UNION

               SELECT 'Manitoba', 'https://th.bing.com/th?id=OSK.HEROUVAlENk3yrT7x0aOOQI8JWKSKPGP0xUwp5UDqZWFcJ4&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM', @CanadaId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);



INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'England' AS state_name, 'https://th.bing.com/th?id=OIP.gSlXEkMcoQAyXL3cbbmQ_wHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2' AS image_url, @UKId AS country_id UNION

               SELECT 'Scotland', 'https://th.bing.com/th/id/OIP.D4qtKrpGbi4xEpmBctnPkAHaDt?w=290&h=175&c=7&r=0&o=5&dpr=1.4&pid=1.7', @UKId UNION

               SELECT 'Wales', 'https://th.bing.com/th?id=OSK.HEROge4-GqlhtG1D5erp_aPy3tQrVhFGGgdddVNQjTKNGSU&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM', @UKId UNION

               SELECT 'Northern Ireland', 'https://th.bing.com/th?id=OSK.HEROk7yXYSEJGjnvU6M-0QOXjAyhXrCF1itPD32fUJPZULg&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM', @UKId UNION

               SELECT 'Cornwall', 'https://th.bing.com/th?id=OIP.vRdraRkJ4rCK6mqQIr5fNAHaE8&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @UKId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);

INSERT INTO states (state_name, image_url, country_id)

SELECT * FROM (SELECT 'New South Wales' AS state_name, 'https://th.bing.com/th?id=OIP.fmi0Hvk3XLEPxLz064oJpAHaE8&w=305&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2 ' AS image_url, @AustraliaId AS country_id UNION

               SELECT 'Queensland', 'https://th.bing.com/th?id=OIP.1fAfHaZd6DByAH5v09i2xgHaE7&w=306&h=203&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @AustraliaId UNION

               SELECT 'Victoria', 'https://th.bing.com/th?id=OIP.dwLUvXaLRZpnaOusI4IUfgHaE7&w=306&h=204&c=8&rs=1&qlt=90&r=0&o=6&dpr=1.4&pid=3.1&rm=2', @AustraliaId UNION

               SELECT 'Tasmania', 'https://th.bing.com/th?id=OSK.HEROR44Fegr8CLkqsCztEx226Aij5ycPmNvnvD9OG1XKunU&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM', @AustraliaId UNION

               SELECT 'South Australia', 'https://th.bing.com/th?id=OSK.HERO78MFA5CVXmmXiE2Sc6rfsolxdXXgnE2020iFlQbB4UM&w=472&h=280&c=1&rs=2&r=0&o=6&dpr=1.4&pid=SANGAM ', @AustraliaId) AS tmp

WHERE NOT EXISTS (

    SELECT 1 FROM states WHERE state_name = tmp.state_name AND country_id = tmp.country_id

);