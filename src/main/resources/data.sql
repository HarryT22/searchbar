INSERT INTO food (id,name,menge)VALUES(1,"Mehl");
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(1,false,false,false);
INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,is_vegan,is_vegetarisch,unvertraeglichkeiten_id,mengen)
VALUES(1,"Burger Brötchen",12,15,27,2,"FRÜHSTÜCK",TRUE,TRUE,1,"200 Gramm");
