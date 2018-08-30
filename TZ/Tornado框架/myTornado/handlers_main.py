#!/usr/bin/env python3
# -*- coding=utf-8 -*-

import tornado.httpserver
import tornado.web
import tornado.ioloop
import tornado.escape
from tornado.options import define, options
import tornado.ioloop

# 定义一个默认的接口
define("port", default=11111, help="run port", type=int)
define("start", default=False, help="start server", type=bool)


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        atg = "<a href=https://www.baidu.com>百度</a>"
        self.render("05extend.html", atg=atg)


if __name__ == "__main__":
    options.parse_command_line()
    if options.start:
        application = tornado.web.Application(
            handlers=[
                (r'/main', MainHandler),
            ],
            template_path="templates",
            static_path="statics",
            # autoescape=None,
            debug=True
        )
        http_server = tornado.httpserver.HTTPServer(application)  # 通过应用实例创建服务器实例
        http_server.listen(options.port)  # 监听端口
        print('start server...')
        tornado.ioloop.IOLoop.instance().start()  # 启动服务
