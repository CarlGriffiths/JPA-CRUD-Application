
drop table Movie;
drop table Studio;
drop table UserAdmin;
drop table UserRegular;
drop table MovieUser;




drop sequence mId_seq;
drop sequence uId_seq;
drop sequence sId_seq;


create sequence mId_seq increment by 1 start with 1;
create sequence uId_seq increment by 1 start with 1;
create sequence sId_seq increment by 1 start with 1;


create table Studio(
    sId number Primary key,
   sName VARCHAR(255)
    
   
);

create table Movie (
    mId number Primary key,
    mName VARCHAR(255),
    mRating number,
    runTimeMin number,
    budget number,
    boxOffice number,
    sId number,
    
FOREIGN KEY (sId) REFERENCES Studio (sId)
);



create table MovieUser(
    uName VARCHAR(255) Primary key,
    muId number,
    TYPE VARCHAR2(20),
    
    uPass VARCHAR(255)

    
    
);

create table UserAdmin(
    uName VARCHAR(255) Primary key,
    
    FOREIGN KEY (uName) REFERENCES MovieUser (uName)
);

create table UserRegular(
   uName VARCHAR(255) Primary key,
    
    FOREIGN KEY (uName) REFERENCES MovieUser (uName)
);


INSERT INTO Studio values(sId_seq.nextVal, 'Sony');
INSERT INTO Movie values(mId_seq.nextVal, 'War for the Planet of the Apes', 10, 190, 300, 500, 1);




