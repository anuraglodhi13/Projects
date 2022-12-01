/*
Exercise 2: Write separate queries using a join, a subquery, a CTE, and then an EXISTS to list all AdventureWorks customers who have not placed 
an order.
*/

--Using Exist
select * 
from Sales.Customer as C
where not exists
(select * from Sales.SalesOrderHeader as S
where C.customerid = S.customerid)