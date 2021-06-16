import {deserialize} from 'cerialize';

class Auth {

    constructor(username: string, token : string ) {
        this.username = username;
        this.token = token;
        this.setAuthCookie();
    }

    setAuthCookie() {
        document.cookie = `token=${this.token}; path="/"`;
        document.cookie = `username=${this.username}; path="/"`;
    }

    getAuthCookie() {

    }

    private getCookie(c_name) {
        if (document.cookie.length > 0) {
            let c_start = document.cookie.indexOf(c_name + "=");
            if (c_start != -1) {
                c_start = c_start + c_name.length + 1;
                let c_end = document.cookie.indexOf(";", c_start);
                if (c_end == -1) {
                    c_end = document.cookie.length;
                }
                return unescape(document.cookie.substring(c_start, c_end));
            }
        }
        return "";
    }


    @deserialize username: string;
    @deserialize token: string;

};



export default Auth;