import { createBrowserRouter } from "react-router-dom";

import Main from "../pages/index";
import App from "../App";
import Register from "../pages/user/Register/index";
import RegisterRes from "../components/Result/index";
const router = createBrowserRouter([
    {
        path:"/",
        element: <App />
    },
    {
        path:"/main",
        element: <Main/>
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