import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./login/login";
import Main from "./main/main";
import GameRoom from "./gameRoom/gameroom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/main" element={<Main />}></Route>
        <Route path="/room" element={<GameRoom />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
