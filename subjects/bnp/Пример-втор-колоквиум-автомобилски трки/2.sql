-- Релационата база е дефинирана преку следните релации: 

-- Pateka(ime, grad, drzava, dolzina, tip)
-- Trka(ime, krugovi, pateka*)
-- Odrzana_trka(ime*, datum, vreme)
-- Vozac(vozacki_broj, ime, prezime, nacionalnost, datum_r)
-- Tim(ime, direktor)
-- Sponzori(ime*, sponzor)
-- Vozi_za(vozacki_broj*, ime_tim*, datum_pocetok, datum_kraj)
-- Ucestvuva(ID, vozacki_broj*, ime_tim*, ime_trka*, datum_trka*, pocetna_p, krajna_p, poeni)


-- Да се напише DML израз со кој ќе се вратат информациите за возачите кои во 2023 година освоиле
-- (еден или повеќе) поени на одржани трки со помалку од 70 кругови подредени според датумот на 
-- раѓање по опаѓачки редослед.

-- потребни ни се
-- информациите за возачите во 2023 година
-- кои освоиле еден или повеќе поени
-- со најмалку 70 кругови
-- подредени според датум на раѓање

SELECT DISTINCT
    v.vozacki_broj,
    v.ime,
    v.prezime,
    v.nacionalnost,
    v.datum_r
FROM vozac AS v
JOIN Ucestvuva AS uces
    ON v.vozacki_broj = uces.vozacki_broj
JOIN trka AS trk
    ON uces.ime_trka = trk.ime
WHERE trk.datum_trka BETWEEN '2023-01-01' AND '2023-12-31'
  AND uces.poeni >= 1
  AND trk.krugovi < 70
ORDER BY v.datum_r DESC;
