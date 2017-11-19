drop table Movie;
drop table UserAdmin;
drop table UserRegular;
drop table MovieUser;



drop sequence mId_seq;
drop sequence uId_seq;



create sequence mId_seq increment by 1 start with 1;
create sequence uId_seq increment by 1 start with 1;


create table Movie (
    mId number Primary key,
    mName VARCHAR(255),
    mRating number,
    runTimeMin number,
    budget number,
    boxOffice number
);

create table MovieUser(
    muId number Primary key,
    TYPE VARCHAR2(20),
    uName VARCHAR(255),
    uPass VARCHAR(255)
    
);

create table UserAdmin(
    muId number Primary key,
    
    FOREIGN KEY (muId) REFERENCES MovieUser (muId)
);

create table UserRegular(
    muId number Primary key,
    
    FOREIGN KEY (muId) REFERENCES MovieUser (muId)
);

INSERT INTO Movie values(mId_seq.nextVal, 'Thor', 10, 190, 300, 500 );
INSERT INTO Movie values(mId_seq.nextVal, 'The wlaking dead', 9, 150, 100, 120);

INSERT INTO MovieUser values(uId_seq.nextVal, 'a', 'admin', 'pass');
INSERT INTO UserAdmin values(uId_seq.currVal);

