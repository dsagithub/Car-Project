drop user 'car'@'localhost';
create user 'car'@'localhost' identified by 'car';
grant all privileges on cardb.* to 'car'@'localhost';
flush privileges;
