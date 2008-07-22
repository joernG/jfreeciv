CREATE TABLE auth (
  id int(11) NOT null auto_increment,
  name varchar(32) default null,
  password varchar(32) default null,
  email varchar(128) default null,
  createtime int(11) default null,
  accesstime int(11) default null,
  address varchar(255) default null,
  createaddress varchar(15) default null,
  logincount int(11) default '0',
  PRIMARY KEY  (id),
  UNIQUE KEY name (name)
) TYPE=MyISAM;

CREATE TABLE loginlog (
  id int(11) NOT null auto_increment,
  name varchar(32) default null,
  logintime int(11) default null,
  address varchar(255) default null,
  succeed enum('S','F') default 'S',
  PRIMARY KEY  (id)
) TYPE=MyISAM;
