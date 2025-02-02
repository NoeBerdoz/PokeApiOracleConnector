-- This script creates the Oracle database schema for retrieving the Pokemons data from the API.
CREATE TABLE TEAMS
(
    ID   NUMBER GENERATED AS IDENTITY PRIMARY KEY,
    NAME VARCHAR2(255)
        CONSTRAINT NN_TEAMS_NAME NOT NULL
);

CREATE TABLE STATS
(
    ID              NUMBER GENERATED AS IDENTITY PRIMARY KEY,
    HP              NUMBER
        CONSTRAINT NN_STATS_HP NOT NULL,
    ATTACK          NUMBER
        CONSTRAINT NN_STATS_ATTACK NOT NULL,
    DEFENSE         NUMBER
        CONSTRAINT NN_STATS_DEFENSE NOT NULL,
    SPECIAL_ATTACK  NUMBER
        CONSTRAINT NN_STATS_SPECIAL_ATTACK NOT NULL,
    SPECIAL_DEFENSE NUMBER
        CONSTRAINT NN_STATS_SPECIAL_DEFENSE NOT NULL,
    SPEED           NUMBER
        CONSTRAINT NN_STATS_SPEED NOT NULL
);

CREATE TABLE POKEMONS
(
    ID       NUMBER GENERATED AS IDENTITY PRIMARY KEY,
    NAME     VARCHAR2(255)
        CONSTRAINT U_POKEMONS_NAME UNIQUE
        CONSTRAINT NN_POKEMONS_NAME NOT NULL,
    STATS_ID NUMBER,
    IMAGE    BLOB,
    CONSTRAINT FK_POKEMONS_STATS FOREIGN KEY (STATS_ID) REFERENCES STATS (ID)
);

CREATE TABLE TEAM_POKEMONS_ORDERED
(
    ID           NUMBER GENERATED AS IDENTITY PRIMARY KEY,
    TEAM_ID      NUMBER
        CONSTRAINT NN_TEAM_POKEMONS_ORDERED_TEAM_ID NOT NULL
        CONSTRAINT U_TEAM_POKEMONS_ORDERED_TEAM_ID UNIQUE,
    POKEMON_ID_1 NUMBER,
    POKEMON_ID_2 NUMBER,
    POKEMON_ID_3 NUMBER,
    POKEMON_ID_4 NUMBER,
    POKEMON_ID_5 NUMBER,
    POKEMON_ID_6 NUMBER,
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_TEAM FOREIGN KEY (TEAM_ID) REFERENCES TEAMS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_1 FOREIGN KEY (POKEMON_ID_1) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_2 FOREIGN KEY (POKEMON_ID_2) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_3 FOREIGN KEY (POKEMON_ID_3) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_4 FOREIGN KEY (POKEMON_ID_4) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_5 FOREIGN KEY (POKEMON_ID_5) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_TEAM_POKEMONS_ORDERED_POKEMON_6 FOREIGN KEY (POKEMON_ID_6) REFERENCES POKEMONS (ID)
);

CREATE TABLE TYPES
(
    ID   NUMBER GENERATED AS IDENTITY PRIMARY KEY,
    NAME VARCHAR2(255)
        CONSTRAINT U_TYPES_NAME UNIQUE NOT NULL
);

CREATE TABLE POKEMON_TYPES
(
    POKEMON_ID NUMBER,
    TYPE_ID    NUMBER,
    CONSTRAINT PK_POKEMON_TYPES PRIMARY KEY (POKEMON_ID, TYPE_ID),
    CONSTRAINT FK_POKEMON_TYPES_POKEMON FOREIGN KEY (POKEMON_ID) REFERENCES POKEMONS (ID),
    CONSTRAINT FK_POKEMON_TYPES_TYPE FOREIGN KEY (TYPE_ID) REFERENCES TYPES (ID)
);