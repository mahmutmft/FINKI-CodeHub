-- Релационата база е дефинирана преку следните релации:

-- Lice(id, mbr, ime, prezime, data_r, vozrast, pol)
-- Med_lice(id*, staz)
-- Test(id*, shifra, tip, datum, rezultat, laboratorija)
-- Vakcina(shifra, ime, proizvoditel)
-- Vakcinacija(id_lice*, id_med_lice*, shifra_vakcina*)
-- Vakcinacija_datum(id_lice*, id_med_lice*, shifra_vakcina*, datum)

-- Да се напише DML израз со кој ќе се вратат матичните броеви на лицата 
-- (сортирани во растечки редослед) кои биле позитивни и потоа примиле барем една доза вакцина.


-- потребни ни се:
-- матичните броеви на лицата 
-- сортирани во растечки редослед 
-- кои биле позитивни 
-- и потоа примале барем една доза вакцина

SELECT DISTINCT
    l.id
FROM lice AS l
JOIN test AS t
    ON l.id = t.id
JOIN Vakcinacija_datum AS vd
    ON vd.id_lice = l.id
WHERE rezultat = 'pozitiven'
  AND vd.datum >= t.datum
ORDER BY
    l.id;

