INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(1,false,false,false);
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(2,false,false,true);


INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(1,"Burger Brötchen",12,15,27,2,"FRÜHSTÜCK",TRUE,TRUE,1);
INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id)
VALUES(2,"Sandwich",2,3,5,2,"FRÜHSTÜCK",TRUE,TRUE,2);

INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Mehl","200Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Wasser","200 ML");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Hefe","100Gramm");
INSERT INTO rezept_mengen_mapping(rezepte_id,food_name,rezepte_menge) VALUES (1,"Salz","10Gramm");