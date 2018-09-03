#!/usr/bin/env python3
# -*- coding=utf8 -*-
import  sys
sys.path.append(r'/home/pyvip/virtual/Review_TZ/R_Tornado/util')
import tornado.httpserver
import tornado.web
import tornado.ioloop
import tornado.escape
from tornado.options import define, options
import tornado.ioloop
import time
from connect import session
from user_table import User


def search_user(username):
    rows = session.query(User.password).filter(User.username == username)
    # print(rows)
    return rows.one()


# 定义一个默认的接口
define("port", default=11111, help="run port", type=int)
define("start", default=False, help="start server", type=bool)


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("11index.html", error=None)



class LoginHandler(tornado.web.RequestHandler):
    def post(self, *args, **kwargs):
        user = self.get_argument('user', 'None')
        pwd = self.get_argument('pwd', 'None')
        try:
            data = search_user(user)
            if data and pwd == data[0]:
                self.render("12login.html", username=user)
        except Exception as e:
            self.redirect("/main")


if __name__ == "__main__":
    options.parse_command_line()
    if options.start:
        application = tornado.web.Application(
            handlers=[
                (r'/main', MainHandler),
                (r'/login', LoginHandler),
            ],
            template_path="templates",
            static_path="statics",
            # autoescape=None,
            debug=True,
        )
        http_server = tornado.httpserver.HTTPServer(application)  # 通过应用实例创建服务器实例
        http_server.listen(options.port)  # 监听端口
        print('start server...')
        tornado.ioloop.IOLoop.instance().start()  # 启动服务
