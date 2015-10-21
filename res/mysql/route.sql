use marshall;
drop table if exists route;
create table route (
	routeId int unsigned not null auto_increment,
	routeName varchar(15) not null,
	grade varchar(15) not null,
	crag varchar(50) not null,
	primary key(routeId)
);
