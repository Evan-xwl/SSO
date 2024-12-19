import Login from "./pages/user/Login/index";
import "./index.scss"
import Footer from "./components/Footer/index";
function LoginMain() {

  return (
    <div className="container">
        <div className="login-mask">
            <Login></Login>
        </div>
      <Footer></Footer>
    </div>
  )
}

export default LoginMain
