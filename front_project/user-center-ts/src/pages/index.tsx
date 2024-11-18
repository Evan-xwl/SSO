import React from 'react';
import { Carousel } from 'antd';

const contentStyle: React.CSSProperties = {
    margin: 0,
    height: '360px',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
};

const Main: React.FC = () => {
    const onChange = (currentSlide: number) => {
        console.log(currentSlide);
    };

    return (
        <Carousel afterChange={onChange}>
            <div>
                <h3 style={contentStyle}>这</h3>
            </div>
            <div>
                <h3 style={contentStyle}>是</h3>
            </div>
            <div>
                <h3 style={contentStyle}>主</h3>
            </div>
            <div>
                <h3 style={contentStyle}>页</h3>
            </div>
        </Carousel>
    );
};

export default Main;