import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Room(props) {
  const [userList, setUserList] = useState([]);
  const navigate = useNavigate();

  const buildUserList = (players) => {
    setUserList([]);

    for (var player in players) {
      // player[0] = userID, player[1] = job
      let body = {
        name: players[player][0],
        job: players[player][1],
      };
      console.log(body);
      setUserList((_userList) => [..._userList, body]);
    }
  };

  const roomJoin = (job, roomID) => {
    console.log("clicked");
    // for (var player in props.playerList) {
    //   if (job === props.playerList[player][1]) {
    //     console.log("duplicate");
    //     return;
    //   }
    // }

    var param = { roomID: roomID, job: job };
    axios
      .get("http://localhost:8080/roomlist/join", {
        params: param,
        headers: { Authorization: sessionStorage.getItem("token") },
      })
      .then((res) => {
        navigate("/room", { state: roomID });
      });
  };

  useEffect(() => {
    buildUserList(props.playerList);
  }, [props.playerList]);

  return (
    <button onClick={() => roomJoin("warrior", props.roomID)}>
      name : {props.roomName} / password : {props.roomPassword} / playerNumber :
      {props.playerNumber}
      <ul>
        {userList &&
          userList.map((_userList, index) => (
            <li key={index}>
              name:{_userList.name} / job:{_userList.job}
            </li>
          ))}
      </ul>
    </button>
  );
}

export default Room;
