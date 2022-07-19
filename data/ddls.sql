create schema 'aoue-analysis';

create table cand
(
    cand_id     int auto_increment
        primary key,
    cand_nm     varchar(100)  not null,
    cand_party  varchar(100)  not null,
    cand_amount int default 0 not null comment '总捐献额',
    constraint cand_cand_id_uindex
        unique (cand_id)
)
    comment '候选者';
