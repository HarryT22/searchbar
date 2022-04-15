INSERT INTO food (id,name)VALUES(1,"Mehl");
INSERT INTO food (id,name)VALUES(2,"Wasser");
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(1,false,false,false);
INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,rezept_menge,food_name,rezept_menge,food_name)
VALUES(1,"Burger Brötchen",12,15,27,2,"FRÜHSTÜCK",TRUE,TRUE,1,"200 Gramm","Mehl","400 ML","Wasser");
