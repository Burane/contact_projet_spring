import axios from 'axios';
import authHeader from './auth-header.js';

const API_URL = '/api/contact/';

class UserService {

    getContact() {
        return axios.post(API_URL + 'all', {}, { headers: authHeader() });
    }

}

export default new UserService();