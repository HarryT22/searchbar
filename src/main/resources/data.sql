INSERT INTO food (id,name,menge)VALUES(1,"Mehl","200 Gramm");
INSERT INTO unvertraeglichkeiten (id,histamine,fructose,lactose)VALUES(1,false,false,false);
INSERT INTO rezepte (id,name,arbeitszeit,kochzeit,gesamtzeit,portionen,menueart,isVegan,isVegetarisch,unvertraeglichkeiten_id)
VALUES(1,"Burger Brötchen",12,15,27,2,"FRÜHSTÜCK",true,true,1);