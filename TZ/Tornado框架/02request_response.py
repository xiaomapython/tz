#!/usr/bin/env python3
# -*- coding=utf-8 -*-
import tornado.ioloop
import tornado.web


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("hello world")


class SendHandler(tornado.web.RequestHandler):
    def get(self):
        self.send_error(404)

    def write_error(self, status_code, **kwargs):
        self.write("页面不存在，错误码为： %s" % status_code)


application = tornado.web.Application(
    headlers=[
        (r'/', MainHandler),
        (r"/send", SendHandler)
    ],
    template_path="test"
    # debug=True
)


if __name__ == "__main__":
    application.listen(8000)
    tornado.ioloop.IOLoop.instance().start()







