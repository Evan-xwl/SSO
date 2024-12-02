import Login from "./pages/user/Login/index";
import "./index.scss"
import Footer from "./components/Footer/index";
function App() {

  return (
    <div className="container">
        <div className="login-mask">
            <Login></Login>
        </div>
      <Footer></Footer>
    </div>
  )
}

export default App
