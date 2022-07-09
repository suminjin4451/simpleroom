import logo from "./logo.svg";
import "./App.css";
import { Component } from "react";

class Header extends Component {
  render() {
    return (
      <header className="Header, header">
        <link
          rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20,400,0,0"
        />

        <img
          src={logo}
          className="App-logo"
          alt="logo"
          style={{ width: "50px", height: "50px" }}
        />
        <p>Rael Super Cute</p>
        <div style={{ display: "flex" }}>
          <div
            style={{
              display: "flex",
              marginRight: "30px",
              flexDirection: "column",
            }}
          ></div>
        </div>
      </header>
    );
  }
}

export default Header;
