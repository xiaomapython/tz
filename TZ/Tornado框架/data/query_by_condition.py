#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from connect import session
from user_modules import User

def search_user():
    rows = session.query(User.id).filter(User.username==2)
    print(rows)
    print('-----------------------------------------')
    # 和上面一样的效果
    rows = session.query(User.id).filter_by(username=2)
    print(rows)


if __name__ == '__main__':
    search_user()
    print('==========================模糊查询==============================')
    # 模糊查询
    print(session.query(User.id).filter(User.username.like ('h%')).all())
    print(session.query(User.username).filter(User.username.like('h%')).all())
    print(session.query(User.username).filter(User.username.notlike('h%')).all())
    print('=-'*15+'in And notin'+'-='*15)
    # in使用
    print(session.query(User.id).filter(User.username.in_(['hcg', 'mt'])).all())
    print(session.query(User.id).filter(User.username.notin_(['hcg', 'mt'])).all())
    # is 使用
    print(session.query(User.id).filter(User.username.is_(None)).all())
    print(session.query(User.username).filter(User.password.is_(None)).all())
    print(session.query(User.id).filter(User.username.isnot(None)).all())
    print('=====================limit 使用 限制查询======================')
    # limit 使用 限制查询
    print(session.query(User.username).all())  # 查询所有
    print(session.query(User.username).limit(2).all())  # 查询前两个
    print(session.query(User.username).offset(3).all())  # 设置偏移量，从第3个开始查询
    print(session.query(User.username).slice(0,2).all())  # 切片查询
    print(session.query(User.id).filter(User.username.like('1%')).one())  # 查询只满足条件的一条数据，如果查到多条会报错
    print('===============================排序====================================')
    # 排序(默认升序排列)
    print(session.query(User.id,User.username,User.password).order_by(User.id).all())
    print(session.query(User.id,User.username,User.password).order_by(User.id).limit(3).all())

    # 降序排列
    from sqlalchemy import desc
    print(session.query(User.id, User.username, User.password).order_by(desc(User.id)).all())
    print('=+'*15+'分组查询'+'+='*15)
    # 分组
    from sqlalchemy import func,extract
    print(session.query(User.password,func.count(User.username)).group_by(User.password).all())
    print(session.query(User.password, func.count(User.username)).group_by(User.password).having(func.count(User.username)>1).all())
    # 提取时间的分钟
    print(session.query(extract('minute',User.creatime).label('minute'),func.count(User.id)).group_by('minute').all())
