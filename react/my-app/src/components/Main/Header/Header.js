import logo from "../logo.png";
import style from "./Header.module.css";

function Header(){
    return(
    <div className={style.header}>
        <div className="header__logo">
            <p className="header__logo__title">swapMe</p>
            <img src={logo} alt="logo" className="header__logo__img"/>
        </div>
        <div className="header__location">
            <input type="text" className={style.header__location__input} placeholder="What are you looking for?"/>
        </div>
        <div className="header__panel">
            <img src="" alt="heart"/>
            <img src="" alt="chat"/>
            <img src="" alt="sound"/>
            <img src="" alt="profile"/>
        </div>
    </div> )
}

export default Header;