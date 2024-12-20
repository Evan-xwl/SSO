import React, {useEffect, useState} from 'react';
import { LockOutlined, UserOutlined, GithubOutlined } from '@ant-design/icons';
import {Button, Checkbox, Form, Input, Flex, Card, notification} from 'antd';
import {useNavigate} from "react-router-dom";
import type { NotificationArgsProps } from 'antd';
import {request} from "../../../utils/index";
import "./index.scss"
import "../../../index.scss"
type NotificationPlacement = NotificationArgsProps['placement'];
const Login: React.FC = () => {
  const [source, setSource] = useState<string | null>(null);
  useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        setSource(urlParams.get('source'));
      }
    ,[]);
  const navigate = useNavigate();
  const [errorDisp, setErrorDisp] = useState(true);
  const [errorText, setErrorText] = useState("");
  const onFinish = async (values: any) => {
    try {
      const res = await request.post(`/usercenter/login?source=${source}`, values);
      if (res.data && res.data.code === 1000) {
        //登录成功则根据source跳转到对应页面并附上用户信息
        const sourceURL = res.data.redirectUrl;
        if (sourceURL) {
          window.location.href = sourceURL;
        } else {
          //无重定向则跳转主页面
          navigate("/");
        }
      } else {
        setErrorText(res.data.msg);
        setErrorDisp(false);
      }
    } catch (error) {
      alert(error)
    }
  };
  const componentStyles = {
    header: {
      backgroundColor: 'lightblue',
      padding: '10px'
    },
    body: {
      fontSize: '16px',
      color: 'black'
    },
    extra: {}, // 如果某个部分暂时不需要设置样式，可以传一个空对象
    title: {
      fontWeight: 'bold',
      marginBottom: '5px'
    },
    actions: {
      display: 'flex',
      justifyContent: 'space-between'
    },
    cover: {
      width: '100%',
      height: '200px',
      objectFit: 'cover'
    }
  };

  const [api, contextHolder] = notification.useNotification();


  const openNotification = (placement: NotificationPlacement) => {
    console.log("点击忘记密码")
    api.info({
      message: '下次别忘',
      description:
          '抱歉，忘记密码我们也没有办法喔~',
      placement,
    });
  };



  return (
      <div className="login">
        {contextHolder}
        <Card className="login-container"
              title={
                <div style={{
                  textAlign: 'center',
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                  height: '100%',
                  fontStyle: 'italic',
                  color:'#CD5C5C',
                  fontSize: '20px',
                }}>若凌永远不困</div>
              }
              bodyStyle={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                minHeight: '300px' // 可以根据实际情况调整这个高度值，确保有足够空间让Form居中显示
              }}
        >
          <Form
              name="login"
              initialValues={{ remember: true }}
              style={{ maxWidth: 360 }}
              onFinish={onFinish}
          >
            <Form.Item
                name="userAccount"
                rules={[{ required: true, message: '账号不能为空!' }]}
                label={"账号"}
            >
              <Input prefix={<UserOutlined />} placeholder="请输入您的账号" />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[{ required: true, message: '密码不能为空!' }]}
                label={"密码"}
            >
              <Input prefix={<LockOutlined />} type="password" placeholder="请输入您的密码" />
            </Form.Item>
            <div hidden={errorDisp}  className="fail-msg">{errorText}</div>
            <Form.Item>
              <Flex justify="space-between" align="center">
                <Form.Item name="remember" noStyle>
                  <Checkbox disabled={true}>记住账号</Checkbox>
                </Form.Item>
                <a href={"#"} onClick={() => openNotification('top')}>忘记密码</a>
              </Flex>
            </Form.Item>

            <Form.Item>
              <Button block type="primary" htmlType="submit">
                登录
              </Button>
              <a href="/register" className="register">立即注册!</a>
            </Form.Item>
          </Form>
        </Card>
      </div>
  );
};

export default Login;