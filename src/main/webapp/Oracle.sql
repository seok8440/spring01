create table member(
userid varchar2(50) not null primary key,
passwd varchar2(50) not null,
name varchar2(50) not null,
email varchar2(50),
join_date date default sysdate
);

insert into member(userid,passwd,name,email) values
('seok7777','1234','김진석','seok7777@naver.com');

select * from member;
commit

drop table member;

-- 모든 유저 검색--
select * from all_users;