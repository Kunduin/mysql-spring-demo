create table IF NOT EXISTS scheme
(
  scheme_id          int auto_increment
    primary key,
  call_price         float        null,
  free_call_time     int          null,
  free_message_count int          null,
  free_online_time   int          null,
  is_online_local    bit          null,
  message_price      float        null,
  scheme_name        varchar(255) null,
  online_price       float        null,
  price              float        null
)
  engine = MyISAM;


create table IF NOT EXISTS scheme
(
  subscription_id int auto_increment
    primary key,
  is_active       bit  null,
  start_at        date null,
  scheme_id       int  null,
  user_id         int  null
)
  engine = MyISAM;



create table IF NOT EXISTS user
(
  user_id           int auto_increment
    primary key,
  call_time         int          null,
  local_online_time int          null,
  message_count     int          null,
  user_name         varchar(255) null,
  sum_online_time   int          null
)
  engine = MyISAM;


INSERT INTO user (user_id, call_time, local_online_time, message_count, user_name, sum_online_time)
VALUES
(1, 0, 0, 0, 'user0', 0),
(2, 1, 1, 1, 'user1', 1),
(3, 2, 2, 2, 'user2', 2),
(4, 3, 3, 3, 'user3', 3),
(5, 4, 4, 4, 'user4', 4),
(6, 5, 5, 5, 'user5', 5),
(7, 6, 6, 6, 'user6', 6),
(8, 7, 7, 7, 'user7', 7),
(9, 8, 8, 8, 'user8', 8),
(10, 9, 9, 9, 'user9', 9);