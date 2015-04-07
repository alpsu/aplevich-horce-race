SET search_path TO 'test';

INSERT INTO place (id, name) VALUES
  (1, 'Кэмптон'), (2, 'Саутвелл'),
  (3, 'Хантингтон'), (4, 'Челтенхем'),
  (5, 'Лингфилд'), (6, 'Понтефракт'),
  (7, 'Фейрихаус'), (8, 'Челмсфорд Сити');

INSERT INTO horce (id, name, trainer, age) VALUES
  (1, 'The Ginger Berry', 'Dr J D Scargill', 5), (2, 'Bold Runner', 'J Santos', 4), (3, 'Maison Brillet', 'C Drew', 8),
  (4, 'The Blue Dog', 'P S McEntee', 8), (5, 'Eurato', 'J L Spearing', 5), (6, 'Lily Edge', 'J J Bridger', 6),
  (7, 'Paris Snow', 'Ian Williams', 5), (8, 'Capers Royal Star', 'R J Smith', 4), (9, 'Fair Ranger', 'C Gordon', 4),
  (10, 'Ninety Minutes', 'J R Best', 4), (11, 'Sahara Desert', 'Jane Chapple-Hyam', 4), (12, 'Artisan', 'S Lycett', 7),
  (13, 'Konnos Bay', 'S A Harris', 3), (14, 'Moon Arc', 'K Dalgleish', 3), (15, 'Ventura Castle', 'R Hannon', 3),
  (16, 'Paco''s Dream', 'H J L Dunlop', 3), (17, 'Sarafina', 'J R Best', 3), (18, 'Legend''s Gate', 'C Appleby', 3),
  (19, 'Molten Lava', 'P F I Cole', 3), (20, 'Putting Green', 'Sir Michael Stoute', 3);

INSERT INTO jockey (id, fname, lname) VALUES
  (1, 'R', 'Kingscote'), (2, 'Harry', 'Bentley'), (3, 'Hayley', 'Turner'),
  (4, 'G', 'Baker'), (5, 'L P', 'Keniry'), (6, 'M M', 'Monaghan'),
  (7, 'Andrea', 'Atzeni'), (8, 'Adam', 'Beschizza'), (9, 'R', 'Winston'),
  (10, 'Michael J M', 'Murphy'), (11, 'K T', 'O''Neill'), (12, 'Simon', 'Pearce'),
  (13, 'D', 'Swift'), (14, 'Jim', 'Crowley'), (15, 'T E', 'Durcan'),
  (16, 'Catherine', 'Gannon'), (17, 'Kieren', 'Fox'), (18, 'W', 'Buick'),
  (19, 'J P', 'Fahy'), (20, 'R', 'Hughes');

INSERT INTO user_account (id, name, login, password, role) VALUES
  (1, 'Alex', 'admin', 'admin', 1), (2, 'Andrew', 'bookie', 'bookie', 2), (3, 'Vano', 'client', 'client', 0);