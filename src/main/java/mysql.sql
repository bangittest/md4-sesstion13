create database cateproduct;
use cateproduct;
create table category(
    id int primary key auto_increment,
    name varchar(55) not null,
    status bit(1) default 1
);

create table product(
    id int primary key auto_increment,
    name varchar(255) not null ,
    price double ,
    category_id int not null ,
    foreign key (category_id) references category(id)
);
delimiter //
create procedure PROC_FIND_ALL_CATEGORY()
begin
    select * from category;
end;

delimiter //
create procedure PROC_FIND_ALL_PRODUCT()
begin
    select *from product;
end //

delimiter //
create procedure PROC_FIND_BY_ID_CATEGORY(in _id int)
begin
    select *from category where id=_id;
end //

delimiter //
create procedure PROC_FIND_BY_ID_PRODUCT(in _id int)
begin
    select *from product where id=_id;
end //

delimiter //
create procedure PROC_CREATE_CATEGORY(in _name varchar(55) ,in _status bit)
begin
    insert into category(name, status) VALUES (_name,_status);
end //

delimiter //
create procedure PROC_CREATE_PRODUCT(in _name varchar(255),_price double,_category_id int)
begin
    insert into product(name, price, category_id) values (_name,_price,_category_id);
end //


delimiter //
create procedure PROC_UPDATE_CATEGORY(in _id int,in _name varchar(55),_status bit)
begin
    update category set name=_name ,status=_status where id=_id;
end //

delimiter //
create procedure PROC_UPDATE_PRODUCT(in _id int,in _name varchar(255),_price double,_category_id int)
begin
    update product set name=_name,price=_price,category_id=_category_id where id=_id;
end //


delimiter //
create procedure PROC_DELETE_PRODUCT(in _id int)
begin
    delete from product where id=_id;
end //

delimiter //
create procedure PROC_DELETE_CATEGORY(in _id int)
begin
    delete from category where id=_id;
end //
