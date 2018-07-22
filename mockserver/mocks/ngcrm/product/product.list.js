/**
 * 产品列表s
 *
 * @url /products
 */

module.exports = {
  "code": function () { // 1/10 的概率返回错误码 1.
    return Math.random() < 0.1 ? 1 : 0;
  },
  "list|5-10": [
    {
      "id|+1":  "@integer(10000)",
      "name": "@ctitle",
      "catalog": "@word",
      "description":"@cparagraph"
    }
  ]
};