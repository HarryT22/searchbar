INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(1,"Fleisch mit Reis",25,15,40,6,"MITTAGESSEN",FALSE,FALSE,1);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(1,false,false,false);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Paprika","1.5 Kilo");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Tomaten","1 Kilo");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Reis","600 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Zwiebeln","500 Gramm");

INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(2,"Fleisch Bolognese",20,30,50,4,"MITTAGESSEN",false,false,2);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(2,false,false,true);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (2,"Knollensellerie","200 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (2,"Erbsen","500 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (2,"Reis","600 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (2,"Möhren","500 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (2,"Hackfleisch","1.2 Kilo");

INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(3,"Fleisch",15,15,30,6,"FRÜHSTÜCK",FALSE,FALSE,3);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(3,true,false,true);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (3,"Hackfleisch, gemischt","800 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (3,"Mortadelle, fein gehackt","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (3,"Parmesan, gerieben","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (3,"Cornichons, fein gehackt","50 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (3,"Toastbrot","2 Scheiben");

INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(4,"Fleisch Salat",15,15,30,6,"ABENDESSEN",FALSE,FALSE,4);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(4,true,false,true);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (4,"Hackfleisch, gemischt","800 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (4,"Mortadelle, fein gehackt","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (4,"Parmesan, gerieben","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (4,"Cornichons, fein gehackt","50 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (4,"Toastbrot","2 Scheiben");

INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(5,"Fleisch vom grill",15,15,30,6,"ABENDESSEN",FALSE,FALSE,5);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(5,true,false,true);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (5,"Hackfleisch, gemischt","800 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (5,"Mortadelle, fein gehackt","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (5,"Parmesan, gerieben","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (5,"Cornichons, fein gehackt","50 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (5,"Toastbrot","2 Scheiben");

INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(6,"Fleisch mit Hahn",15,15,30,6,"ABENDESSEN",FALSE,FALSE,6);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(6,true,false,true);
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (6,"Hackfleisch, gemischt","800 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (6,"Mortadelle, fein gehackt","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (6,"Parmesan, gerieben","100 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (6,"Cornichons, fein gehackt","50 Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (6,"Toastbrot","2 Scheiben");
