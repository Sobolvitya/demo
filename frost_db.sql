drop database frost;
create database frost;
use frost;

create table user(
	id int not null auto_increment,
    full_name char(50) not null default '',
    email char(50) not null DEFAULT  '',
    pass_hash char(250) not null default '1111',
    level int not null default 0,
    registration_date date not null default '1970.01.01',
    type enum('admin', 'user') not null default 'user',
    primary key(id)
);

create table tutorial(
	id int not null auto_increment,
    name char(50) not null default '',
    level int not null default 0,
    primary key(id)
);

create table task(
	id int not null auto_increment,
  tutorial_id int,
    name char(50) not null default '',
    level int not null default 0,
    max_score int not null default 0,
    primary key(id),
    foreign key(tutorial_id) references tutorial(id)
);


create table user_tutorial(
	user_id int not null,
    tutorial_id int not null,
    primary key(user_id, tutorial_id),
    foreign key(user_id) references user(id),
	foreign key(tutorial_id) references tutorial(id)
);

create table submission(
	id int not null auto_increment,
    score int not null default 0,
    task_id int,
    user_id int,
    primary key(id),
    foreign key(user_id) references user(id),
    foreign key(task_id) references task(id)
);

