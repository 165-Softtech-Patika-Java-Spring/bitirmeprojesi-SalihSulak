IF NOT EXIST(select * from vat_rates) THEN
insert into vat_rates(product_type,vat_percentage) values('FOOD',1),('STATIONARY',8),('CLOTHES',8),('TECHNOLOGY',18),('CLEANING',18),('OTHER',18);
END IF;
