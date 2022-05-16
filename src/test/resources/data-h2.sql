INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (true,true,true);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (true,false,false);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (true,true,false);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (true,false,true);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (false,false,false);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (false,false,true);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (false,true,true);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose) VALUES (false,true,false);

INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Fleisch',200,400,'200 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Salat',200,400,'150 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Helm',200,400,'200 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Huhn',200,400,'200 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Oktopus',200,400,'200 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Reis',200,400,'200 g');
INSERT INTO food(name,proteine,kalorien,menge) VALUES ('Kartoffel',200,400,'200 g');

INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch A',25,15,6,'MITTAGESSEN',FALSE,FALSE,1);
INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch B',25,15,6,'MITTAGESSEN',FALSE,FALSE,2);
INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch C',25,15,6,'MITTAGESSEN',FALSE,FALSE,2);
INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch D',25,15,6,'MITTAGESSEN',FALSE,FALSE,2);
INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch E',25,15,6,'MITTAGESSEN',FALSE,FALSE,2);
INSERT INTO rezepte(name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id) VALUES ('Fleisch F',25,15,6,'MITTAGESSEN',FALSE,FALSE,2);

INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (1,1);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (2,2);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (3,3);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (4,4);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (5,5);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (6,6);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (6,7);
