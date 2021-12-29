const serverID = document.getElementById('server1');
const formID = document.getElementById('form1');
let formAction = formID.getAttribute('action');
formAction = "addUserToServer/" + serverID;