#!/usr/bin/env python3
# -*- coding=utf-8 -*-

import tornado.ioloop
import tornado.web
from tornado.options import define, options
# 定义一个默认的接口
define("port", default=11111, help="run port", type=int)
define("start", default=False, help="start server", type=bool)


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("hello world\n")


application = tornado.web.Application([
    (r'/main', MainHandler)
])


if __name__ == "__main__":
    options.parse_command_line()
    if options.start:
        http_server = tornado.httpserver.HTTPServer(application)  # 通过应用实例创建服务器实例
        http_server.listen(options.port)  # 监听端口
        print('start server...')
        tornado.ioloop.IOLoop.instance().start()  # 启动服务
