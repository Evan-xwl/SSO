import './index.scss'

const NavBar = ({username}) => {
  return (
    <nav className="nav">
      <div className="menu">
        <div className="menu-item active">
          点菜<span className="menu-active-bar"></span>
        </div>
        <div className="menu-item">
          评价<span className="menu-comment">863</span>
        </div>
        <div className="menu-welcome">欢迎 {username} 用户</div>
      </div>

      <div className="menu-search">
        <div className="menu-form">
          <div className="menu-search-icon"></div>
          <div className="menu-search-text">请输入菜品名称</div>
        </div>
      </div>
    </nav>
  )
}

export default NavBar
