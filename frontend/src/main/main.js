import "../App.css";
import { useEffect, useState } from "react";
import * as axios from "axios";
import Header from "../header";
import Room from "./room";

function Main() {
  const [roomList, setRoomList] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/roomlist", {
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then((res) => {
        setRoomList(res.data);
      })
      .catch((e) => console.log(e));
  }, []);

  const refreshList = () => {
    axios
      .get("http://localhost:8080/roomlist", {
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then((res) => {
        setRoomList(res.data);
      })
      .catch((e) => console.log(e));
  };

  return (
    <div className="App">
      <Header></Header>
      <p> here</p>
      <button onClick={() => refreshList()}> refresh </button>
      <ul>
        {roomList &&
          roomList.map((_roomList, index) => (
            <li key={index}>
              <Room
                roomID={_roomList.roomID}
                roomName={_roomList.roomName}
                roomPassword={_roomList.roomPassword}
                playerNumber={_roomList.playerNumber}
                playerList={_roomList.playerList}
              />
            </li>
          ))}
      </ul>
    </div>
  );
}

export default Main;
