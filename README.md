# SSO
# 包含SSO系统、子系统1、子系统2



sso-server端实现登录状态保存的方式：

- redis（首选）-->客户端用session（减少sso-server请求次数？）
- session（但是要设置cookie的域名路径？--浏览器会将该cookie（sessionId）发给多个客户端）
- token



### 总结：

- React中的setStatexxx()是异步的。
- FormData对象会自动将类型转换为string（例如object的null-->"null"）。
- 后端的response.sendRedirect重定向方式会导致前端重新请求，原请求数据丢失-->后端直接返回重定向url。
- 传参时，后端可以用@RequestBody或者@RequestBody Map<String, String>接收。
- @RequestParam一般从URL或者Form表单中获取参数，无法从请求体中得到。
- 浏览器内容安全策略（CSP）错误：从URL中获取参数+动态URL重定向。


issue：
1、使用localstorge存储token无法实现跨域自动携带（引入中间件实现token转发？）
2、成功登录后以cookie形式返回token，但是再次访问相同接口无法自动携带cookie。
3、如何实现在已登录的情况下，访问登录页面时通过Cookie实现自动登录？（目前利用confirmLogin接口判断是否为有效Cookie，但是无法实现重定向--通过source判断源的相同逻辑从login中抽出来，以结果形式返回redriectUrl？）