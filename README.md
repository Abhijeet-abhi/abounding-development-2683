<h1 align="center">Online Hardware And Software Support System</h1>

This is a console-based project that can be used by an organisation internally to support and maintain the system environment of the organisation. The users of this application can be the employees of the organisation, System Engineers, and the HOD(Head of Department). 

<h3>Functionalities:- </h3>

<h5>Employees:-</h5> Can login or register to the system. || Can raise a ticket if facing any software or hardware issues with his system. || Can check the status of a raised complaint. || Can check history of all the complaints raised by him/her. || Can change his password for log in.

<h5>HOD:-</h5>Can login to the system. || Can register a new System Engineer(Software/Hardware). || Can check all the newly raised complaints. || Can assign complaints to an Engineer || Can check the complete complaint history. || Remove an Engineer.

<h5>Engineer:-</h5> Can login to the system. || Can check complaints assigned to him/her. || Can update the status of the complaints he/she is assigned. || Can check all the complaints attended by him or her. || Can change his/her password.

<h3>ER Diagram:-</h3>

![ER Diagram](https://user-images.githubusercontent.com/69115064/221394047-f348aede-222f-45fe-8326-d57901b22475.png)

<h3>SQL Tables Code as below</h3>
<h6>Step-1: Create HOD Table</h6>

	Create table Hod(
    empUserName varchar(30) unique,
    empPassword varchar(20)
    );

<h6>Step-2: Create Engineer Table</h6>
	
	Create table engineer(
    engId int auto_increment,
    engName varchar(20),
    engUsername varchar(30) not null unique,
    engPassword varchar(20) not null,
    engCategory varchar(20),
    PRIMARY KEY (engId)
    );

<h6>Step-3: Create Employee Table</h6>
	
	Create table employee(
    empID int primary key auto_increment,
    empName varchar(20),
    empUserName varchar(30) unique,
    empPassword varchar(20)
    );
    
<h6>Step-4: Create ComplainBook Table</h6>
	
	Create table complainBook(
    Sno int(6) primary key auto_increment,
    TicketNumber int(10) unique,
    Type varchar(20) not null,
    Description varchar(50),
    StatusOfComplain varchar(15) default 'New',
    AssignEngineer varchar(30),
    ComplainByEmployee varchar(20),
    FOREIGN KEY (AssignEngineer) REFERENCES engineer(engUsername)
    ON DELETE SET NULL
    );

<h6>Step-5: Create ComplainHistory Table</h6>

	Create table complainHistory(
    Sno int(6) primary key auto_increment,
    TicketNumber int(10) not null unique,
    Type varchar(20) not null,
    Description varchar(50) not null,
    StatusOfComplain varchar(15) default 'Completed',
    AssignEngineer varchar(30),
    ComplainByEmployee varchar(20) not null
    );

-----------------------------------------------------------------------------------------------------------------------------------------------------------

<h2 align="left"><i>Tech Stack Used :</i></h2>
<div align="left">
<img alt="Java" src="https://img.shields.io/badge/java-f89820.svg?style=for-the-badge&logo=java&logoColor=white"/>
<img alt="MySQL" src="https://img.shields.io/badge/MySql-00758f?style=for-the-badge&logo=mysql&logoColor=white"/>
