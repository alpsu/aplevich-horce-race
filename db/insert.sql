SET search_path TO 'public';

DELETE FROM runner;
DELETE FROM race;
DELETE FROM user_account;
DELETE FROM jockey;
DELETE FROM horce;
DELETE FROM place;

INSERT INTO place (name) VALUES
  ('Кэмптон'), ('Саутвелл'),
  ('Хантингтон'), ('Челтенхем'),
  ('Лингфилд'), ('Понтефракт'),
  ('Фейрихаус'), ('Челмсфорд Сити');

INSERT INTO horce (name, trainer, age) VALUES
  ('The Ginger Berry', 'Dr J D Scargill', 5), ('Bold Runner', 'J Santos', 4), ('Maison Brillet', 'C Drew', 8),
  ('The Blue Dog', 'P S McEntee', 8), ('Eurato', 'J L Spearing', 5), ('Lily Edge', 'J J Bridger', 6),
  ('Paris Snow', 'Ian Williams', 5), ('Capers Royal Star', 'R J Smith', 4), ('Fair Ranger', 'C Gordon', 4),
  ('Ninety Minutes', 'J R Best', 4), ('Sahara Desert', 'Jane Chapple-Hyam', 4), ('Artisan', 'S Lycett', 7),
  ('Konnos Bay', 'S A Harris', 3), ('Moon Arc', 'K Dalgleish', 3), ('Ventura Castle', 'R Hannon', 3),
  ('Paco''s Dream', 'H J L Dunlop', 3), ('Sarafina', 'J R Best', 3), ('Legend''s Gate', 'C Appleby', 3),
  ('Molten Lava', 'P F I Cole', 3), ('Putting Green', 'Sir Michael Stoute', 3);

INSERT INTO jockey (fname, lname) VALUES
  ('R', 'Kingscote'), ('Harry', 'Bentley'), ('Hayley', 'Turner'),
  ('G', 'Baker'), ('L P', 'Keniry'), ('M M', 'Monaghan'),
  ('Andrea', 'Atzeni'), ('Adam', 'Beschizza'), ('R', 'Winston'),
  ('Michael J M', 'Murphy'), ('K T', 'O''Neill'), ('Simon', 'Pearce'),
  ('D', 'Swift'), ('Jim', 'Crowley'), ('T E', 'Durcan'),
  ('Catherine', 'Gannon'), ('Kieren', 'Fox'), ('W', 'Buick'),
  ('J P', 'Fahy'), ('R', 'Hughes');

INSERT INTO user_account (name, login, password, role) VALUES
  ('Alex', 'admin', 'admin', 1), ('Andrew', 'bookie', 'bookie', 2), ('Vano', 'client', 'client', 0);

INSERT INTO race (place_id, description, distance, start, quantity) VALUES
  (1, 'Королевская гонка', '0m 5f 3y', '2015-05-15 12:00:00', 4), (1, 'Вызов короля', '0m 5f 3y', '2015-05-15 12:30:00', 5);

INSERT INTO runner (horce_id, jockey_id, race_id, koefficient, place) VALUES
  (1, 1, 1, 1.25, 1), (2, 2, 1, 2.03, 2), (3, 3, 1, 1.7, 3),
  (4, 4, 1, 7.2, 4), (5, 5, 2, 4.3, 1), (6, 6, 2, 1.7, 2),
  (7, 7, 2, 6.2, 3), (8, 8, 2, 4.5, 4), (9, 9, 2, 5.7, 5);