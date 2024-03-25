CREATE TABLE Property
(
    property_id       SERIAL PRIMARY KEY,
    advert_date       DATE,
    area              DOUBLE PRECISION,
    bill_of_sale      VARCHAR(255) CHECK (bill_of_sale IN ('Var', 'Yox')),
    building_floor    INTEGER,
    city              VARCHAR(255) CHECK (city IN ('Bakı', 'Gəncə', 'Sumqayıt', 'Mingəçevir')),
    description       TEXT,
    flat_floor        INTEGER,
    metro             VARCHAR(255) CHECK (metro IN
                                          ('20 Yanvar', '28 May', '8 Noyabr', 'Avtovağzal', 'Azadlıq', 'Bakmil','Dərnəgül'
                                          ,'Əhmədli','Elmlər Akademiyası','Gənclik','Həzi Aslanov','İçərişəhər','İnşaatçılar'
                                          ,'Koroğlu','Memar Əcəmi','Neftçilər','Nərimanov','Nəsimi','Nizami','Qara Qarayev'
                                          ,'Sahil','Ulduz','Xalqlar dostluğu','Xətai','Xocəsən','Yasamal')),
    price             INTEGER,
    property_category VARCHAR(255) CHECK (property_category IN ('Həyət evi/Bağ evi', 'Köhnə tikili', 'Torpaq', 'Yeni tikili')),
    renovation        VARCHAR(255) CHECK (renovation IN ('var', 'yoxdur', 'N/A')),
    rooms             INTEGER,
    target            VARCHAR(255),
    updated_date      DATE
);