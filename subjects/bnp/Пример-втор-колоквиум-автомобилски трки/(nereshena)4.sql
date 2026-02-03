-- Релационата база е дефинирана преку следните релации: 

-- Pateka(ime, grad, drzava, dolzina, tip)
-- Trka(ime, krugovi, pateka*)
-- Odrzana_trka(ime*, datum, vreme)
-- Vozac(vozacki_broj, ime, prezime, nacionalnost, datum_r)
-- Tim(ime, direktor)
-- Sponzori(ime*, sponzor)
-- Vozi_za(vozacki_broj*, ime_tim*, datum_pocetok, datum_kraj)
-- Ucestvuva(ID, vozacki_broj*, ime_tim*, ime_trka*, datum_trka*, pocetna_p, krajna_p, poeni)

-- Да се напише/ат соодветниот/те тригер/и за одржување на референцијалниот интегритет 
-- на релацијата „УЧЕСТВУВА“ доколку треба да се исполнети следните барања:

-- Сакаме да водиме евиденција за учествата на трки од возачи кои се избришани од системот.
-- Не сакаме да водиме евиденција за учествата на трки од тимови кои се избришани од системот.


create trigger trg_vozac_delete
before delete on vozac
for each row
begin
    update Ucestvuva
    set vozacki_broj = null
    where vozacki_broj = old.vozacki_broj
end;
