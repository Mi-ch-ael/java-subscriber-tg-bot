--liquibase formatted sql

--changeset nvoxland:1
CREATE SCHEMA IF NOT EXISTS scrapper;
CREATE TABLE IF NOT EXISTS scrapper.hyperlink(
    link_id INT PRIMARY KEY,
    last_modified TIMESTAMP NOT NULL,
    url VARCHAR(220)
);
CREATE TABLE IF NOT EXISTS scrapper.chat(
    chat_id INT PRIMARY KEY,
    telegram_chat_id BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS scrapper.hyperlink_chat(
    link_id INT,
    chat_id INT,
    FOREIGN KEY (link_id) REFERENCES scrapper.hyperlink (link_id),
    FOREIGN KEY (chat_id) REFERENCES scrapper.chat (chat_id),
    PRIMARY KEY (link_id, chat_id)
);