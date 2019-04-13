DELETE FROM ORDR;
DELETE FROM CITY;
DELETE FROM COUNTRY;
DELETE FROM USR;

INSERT INTO COUNTRY (NAME, LANGUAGE) VALUES ('USA', 'English');
INSERT INTO COUNTRY (NAME, LANGUAGE) VALUES ('Japan', 'Japanese');
INSERT INTO COUNTRY (NAME, LANGUAGE) VALUES ('Russia', 'Russian');
INSERT INTO COUNTRY (NAME, LANGUAGE) VALUES ('Brazil', 'Portuguese');

--Sochi--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Russia'),
                    'Sochi',
                    500000,
                    FALSE,
                    'TROPICAL',
                    'BEACH_N_SKI_RESORT',
                    2,
                    NULL,
                    2
                   );
--New York--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'USA'),
                    'New York',
                    10000000,
                    FALSE,
                    'TEMPERATE',
                    'SIGHTSEE',
                    NULL,
                    20,
                    NULL
                   );
--Los Angeles--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'USA'),
                    'Los Angeles',
                    4000000,
                    FALSE,
                    'TROPICAL',
                    'BEACH_N_SIGHTSEE',
                    3,
                    20,
                    NULL
                   );
--San Francisco--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'USA'),
                    'San Francisco',
                    1000000,
                    FALSE,
                    'TEMPERATE',
                    'BEACH_N_SIGHTSEE',
                    2,
                    50,
                    NULL
                   );
--Rio de Janeiro--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Brazil'),
                    'Rio de Janeiro',
                    6000000,
                    FALSE,
                    'TROPICAL',
                    'BEACH_N_SIGHT',
                    5,
                    2,
                    NULL
                   );
--Tokyo--
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATTION,
                    IS_CAPITAL,
                    CLIMATE,
                    DISCRIMINATOR,
                    NUM_OF_BEACHES,
                    NUM_OF_SIGHTS,
                    NUM_OF_SKI_RESORTS
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Japan'),
                    'Tokyo',
                    9000000,
                    TRUE,
                    'TEMPERATE',
                    'SIGHTSEE',
                    NULL,
                    2,
                    NULL
                   );