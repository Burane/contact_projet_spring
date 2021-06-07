import {deserialize} from 'cerialize';

class Auth {

    @deserialize username: string;
    @deserialize token: string;

};

export default Auth;