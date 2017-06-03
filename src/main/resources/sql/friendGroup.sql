
drop DATABASE IF EXISTS yudo;

create DATABASE yudo;

use yudo;


/**用户表**/
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  alais VARCHAR(40) DEFAULT NULL ,
  headPortait  VARBINARY(500) DEFAULT null,
  email VARCHAR (40) DEFAULT NULL ,
  gender VARCHAR (10) DEFAULT NULL ,
  realName VARCHAR (40) DEFAULT NULL ,
  phone VARCHAR (20) DEFAULT NULL ,
  createTime DATETIME DEFAULT NULL ,
  username VARCHAR (40) DEFAULT NULL ,
  password VARCHAR (40) DEFAULT NULL ,
  selfIntroduce VARCHAR (100) DEFAULT NULL ,
  tag VARCHAR (10) DEFAULT NULL ,
  vehicleType VARCHAR (20) DEFAULT NULL ,
  interest VARCHAR (40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET = utf8;


/*消息表*/
DROP TABLE IF EXISTS message;
CREATE TABLE message (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  uid int(11) NOT NULL ,
  content VARCHAR(100) DEFAULT NULL ,
  link VARCHAR(100) DEFAULT NULL ,
  replycount INTEGER DEFAULT NULL ,
  forwardcount INTEGER DEFAULT NULL ,
  favcount INTEGER DEFAULT NULL ,
  topic VARCHAR(40) DEFAULT NULL ,
  createtime DATETIME DEFAULT null,
  CONSTRAINT fk_user_message FOREIGN KEY(uid) REFERENCES USER(id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;


/*回复表*/
drop table IF EXISTS messageReply;
create table messageReply (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  mid int(11),
  uid int(11),
  createTime DATETIME DEFAULT null,
  content VARCHAR(100) DEFAULT null,
  constraint fk_message_reply FOREIGN KEY(mid) REFERENCES message(id),
  CONSTRAINT fk_user_reply FOREIGN KEY(uid) REFERENCES user(id)
)ENGINE=innodb default charset = utf8;


/*转发表*/
drop table if exists forward;
create table forward(
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  uid int(11) not null,
  buid int(11) not null,
  mid int(11) not null,
  CONSTRAINT fk_message_forward FOREIGN KEY (mid) REFERENCES message(id),
  CONSTRAINT fk_user_forward_1 FOREIGN KEY (uid) REFERENCES user(id),
  CONSTRAINT fk_user_forward_2 FOREIGN KEY (buid) REFERENCES user(id)
)ENGINE=innodb DEFAULT CHARSET = utf8;


/*收藏表*/
drop table if exists favourite;
create table favourite(
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  uid int(11) not null,
  mid int(11) not null
)ENGINE = innodb DEFAULT CHARSET = utf8;


/*相册photoFolder表*/
# DROP TABLE IF EXISTS photoFolder;
# create table  photoFolder(
#   id Char(11) not null primary key,
#   uid Char(11),
#   mid CHAR(11),
#   name varchar(20) not null,
#   count int not null,
#   constraint FK1 foreign key(uid) references user(id)
# )ENGINE=InnoDB DEFAULT CHARSET = utf8;


/*图片photo表*/
DROP TABLE IF EXISTS photo;
create table photo(
  id int(11) NOT NULL AUTO_INCREMENT primary key,
  uid int(11) DEFAULT null,
  mid int(11) DEFAULT null,
  name VARCHAR(20) DEFAULT null,
  address VARCHAR(100) DEFAULT null,
  CONSTRAINT fk_user_photo FOREIGN KEY(uid) REFERENCES user(id),
  CONSTRAINT fk_message_photo FOREIGN KEY (mid) REFERENCES message(id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;