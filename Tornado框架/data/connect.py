#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from sqlalchemy import create_engine

HOSTNAME = '127.0.0.1'
PORT = '3306'
DATABASE = 'mydb'
USERNAME = 'root'
PASSWORD = 'qwe'

db_url = 'mysql+pymysql://{}:{}@{}:{}/{}?charset=utf8'.format(
    USERNAME, PASSWORD, HOSTNAME, PORT, DATABASE
)

engine = create_engine(db_url)


# 创建一个基类
from sqlalchemy.ext.declarative import declarative_base
Base = declarative_base(engine)


# 创建会话
from sqlalchemy.orm import sessionmaker
Session = sessionmaker(engine)
session = Session()


if __name__ == "__main__":

    conn = engine.connect()
    result = conn.execute('select 1')
    print(result.fetchone())
