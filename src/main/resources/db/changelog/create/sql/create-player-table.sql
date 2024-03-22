CREATE TABLE Player (
                        player_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        club_id INT NOT NULL REFERENCES club(club_id),
                        position_id INT NOT NULL REFERENCES position(position_id),
                        player_name VARCHAR(50) NOT NULL,
                        citizenship INT NOT NULL REFERENCES country(country_id)
);