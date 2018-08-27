#!/usr/bin/env python3
# -*- coding=utf-8 -*-

import tornado.ioloop
import tornado.web


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("hello world\n")


application = tornado.web.Application([
    (r'/main', MainHandler)
])


if __name__ == "__main__":
    application.listen(11111)
    tornado.ioloop.IOLoop.instance().start()
