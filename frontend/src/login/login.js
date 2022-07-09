import logo from "../logo.svg";
import "../App.css";
import { useState } from "react";
import * as axios from "axios";
import { useNavigate } from "react-router-dom";

function Login(props) {
  const [userID, setUserID] = useState("");
  const [userPassword, setUserPassword] = useState("");

  const navigate = useNavigate();

  const loginProcess = () => {
    axios
      .post("http://localhost:8080/auth/login", {
        userID: userID,
        userPassword: userPassword,
      })
      .then((res) => {
        console.log(res.data);
        sessionStorage.setItem("token", "Bearer " + res.data);
        console.log(sessionStorage.getItem("token"));
        navigate("/main");
      })
      .catch((e) => console.log(e));
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>아래에 아이디와 비밀번호를 입력해주세요.</p>
        <input
          placeholder="your ID Here"
          onChange={(e) => setUserID(e.target.value)}
        ></input>
        <input
          placeholder="your Password Here"
          onChange={(e) => setUserPassword(e.target.value)}
        ></input>
        <button onClick={() => loginProcess()}>Login!</button>
      </header>
    </div>
  );
}

export default Login;
