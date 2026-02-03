-- На сликата подолу е прикажан дел од ЕР дијаграм за систем за автомобилски трки.

-- Релационата база е дефинирана преку следните релации: 
-- Pateka(ime, grad, drzava, dolzina, tip)
-- Trka(ime, krugovi, pateka*)
-- Odrzana_trka(ime*, datum, vreme)
-- Vozac(vozacki_broj, ime, prezime, nacionalnost, datum_r)
-- Tim(ime, direktor)
-- Sponzori(ime*, sponzor)
-- Vozi_za(vozacki_broj*, ime_tim*, datum_pocetok, datum_kraj)
-- Ucestvuva(ID, vozacki_broj*, ime_tim*, ime_trka*, datum_trka*, pocetna_p, krajna_p, poeni)

-- Да се напише DML израз со кој за секоја трка ќе се врати возачот кој има најмногу победи на таа трка.

нас ни се потребни
информации: vozacki_broј, име на трката

