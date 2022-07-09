// import axios from "axios";
// import { useEffect, useState } from "react";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "../header";
import PlayerList from "./playerlist";
import * as StompJs from "@stomp/stompjs";

function GameRoom() {
  const client = useRef({});
  const { state } = useLocation();
  const navigate = useNavigate();
  const [roomID, setRoomID] = useState(0);
  const [roomName, setRoomName] = useState("");
  const [playerNumber, setPlayerNumber] = useState(0);
  const [playerList, setPlayerList] = useState({});

  // HTTP

  const getRoomInformation = (roomID) => {
    axios
      .get("http://localhost:8080/roomlist/" + roomID, {
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then((res) => initiallizeRoom(res.data));
  };

  const initiallizeRoom = (roomData) => {
    setRoomID(roomData.roomID);
    setRoomName(roomData.roomName);
    setPlayerNumber(roomData.playerNumber);
    setPlayerList(roomData.playerList);
  };

  const exitRoom = (roomID) => {
    axios
      .get("http://localhost:8080/roomlist/exit", {
        params: { roomID: roomID },
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then(navigate("/main"));
  };

  // Socket

  const connectSocket = () => {
    if (client.current.connect) {
      return;
    }
    client.current = new StompJs.Client({
      brokerURL: "ws://localhost:8080/stomp/chat", // 웹소켓 서버로 직접 접속
      // webSocketFactory: () => new SockJS("/ws-stomp"), // proxy를 통한 접속
      connectHeaders: {
        Authorization: sessionStorage.getItem("token"),
      },
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        // client.current.subscribe(
        //   `/sub/chat/roomlist`,
        //   ({ body }) => {
        //     console.log(body);
        //     setRoomList((_roomList) => [..._roomList, JSON.parse(body)]);
        //   },
        //   { id: "room" }
        // );
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });
    client.current.activate();
  };

  const disconnectSocket = () => {
    if (client.current.connected) client.current.deactivate();
  };

  useEffect(() => {
    getRoomInformation(state);
    connectSocket();

    return () => {
      disconnectSocket();
    };
  }, []);

  return (
    <div className="App">
      <Header></Header>
      <button onClick={() => exitRoom(state)}> Exit </button>
      <PlayerList></PlayerList>
    </div>
  );
}

export default GameRoom;
