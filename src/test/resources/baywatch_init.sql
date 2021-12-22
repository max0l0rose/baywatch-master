 
CREATE TABLE BEACH(
    ID BIGINT NOT NULL,
    NAME VARCHAR(255)
);         
ALTER TABLE BEACH ADD CONSTRAINT CONSTRAINT_3 PRIMARY KEY(ID);
 
INSERT INTO BEACH(ID, NAME) VALUES
(1, 'Malibu'),
(2, 'El Segundo'),
(3, 'Hermosa'),
(4, 'Long Beach'),
(5, 'Marina Del Rey');    
CREATE TABLE LIFEGUARD(
    ID BIGINT NOT NULL,
    NAME VARCHAR(255),
    SPEED INTEGER,
    BEACH_ID BIGINT,
    BOSS_ID BIGINT
);      
ALTER TABLE LIFEGUARD ADD CONSTRAINT CONSTRAINT_9 PRIMARY KEY(ID);
            
INSERT INTO LIFEGUARD(ID, NAME, SPEED, BEACH_ID, BOSS_ID) VALUES
(6, 'Mitch Buchannon', 5, 1, NULL),
(7, 'John D. Cort', 3, 1, 6),
(8, 'Eddie Kramer', 2, 1, 6),
(9, 'Summer Quinn', 4, 1, 6),
(10, 'C. J. Parker', 4, 2, NULL),
(11, 'Hobie Buchannon', 4, 2, 10),
(12, 'Trevor Cole', 4, 2, 10),
(13, 'Gina Pomeroy', 2, 2, 10),
(14, 'Jimmy Slade', 5, 2, 10),
(15, 'Ben Edwards', 3, 2, 10),
(16, 'Craig Pomeroy', 4, 3, NULL),
(17, 'Jill Riley', 3, 3, 16),
(18, 'Shauni McClain', 2, 3, 16),
(19, 'Hobie Buchannon', 5, 3, 16),
(20, 'Garner Ellerbee', 4, 3, 16),
(21, 'Don Thorpe', 3, 3, 16),
(22, 'Harvey Miller', 1, 3, 16),
(23, 'Matt Brody', 4, 4, NULL);             
ALTER TABLE LIFEGUARD ADD CONSTRAINT FKFJTYJ7PAI8E3O74QLIWSU4L9K
    FOREIGN KEY(BEACH_ID) REFERENCES BEACH(ID);
ALTER TABLE LIFEGUARD ADD CONSTRAINT FKLFXXF9O7XAQT319G4DD0E1JRR
    FOREIGN KEY(BOSS_ID) REFERENCES LIFEGUARD(ID);

