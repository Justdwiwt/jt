create table tb_item_cat
(
  id         bigint not null auto_increment,
  parent_id  bigint comment '父分类ID=0时，代表一级分类',
  name       varchar(150),
  status     int(1)          default 1
  comment '默认值为1，可选值：1正常，2删除',
  sort_order int(4) not null,
  is_parent  tinyint(1),
  created    datetime,
  updated    datetime,
  primary key (id)
);

create index parent_id
  on tb_item_cat
  (
    parent_id,
    status
  );

create index sort_order
  on tb_item_cat
  (
    sort_order
  );