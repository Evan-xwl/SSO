import React, {useState} from 'react';
import { LockOutlined, UserOutlined, GithubOutlined } from '@ant-design/icons';
import { Button, Checkbox, Form, Input, Flex, Card } from 'antd';
import {useNavigate} from "react-router-dom";
import "../Login/index.scss"
import {request} from "../../../utils/index";
import Footer from "../../../components/Footer/index";
import Login from "../Login/index";

const Register: React.FC = () => {
  const navigate = useNavigate();
  const [errorDisp, setErrorDisp] = useState(true);
  const [errorText, setErrorText] = useState("");
  const onFinish = async (values: any) => {
    try {
      const res = await request.post('/usercenter/register', values);
      if (res.data && res.data.code === 1000) {
        navigate('/RegisterRes');
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

  return (
      <div className="container">
        <div className="login-mask">
          <div className="login">
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
                    }}>欢迎注册账号</div>
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
                    label="账号"
                >
                  <Input prefix={<UserOutlined />} placeholder="请输入您的账号" />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[{ required: true, message: '密码不能为空!' }]}
                    label="密码"
                >
                  <Input prefix={<LockOutlined />} type="password" placeholder="请输入您的密码" />
                </Form.Item>
                <Form.Item
                    name="checkPassword"
                    rules={[{ required: true, message: '密码不能为空!' }]}
                    label="确认密码"
                >
                  <Input prefix={<LockOutlined />} type="password" placeholder="请再输入您的密码" />
                </Form.Item>
                <div hidden={errorDisp}  className="fail-msg">{errorText}</div>
                <Form.Item>
                  <Button block type="primary" htmlType="submit">
                    注册账号
                  </Button>
                </Form.Item>
              </Form>
            </Card>
          </div>
        </div>
        <Footer></Footer>
      </div>

  );
};

export default Register;