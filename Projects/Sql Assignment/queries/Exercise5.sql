/*
Exercise 5: Write a Procedure supplying name information from the Person.Person table and accepting a filter for the first name. Alter the above 
Store Procedure to supply Default Values if user does not enter any value. ( Use AdventureWorks)
*/

-- if not prvoide any input it return the information according to default value ken

create procedure getInfoByFirstName
@name varchar(50) = 'ken'
as
select *
from Person.Person
where FirstName=@Name; 

-- example query to test with default value and ruth as first name
-- exec dbo.getInfoByFirstName @Name = default;
-- exec dbo.getInfoByFirstName @Name = 'ruth';