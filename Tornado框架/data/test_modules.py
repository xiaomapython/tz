#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from connect import session
from user_modules import User

def add_user():
    person = User(username='mjc', password='qwe123')
    # 往表中插入一条数据
    session.add(person)
    # 往表中插入多条数据
    session.add_all(
        [
            User(username='hcg', password='789'),
            User(username='mm', password='waafd'),
            User(username='mt', password='129hhh'),
        ]
    )

    session.commit()


def search_user():
    rows = session.query(User).all()
    print(rows)

    rows = session.query(User).first()
    print(rows)

def update_user():
    rows = session.query(User).filter(User.username=='mjc').update({User.password:"qweqe"})
    print(rows)
    session.commit()


def delete_user():
    rows = session.query(User).filter(User.username == 'mjc').delete()
    print(rows)
    session.commit()

if __name__ == '__main__':
    # add_user()
    # search_user()
    # update_user()
    delete_user()