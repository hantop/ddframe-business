/**
 * 首页 - 友情链接
 *
 * @url /use-nodejs-exports
 *
 * 在这里你可以写详细的说明参数的信息
 */

 module.exports = {
    "code": function () { // 1/10 的概率返回错误码 1.
      return Math.random() < 0.1 ? 1 : 0;
    },
    "list|5-10": [
      {"title": "@title", "link": "@url"}
    ]
  };