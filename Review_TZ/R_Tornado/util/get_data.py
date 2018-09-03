#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from connect import session
import sys
sys.path.append(r'F:\开发工具\python系列\virtual\test_tornado\data\connect.py')
from user_table import User

def search_user(user):
    rows = session.query(User.password).filter(User.username == user)
    print(rows)
    return rows.one()

if __name__ == '__main__':
    data = search_user('mjc')
    if data:
        print(1)
    else:
        print(0)
