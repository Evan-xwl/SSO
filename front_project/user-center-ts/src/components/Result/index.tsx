import React from 'react';
import { Button, Result } from 'antd';
import {useNavigate} from "react-router-dom";

const RegisterRes: React.FC = () => {
    const navigate = useNavigate();
    return (
        <Result
            title="恭喜您，账号注册成功"
            extra={
                <Button type="primary" key="console" onClick={() => {navigate("/")}}>
                    去登录~
                </Button>
            }
        />
    );
}

export default RegisterRes;