create database if not exists zafunity;

use zafunity;

-- auto-generated definition
create table post
(
    post_id     bigint auto_increment comment '帖子ID'
        primary key,
    title       varchar(50)                          not null comment '帖子标题',
    content     text                                 not null comment '帖子内容',
    creater_id  bigint                               not null comment '用户ID',
    starts      int        default 0                 not null comment '帖子点赞数',
    post_tags   varchar(200)                         null comment '帖子标签(JSON)',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint(1) default 0                 not null comment '是否删除 0未删除 1已删除'
)
    comment '帖子表';

create index post_title_index
    on post (title);

-- auto-generated definition
create table user
(
    user_id       bigint auto_increment comment '用户ID'
        primary key,
    user_account  varchar(50)                           not null comment '用户账号',
    user_password varchar(50)                           not null comment '用户密码',
    email         varchar(50)                           not null comment '用户邮箱',
    user_role     varchar(20) default 'user'            not null comment '用户角色 user/admin/ban',
    user_avatar   varchar(200)                          null comment '用户头像',
    user_nickname varchar(50)                           null comment '用户昵称',
    user_gender   varchar(10) default '保密'            null comment '用户性别',
    user_birthday date                                  null comment '用户生日',
    user_grade    varchar(20) default '未知'            null comment '用户年级',
    user_profile  text                                  null comment '用户简介',
    user_tags     varchar(200)                          null comment '用户标签(JSON)',
    create_time   timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint(1)  default 0                 not null comment '是否删除 0未删除 1已删除'
)
    comment '用户表';

create index user_user_nickname_index
    on user (user_nickname);

