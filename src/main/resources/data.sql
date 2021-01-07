insert into accounts(account_id,cust_id,account_name,balance_date,currency,balance,account_type)
values('1000000007','1001','Salary Account','2020-12-09','USD',1000,'Savings');
insert into accounts(account_id,cust_id,account_name,balance_date,currency,balance,account_type)
values('1000000008','1001','Fixed Deposit','2019-12-09','USD',2000,'Savings');
insert into accounts(account_id,cust_id,account_name,balance_date,currency,balance,account_type)
values('1000000009','1001','OD Account','2018-12-09','USD',3000,'Current');
insert into transaction(trans_id,amount,trans_date,trans_details,account_id,credit)
 values('12345670',30000,'2018-06-09','credited from ABC','1000000007',true);
insert into transaction(trans_id,amount,trans_date,trans_details,account_id,credit)
values('12345671',10000,'2019-07-10','debited for electricity bill','1000000007',false);
insert into transaction(trans_id,amount,trans_date,trans_details,account_id,credit)
 values('12345672',5000,'2020-12-12','debited in ATM','1000000007',false);