#!/usr/bin/env python3
# -*- coding=utf-8 -*-
from util.connect import session
from util.user_table import User


def search_user(user):
    rows = session.query(User.password).filter(User.username == user)
    print(rows)
    return rows.one()

if __name__ == "__main__":
    search_user('mjc')



