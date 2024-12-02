import React from 'react';
import {GithubOutlined} from "@ant-design/icons";
import "./index.scss"

const Footer = () => {
    return (
        <footer className="footer">
            <a href={"https://github.com/Evan-xwl/SSO"} className="link" target={"_blank"}>
                <div className="bottom"><span>不会困的小文洛 </span><GithubOutlined /></div>
            </a>
        </footer>
    );
}

export default Footer;