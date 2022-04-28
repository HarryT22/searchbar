INSERT INTO unvertraeglichkeiten (histamine,fructose,lactose)VALUES(false,false,false);
INSERT INTO unvertraeglichkeiten (histamine,fructose,lactose)VALUES(false,false,false);
INSERT INTO food(name,proteine,kalorien,menge) VALUES("Fleisch",200,400,"200 g");
INSERT INTO food(name,proteine,kalorien,menge) VALUES("Salat",200,400,"150 g");
INSERT INTO food(name,proteine,kalorien,menge) VALUES("Helm",200,400,"200 g");
INSERT INTO rezepte (name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES("Fleisch A",25,15,40,6,"MITTAGESSEN",FALSE,FALSE,1);
INSERT INTO rezepte (name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES("Fleisch B",25,15,40,6,"MITTAGESSEN",FALSE,FALSE,2);
INSERT INTO rezepte_foods(rezepte_id,foods_id) VALUES (1,1);
INSERT INTO rezepte_foods(rezepte_id,foods_id) VALUES (1,2);
INSERT INTO rezepte_foods(rezepte_id,foods_id) VALUES (2,3);





