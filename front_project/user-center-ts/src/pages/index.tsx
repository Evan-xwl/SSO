import React from 'react';
import "./index.scss"


const Main: React.FC = () => {
    const goMeiDiHen = () => {
        window.location.href = 'http://localhost:9981';
    };

    const goEdekuai = () => {
        window.location.href = 'http://localhost:9981';
    };

    return (
            <div className="main">
                <div className="header-main"/>
                <div className="content-main">
                    <div className="beauty" onClick={goMeiDiHen}>美滴很</div>
                    <img src={"/main-direction.png"} alt={"左箭头"} className="img-left"/>
                    <img src={"/SpongeBob.png"} alt={"主图"} className="img-main"/>
                    <img src={"/main-direction.png"} alt={"右箭头"} className="img-right"/>
                    <div className="hungry" onClick={goEdekuai}>饿滴快</div>
                </div>
            </div>
    );
};

export default Main;