#!/usr/bin/env python3
# -*- coding=utf-8 -*-

from datetime import datetime
from sqlalchemy import Column, Integer, String, DateTime, Boolean

from connect import Base


class User(Base):
    __tablename__ = 'user'
    id = Column(Integer,primary_key=True)
    username = Column(String(20), nullable=False)
    password = Column(String(50), nullable=False)
    create_time = Column(DateTime,default=datetime.now)
    _locked = Column(Boolean, default=False, nullable=False)

    def __repr__(self):
        return '''[%s, %s, %s,%s,%s]''' % (
            self.id,
            self.username,
            self.password,
            self.create_time,
            self._locked
        )


if __name__ == '__main__':
    Base.metadata.create_all()




