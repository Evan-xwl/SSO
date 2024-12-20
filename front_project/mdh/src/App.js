import NavBar from './components/NavBar'
import Menu from './components/Menu'
import Cart from './components/Cart'
import FoodsCategory from './components/FoodsCategory'
import './App.scss'

import {useDispatch, useSelector} from "react-redux";
import {fetchFoodList} from "./store/modules/takeaway";
import {useEffect, useState} from "react";
import axios from "axios";

const App = () => {
  // action触发
  const dispatch = useDispatch();
  const [username, setUsername] = useState("");
  const {foodList, activeIndex} = useSelector(state => state.foods);
    const [loading, setLoading] = useState(true); // 加载状态
  useEffect(() => {
      let token = "";
      // 1.获取用户信息
      //1.1 从URL中获取用户名（登录跳转时携带）
      const urlParams = new URLSearchParams(window.location.search);
      async function fetchData() {
          if (localStorage.getItem("token") !== "null") {
              token = localStorage.getItem("token");
          }else {
              token = urlParams.get("token");
              console.log("urlParams" + token)
              if (token !== null && token !== "null") {
                  localStorage.setItem("token", token);
              }
          }
          const formData = new FormData();
          formData.append('token', token);
          try {
              const response = await axios.post(`http://localhost:8082/client/msg`,
                  formData
                        ,{
                      headers: {
                          'Content-Type': 'multipart/form-data'
                      }});
              if (response.data.username) {
                  setUsername(response.data.username);
                  setLoading(false);
              } else {
                  console.log(response.request.responseURL)
                  window.location.href = response.request.responseURL + "?source=mdh";
              }
          } catch (error) {
              console.error("There was an error fetching the userInfo!", error);
          }
      }
      fetchData();
      // 2.获取食品信息
      dispatch(fetchFoodList());
      // dispatch(fetchUserInfo());
  }, [dispatch]);

    if (loading) {
        return null;
    }
  return (
    <div className="home">
      {/* 导航 */}
      <NavBar
          username={username}
      />

      {/* 内容 */}
      <div className="content-wrap">
        <div className="content">
          <Menu />

          <div className="list-content">
            <div className="goods-list">
              {/* 外卖商品列表 */}
              {foodList.map((item, index) => {
                return (
                    activeIndex === index && <FoodsCategory
                    key={item.tag}
                    // 列表标题
                    name={item.name}
                    // 列表商品
                    foods={item.foods}
                  />
                )
              })}
            </div>
          </div>
        </div>
      </div>

      {/* 购物车 */}
      <Cart />
    </div>
  )
}

export default App
