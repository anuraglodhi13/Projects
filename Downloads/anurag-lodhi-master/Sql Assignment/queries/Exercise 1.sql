--Exercise 1

--1. Display the number of records in the [SalesPerson] table. (Schema(s) involved: Sales)
select Count(*) as NumberOfRecords
from Sales.SalesPerson;


--2. Select both the FirstName and LastName of records from the Person table where the FirstName begins with the letter ‘B’. (Schema(s) involved: Person)
select FirstName, LastName 
from Person.Person
where FirstName like 'B%';


--3. Select a list of FirstName and LastName for employees where Title is one of Design Engineer, Tool Designer or Marketing Assistant. (Schema(s) involved: HumanResources, Person)
select FirstName, LastName
from Person.Person p, HumanResources.Employee e
where p.BusinessEntityID = e.BusinessEntityID and e.JobTitle In ('Design Engineer' , 'Tool Designer', 'Marketing Assistant');


--4. Display the Name and Color of the Product with the maximum weight. (Schema(s) involved: Production)
select Name, Color, ProductID
from Production.Product
where Weight = (Select MAX(Weight) from Production.Product);


--5. Display Description and MaxQty fields from the SpecialOffer table. Some of the MaxQty values are NULL, in this case display the value 0.00 instead. (Schema(s) involved: Sales)
select isnull(MaxQty,0.00) as MaxQty,Description
FROM Sales.SpecialOffer;


--6. Display the overall Average of the [CurrencyRate].[AverageRate] values for the exchange rate ‘USD’ to ‘GBP’ for the year 2005 i.e. FromCurrencyCode = ‘USD’ and ToCurrencyCode = ‘GBP’. Note: The field [CurrencyRate].[AverageRate] is defined as 'Average exchange rate for the day.' (Schema(s) involved: Sales)
SELECT AVG(EndOfDayRate) as 'Average_exchange_rate_for_the_day' 
from Sales.CurrencyRate 
where FromCurrencyCode = 'USD' and ToCurrencyCode = 'GBP' and YEAR((CurrencyRateDate)) = 2005;


--7. Display the FirstName and LastName of records from the Person table where FirstName contains the letters ‘ss’. Display an additional column with sequential numbers for each row returned beginning at integer 1. (Schema(s) involved: Person)
select ROW_NUMBER() Over(Order by FirstName) No, FirstName, lastName
from Person.Person
Where FirstName like '%ss%';


--8. Sales people receive various commission rates that belong to 1 of 4 bands. (Schema(s) involved: Sales)Display the [SalesPersonID] with an additional column entitled ‘Commission Band’ indicating the appropriate band as above
select BusinessEntityID as SalesPersonId,  
case when CommissionPct = 0 then 'Band 0'
when CommissionPct > 0 and CommissionPct <= 0.01 then 'Band 1'
when CommissionPct > 0.01 and CommissionPct <= 0.015 then 'Band 2'
when CommissionPct > 0.015 then 'Band 3'
end as CommissionBand
from Sales.SalesPerson;


--9. Display the managerial hierarchy from Ruth Ellerbrock (person type – EM) up to CEO Ken Sanchez. Hint: use [uspGetEmployeeManagers] (Schema(s) involved: [Person], [HumanResources]) 
DECLARE @EID int = 
( SELECT Person.Person.BusinessEntityID
FROM Person.Person WHERE FirstName = 'Ruth' AND LastName = 'Ellerbrock' AND PersonType ='EM' ) ; 
EXEC dbo.uspGetEmployeeManagers @BusinessEntityID = @EID;


--10. Display the ProductId of the product with the largest stock level. Hint: Use the Scalar-valued function [dbo]. [UfnGetStock]. (Schema(s) involved: Production)*/
select ProductID 
from Production.ProductInventory 
where Quantity in
(select max(dbo.ufnGetStock(ProductID))
FROM Production.ProductInventory);