INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (true,true,true,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (true,false,false,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (true,true,false,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (true,false,true,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (false,false,false,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (false,false,true,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (false,true,true,0);
INSERT INTO unvertraeglichkeiten(histamine,fructose,lactose,version) VALUES (false,true,false,0);

INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Fleisch',200,400,'200 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Salat',200,400,'150 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Helm',200,400,'200 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Huhn',200,400,'200 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Oktopus',200,400,'200 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Reis',200,400,'200 g',0);
INSERT INTO food(name,proteine,kalorien,menge,version) VALUES ('Kartoffel',200,400,'200 g',0);

INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch A',25,15,6,'MITTAGESSEN',FALSE,FALSE,1,0);
INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch B',25,15,6,'MITTAGESSEN',FALSE,FALSE,2,0);
INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch C',25,15,6,'MITTAGESSEN',FALSE,FALSE,3,0);
INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch D',25,15,6,'MITTAGESSEN',FALSE,FALSE,4,0);
INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch E',25,15,6,'MITTAGESSEN',FALSE,FALSE,5,0);
INSERT INTO rezepte(author,name,arbeitszeit,kochzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,version) VALUES ('Sanra','Fleisch F',25,15,6,'MITTAGESSEN',FALSE,FALSE,6,0);

INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (1,1);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (2,2);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (3,3);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (4,4);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (5,5);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (6,6);
INSERT INTO rezepte_foods(rezepte_id,food_id) VALUES (6,7);

INSERT INTO user(name,email,version) VALUES('Pablo','pablo@fhms.de',0);
INSERT INTO user(name,email,version) VALUES('Pedro','pedro@fhms.de',0);