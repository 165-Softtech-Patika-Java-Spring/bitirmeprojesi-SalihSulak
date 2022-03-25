import axios from "axios";

class AuthenticationService{

    login(username, password){

        const data = {
            username: username,
            password: password
        }

        const url = "api/v1/auth/login"

        return axios.post(url, data);
    }
    register(name, surname, username, password){

        const data = {
        name : name,
        surname : surname,
        username : username,
        password : password
        }

        const url = "/api/v1/auth/register";

        return axios.post(url, data);
    }

}

export default new AuthenticationService();