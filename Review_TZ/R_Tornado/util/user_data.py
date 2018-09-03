#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from connect import session
from user_table import User


def add_data():
    person = User(username='mjc', password='123')
    session.add(person)
    session.commit()


if __name__ == '__main__':
    add_data()
