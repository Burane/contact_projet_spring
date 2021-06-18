import {deserialize} from 'cerialize';

class Auth {

    @deserialize username: string;
    @deserialize token: string;

    constructor(username: string, token : string ) {
        this.username = username;
        this.token = token;
    }

};



export default Auth;