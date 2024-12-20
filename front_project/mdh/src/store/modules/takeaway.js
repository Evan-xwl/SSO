import {createSlice} from "@reduxjs/toolkit";
import axios from "axios";
const foodStore = createSlice({
    name: 'foods',
    initialState: {
        foodList: [],
        activeIndex: 0,
        cartList: [],
        userInfo: ""
    },
    reducers: {
        setFoodList(state, action) {
            state.foodList = action.payload;
        },
        setActiveIndex(state, action) {
            state.activeIndex = action.payload;
        },
        addCartList(state, action) {
            // cartList中的对象是item
            const item = state.cartList.find(item => item.id === action.payload.id);
            if (item) {
                item.count++;
            } else {
                state.cartList.push(action.payload);
            }
        },
        increCount(state, action) {
            const item = state.cartList.find(item => item.id === action.payload.id);
            item.count++;
        },
        decreCount(state, action) {
            const item = state.cartList.find(item => item.id === action.payload.id);
            if (item.count === 1) {
                state.cartList = state.cartList.filter(ele => item.id !== ele.id);
                return;
            }
            item.count--;
        },
        clearCart(state) {
            state.cartList = [];
        },
        setUserInfo(state, action) {
            state.userInfo = action.payload;
        }
    }
});

const {setUserInfo, setFoodList, setActiveIndex, addCartList, increCount, decreCount, clearCart} = foodStore.actions
// 异步获取数据
const fetchFoodList = () => {
    return async (dispatch) => {
        const res = await axios.get('http://localhost:3004/takeaway');
        dispatch(setFoodList(res.data));
    }
}

const fetchUserInfo = () => {
    return async (dispatch) => {
        const res = await axios.get('http://localhost:8081/client/msg?username=zss');
        if (res.data.username) {
            dispatch(setUserInfo(res.data.username));
        }
    }
}

export {fetchFoodList, setActiveIndex, addCartList, increCount, decreCount, clearCart, fetchUserInfo}
const reducer = foodStore.reducer
export default reducer