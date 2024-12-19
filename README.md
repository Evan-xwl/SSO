# SSO
# 包含SSO系统、子系统1、子系统2



sso-server端实现登录状态保存的方式：

- redis（首选）-->客户端用session（减少sso-server请求次数？）
- session（但是要设置cookie的域名路径？--浏览器会将该cookie（sessionId）发给多个客户端）
- token
