import { createBrowserRouter } from "react-router-dom";

import Main from "../pages/index";
import LoginMain from "../LoginMain";
import Register from "../pages/user/Register/index";
import RegisterRes from "../components/Result/index";
const router = createBrowserRouter([
    {
        path:"/",
        element: <Main />
    },
    {
        path:"/login",
        element: <LoginMain />
    },
    {
        path:"/register",
        element:<Register/>
    },
    {
        path:"/registerRes",
        element:<RegisterRes/>
    }
])

export default router;