To make sure everything work please connect with Nagarro VPN first.

Run the applications in the order given below to work everything fine:-

1.Eureka Server

2.Spring Cloud Config Server

3.Api Gateway

4.Customer Management Service

5.Account Management Service

Go to http://localhost:8761/ and checks if Account Management Service, Api Gateway and Customer Management 
Service are registered on it or not.Also scroll down the page and copy the ip address. Now go to AccountManagementService>src>main>java>com>nagarro>constants>Constant.java and paste the ip address you copied earlier and restart this service. 
Follow the same steps for Customer Management Service.

API Gateway Configurations routing can be found in application.properties in api gateway directory.

As per requirement I have created the centralised configuration management.
It contains the db configuration for both customer and account services.
If anything fails must correct your local my sql configuration in Config Repo>application.yml.
And update the correct path of application.yml file in Spring Cloud Config Server's directory application.properties file. 
For now it's given according to my local machine.
If Spring Cloud Config Server fails to start make sure to create local git repo for Config Repo.

Services API's with pojo (can check by hitting postman)

CustomerMangementService 
1. Add Customer

Post Method
```URL : http://<ip-address>:8082/banking/customer```
Sample pojo : 
```
{
        "name": "Hji",
        "accno":"234",
        "email":"hi@abc.com"
}

```
2. Get All Customers

Get Method 
```URL : http://<ip-address>:8082/banking/customer/all```

3. Get Single Customer 

Get Method
```URL : http://<ip-address>:8082/banking/customer/<customer-id>```

4. Update Customer Details

Put Method 
```URL : http://<ip-address>:8082/banking/<customer-id>```
Sample Pojo: 
```
{
        "name": "Hj",
        "accno":"234",
        "email":"hia@abc.com"
}
```
5. Delete Customer 

Delete Method 
```URL : http://<ip-address>:8082/banking/<customer-id>```

AccountMangementService 

1. Add Money To Account

Put Method 
```URL : http://<ip-address>:8083/banking/account/addmoney```
Sample Pojo
```
{
    "accno":"234",
    "money":"1000"
}
```

2. Withdraw Money From Account
Put Method
```URL : http://<ip-address>:8083/banking/account/withdrawmoney```
Sample Pojo
```
{
    "accno":"234",
    "money":"1000"
}
```

3. Get Account Details 
Get Method
```URL : http://<ip-address>:8083/banking/account/<accountNo>```

4. Delete Account
Delete Method
```URL : http://<ip-address>:8083/banking/account/<accountNo>```